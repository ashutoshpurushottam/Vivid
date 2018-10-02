package com.eigendaksh.vivid.application;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.StrictMode;
import android.support.v4.app.Fragment;

import com.eigendaksh.vivid.BuildConfig;
import com.eigendaksh.vivid.data.db.VividDatabase;
import com.eigendaksh.vivid.di.component.AppComponent;
import com.eigendaksh.vivid.di.component.DaggerAppComponent;
import com.eigendaksh.vivid.di.module.ContextModule;
import com.eigendaksh.vivid.di.module.PresenterModule;
import com.facebook.stetho.Stetho;

import timber.log.Timber;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

public class VividApplication extends Application {

    private static volatile AppComponent component;
//    private VividDatabase mDB;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private void initDagger(){
        component = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .presenterModule(new PresenterModule(this))
                .build();

    }


    private void setUpTimber() {
        if(BuildConfig.DEBUG) {
            // print line number as well
            Timber.plant(new Timber.DebugTree() {
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    return super.createStackElementTag(element) + ":" + element.getLineNumber();
                }
            });
        }
    }

    public static synchronized AppComponent getComponent() {
        return component;
    }




    /**
     * Set up strictMode to catch accidental disk or network access on the application's main thread.
     */
    private void setupStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectAll()
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .build());
        }
    }

    private void setUpStetho() {
        if(BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }

//    public static VividGraph getComponent(Context context) {
//        return ((VividApplication) context.getApplicationContext()).mComponent;
//    }
//
//    public static VividGraph getComponent(android.support.v4.app.Fragment fragment) {
//        return getComponent(fragment.getContext());
//    }

//    public static VividDatabase getDatabase(Context context) {
//        return ((VividApplication) context.getApplicationContext()).mDB;
//    }
//
//    public static VividDatabase getDatabase(Fragment fragment) {
//        return ((VividApplication) fragment.getContext()).mDB;
//    }


}
