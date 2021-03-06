package com.zxg.myokhttpframwork;

import android.util.Log;

public class OkHttpLogUtil {
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;
    /**
     * 设置日志开放级别
     */
    public static final int LEVEL = VERBOSE;

    public static void v(String msg) {
        if (LEVEL <= VERBOSE) {
            Log.v("---------TAG---------", msg);
        }
    }
    public static void v(String tag,String msg) {
        if (LEVEL <= VERBOSE) {
            Log.v(tag, msg);
        }
    }

    public static void d(String msg) {
        if (LEVEL <= DEBUG) {
            Log.d("---------TAG---------", msg);
        }
    }
    public static void d(String tag,String msg) {
        if (LEVEL <= VERBOSE) {
            Log.d(tag, msg);
        }
    }

    public static void i(String msg) {
        if (LEVEL <= INFO) {
            Log.i("---------TAG---------", msg);
        }
    }
    public static void i(String tag,String msg) {
        if (LEVEL <= VERBOSE) {
            Log.i(tag, msg);
        }
    }

    public static void w(String msg) {
        if (LEVEL <= WARN) {
            Log.w("---------TAG---------", msg);
        }
    }
    public static void w(String tag,String msg) {
        if (LEVEL <= VERBOSE) {
            Log.w(tag, msg);
        }
    }

    public static void e(String msg) {
        if (LEVEL <= ERROR) {
            Log.e("---------TAG---------", msg);
        }
    }
    public static void e(String tag,String msg) {
        if (LEVEL <= VERBOSE) {
            Log.e(tag, msg);
        }
    }
}
