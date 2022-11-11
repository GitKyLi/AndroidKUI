package com.kyli.kui.utils.log;

import android.util.Log;

public class LogUtils {

    public static boolean LOG_ENABLE = true;

    public static void i(String tag, String i) {
        if (LOG_ENABLE)
            delegate.i(tag, i);
    }


    public static void e(String tag, String e) {
        if (LOG_ENABLE)
            delegate.e(tag, e);
    }


    public static void v(String tag, String v) {
        if (LOG_ENABLE)
            delegate.v(tag, v);
    }


    public static void d(String tag, String d) {
        if (LOG_ENABLE)
            delegate.d(tag, d);
    }


    public static void w(String tag, String w) {
        if (LOG_ENABLE)
            delegate.w(tag, w);
    }

    public interface KLog {
        void i(String tag, String i);

        void e(String tag, String e);

        void v(String tag, String v);

        void d(String tag, String d);

        void w(String tag, String w);

    }

    private static KLog delegate = AndroidLog.instance;

    /**
     * @return 默认为安卓系统的日志系统
     */
    public static KLog getDefault() {
        return AndroidLog.instance;
    }

    /**
     * @param delegate 自定义或者第三方委托实现
     */
    public static void setDelegate(KLog delegate) {
        LogUtils.delegate = delegate;
    }

    private static class AndroidLog implements KLog {

        public static AndroidLog instance = new AndroidLog();


        private AndroidLog() {

        }

        @Override
        public void i(String tag, String i) {
            Log.i(tag, i);
        }

        @Override
        public void e(String tag, String e) {
            Log.e(tag, e);
        }

        @Override
        public void v(String tag, String v) {
            Log.v(tag, v);
        }

        @Override
        public void d(String tag, String d) {
            Log.d(tag, d);
        }

        @Override
        public void w(String tag, String w) {
            Log.w(tag, w);


        }
    }
}
