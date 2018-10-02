package com.eigendaksh.vivid.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.StringRes;

import com.eigendaksh.vivid.R;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

public class SharedPreferenceStore implements UserPreferenceStore {

    private Context mContext;
    private SharedPreferences mPrefs;

    public SharedPreferenceStore(Context context) {
        mContext = context;
        mPrefs = PreferenceManager.getDefaultSharedPreferences(mContext);
    }


    private boolean contains(@StringRes int keyRes) {
        return mPrefs.contains(mContext.getString(keyRes));
    }

    private boolean getBoolean(@StringRes int keyRes, boolean defaultValue) {
        return mPrefs.getBoolean(mContext.getString(keyRes), defaultValue);
    }

    private int getInt(@StringRes int keyRes, int defaultValue) {
        return mPrefs.getInt(mContext.getString(keyRes), defaultValue);
    }

    private long getLong(@StringRes int keyRes, long defaultValue) {
        return mPrefs.getLong(mContext.getString(keyRes), defaultValue);
    }

    private float getFloat(@StringRes int keyRes, float defaultValue) {
        return mPrefs.getFloat(mContext.getString(keyRes), defaultValue);
    }

    private String getString(@StringRes int keyRes, String defaultValue) {
        return mPrefs.getString(mContext.getString(keyRes), defaultValue);
    }

    private void putBoolean(@StringRes int keyRes, boolean value) {
        mPrefs.edit()
                .putBoolean(mContext.getString(keyRes), value)
                .apply();
    }

    private void putInt(@StringRes int keyRes, int value) {
        mPrefs.edit()
                .putInt(mContext.getString(keyRes), value)
                .apply();
    }

    private void putFloat(@StringRes int keyRes, float value) {
        mPrefs.edit()
                .putFloat(mContext.getString(keyRes), value)
                .apply();
    }

    private void putLong(@StringRes int keyRes, long value) {
        mPrefs.edit()
                .putLong(mContext.getString(keyRes), value)
                .apply();
    }

    private void putString(@StringRes int keyRes, String value) {
        mPrefs.edit()
                .putString(mContext.getString(keyRes), value)
                .apply();
    }

    @Override
    public void setDefaultLatitude(double latitude) {
        putFloat(R.string.pref_key_latitude, (float) latitude);
    }

    @Override
    public double getDefaultLatitude() {
        return getFloat(R.string.pref_key_latitude, 0);
    }

    @Override
    public void setDefaultLongitude(double longitude) {
        putFloat(R.string.pref_key_longitude, (float) longitude);
    }

    @Override
    public double getDefaultLongitude() {
        return getFloat(R.string.pref_key_longitude, 0);
    }

}
