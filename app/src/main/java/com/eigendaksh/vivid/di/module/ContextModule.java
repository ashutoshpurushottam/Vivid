package com.eigendaksh.vivid.di.module;

import android.content.Context;

import com.eigendaksh.vivid.network.OpenWeatherAPI;
import com.eigendaksh.vivid.network.OpenWeatherService;
import com.eigendaksh.vivid.preferences.SharedPreferenceStore;
import com.eigendaksh.vivid.preferences.UserPreferenceStore;
import com.eigendaksh.vivid.screens.weather.WeatherView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

@Module
public class ContextModule {
    private final Context mContext;

    public ContextModule(Context context) {
        this.mContext = context;
    }

    @Provides
    public Context provideContext(){
        return mContext;
    }

    @Provides
    @Singleton
    public UserPreferenceStore providePreferencesStore() {
        return new SharedPreferenceStore(mContext);
    }

    @Provides
    @Singleton
    public OpenWeatherService provideOpenWeatherService(Context context, UserPreferenceStore userPreferenceStore) {
        return OpenWeatherAPI.getService(context, userPreferenceStore);
    }


}
