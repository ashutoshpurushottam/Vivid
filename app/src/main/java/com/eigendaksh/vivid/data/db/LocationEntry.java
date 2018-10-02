package com.eigendaksh.vivid.data.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

@Entity(tableName = "location_table", indices = {@Index(value = {"location_setting"}, unique = true)})
public class LocationEntry {

    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo(name = "location_setting")
    private String locationSetting;
    @ColumnInfo(name = "city_name")
    private String cityName;
    @ColumnInfo(name = "coord_lat")
    private double coordLat;
    @ColumnInfo(name = "coord_long")
    private double coordLong;

    public LocationEntry() {
    }

    public String getLocationSetting() {
        return locationSetting;
    }

    public void setLocationSetting(String locationSetting) {
        this.locationSetting = locationSetting;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getCoordLat() {
        return coordLat;
    }

    public void setCoordLat(double coordLat) {
        this.coordLat = coordLat;
    }

    public double getCoordLong() {
        return coordLong;
    }

    public void setCoordLong(double coordLong) {
        this.coordLong = coordLong;
    }

    @Override
    public String toString() {
        return "LocationEntry{" +
                "id=" + id +
                ", locationSetting='" + locationSetting + '\'' +
                ", cityName='" + cityName + '\'' +
                ", coordLat=" + coordLat +
                ", coordLong=" + coordLong +
                '}';
    }
}
