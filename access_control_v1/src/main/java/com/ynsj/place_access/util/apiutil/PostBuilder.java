package com.ynsj.place_access.util.apiutil;


import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * POST请求构造器
 * Created by admin on 2016/11/5.
 */
public class PostBuilder {

    private String url;
    private FormBody.Builder params;//用于存放POST方法的参数

    public PostBuilder(String subUrl) {
        url = ApiConfig.SERVER_IP + subUrl;
        params = new FormBody.Builder();
    }

    //添加参数
    public PostBuilder addParam(String key, String value) {
        params.add(key, value);
        return this;
    }

    //添加参数
    public PostBuilder addParam(String key, long value) {
        addParam(key, String.valueOf(value));
        return this;
    }

    //添加参数
    public PostBuilder addParam(String key, int value) {
        addParam(key, String.valueOf(value));
        return this;
    }

    //添加参数
    public PostBuilder addParam(String key, double value) {
        addParam(key, String.valueOf(value));
        return this;
    }

    //添加参数
    public PostBuilder addParam(String key, boolean value) {
        addParam(key, String.valueOf(value));
        return this;
    }

    public String getUrl() {
        return url;
    }

    public FormBody.Builder getParams(){
        return params;
    }

    public RequestBody build() {
        return params.build();
    }

}
