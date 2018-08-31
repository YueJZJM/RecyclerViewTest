package com.example.yuejz.recyclerviewtest;

import android.app.Application;
import android.content.Context;

import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;

public class MyApplication extends Application {
    private static Context sContext;

    // 数据接收的 URL
    final String SA_SERVER_URL = "YOUR_SERVER_URL";

    // Debug 模式选项
    //   SensorsDataAPI.DebugMode.DEBUG_OFF - 关闭 Debug 模式
    //   SensorsDataAPI.DebugMode.DEBUG_ONLY - 打开 Debug 模式，校验数据，但不进行数据导入
    //   SensorsDataAPI.DebugMode.DEBUG_AND_TRACK - 打开 Debug 模式，校验数据，并将数据导入到神策分析中
    // 注意！请不要在正式发布的 App 中使用 Debug 模式！
    final SensorsDataAPI.DebugMode SA_DEBUG_MODE = SensorsDataAPI.DebugMode.DEBUG_OFF;

    @Override
    public void onCreate() {
        super.onCreate();
        //sContext.getPackageName()
        sContext = getApplicationContext();
        sContext.getPackageName();
        initSA();
    }

    public static Context getContext() {
        return sContext;
    }

    void initSA(){
        SensorsDataAPI.sharedInstance(
                this,                               // 传入 Context
                SA_SERVER_URL,                      // 数据接收的 URL
                SA_DEBUG_MODE);                     // Debug 模式选项
    }
}
