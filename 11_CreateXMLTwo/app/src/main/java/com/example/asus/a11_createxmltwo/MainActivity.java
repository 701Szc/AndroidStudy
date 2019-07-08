package com.example.asus.a11_createxmltwo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class  MainActivity extends AppCompatActivity {
    public List<Sms> smsLists;
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
    //    点击按钮通过XmlSerializer的方式生成一个xml文件
    public void click(View v){
        try {
//        [1]获取XmlSerializer类的实例  通过Xml这个工具类去获取
            XmlSerializer serializer = Xml.newSerializer();
//        [2]设置 XmlSerializer 序列化参数
            File file = new File(Environment.getExternalStorageDirectory().getPath(),"smsbackup3.xml");
            FileOutputStream fos = new FileOutputStream(file);
            serializer.setOutput(fos,"utf-8");
//            [3]开始写xml文档开头
            serializer.startDocument("utf-8",true);
//            [4]写xml的根节点namespace命名空间
            serializer.startTag(null,"smss");
//            [5]循环写sms节点
            for(Sms sms: smsLists){
                serializer.startTag(null,"sms");
//                开始address节点
                serializer.startTag(null,"address");
                serializer.text(sms.getAddress());
                serializer.endTag(null,"address");

                //                开始address节点
                serializer.startTag(null,"body");
                serializer.text(sms.getBody());
                serializer.endTag(null,"body");


                //                开始address节点
                serializer.startTag(null,"date");
                serializer.text(sms.getDate());
                serializer.endTag(null,"date");
                serializer.endTag(null,"sms");
            }
            serializer.endTag(null,"smss");
//            写文档结尾
            serializer.endDocument();
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();


        }


    }
}
