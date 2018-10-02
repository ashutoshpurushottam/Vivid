package com.eigendaksh.vivid.background;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;

import timber.log.Timber;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

public class CleanDbService extends JobService {

    boolean isWorking = false;
    boolean jobCancelled = false;

    @Override
    public boolean onStartJob(JobParameters params) {
        isWorking = true;
        Timber.d("onStartJob called");
        // Start Service for inserting data into database
        Intent intent = new Intent(this, DeleteDataFromDbService.class);
        startService(intent);
        return isWorking;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        jobCancelled = true;
        boolean needsReschedule = isWorking;
        jobFinished(params, needsReschedule);
        return needsReschedule;
    }

}
