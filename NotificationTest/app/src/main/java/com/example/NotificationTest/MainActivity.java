package com.example.NotificationTest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendNotice = (Button)findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.send_notice:
//                创建点击之后的意图
                Intent intent = new Intent(this,NotificationActivity.class);
                /*
                * 1.content
                * 2.
                * 3.intent对象
                * 4.确定PendingIntent的行为
                *
                * */
                PendingIntent pi =  PendingIntent.getActivity(this,0,intent,0);
//                创建通知管理对象
                NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//                创建Notification对象
                Notification notification = new NotificationCompat.Builder(this)
                        .setContentTitle("This is content title")
                        .setContentText("This is content text")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                        .setContentIntent(pi)
////              播放音频
//                        .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")))
//                        /*
//                         * 0:手机静止的时长
//                         * 1000震动时长
//                         *
//                         * */
//                        .setVibrate(new long[]{0,1000,1000,1000})
//                        /*
//                         * 1.LED灯的颜色
//                         * 2.LED灯亮起的时长
//                         * 3.LED灯暗去的时长
//                         * */
//                        .setLights(Color.GREEN,1000,1000)
//                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setContentText("111111111111111111111111111111111111111111" +
                                "111111111111111111111111111111111111111111111111" +
                                "1111111111111111111111111111111111111111111111")
//                        点击通知后消失
                        .setAutoCancel(true)
                        .build();
                manager.notify(1,notification);
                break;

            default:
                break;
        }
    }
}
