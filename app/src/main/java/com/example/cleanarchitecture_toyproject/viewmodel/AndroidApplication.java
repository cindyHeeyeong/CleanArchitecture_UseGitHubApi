package com.example.cleanarchitecture_toyproject.viewmodel;

import android.app.Application;

public class AndroidApplication extends Application {

    private static AndroidApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
    public static AndroidApplication getInstance(){
        return instance;
    }
}
