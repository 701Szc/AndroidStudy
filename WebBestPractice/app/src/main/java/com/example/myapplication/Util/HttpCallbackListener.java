package com.example.myapplication.Util;

public interface HttpCallbackListener {
//    当服务器成功响应我们请求
    void onFinish(String response);
//    当进行网络操作出现错误的时候
    void onError(Exception e);
}
