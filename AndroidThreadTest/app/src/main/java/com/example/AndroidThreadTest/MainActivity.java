package com.example.AndroidThreadTest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int UPDATE_TEXT = 1;
    private TextView text;
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch(msg.what){
                case UPDATE_TEXT:
//                    在这里可以进行ui操作
                    text.setText("Nice to meet you ");
                    break;
                    default:
                        break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView)findViewById(R.id.text);
        Button changeText = (Button)findViewById(R.id.change_text);
        //点击按钮发送消息
        changeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.change_text:
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
//                                text.setText("Nice to meet you ");
                                Message message = new Message();
                                message.what = UPDATE_TEXT;
//                                将Message对象发送出去
                                handler.sendMessage(message);
                            }
                        }).start();
                        break;
                        default:
                            break;

                }
            }
        });
    }
}
