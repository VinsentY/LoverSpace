package com.example.vinsent_y.loverspace.base;

import android.app.Application;
import android.content.Context;

import com.example.vinsent_y.loverspace.util.StaticClass;

import cn.bmob.v3.Bmob;

public class BaseApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        //Bmod初始化
        Bmob.initialize(this, StaticClass.BMOB_APP_ID);
        context = getApplicationContext();
    }


    public static Context getApplication() {
        return context;
    }
}
