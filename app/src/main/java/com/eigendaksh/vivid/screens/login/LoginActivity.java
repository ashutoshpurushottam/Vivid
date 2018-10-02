package com.eigendaksh.vivid.screens.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.eigendaksh.vivid.R;
import com.eigendaksh.vivid.di.module.LoginActivityModule;
import com.eigendaksh.vivid.di.scope.ActivityContext;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity {

    @Inject
    ActivityContext mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initInjector();

    }

    private void initInjector() {
//        DaggerLoginActivityComponent.builder()
//                .appComponent(AppComponentFactory.create(this))
//                .loginActivityModule(new LoginActivityModule(this))
//                .build();
    }
}
