package com.ynsj.place_access.model;

import java.util.List;

public class AdminModel {
    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public List<Info> getInfo() {
        return info;
    }

    public void setInfo(List<Info> info) {
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private boolean ret;
    private String errMsg;
    private List<Info> info;
    private int code;



    public static class Info {

        private int id;
        private String name;
        private String nfc_id;
        private long create_time;
        private long update_time;
        private int type;
        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setNfc_id(String nfc_id) {
            this.nfc_id = nfc_id;
        }
        public String getNfc_id() {
            return nfc_id;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }
        public long getCreate_time() {
            return create_time;
        }

        public void setUpdate_time(long update_time) {
            this.update_time = update_time;
        }
        public long getUpdate_time() {
            return update_time;
        }

        public void setType(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }

    }
}
