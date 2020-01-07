package com.ynsj.place_access.model;


/**
 * Auto-generated: 2019-06-29 21:58:21
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class SearchBean {

    private boolean ret;
    private String errMsg;
    private Info info;
    private int code;
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

    public void setInfo(Info info) {
        this.info = info;
    }
    public Info getInfo() {
        return info;
    }

    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }
    /**
     * Copyright 2019 bejson.com
     */


    /**
     * Auto-generated: 2019-06-29 21:58:21
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public class Info {

        private int is_sales;
        private String finger_id;
        private String finger_id2;
        private long bath_minutes;
        private String finger_id3;
        private String mobile;
        private String user_nicename;
        private int id;
        private String avatar;
        private String nfc_id;
        public void setIs_sales(int is_sales) {
            this.is_sales = is_sales;
        }
        public int getIs_sales() {
            return is_sales;
        }

        public void setFinger_id(String finger_id) {
            this.finger_id = finger_id;
        }
        public String getFinger_id() {
            return finger_id;
        }

        public void setFinger_id2(String finger_id2) {
            this.finger_id2 = finger_id2;
        }
        public String getFinger_id2() {
            return finger_id2;
        }

        public void setBath_minutes(long bath_minutes) {
            this.bath_minutes = bath_minutes;
        }
        public long getBath_minutes() {
            return bath_minutes;
        }

        public void setFinger_id3(String finger_id3) {
            this.finger_id3 = finger_id3;
        }
        public String getFinger_id3() {
            return finger_id3;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
        public String getMobile() {
            return mobile;
        }

        public void setUser_nicename(String user_nicename) {
            this.user_nicename = user_nicename;
        }
        public String getUser_nicename() {
            return user_nicename;
        }

        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
        public String getAvatar() {
            return avatar;
        }

        public void setNfc_id(String nfc_id) {
            this.nfc_id = nfc_id;
        }
        public String getNfc_id() {
            return nfc_id;
        }

    }

}