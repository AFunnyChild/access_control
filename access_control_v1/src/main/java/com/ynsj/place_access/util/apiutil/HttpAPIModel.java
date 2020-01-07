package com.ynsj.place_access.util.apiutil;


/**
 * Created by Administrator on 2018/7/12.
 */

public class HttpAPIModel extends BaseModel{

    private static HttpAPIModel httpAPIModel;

    public static HttpAPIModel getInstance() {
        if (httpAPIModel == null)
            httpAPIModel = new HttpAPIModel();
        return httpAPIModel;
    }

    public <T> void doPost(PostBuilder postBuilder, final Class<T> clazz , final HttpAPIListener<T> httpAPIListener){
        LogHelper.e("ATU","post提交地址"+postBuilder.getUrl());
        ApiUtil.enqueuePost(postBuilder, new ResponseListener() {
            @Override
            public void onSuccessResponse(String response) {
                LogHelper.e("ATU HttpAPIModel doPost","成功返回的值"+response);
                try {
                    final T responseObject = JacksonObjectMapper.getInstance().readValue(response, clazz);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            httpAPIListener.onSuccessResponse(responseObject);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            httpAPIListener.onFailResponse("数据解析错误");
                        }
                    });
                }

            }
            @Override
            public void onFailResponse(final String message, int status) {
                LogHelper.e("ATU HttpAPIModel doPost","失败返回"+message+" 状态码"+status);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        httpAPIListener.onFailResponse(message);
                    }
                });
            }
        });
    }

    public <T> void doGet(GetBuilder getBuilder, final Class<T> clazz , final HttpAPIListener<T> httpAPIListener){
        ApiUtil.enqueueGet(getBuilder, new ResponseListener() {
            @Override
            public void onSuccessResponse(String response) {
                LogHelper.e("ATU HttpAPIModel doGet","成功返回的值"+response);
                try {
                    final T responseObject = JacksonObjectMapper.getInstance().readValue(response, clazz);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            httpAPIListener.onSuccessResponse(responseObject);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            httpAPIListener.onFailResponse("数据解析错误");
                        }
                    });
                }
            }

            @Override
            public void onFailResponse(final String message, int status) {
                LogHelper.e("ATU HttpAPIModel doPost","失败返回的值"+message+" 状态码"+status);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        httpAPIListener.onFailResponse(message);
                    }
                });
            }
        });
    }

    public interface HttpAPIListener<T>{
        void onSuccessResponse(T responseObject);
        void onFailResponse(String msg);
    }
}
