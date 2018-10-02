package com.eigendaksh.vivid.preferences;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

public interface UserPreferenceStore {
    void setDefaultLatitude(double latitude);
    double getDefaultLatitude();

    void setDefaultLongitude(double longitude);
    double getDefaultLongitude();

}
