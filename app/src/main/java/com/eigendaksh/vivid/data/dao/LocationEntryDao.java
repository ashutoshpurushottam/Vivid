package com.eigendaksh.vivid.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.eigendaksh.vivid.data.db.LocationEntry;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

@Dao
public interface LocationEntryDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertAll(LocationEntry... entries);

    @Query("SELECT * FROM location_table WHERE location_setting = :locationSetting")
    LocationEntry getLocationByLocationSetting(String locationSetting);

    @Query("SELECT * FROM location_table")
    Flowable<List<LocationEntry>> getAllLocations();

    @Query("SELECT * FROM location_table WHERE id = :locationId")
    Flowable<LocationEntry> getLocationFromId(long locationId);
}
