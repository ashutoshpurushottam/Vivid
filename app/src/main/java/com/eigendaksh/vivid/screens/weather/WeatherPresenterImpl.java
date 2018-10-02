package com.eigendaksh.vivid.screens.weather;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;

import com.eigendaksh.vivid.application.AppConstants;
import com.eigendaksh.vivid.application.VividApplication;
import com.eigendaksh.vivid.data.dao.LocationEntryDao;
import com.eigendaksh.vivid.data.dao.WeatherEntryDao;
import com.eigendaksh.vivid.data.db.LocationEntry;
import com.eigendaksh.vivid.data.db.WeatherEntry;
import com.eigendaksh.vivid.model.WeatherItem;
import com.eigendaksh.vivid.network.OpenWeatherService;
import com.eigendaksh.vivid.openweathermap.WeatherData;
import com.eigendaksh.vivid.openweathermap.WeatherResponse;
import com.eigendaksh.vivid.preferences.UserPreferenceStore;
import com.eigendaksh.vivid.utils.utilities.Utility;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

public class WeatherPresenterImpl implements WeatherPresenter {

    @Inject UserPreferenceStore _mPreferenceStore;
    @Inject OpenWeatherService _mWeatherService;

    private Context mContext;

    private WeatherView weatherView;

    public WeatherPresenterImpl(Context context) {
        this.mContext = context;
        VividApplication.getComponent(context).inject(this);
    }


    @Override
    public void setView(WeatherView weatherView) {
        this.weatherView = weatherView;

    }

    @Override
    public void updateWeather() {
        sendDataToView();
    }

    private void fetchDataFromApi() {

        _mWeatherService.fetchWeatherForecasts(_mPreferenceStore.getDefaultLongitude(),
                _mPreferenceStore.getDefaultLatitude())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(new Consumer<WeatherResponse>() {
                    @Override
                    public void accept(WeatherResponse weatherResponse) throws Exception {
                        insertDataIntoDbFromResponse(mContext, weatherResponse);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.e(throwable.getMessage());
                    }
                });
    }




    private void insertDataIntoDbFromResponse(Context context, WeatherResponse response) {
        String cityName = response.getCity().getName();
        Double latitude = response.getCity().getCoord().getLat();
        Double longitude = response.getCity().getCoord().getLon();
        String locationSetting = cityName + "-" + latitude.intValue() + longitude.intValue();

        // See if location already exists in DB
        LocationEntryDao locationEntryDao =
                VividApplication.getDatabase(context).getLocationEntryDao();

        try {
            LocationEntry locationEntry = new LocationEntry();
            locationEntry.setLocationSetting(locationSetting);
            locationEntry.setCityName(cityName);
            locationEntry.setCoordLat(latitude);
            locationEntry.setCoordLong(longitude);
            locationEntryDao.insertAll(locationEntry);
        } catch (SQLiteConstraintException e) {
            // this can be ignored as it may occur because location already exists
            Timber.w(e.getLocalizedMessage());
        } finally {
            // Location already exists in DB only insert weather data
            LocationEntry selectedLocation = locationEntryDao.getLocationByLocationSetting(locationSetting);

            long selectedLocationId = selectedLocation.id;
            // Get weather data from response
            List<WeatherData> weatherDataList = response.getList();
            WeatherEntry[] weatherEntries = new WeatherEntry[weatherDataList.size()];
            // keep track of index number to update array
            int i = 0;
            for (WeatherData weatherData : weatherDataList) {
                String shortDescription = weatherData.getWeather().get(0).getDescription();
                Integer weatherId = weatherData.getWeather().get(0).getId();
                WeatherEntry weatherEntry = new WeatherEntry();
                weatherEntry.setLocationId(selectedLocationId);
                weatherEntry.setDate(weatherData.getDt());
                weatherEntry.setShortDescription(shortDescription);
                weatherEntry.setWeatherId(weatherId);
                weatherEntry.setMinTemp(weatherData.getTemp().getMin());
                weatherEntry.setMaxTemp(weatherData.getTemp().getMax());
                weatherEntry.setHumidity(weatherData.getHumidity());
                weatherEntry.setPressure(weatherData.getPressure());
                weatherEntry.setWindSpeed(weatherData.getSpeed());
                weatherEntry.setDegrees(weatherData.getDeg());
                weatherEntry.setCoordLat(latitude);
                weatherEntry.setCoordLong(longitude);
                weatherEntry.setCityName(selectedLocation.getCityName());
                weatherEntries[i] = weatherEntry;
                i++;
            }

            WeatherEntryDao weatherEntryDao =
                    VividApplication.getDatabase(context).getWeatherEntryDao();

            try {
                // Insert weather entry to the db of application
                weatherEntryDao.insertAll(weatherEntries);
            } catch (Exception ignore) {
            } finally {
                sendDataToView();
            }
        }

    }

    private void sendDataToView() {
        WeatherEntryDao weatherEntryDao = VividApplication.getDatabase(mContext).getWeatherEntryDao();
        final double currentLatitude = _mPreferenceStore.getDefaultLatitude();
        final double currentLongitude = _mPreferenceStore.getDefaultLongitude();

        // Get start of day for the current day
        // Get current time and the start of the day corresponding to the current time
        long currentTime = new Date().getTime();
        long currentTimeDayStart = Utility.getStartOfDayMillis(currentTime) / 1000L; // get time in seconds

        Flowable<List<WeatherEntry>> flowable = weatherEntryDao.getAllForLatAndLongAndToday(
                currentLatitude - AppConstants.DISTANCE_THRESHOLD,
                currentLatitude + AppConstants.DISTANCE_THRESHOLD,
                currentLongitude - AppConstants.DISTANCE_THRESHOLD,
                currentLongitude + AppConstants.DISTANCE_THRESHOLD,
                currentTimeDayStart
        );

        final List<WeatherItem> weatherItemList = new ArrayList<>();

        flowable.subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<List<WeatherEntry>>() {
                    @Override
                    public void accept(List<WeatherEntry> weatherEntries) throws Exception {
                        if(weatherEntries.size() < 10 || (currentLatitude == 0 && currentLongitude ==0)) {
                            fetchDataFromApi();
                        } else {
                            for (int i = 0; i < weatherEntries.size(); i++) {
                                WeatherEntry currentEntry = weatherEntries.get(i);
                                WeatherItem item = new WeatherItem();
                                item.setId(currentEntry.id);
                                item.setDateString(Utility.getFriendlyDayString(mContext, currentEntry.getDate() * 1000L));
                                item.setCityName(currentEntry.getCityName());
                                item.setDrawable(Utility.getIconResourceForWeatherCondition(currentEntry.getWeatherId()));
                                item.setMaxTemp(Utility.formatTemperature(mContext, currentEntry.getMaxTemp()));
                                item.setMinTemp(Utility.formatTemperature(mContext, currentEntry.getMinTemp()));
                                item.setShortDescription(currentEntry.getShortDescription());

                                weatherItemList.add(item);
                            }

                            weatherView.loadWeather(weatherItemList);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.w(throwable.getMessage());
                        weatherView.showError();
                    }
                });


    }
}

