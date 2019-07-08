package com.example.asus.day3_usegoogleapitosql;

import android.content.ContentValues;
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
        /*
        * table 表名
        *ContentValues 内部封装了一个map key: 对应列的名字  value 对应值
        * */
        ContentValues values = new ContentValues();
        values.put("name","王五");
        values.put("phone","110");
//        返回值代表插入新行的id
        long insert = db.insert("info",null,values);//底层在组拼sql语句
        db.close();
        if(insert>0){
            Toast.makeText(getApplicationContext(),"添加成功",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"添加失败",Toast.LENGTH_LONG).show();
        }


    }
    public void click2(View v){
        SQLiteDatabase db = myopenHelper.getWritableDatabase();
        int delete = db.delete("info","name=?",new String[]{"王五"});
        db.close();
        Toast.makeText(getApplicationContext(),"删除了"+delete+"行",Toast.LENGTH_LONG).show();
    }
    public void click3(View v){
        SQLiteDatabase db = myopenHelper.getReadableDatabase();
        db.execSQL("update info set phone = ? where name = ?",new Object[]{"456498453","张三"});
        db.close();
    }
    public void click4(View v){
        SQLiteDatabase db = myopenHelper.getWritableDatabase();
        /*
        * table 表名
        * columns 代表你要查询的列
        * selection 根据什么查询phone
        * */
        Cursor cursor = db.query("info",new String[]{"name","phone"},"name=?",new String[]{"王五"},null,null,null);
        if(cursor!=null&&cursor.getCount()>0){
            while(cursor.moveToNext()){
                String phone = cursor.getString(0);
                System.out.print("Phone: "+ phone);

            }
        }

    }

}
