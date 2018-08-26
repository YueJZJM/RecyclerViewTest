package com.example.yuejz.recyclerviewtest;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        //sContext.getPackageName()
        sContext = getApplicationContext();
        sContext.getPackageName();
    }

    public static Context getContext() {
        return sContext;
    }
}
