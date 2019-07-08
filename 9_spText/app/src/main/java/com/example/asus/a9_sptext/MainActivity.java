package com.example.asus.a9_sptext;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText et_name;
    private EditText et_uesrpassword;
    private CheckBox cb_ischeck;
    private Button btn;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name = (EditText) findViewById(R.id.edit_username);
        et_uesrpassword = (EditText)findViewById(R.id.edit_userpassword);
        cb_ischeck = (CheckBox)findViewById(R.id.isCheck);
//        [2]在config.xml文件中把我们关心的数据取出来，然后显示到edittext控件
        /*
         *   name 会帮助我们生成一个xml文件
         *   mode代表模式
         * */
        sp = getSharedPreferences("config",0);
        String name =sp.getString("name","");
        String pwd = sp.getString("pwd","");
//        [3]把name和pwd设置到edittext上
        et_name.setText(name);
        et_uesrpassword.setText(pwd);
    }


    public void login(View v){
        String name = et_name.getText().toString().trim();
        String pwd = et_uesrpassword.getText().toString().trim();
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd)){
            Toast.makeText(MainActivity.this,"用户名或密码为空",Toast.LENGTH_SHORT).show();
        }else{
            System.out.print("连接服务器   进行登录  ");
            if(cb_ischeck.isChecked()){
//                使用SharedPreferences 去保存数据
//                拿到SharedPreferences实例
                /*
                *   name 会帮助我们生成一个xml文件
                *   mode代表模式
                * */

//                获取sp的编辑器
                SharedPreferences.Editor edit =sp.edit();
                edit.putString("name",name);
                edit.putString("pwd",pwd);
                edit.commit();

            }else{
                Toast.makeText(MainActivity.this,"请勾选checkBox",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
