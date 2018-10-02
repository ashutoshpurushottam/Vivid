package com.eigendaksh.vivid.screens.weatherdetail;

import com.eigendaksh.vivid.model.WeatherItem;
import com.eigendaksh.vivid.model.WeatherItemDetail;

import java.util.List;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

public interface WeatherDetailView {
    void showLoading();
    void hideLoading();
    void showWeatherDetail(WeatherItemDetail detail);
}
