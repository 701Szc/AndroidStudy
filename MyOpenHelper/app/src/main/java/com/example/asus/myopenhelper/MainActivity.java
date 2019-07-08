package com.example.asus.myopenhelper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private MyopenHelper myopenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myopenHelper = new MyopenHelper(getApplicationContext());
////        打开或者创建数据库，如果是第一次创建
//        SQLiteDatabase sqLiteDatabase = myopenHelper.getWritableDatabase();
////        磁盘满了  只读
//        SQLiteDatabase sqLiteDatabase1 = myopenHelper.getReadableDatabase();

    }
    public void click1(View v){
//        [1]获取数据库对象
        SQLiteDatabase db = myopenHelper.getWritableDatabase();
//        [2]执行增加一条的sql语句
        db.execSQL("insert into info(name,phone) values(?,?);",new Object[]{"张三","4654665"});
//        [3]数据库用完需要关掉
        db.close();

    }
    public void click2(View v){
        SQLiteDatabase db = myopenHelper.getWritableDatabase();
        db.execSQL("delete from info where name = ?",new Object[]{"张三"});
        db.close();
    }
    public void click3(View v){
        SQLiteDatabase db = myopenHelper.getReadableDatabase();
        db.execSQL("update info set phone = ? where name = ?",new Object[]{"456498453","张三"});
        db.close();
    }
    public void click4(View v){
        SQLiteDatabase db = myopenHelper.getReadableDatabase();
        Cursor cursor =db.rawQuery("select * from info;",null);
        if(cursor != null && cursor.getCount()>0){
            while(cursor.moveToNext()){
//                   代表列的索引
               String name = cursor.getString(1);
               String phone = cursor.getString(2);
                Toast.makeText(MainActivity.this,"name:"+name+"------"+"phone:"+phone,Toast.LENGTH_LONG).show();
            }
        }
    }

}
