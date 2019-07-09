package com.example.ServiceTest;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyInenetService extends IntentService {

    public MyInenetService() {
        super("MyInenetService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
//        打印当前线程的id
        Log.d("MyInenetService", "Thread id is"+ Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyInenetService", "onDestroy executed");
    }
}
