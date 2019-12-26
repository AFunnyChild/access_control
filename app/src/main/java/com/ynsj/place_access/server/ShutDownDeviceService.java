package com.ynsj.place_access.server;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ynsj.place_access.util.ServerConfig;
import com.ynsj.place_access.util.StaticDataUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * createdUser : xuyanyun
 * createData :  2018/11/29/029
 * remark : 定时重启的服务
 * hasBug? 佛祖保佑！
 * name ：
 */

public class ShutDownDeviceService extends Service {
    private ScheduledExecutorService threadPool = null;
    private int betweenTime = 59;//间隔59秒执行一次
    private int delayTime = 2;//线程池开启5庙后执行
    private String oneTime = "";//重启时间一
    private String twoTime = "12:00";//重启时间二
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    String[] rebootArray = {"su", "-c", "reboot"};//执行重启的命令
    String dateStr = "";//获取的时间
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        threadPool = Executors.newScheduledThreadPool(3);
        oneTime= ServerConfig.getInstance().reboot_tim;
        Log.e("service", "开始任务" );
        executeShutDown();
    }


    public void executeShutDown() {
        threadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                sdf.setTimeZone(TimeZone.getTimeZone("GMT+08"));
               dateStr = sdf.format(new Date());
                if (dateStr.equals(oneTime) ) {
                    Log.e("service", "运行定时任务" );
                    StaticDataUtil.getInstance().manager.reboot();
                }
            }
        }, delayTime, betweenTime, TimeUnit.SECONDS);
    }

    @Override
    public void onDestroy() {
        threadPool.shutdown();
        threadPool = null ;
        dateStr = null ;
        super.onDestroy();
    }


}