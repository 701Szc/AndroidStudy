package com.example.myapplication;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;
//对所有活动进行管理
public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();
    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    public static void finishAll(){
        for(Activity activity:activities){
//            结束一个activity的生命周期 在ondestroy之后   所以会调用生命周期中的方法
//            Call this when your activity is done and should be closed.
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
        activities.clear();
    }
}
