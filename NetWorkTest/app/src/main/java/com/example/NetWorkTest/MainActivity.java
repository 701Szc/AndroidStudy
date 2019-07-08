package com.example.NetWorkTest;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.ResponseCache;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView reponseText;
    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendRequest = (Button)findViewById(R.id.send_request);
        reponseText = (TextView)findViewById(R.id.response_text);
        sendRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.send_request){
//            sendRequestWithHttpURLConnection();
              sendRequestWithOkHttp();
        }
    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
//                            .url("http://www.baidu.com")
                            .url("http://10.0.2.2/get_data.json")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
//                    登录网页
//                    showResponse(responseData);
//                    利用Pll方法解析xml数据
//                    parseXMLWithPull(responseData);
//                    利用SAX方法解析xml数据
//                    parseXMLWithSAX(responseData);
//                    使用JSONObject解析json数据
//                    parseJSONWithJSONObject(responseData);
//                    使用gson解析json
                    parseJSONWithGSON(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithGSON(String jsonData) {
        Gson gson = new Gson();
        List<App> appList = gson.fromJson(jsonData,new TypeToken<List<App>>(){}.getType());
        for(App app:appList){
            Log.d(TAG, "parseJSONWithGSON: id is"+app.getId());
            Log.d(TAG, "parseJSONWithGSON: name is "+ app.getName());
            Log.d(TAG, "parseJSONWithGSON: version is "+ app.getVersion());
        }
    }

//    private void parseJSONWithJSONObject(String jsonData) {
//        try {
//            JSONArray jsonArray = new JSONArray(jsonData);
//            for(int i=0;i<jsonArray.length();i++){
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                String id = jsonObject.getString("id");
//                String name =jsonObject.getString("name");
//                String version = jsonObject.getString("version");
//                Log.d(TAG, "id is"+id);
//                Log.d(TAG, "name is"+name);
//                Log.d(TAG, "version is"+version);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

//    private void parseXMLWithSAX(String xmlData) {
//        try{
//            SAXParserFactory factory = SAXParserFactory.newInstance();
//            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
//            ContentHandler handler = new ContentHandler();
////            将 ContentHandler 的实例设置到xmlReader中
//            xmlReader.setContentHandler(handler);
////            开始执行解析
//            xmlReader.parse(new InputSource(new StringReader(xmlData)));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

//    private void parseXMLWithPull(String xmlData) {
//
//        try{
////            获取xmlPullParserFactory的实例
//            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
////            借助xmlPullParserFactory的实例获取到xmlPullParse对象
//            XmlPullParser xmlPullParser = factory.newPullParser();
////            将服务器返回的xml数据传入  并开始解析
//            xmlPullParser.setInput(new StringReader(xmlData));
////            获取当前解析事件
//            int eventType = xmlPullParser.getEventType();
//            String id = "";
//            String name = "";
//            String version = "";
////            在while中不断解析
//            while(eventType != XmlPullParser.END_DOCUMENT){
//                String nodeName = xmlPullParser.getName();
//                switch(eventType){
////                    开始解析某个结点
//                    case XmlPullParser.START_TAG:{
//                        if("id".equals(nodeName)){
//                            id = xmlPullParser.nextText();
//                        }else if("name".equals(nodeName)){
//                            name = xmlPullParser.nextText();
//                        }else if("version".equals(nodeName)){
//                            version = xmlPullParser.nextText();
//                        }
//                        break;
//                    }
////                    完整解析某个节点
//                        case XmlPullParser.END_TAG:{
//                            if("app".equals(nodeName)){
//                                Log.d(TAG, "id is "+ id);
//                                Log.d(TAG, "name is "+ name);
//                                Log.d(TAG, "version is "+ version);
//                            }
//                            break;
//                        }
//                        default:
//                            break;
//                }
//            }
//
//        }catch (Exception e){
//
//        }
//
//    }


//        private void sendRequestWithHttpURLConnection() {
////        开启线程来发起网络请求
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                HttpURLConnection connection = null;
//                BufferedReader reader = null;
//                try{
//                    URL url = new URL("http://www.baidu.com");
//                    connection = (HttpURLConnection) url.openConnection();
//                    connection.setRequestMethod("GET");
//                    connection.setConnectTimeout(8000);
//                    connection.setReadTimeout(8000);
//                    InputStream in = connection.getInputStream();
////                    下面对获取到的输入流进行读取
//                    reader = new BufferedReader(new InputStreamReader(in));
//                    StringBuilder response = new StringBuilder();
//                    String line;
//                    while((line = reader.readLine())!= null){
//                        response.append(line);
//                    }
//                    showResponse(response.toString());
//                }catch (Exception e){
//
//                }finally {
//                    if(reader != null){
//                        try{
//                            reader.close();
//                        }catch (Exception e){
//
//                        }
//                    }
//                    if(connection != null){
//                        connection.disconnect();
//                    }
//                }
//            }
//        }).start();
//    }
//
    private void showResponse(final String response) {
//        将线程切换到主线程
//        安卓不允许ui操作在子线程中实现
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                在这里进行UI操作，将结果显示到界面上
                reponseText.setText(response);
            }
        });
    }
}
