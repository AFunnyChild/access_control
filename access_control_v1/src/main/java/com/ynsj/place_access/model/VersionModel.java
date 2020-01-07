package com.ynsj.place_access.model;

public class VersionModel {

    private boolean ret;
    private String errMsg;
    private VersionModel.Info info;

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

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public static class Info {
        private float version;
        private String dowload_url;

        public float getVersion() {
            return version;
        }

        public void setVersion(float version) {
            this.version = version;
        }

        public String getDowload_url() {
            return dowload_url;
        }

        public void setDowload_url(String dowload_url) {
            this.dowload_url = dowload_url;
        }
    }

}
