package com.jeanboy.component.location.utils;

import android.util.Log;

/**
 * @author caojianbo
 * @since 2019/12/6 17:33
 */
public class LogUtil {

    private static final String TAG = "LocationMaster";
    private static final boolean IS_DEBUG = false;

    public static void d(String tag, String msg) {
        if (!IS_DEBUG) return;
        Log.d(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (!IS_DEBUG) return;
        Log.d(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (!IS_DEBUG) return;
        Log.e(tag, msg);
    }

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void i(String msg) {
        i(TAG, msg);
    }

    public static void e(String msg) {
        e(TAG, msg);
    }
}
