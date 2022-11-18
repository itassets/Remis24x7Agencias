package com.remis24x7agencias;

import android.app.Application;
import android.os.SystemClock;

public class MyRemisApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(3000);
    }

}