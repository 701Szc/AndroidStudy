package com.example.asus.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

/*
* 动态添加碎片：
* 1.创建待添加的碎片实例
* 2.获取FragmentManager，在活动中直接getSupportFragmentManager可以得到
* 3.开启一个事务，通过调用beginTransaction方法开启
* 4.向容器内添加或替换碎片，一般使用replace方法实现，需要传入容器的id和待添加的碎片实例
* 5.调用commit方法完成
* */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button =(Button)findViewById(R.id.button);
        button.setOnClickListener(this);
        replaceFragment(new RightFragment());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                replaceFragment(new AnotherRightFragment());
                break;
            default:
                break;
        }
    }

    private void replaceFragment(Fragment fragment){
////        获取fragmentManager对象
//        FragmentManager fragmentManager = getSupportFragmentManager();
////        开启一个事务
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.right_layout,fragment);
////        将事务添加到返回栈中
//        transaction.addToBackStack(null);
//        transaction.commit();
    }
}
