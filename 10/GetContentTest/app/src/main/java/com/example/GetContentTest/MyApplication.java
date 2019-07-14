package com.example.GetContentTest;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePalApplication;

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
//        避免与LitePal有冲突
        /*
        * 相当于我们把全局的Context对象通过参数传递给LitePal
        * */
        LitePalApplication.initialize(context);
    }

    public static Context getContext(){
        return context;
    }
}
