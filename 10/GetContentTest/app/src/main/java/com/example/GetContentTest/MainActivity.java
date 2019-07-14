package com.example.GetContentTest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Person person  = new Person();
        person.setName("Tom");
        person.setAge(20);
        Intent intent = new Intent(this,SecounActivity.class);
        intent.putExtra("person1",person);
        startActivity(intent);
    }
}
