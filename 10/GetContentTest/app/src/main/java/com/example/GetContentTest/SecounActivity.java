package com.example.GetContentTest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SecounActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        /*
        * 第一种方法：   实现Serializable
        * */
       Intent intent =  getIntent();
       Person person = (Person)intent.getSerializableExtra("person1");
       /*
       * 第二种方法： 实现Parcelable
       * */

       Person2 person2 = (Person2)intent.getParcelableExtra("person1");
    }

}
