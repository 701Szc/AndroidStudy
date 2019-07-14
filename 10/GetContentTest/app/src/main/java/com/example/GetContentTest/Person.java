package com.example.GetContentTest;

import java.io.Serializable;
/*
*利用intent传递对象  方法一
* 实现serializable接口，所有的person对象都是序列化  可以用intent传输
*/

public class Person implements Serializable {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
