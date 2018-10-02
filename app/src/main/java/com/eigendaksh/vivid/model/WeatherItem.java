package com.eigendaksh.vivid.model;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

public class WeatherItem {

    private long id;
    private String dateString;
    private String cityName;
    private String minTemp;
    private String maxTemp;
    private int drawable;
    private String shortDescription;

    public WeatherItem() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public long getId() {
        return id;
    }

    public String getDateString() {
        return dateString;
    }

    public String getCityName() {
        return cityName;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public int getDrawable() {
        return drawable;
    }

    public String getShortDescription() {
        return shortDescription;
    }
}
