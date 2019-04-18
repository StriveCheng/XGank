package com.example.strive.xgank;

import android.app.Application;

import com.example.strive.xgank.common.network.NetworkManger;

/**
 * Created by strive on 2018/8/21.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetworkManger.getInstance().init();
    }
}
