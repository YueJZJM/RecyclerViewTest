package com.example.yuejz.recyclerviewtest.util;

import android.os.Build;
import android.util.Log;

import com.example.yuejz.recyclerviewtest.BuildConfig;


public class LogUtil {
    public static final int VERBBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;
    public static int Level = VERBBOSE;

    static {
        if (!BuildConfig.DEBUG){
            Level = NOTHING;
        }
    }

    public static void v(String tag, String msg){
        if (Level <= VERBBOSE){
            Log.v(tag,msg);
        }
    }

    public static void d(String tag, String msg){
        if (Level <= DEBUG){
            Log.d(tag,msg);
        }
    }

    public static void i(String tag, String msg){
        if (Level <= INFO){
            Log.i(tag,msg);
        }
    }

    public static void w(String tag, String msg){
        if (Level <= WARN){
            Log.w(tag,msg);
        }
    }

    public static void e(String tag, String msg){
        if (Level <= ERROR){
            Log.e(tag,msg);
        }
    }


}
