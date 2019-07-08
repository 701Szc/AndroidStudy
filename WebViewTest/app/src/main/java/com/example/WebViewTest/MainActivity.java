package com.example.WebViewTest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = (WebView)findViewById(R.id.web_view);
//        如果访问的页面中有 Javascript,则 WebView 必须设置支持 Javascript。
        webView.getSettings().setJavaScriptEnabled(true);
//        仍然在原软件中跳转，不打开浏览器跳转
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.baidu.com");

    }
}
