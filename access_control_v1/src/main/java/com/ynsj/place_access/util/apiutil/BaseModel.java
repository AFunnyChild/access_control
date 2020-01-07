package com.ynsj.place_access.util.apiutil;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by Administrator on 2018/5/3.
 */

public class BaseModel {

    protected Handler handler = new Handler(Looper.getMainLooper());

    protected void runOnUiThread(Runnable runnable){
        handler.post(runnable);
    }
}
