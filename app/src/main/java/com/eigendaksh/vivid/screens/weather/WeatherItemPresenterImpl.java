package com.eigendaksh.vivid.screens.weather;

import com.eigendaksh.vivid.model.WeatherItem;

import java.util.List;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

public class WeatherItemPresenterImpl implements WeatherItemPresenter {

    private final List<WeatherItem> weatherItems;

    public WeatherItemPresenterImpl(List<WeatherItem> weatherItems) {
        this.weatherItems = weatherItems;
    }

    @Override
    public void onBindWeatherRowViewAtPosition(WeatherItemRowView rowView, int position) {
        WeatherItem weatherItem = weatherItems.get(position);
        rowView.setDateString(weatherItem.getDateString());
        rowView.setCityName(weatherItem.getCityName());
        rowView.setIcon(weatherItem.getDrawable());
        rowView.setMaxTemp(weatherItem.getMaxTemp());
        rowView.setMinTemp(weatherItem.getMinTemp());
        rowView.setDescription(weatherItem.getShortDescription());
        rowView.setClickListener(weatherItem.getId());
    }

    @Override
    public int getRowCount() {
        return weatherItems.size();
    }

    @Override
    public void renewItems(List<WeatherItem> newItems) {
        weatherItems.clear();
        weatherItems.addAll(newItems);
    }
}
