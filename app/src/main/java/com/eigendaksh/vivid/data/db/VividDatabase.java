package com.eigendaksh.vivid.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.eigendaksh.vivid.data.dao.LocationEntryDao;
import com.eigendaksh.vivid.data.dao.NotesItemDao;
import com.eigendaksh.vivid.data.dao.WeatherEntryDao;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */


@Database(entities = {WeatherEntry.class, LocationEntry.class, NotesItem.class}, version = 2)
public abstract class VividDatabase extends RoomDatabase {
    public abstract LocationEntryDao getLocationEntryDao();
    public abstract WeatherEntryDao getWeatherEntryDao();
    public abstract NotesItemDao getNotesItemDao();
}
