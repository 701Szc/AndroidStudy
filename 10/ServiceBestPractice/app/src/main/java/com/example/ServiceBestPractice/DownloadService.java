package com.example.ServiceBestPractice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import java.io.File;

public class DownloadService extends Service {

    private DownloadTask downloadTask;
    private String downloadUrl;
    //创建匿名类实例
    private DownloadListener listener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1,getNotification("Downloading...",progress));
        }

        @Override
        public void onSuccess() {
            downloadTask = null;
//            下载成功时将前台服务通知关闭，并创建一个下载成功的通知
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("Download Success",-1));
            Toast.makeText(DownloadService.this,"Download Success",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailed() {
            downloadTask = null;
            //下载失败时将前台服务通知关闭，并创建一个下载失败的通知
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("Download Failed",-1));
            Toast.makeText(DownloadService.this,"Download Failed",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPaused() {
            downloadTask = null;
            Toast.makeText(DownloadService.this,"Paused",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCanceled() {
            downloadTask = null;
            stopForeground(true);
            Toast.makeText(DownloadService.this,"Cancel",Toast.LENGTH_SHORT).show();

        }
    };


    private DownloadBinder mBinder = new DownloadBinder();


    public DownloadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
       return  mBinder;
    }
//服务可与前台进行通信
    class DownloadBinder extends Binder {
        public void startDownload(String url){
            if(downloadTask == null){
                downloadUrl = url;
                downloadTask = new DownloadTask(listener);
                downloadTask.execute(downloadUrl);
////                开启前台服务
                startForeground(1,getNotification("Downloading...",0));
                Toast.makeText(DownloadService.this,"Downloading...",Toast.LENGTH_SHORT).show();
            }
        }

        public void pauseDownload(){
            if(downloadTask != null){
                downloadTask.pauseDownload();
            }
        }
        public void cnacelDownload(){
            if(downloadTask != null){
                downloadTask.cancelDownload();
            } else {
                if(downloadUrl != null){
//                    取消下载时需将文件删除，并将通知关闭
                    String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                    String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directory+fileName);
                    if(file.exists()){
                        file.delete();
                    }
                    getNotificationManager().cancel(1);
//                    停止前台服务
                    stopForeground(true);
                    Toast.makeText(DownloadService.this,"Canceled",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
//           创建通知
    private Notification getNotification(String title, int progress) {
        Intent intent  = new Intent(this,MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,intent,0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                                                                    .setSmallIcon(R.mipmap.ic_launcher)
                                                                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                                                                    .setContentIntent(pi)
                                                                    .setContentTitle(title);
        if(progress > 0){
//            当progress大于或等于0时才需显示下载进度
            builder.setContentText(progress + "%");
            /*
            * 1.传入通知的最大进度
            * 2.传入通知的当前进度
            * 3.是否使用模糊进度条
            * */
            builder.setProgress(100,progress,false);
        }
        return builder.build();
    }

    private NotificationManager getNotificationManager() {
        return (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    }

}
