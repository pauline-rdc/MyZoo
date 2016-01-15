package com.example.pauliner.myzoo;

import android.app.Application;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by Stéphane.e on 15/01/2016.
 */
public class MyApplication extends Application {
    private static Bus eventBus;
    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        eventBus = new Bus(ThreadEnforcer.ANY);
    }

    public Bus getEventBus() {
        return eventBus;
    }
}
