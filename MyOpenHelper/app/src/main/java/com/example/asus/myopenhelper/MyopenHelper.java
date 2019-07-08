package com.example.asus.myopenhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyopenHelper extends SQLiteOpenHelper {
    /*
    *  @param context 上下文
    *  name 数据库的名字
    *  factory 目的创建cursor对象
    *  verson  数据库的版本
    * */
    public MyopenHelper(Context context){
        super(context,"text.db",null,1);
    }
/*
* 当数据库第一次创建的时候调用
* 适合做表结构的初始化
* */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table info(_id integer primary key autoincrement,name varchar(20), phone varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
