package com.example.wenyapplication.application;

import android.app.Application;

import androidx.multidex.MultiDex;


public class App extends Application {
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        // 主要是添加下面这句代码
        MultiDex.install(this);
        instance = this;
    }


    public static App getApplication() {
        return instance;
    }

}
