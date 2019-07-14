package com.example.GetContentTest;

import android.os.Parcel;
import android.os.Parcelable;

public class Person2 implements Parcelable {

    private String name;
    private int age;

    public static final Creator<Person2> CREATOR = new Creator<Person2>() {
        @Override
        public Person2 createFromParcel(Parcel source) {
            Person2 person2 = new Person2();
            person2.name = source.readString();//读取姓名
            person2.age = source.readInt();//读取age
            return  person2;
        }

        @Override
        public Person2[] newArray(int size) {
            return new Person2[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(age);

    }
}
