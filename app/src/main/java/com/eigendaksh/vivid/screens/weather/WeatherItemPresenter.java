package com.eigendaksh.vivid.screens.weather;

import com.eigendaksh.vivid.model.WeatherItem;

import java.util.List;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

public interface WeatherItemPresenter {

    void onBindWeatherRowViewAtPosition(WeatherItemRowView rowView, int position);
    int getRowCount();
    void renewItems(List<WeatherItem> newItems);

}
