package com.eigendaksh.vivid.screens.weatherdetail;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.eigendaksh.vivid.R;
import com.eigendaksh.vivid.utils.utilities.ActivityUtils;

public class WeatherDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.weather_detail);
            actionBar.setSubtitle(getString(R.string.tutorial_app));
        }

        WeatherDetailFragment detailFragment = (WeatherDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if(detailFragment == null) {
            detailFragment = WeatherDetailFragment.getInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    detailFragment, R.id.contentFrame);
        }


    }
}
