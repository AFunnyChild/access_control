package com.ynsj.place_access.util.apiutil;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * 网络请求工具类
 * Created by Administrator on 2018/5/3.
 */

public class ApiUtil {

//    public static ApiService apiService;
    public static OkHttpClient okHttpClient;
    private static String cookie;

    private static Context mContext;

    /**
     * 初始化
     */
    public static void initOkHttp(Context context, String baseUrl) {
        okHttpClient = new OkHttpClient
                .Builder()
                .connectTimeout(10, TimeUnit.SECONDS)//连接超时
                .readTimeout(30, TimeUnit.SECONDS)//读取超时
                .writeTimeout(30, TimeUnit.SECONDS)//写入超时
//                .addInterceptor(new CustomInterceptor())//自定义拦截器
                .build();

//        Retrofit retrofit = new Retrofit.Builder()
//                .client(okHttpClient)
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        apiService = retrofit.create(ApiService.class);

        mContext = context;
    }

    /**
     * 异步执行GET请求
     *
     * @param builder  GET请求的构造器，其中包含了完整的URL
     * @param listener 回调接口
     */
    public static void enqueueGet(GetBuilder builder, final ResponseListener listener) {
//        if (!TextUtils.isEmpty(session_id))
//            builder.addParam("session_id", session_id.trim());
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(builder.build());
        if (!TextUtils.isEmpty(cookie))
            requestBuilder.addHeader("Cookie", cookie);
        okHttpClient.newCall(requestBuilder.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", e.toString());
                if (e instanceof UnknownHostException)
                    listener.onFailResponse("网络异常，请检查网络", -1);
                else
                    listener.onFailResponse(e.toString(), -1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                responseSuccess(response, listener);
            }
        });
    }

    /**
     * 异步执行POST请求
     *
     * @param builder  POST请求的构造器，其中包含了带参数的请求体和完整的URL
     * @param listener
     */
    public static void enqueuePost(PostBuilder builder, final ResponseListener listener) {
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(builder.getUrl()).post(builder.build());
        if (!TextUtils.isEmpty(cookie))
            requestBuilder.addHeader("Cookie", cookie);
        okHttpClient.newCall(requestBuilder.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", e.toString());
                if (e instanceof UnknownHostException)
                    listener.onFailResponse("网络异常，请检查网络", -1);
                else
                    listener.onFailResponse(e.toString(), -1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                responseSuccess(response, listener);
            }
        });
    }

    private static void responseSuccess(Response response, ResponseListener listener) {
        if (response.isSuccessful()) {
            //获取响应请求头
            String cookieValue = response.header("Set-Cookie");
            if (!TextUtils.isEmpty(cookieValue)) {
                cookie = cookieValue;
            }
            try {
                String responseMsg = response.body().string();
                JSONObject jsonObject = new JSONObject(responseMsg);
                if (jsonObject.has("ret")) {
                    if (jsonObject.optBoolean("ret", false))
                        listener.onSuccessResponse(responseMsg);
                    else
                        listener.onFailResponse(jsonObject.getString("errMsg"), -1);
                } else if (jsonObject.has("code")) {
                    int code = jsonObject.getInt("code");
                    if (code == 0) {
                        //返回成功
                        listener.onSuccessResponse(jsonObject.toString());
                    } else if (code == -100) {//需要重新登录
                        Log.e("登录过期啦！！", "code=" + code);
                        listener.onFailResponse("登录已失效", code);
                    } else {
                        if (jsonObject.has("errMsg")) {
                            listener.onFailResponse(jsonObject.getString("errMsg"), 0);
                        } else if (jsonObject.has("msg")) {
                            listener.onFailResponse(jsonObject.getString("msg"), 0);
                        } else {
                            listener.onFailResponse("未知错误", 0);
                        }
                    }
                } else {
                    //如果没有code、ret和info就返回原始的数据
                    listener.onSuccessResponse(responseMsg);
                }


            } catch (IOException | JSONException e) {
                listener.onFailResponse("data explanation error", -1);
            }
        } else {
            listener.onFailResponse(response.message(), response.code());
        }
        response.body().close();//释放资源
    }


    public static String encodeString(String key) {
        if (TextUtils.isEmpty(key)) return "";
        String newKey = "";
        try {
            newKey = URLEncoder.encode(key, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return newKey;
    }
}
