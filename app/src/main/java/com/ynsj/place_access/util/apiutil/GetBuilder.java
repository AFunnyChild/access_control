package com.ynsj.place_access.util.apiutil;

import android.support.v4.util.ArrayMap;

import java.util.Iterator;

/**
 * Get请求构造器
 * Created by Administrator on 2018/5/3.
 */

public class GetBuilder {

    private String url;
    private ArrayMap<String, String> params;//用户存放GET方法的参数

    public GetBuilder(String subUrl){
        url = ApiConfig.SERVER_IP + subUrl;
        params = new ArrayMap<>();
    }

    //添加参数
    public GetBuilder addParam(String key, String value){
        params.put(key, ApiUtil.encodeString(value));
        return this;
    }

    public GetBuilder addParam(String key, long value){
        addParam(key,String.valueOf(value));
        return this;
    }

    public GetBuilder addParam(String key, int value){
        addParam(key,String.valueOf(value));
        return this;
    }

    public GetBuilder addParam(String key, boolean value){
        addParam(key,String.valueOf(value));
        return this;
    }

    //拼GET方法的完整URL
    private String getParams(){
        //添加url参数
        Iterator<String> iterator = params.keySet().iterator();
        StringBuffer sb = null;
        while (iterator.hasNext()){
            String key = iterator.next();
            String value = params.get(key);
            if (sb == null){
                sb = new StringBuffer();
                if (!url.contains("?")){
                    sb.append("?");
                }else {
                    sb.append("&");
                }

            }else {
                sb.append("&");
            }
            sb.append(key);
            sb.append("=");
            sb.append(value);
        }
        if (sb != null){
            return sb.toString();
        }else {
            return "";
        }
    }

    public String build(){
        if (params.size() > 0)
            return url + getParams();
        return url;
    }
}
