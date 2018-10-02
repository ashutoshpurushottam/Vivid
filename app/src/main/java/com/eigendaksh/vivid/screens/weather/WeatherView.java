package com.eigendaksh.vivid.screens.weather;

import com.eigendaksh.vivid.model.WeatherItem;

import java.util.List;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

public interface WeatherView {

    void start();
    void showLoading();
    void hideLoading();
    void loadWeather(List<WeatherItem> weatherItemList);
    void showError();
}
