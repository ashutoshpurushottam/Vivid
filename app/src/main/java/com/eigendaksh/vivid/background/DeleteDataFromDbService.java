package com.eigendaksh.vivid.background;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.eigendaksh.vivid.application.VividApplication;
import com.eigendaksh.vivid.data.db.WeatherEntry;
import com.eigendaksh.vivid.utils.utilities.Utility;

import java.util.Date;
import java.util.List;

import timber.log.Timber;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development

 */

public class DeleteDataFromDbService extends IntentService {

    public DeleteDataFromDbService() {
        super(DeleteDataFromDbService.class.getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Timber.d("DeleteDataFromDbService is called");
        long currentTime = new Date().getTime();
        long currentTimeDayStart = Utility.getStartOfDayMillis(currentTime) / 1000L;

//        List<WeatherEntry> allOldWeatherEntries =
//                VividApplication.getDatabase(this).getWeatherEntryDao().
//                        getAllOldWeatherEntries(currentTimeDayStart);
//
//        WeatherEntry[] weatherEntries = allOldWeatherEntries.toArray(new WeatherEntry[allOldWeatherEntries.size()]);
//
//        VividApplication.getDatabase(this).getWeatherEntryDao().delete(weatherEntries);

    }
}
