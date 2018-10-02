package com.eigendaksh.vivid.data.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

@Entity(tableName = "weather_table",
        indices = {@Index(value = {"date", "location_id"}, unique = true)})

public class WeatherEntry implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo(name = "location_id")
    private long locationId;
    @ColumnInfo(name = "date")
    private long date;
    @ColumnInfo(name = "short_desc")
    private String shortDescription;
    @ColumnInfo(name = "weather_id")
    private int weatherId;
    @ColumnInfo(name = "min_temp")
    private double minTemp;
    @ColumnInfo(name = "max_temp")
    private double maxTemp;
    private double humidity;
    private double pressure;
    @ColumnInfo(name = "wind_speed")
    private double windSpeed;
    private double degrees;
    @ColumnInfo(name = "coord_lat")
    private double coordLat;
    @ColumnInfo(name = "coord_long")
    private double coordLong;
    @ColumnInfo(name = "city_name")
    private String cityName;


    public WeatherEntry() {
    }

    protected WeatherEntry(Parcel in) {
        id = in.readLong();
        locationId = in.readLong();
        date = in.readLong();
        shortDescription = in.readString();
        weatherId = in.readInt();
        minTemp = in.readDouble();
        maxTemp = in.readDouble();
        humidity = in.readDouble();
        pressure = in.readDouble();
        windSpeed = in.readDouble();
        degrees = in.readDouble();
        coordLat = in.readDouble();
        coordLong = in.readDouble();
        cityName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(locationId);
        dest.writeLong(date);
        dest.writeString(shortDescription);
        dest.writeInt(weatherId);
        dest.writeDouble(minTemp);
        dest.writeDouble(maxTemp);
        dest.writeDouble(humidity);
        dest.writeDouble(pressure);
        dest.writeDouble(windSpeed);
        dest.writeDouble(degrees);
        dest.writeDouble(coordLat);
        dest.writeDouble(coordLong);
        dest.writeString(cityName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WeatherEntry> CREATOR = new Creator<WeatherEntry>() {
        @Override
        public WeatherEntry createFromParcel(Parcel in) {
            return new WeatherEntry(in);
        }

        @Override
        public WeatherEntry[] newArray(int size) {
            return new WeatherEntry[size];
        }
    };

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

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getDegrees() {
        return degrees;
    }

    public void setDegrees(double degrees) {
        this.degrees = degrees;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "WeatherEntry{" +
                "id=" + id +
                ", locationId=" + locationId +
                ", date=" + date +
                ", shortDescription='" + shortDescription + '\'' +
                ", weatherId=" + weatherId +
                ", minTemp=" + minTemp +
                ", maxTemp=" + maxTemp +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", windSpeed=" + windSpeed +
                ", degrees=" + degrees +
                ", coordLat=" + coordLat +
                ", coordLong=" + coordLong +
                '}';
    }
}