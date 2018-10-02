package com.eigendaksh.vivid.screens.weather;

import android.Manifest;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.eigendaksh.vivid.R;
import com.eigendaksh.vivid.application.AppConstants;
import com.eigendaksh.vivid.application.VividApplication;
import com.eigendaksh.vivid.background.CleanDbService;
import com.eigendaksh.vivid.preferences.UserPreferenceStore;
import com.eigendaksh.vivid.screens.notes.NotesActivity;
import com.eigendaksh.vivid.utils.utilities.ActivityUtils;
import com.eigendaksh.vivid.utils.utilities.UIUtilities;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import javax.inject.Inject;

import timber.log.Timber;


public class WeatherActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    @Inject
    UserPreferenceStore _mPreferenceStore;

    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private double mLatitude;
    private double mLongitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VividApplication.getComponent(this).inject(this);
        setContentView(R.layout.activity_main);

        // Set up the toolbar.
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.weather);
            actionBar.setSubtitle(getString(R.string.tutorial_app));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        boolean checkPlayServices = checkPlayServices();
        if (checkPlayServices) {
            // Check permission to read location
            checkPermissions();
        }

        WeatherFragment weatherFragment = (WeatherFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (weatherFragment == null) {
            weatherFragment = WeatherFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    weatherFragment, R.id.contentFrame);
        }

        startCleanDbService();
    }


    @Override
    protected void onStart() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
        super.onStart();
    }

    @Override
    protected void onStop() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    /**
     * Check availability of Google Play Services on the device
     *
     * @return true if play services is enable else false
     */
    private boolean checkPlayServices() {

        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();

        int resultCode = googleApiAvailability.isGooglePlayServicesAvailable(this);

        if (resultCode != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(resultCode)) {
                googleApiAvailability.getErrorDialog(this, resultCode,
                        AppConstants.PLAY_SERVICES_REQUEST).show();
            } else {
                Timber.d("GooglePlayServices not available");
            }
            return false;
        }
        return true;
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, AppConstants.ACCESS_LOCATION_REQUEST);
        } else {
            buildGoogleApiClient();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == AppConstants.ACCESS_LOCATION_REQUEST) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
            } else {
                permissionDenied();
            }
        }
    }

    /**
     * Build client to read location
     */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();

        mGoogleApiClient.connect();

        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(AppConstants.LOCATION_UPDATE_INTERVAL);
        locationRequest.setFastestInterval(AppConstants.FAST_LOCATION_UPDATE_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());

        result.setResultCallback(new ResultCallbacks<LocationSettingsResult>() {
            @Override
            public void onSuccess(@NonNull LocationSettingsResult locationSettingsResult) {
                final Status status = locationSettingsResult.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can initialize location requests here
                        getLocation();
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(WeatherActivity.this, AppConstants.REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            Timber.e(e.getMessage());
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        break;
                }
            }

            @Override
            public void onFailure(@NonNull Status status) {
            }
        });

    }


    /**
     * Get location and call for weather info
     */
    private void getLocation() {
        try {
            mLastLocation = LocationServices.FusedLocationApi
                    .getLastLocation(mGoogleApiClient);
            if (mLastLocation != null) {
                mLongitude = mLastLocation.getLongitude();
                mLatitude = mLastLocation.getLatitude();
                Timber.d("Latitude: " + mLatitude);
                Timber.d("Longitude: " + mLongitude);
                _mPreferenceStore.setDefaultLatitude(mLatitude);
                _mPreferenceStore.setDefaultLongitude(mLongitude);

                WeatherFragment weatherFragment = (WeatherFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.contentFrame);

                if (weatherFragment != null) {
                    weatherFragment.start();
                }


            }
        } catch (SecurityException e) {
            Timber.e(e.getMessage());
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;
        switch (id) {
            case R.id.nav_task:
                intent = new Intent(this, NotesActivity.class);
                startActivity(intent);
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        getLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();

    }

    private void permissionDenied() {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            UIUtilities.showDialog(this,
                    getString(R.string.perm_denied),
                    getString(R.string.perm_denied_dialog),
                    getString(R.string.okay),
                    getString(R.string.cancel),
                    R.drawable.ic_info,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            final Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.addCategory(Intent.CATEGORY_DEFAULT);
                            intent.setData(Uri.parse("package:" + getApplicationContext().getPackageName()));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                            startActivity(intent);
                            finish();
                        }
                    },
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }
            );
        } else {
            UIUtilities.showSingleDialog(this,
                    getString(R.string.perm_denied),
                    getString(R.string.perm_denied_dialog),
                    getString(R.string.okay),
                    R.drawable.ic_info,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            final Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.addCategory(Intent.CATEGORY_DEFAULT);
                            intent.setData(Uri.parse("package:" + getApplicationContext().getPackageName()));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                            startActivity(intent);
                            finish();
                        }
                    });
        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Timber.e(connectionResult.getErrorMessage());
    }


    private void startCleanDbService() {
        ComponentName componentName = new ComponentName(this, CleanDbService.class);
        // Set periodic does not work well with the versions below Nougat
        // So set minimum latency for such devices
        JobInfo jobInfo;
        jobInfo = new JobInfo.Builder(AppConstants.CLEAN_DB_JOB_ID, componentName)
                .setPersisted(true)
                .setPeriodic(AppConstants.CLEAN_DB_PERIOD)
                .build();

        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = jobScheduler.schedule(jobInfo);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Timber.i("CleanDB Job scheduled!");
        } else {
            Timber.i("CleanDB Job not scheduled");
        }
    }


}
