package com.appodroid.mohitkhaitan.restraunline.ParseNotification;

import android.app.Application;

/**
 * Created by MOHIT KHAITAN on 22-03-2016.
 */
public class MyApplication extends Application{

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        ParseUtils.registerParse(this);
    }

    public static synchronized MyApplication getInstance(){
        return mInstance;
    }
}
