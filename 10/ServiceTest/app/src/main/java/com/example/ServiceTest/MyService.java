package com.example.ServiceTest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class MyService extends Service {

    private DownloadBinder mBinder = new DownloadBinder();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
//   打log证实服务开启与关闭
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService", "onCreate: ");
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,intent,0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();
        startForeground(1,notification);
    }
//服务一旦启动就去执行某个动作
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
      new Thread(new Runnable() {
          @Override
          public void run() {
//              处理逻辑

              stopSelf();
          }
      }).start();
        return super.onStartCommand(intent, flags, startId);

    }

//    回收不必要资源
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService", "onDestroy: ");
    }

     class DownloadBinder extends Binder {
        public void startDownload(){
            Log.d("MyServer", "startDownload: ");
        }

        public int getProgress(){
            Log.d("MyServer", "getProgress: ");
            return 0;
        }

    }
}
