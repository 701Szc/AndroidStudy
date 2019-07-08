package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.Util.HttpUtil;

public class MainActivity extends AppCompatActivity {

    String address = "http://www.baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//      获取到解析的数据
        String response = HttpUtil.sendHttpRequest(address);

    }
}
