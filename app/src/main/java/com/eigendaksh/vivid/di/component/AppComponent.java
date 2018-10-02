package com.eigendaksh.vivid.di.component;

import android.app.Application;
import android.content.Context;

import com.eigendaksh.vivid.di.module.ContextModule;
import com.eigendaksh.vivid.di.module.PresenterModule;
import com.eigendaksh.vivid.di.scope.ApplicationContext;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

@Singleton
@Component(modules = {ContextModule.class, PresenterModule.class})
public interface AppComponent {

    @ApplicationContext
    Context getContext();

    void inject(Application application);

}
