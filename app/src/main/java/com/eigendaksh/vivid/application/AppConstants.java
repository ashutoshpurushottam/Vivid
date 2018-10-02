package com.eigendaksh.vivid.application;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

public class AppConstants {
    public final static int PLAY_SERVICES_REQUEST = 1000;
    public final static int REQUEST_CHECK_SETTINGS = 2000;
    public final static int ACCESS_LOCATION_REQUEST = 89;

    public static final double THRESHOLD_CHANGE = 0.3;
    public static final long LOCATION_UPDATE_INTERVAL = 1000 * 60 * 30;
    public static final long FAST_LOCATION_UPDATE_INTERVAL = 1000 * 60 * 10;
    public static final float DISTANCE_THRESHOLD = 50;

    // Intent Constants
    public static final String WEATHER_ID = "WEATHER_ID";
    public static final String NOTE_ID = "NOTE_ID";

    // Service
    public static final int CLEAN_DB_JOB_ID = 189;

    // Constant
    public static final long CLEAN_DB_PERIOD = 60 * 1000 * 60 * 24 * 5; // every 5 days




}
