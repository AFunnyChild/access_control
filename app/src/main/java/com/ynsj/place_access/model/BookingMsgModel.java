package com.ynsj.place_access.model;

import java.util.List;

public class BookingMsgModel {


    private boolean ret;
    private String errMsg;
    private List<BannerModel.Info> info;

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public boolean getRet() {
        return ret;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setInfo(List<BannerModel.Info> info) {
        this.info = info;
    }

    public List<BannerModel.Info> getInfo() {
        return info;
    }
/**
 * Copyright 2019 bejson.com
 */


    /**
     * Auto-generated: 2019-01-22 10:55:41
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public static class Info{
        private String start_time;
        private String end_time;
        private String user_name;
        private int place;

        public String getNfc_id() {
            return nfc_id;
        }

        public void setNfc_id(String nfc_id) {
            this.nfc_id = nfc_id;
        }

        private String nfc_id;


        public String getUser_avatar() {
            return user_avatar;
        }

        public void setUser_avatar(String user_avatar) {
            this.user_avatar = user_avatar;
        }

        private String user_avatar;

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public int getPlace() {
            return place;
        }

        public void setPlace(int place) {
            this.place = place;
        }




    }


}

