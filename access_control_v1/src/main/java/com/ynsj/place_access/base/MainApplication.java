package com.ynsj.place_access.base;

import android.app.Application;
import android.content.Context;

import com.jakewharton.processphoenix.ProcessPhoenix;

public class MainApplication  extends Application {
    private static MainApplication instance;
    public static MainApplication getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

     //   Thread.setDefaultUncaughtExceptionHandler(restartHandler);

//        MyManager manager = MyManager.getInstance(this);
//        manager.setSlideShowNavBar(false);
//        manager.hideNavBar(true);
//        manager.setSlideShowNotificationBar(true);
    }
    public void restartApp() {
        ProcessPhoenix.triggerRebirth(this);
    }
    private Thread.UncaughtExceptionHandler restartHandler = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread thread, Throwable ex) {
            restartApp();
        }
    };
    public static Context getContext() {
        return getInstance().getApplicationContext();
    }

}
