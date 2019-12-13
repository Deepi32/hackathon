package com.example.myapplication5.utils;

import android.util.Log;

public class Utils {

    public static final int LOG_DEBUG = 0;
    public static final int LOG_ERROR = 1;
    public static final int LOG_INFO = 2;
    public static final int LOG_WARNING = 3;
    public static final int LOG_VERBOSE = 4;

    public static void showLogs(String logs) {
        showLogs(LOG_DEBUG, "Utils", logs);
    }
    public static void showLogs(int type, String tag, String log) {
        switch (type) {
            case LOG_DEBUG:
                Log.d(tag, log);
                break;
            case LOG_ERROR:
                Log.e(tag, log);
                break;
            case LOG_INFO:
                Log.i(tag, log);
                break;
            case LOG_WARNING:
                Log.w(tag, log);
                break;
            case LOG_VERBOSE:
                Log.v(tag, log);
                break;
        }
    }


}
