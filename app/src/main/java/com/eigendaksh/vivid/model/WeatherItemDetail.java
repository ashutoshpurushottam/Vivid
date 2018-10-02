package com.eigendaksh.vivid.model;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

public class WeatherItemDetail {

    private long id;
    private String dateString;
    private String cityName;
    private String minTemp;
    private String maxTemp;
    private int drawable;
    private String shortDescription;
    private String windString;
    private String humidityString;
    private String pressureString;

    public WeatherItemDetail() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getWindString() {
        return windString;
    }

    public void setWindString(String windString) {
        this.windString = windString;
    }

    public String getHumidityString() {
        return humidityString;
    }

    public void setHumidityString(String humidityString) {
        this.humidityString = humidityString;
    }

    public String getPressureString() {
        return pressureString;
    }

    public void setPressureString(String pressureString) {
        this.pressureString = pressureString;
    }
}
