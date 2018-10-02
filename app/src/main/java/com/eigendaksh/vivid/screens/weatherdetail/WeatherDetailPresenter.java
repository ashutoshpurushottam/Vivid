package com.eigendaksh.vivid.screens.weatherdetail;


/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

public interface WeatherDetailPresenter {
    void setView(WeatherDetailView detailView);
    void loadData(long id);
}
