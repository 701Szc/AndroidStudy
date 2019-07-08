package com.example.asus.a10_createxmlone;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class  MainActivity extends AppCompatActivity {
    private List<Sms> smsLists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        [1]初始化假数据
        smsLists = new ArrayList<Sms>();
        for(int i =0; i<10; i++){
            Sms sms = new Sms();
            sms.setAddress("10008"+i);
            sms.setBody("nihao"+i);
            sms.setDate("201"+i);
            smsLists.add(sms);
        }
    }
//    点击按钮通过StringBuffer的方式生成一个xml文件
    public void click(View v){
//        [1]创建sb对象
        StringBuffer sb = new StringBuffer();
//        [2]开始组拼xml文件头
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
//        [3]开始组拼xml的根节点
        sb.append("<smss>");
//        [4]开始组拼sms节点
        for(Sms sms: smsLists){
            sb.append("<sms>");
//            [5]开始拼接address节点
            sb.append("<address>");
            sb.append(sms.getAddress());
            sb.append("</address>");
//            [6]body节点
            sb.append("<body>");
            sb.append(sms.getBody());
            sb.append("</body>");
//          [7]
            sb.append("<date>");
            sb.append(sms.getDate());
            sb.append("</date>");
            sb.append("</sms>");
        }

        sb.append("</smss>");
//        [8]把数据保存到sd卡中
        try {
            File f = new File(Environment.getExternalStorageDirectory().getPath(),"smsBack.xml");
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(sb.toString().getBytes());
            fos.close();//关闭流

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
