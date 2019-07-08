package com.example.asus.tellphone;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText et_number;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_number = (EditText)findViewById(R.id.editText);
        btn = (Button)findViewById(R.id.button);
//        设置监听事件
        btn.setOnClickListener(new MyClickListener());
    }
    private class MyClickListener implements  View.OnClickListener{
        @Override
        public void onClick(View v) {
            String number = et_number.getText().toString().trim();
            if(number.equals("")){
                Toast.makeText(MainActivity.this,"number不能为空",Toast.LENGTH_LONG).show();
            }
//            进行拨打电话 意图 Intent
            Intent intent = new Intent();
            /*
            * uri:统一资源标识符
            * url:统一资源定位符www.baidu.com
            * */
            //设置动作
            intent.setAction(Intent.ACTION_CALL);
            //设置要拨打的数据
            intent.setData(Uri.parse("tel:"+number));
            startActivity(intent);
        }
    }
}
