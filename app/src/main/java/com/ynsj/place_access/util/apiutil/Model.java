package com.ynsj.place_access.util.apiutil;


import com.ynsj.place_access.model.AdminModel;
import com.ynsj.place_access.model.BannerModel;
import com.ynsj.place_access.model.BookingMsgModel;
import com.ynsj.place_access.model.SearchBean;

/**
 * Created by Administrator on 2018/9/10.
 */

public class Model {

    private static Model model;

    public static Model getInstance() {
        if (model == null)
            model = new Model();
        return model;
    }



    /**
     * 获取轮播图
     * @param cat
     * @param listener
     */
    public void get_banner(String cat, HttpAPIModel.HttpAPIListener<BannerModel> listener){
        GetBuilder builder = new GetBuilder("/banner/get_banners");
        if (cat != null && !cat.equals(""))
            builder.addParam("cat", cat);
        HttpAPIModel.getInstance().doGet(builder, BannerModel.class, listener);
    }

    /**
     * 获取用户预约场地信息
     * @param nfc_id
     * @param date
     * @param listener
     */
    public void get_menberBookingMsg(String nfc_id , String date, HttpAPIModel.HttpAPIListener<BookingMsgModel> listener){
        PostBuilder builder = new PostBuilder("/store_device/getclass");
        if (nfc_id != null && !nfc_id.equals(""))
            builder.addParam("nfc_id", nfc_id);

        if (date != null && !date.equals(""))
            builder.addParam("date", date);
        HttpAPIModel.getInstance().doPost(builder, BookingMsgModel.class, listener);
    }



    /**
     * 请求开门
     * @param nfc_id
     * @param date
     * @param listener
     */
    public void getOpenTheDoor(String nfc_id , String date, HttpAPIModel.HttpAPIListener<BookingMsgModel> listener){
        PostBuilder builder = new PostBuilder("/store_device/getclass");
        if (nfc_id != null && !nfc_id.equals(""))
            builder.addParam("nfc_id", nfc_id);

        if (date != null && !date.equals(""))
            builder.addParam("date", date);
        HttpAPIModel.getInstance().doPost(builder, BookingMsgModel.class, listener);
    }



    /**
     * 请求开门
     * @param nfc_id
     * @param date
     * @param listener
     */
    public void find_user( String nfc_id,  HttpAPIModel.HttpAPIListener<SearchBean> listener){
        GetBuilder builder = new GetBuilder("/store_device/find_user");
        if (nfc_id != null && !nfc_id.equals(""))
            builder.addParam("nfc_id", nfc_id);
        HttpAPIModel.getInstance().doGet(builder,  SearchBean.class, listener);
    }




    public void get_admin_bynfcid(String nfc_id, HttpAPIModel.HttpAPIListener<AdminModel> listener) {
        PostBuilder postBuilder = new PostBuilder("/store_device/get_admin_bynfc");
        postBuilder.addParam("nfc_id", nfc_id);
        HttpAPIModel.getInstance().doPost(postBuilder, AdminModel.class, listener);

    }




}
