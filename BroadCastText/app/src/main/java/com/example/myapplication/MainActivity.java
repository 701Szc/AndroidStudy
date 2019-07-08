package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;
//    本地广播
    private LocalReceiver localReceiver;
//    本地广播管理
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        获取实例
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent("com.example.myapplication.MY_BROADCAST");
////                传递有序广播
//                sendOrderedBroadcast(intent,null);

                Intent intent= new Intent("com.example.myapplication.LOCAL_BROADCAST");
//                发送本地广播
                localBroadcastManager.sendBroadcast(intent);
            }
        });
        intentFilter = new IntentFilter();
//        intentFilter.addAction("android.net.com.CONNECTIVITY_CHANGE");
        intentFilter.addAction("com.example.myapplication.LOCAL_BROADCAST");
        localReceiver = new LocalReceiver();
//        注册本地广播监听器
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);
        networkChangeReceiver = new NetworkChangeReceiver();
//        注册
        registerReceiver(networkChangeReceiver,intentFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        注销
        unregisterReceiver(networkChangeReceiver);
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    class NetworkChangeReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
//            实例化管理网络连接的实体类
            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
//            得到NetworkInfo的实例
            NetworkInfo networkInfo =connectivityManager.getActiveNetworkInfo();
            if(networkInfo != null&& networkInfo.isAvailable() ){
                Toast.makeText(context,"available",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context,"unavailable",Toast.LENGTH_LONG).show();
            }

//            Toast.makeText( context,"network changes",Toast.LENGTH_SHORT).show();
        }
    }

    private class LocalReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"receiver localbroadcast",Toast.LENGTH_LONG).show();
        }
    }
}
