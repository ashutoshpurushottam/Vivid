package com.eigendaksh.vivid.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.eigendaksh.vivid.data.db.LocationEntry;
import com.eigendaksh.vivid.data.db.WeatherEntry;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

@Dao
public interface WeatherEntryDao {

    @Query("SELECT * FROM weather_table")
    Flowable<List<WeatherEntry>> getAll();

    @Query("SELECT * FROM weather_table WHERE id = :weatherId")
    WeatherEntry getEntryFromId(long weatherId);


    @Query("SELECT * FROM weather_table WHERE coord_lat >= :minLat AND coord_lat <= :maxLat " +
            "AND coord_long >= :minLong AND coord_long <= :maxLong ORDER BY date ASC")
    Flowable<List<WeatherEntry>> getAllForLatAndLong(double minLat, double maxLat, double minLong, double maxLong);

    @Query("SELECT * FROM weather_table WHERE coord_lat >= :minLat AND coord_lat <= :maxLat " +
            "AND coord_long >= :minLong AND coord_long <= :maxLong AND date >= :currentTime ORDER BY date ASC")
    Flowable<List<WeatherEntry>> getAllForLatAndLongAndToday(double minLat,
                                                             double maxLat,
                                                             double minLong,
                                                             double maxLong,
                                                             long currentTime);

    @Query("SELECT * FROM weather_table WHERE date < :inputTime")
    List<WeatherEntry> getAllOldWeatherEntries(long inputTime);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(WeatherEntry... entries);


    @Delete
    void delete(WeatherEntry... entries);

}
