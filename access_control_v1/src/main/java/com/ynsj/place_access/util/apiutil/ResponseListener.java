package com.ynsj.place_access.util.apiutil;

/**
 * 自定义API回调接口
 * Created by admin on 2016/11/5.
 */
public interface ResponseListener {

    /**
     * 请求成功
     *
     * @param response body的返回，给一级解释
     */
    void onSuccessResponse(String response);

    /**
     * 请求失败（网络/后台/数据解释等原因）
     *
     * @param message 失败信息
     * @param status  状态码，如404等，-1表示数据解释错误，0代表后台返回错误
     */
    void onFailResponse(String message, int status);

}
