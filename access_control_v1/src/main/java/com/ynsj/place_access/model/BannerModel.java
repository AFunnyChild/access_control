package com.ynsj.place_access.model;

import java.util.List;

public class BannerModel /**
 * Copyright 2019 bejson.com
 */


        /**
         * Auto-generated: 2019-01-22 10:55:41
         *
         * @author bejson.com (i@bejson.com)
         * @website http://www.bejson.com/java2pojo/
         */


{
    private boolean ret;
    private String errMsg;
    private List<Info> info;

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

    public void setInfo(List<Info> info) {
        this.info = info;
    }

    public List<Info> getInfo() {
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
    public static class Info {

        private String link;
        private int id;
        private String url;

        public void setLink(String link) {
            this.link = link;
        }

        public String getLink() {
            return link;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

    }


}