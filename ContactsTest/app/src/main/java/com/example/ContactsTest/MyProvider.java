package com.example.ContactsTest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
//自定义内容提供器
public class MyProvider extends ContentProvider {

    public static final int TABLE1_DIR=0;
    public static final int TABLE1_ITEM=1;
    public static final int TABLE2_DIR=2;
    public static final int TABLE2_ITEM=3;
    private static UriMatcher uriMatcher;
    static{
//        表示  不匹配任何路径的返回码  NO_MATCH
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
//        匹配content://com.example.app.provider/table1  返回1
        uriMatcher.addURI("com.example.app.provider","table1",TABLE1_DIR);
        uriMatcher.addURI("com.example.app.provider","table1/#",TABLE1_ITEM);
        uriMatcher.addURI("com.example.app.provider","table2",TABLE2_DIR);
        uriMatcher.addURI("com.example.app.provider","table2/#",TABLE2_ITEM);
    }

//    创建和升级数据库操作
    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        switch(uriMatcher.match(uri)){
            case TABLE1_DIR:
//                查询table1表终的所有数据
                break;
            case TABLE1_ITEM:
//                查询table1表终的单条数据
                break;
            case TABLE2_DIR:
//                 查询table2表终的所有数据
                break;
            case TABLE2_ITEM:
                break;
        }
        return null;
    }

    @Override
    public String getType(Uri uri) {
        switch(uriMatcher.match(uri)){
            case TABLE1_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.app.provider.table1";

            case TABLE1_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.app.provider.table1";

             case TABLE2_DIR:
                return"vnd.android.cursor.dir/vnd.com.example.app.provider.table2";

            case TABLE2_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.app.provider.table2";

            default:
                break;
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
