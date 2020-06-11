package com.wheny.whenylibrary.utils;

import android.util.Log;

public class LogUtils {
    private static final String TAG = LogUtils.class.getName();


    public static void i(String tag,String msg){
        if (APPUtils.isOpenLog) Log.i(tag,msg);
    }

    public static void i(String msg){
        i(TAG,msg);
    }


    public static void d(String tag,String msg){
        if (APPUtils.isOpenLog) Log.d(tag,msg);
    }

    public static void d(String msg){
        d(TAG,msg);
    }




    public static void w(String tag,String msg){
        if (APPUtils.isOpenLog) Log.w(tag,msg);
    }

    public static void w(String msg){
        w(TAG,msg);
    }


    public static void e(String tag,String msg){
        if (APPUtils.isOpenLog) Log.e(tag,msg);
    }

    public static void e(String msg){
        e(TAG,msg);
    }










}
