package com.eigendaksh.vivid.screens.weather;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

public interface WeatherItemRowView {

    void setDateString(String dateString);
    void setCityName(String cityName);
    void setMaxTemp(String maxTempString);
    void setMinTemp(String minTempString);
    void setIcon(int icon);
    void setDescription(String description);
    void setClickListener(long id);

}
