package com.ynsj.place_access.util;

import android.app.Activity;

import com.bumptech.glide.request.RequestOptions;
import com.ys.myapi.MyManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class StaticDataUtil {
    public RequestOptions requestOptions;
    public static StaticDataUtil instance = null;
    public String TAG = "StaticUtil";

    public MyManager manager;
    public boolean isNewDevic = true;
    public List<Activity> activityList = new ArrayList<Activity>();


    public static StaticDataUtil getInstance() {
        if (instance == null) {
            instance = new StaticDataUtil();
        }
        return instance;
    }


    public StaticDataUtil() {
        server();

    }


    public void server() {
        //加载配置数据
        try {
            ServerConfig.getInstance().loadnew();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
