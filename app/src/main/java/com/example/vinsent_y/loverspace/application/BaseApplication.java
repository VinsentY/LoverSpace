package com.example.vinsent_y.loverspace.application;

import android.app.Application;

import com.example.vinsent_y.loverspace.util.StaticClass;

import cn.bmob.v3.Bmob;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Bmod初始化
        Bmob.initialize(this, StaticClass.BMOB_APP_ID);
    }
}
