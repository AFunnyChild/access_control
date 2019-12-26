package com.ynsj.place_access.model;

public class UartModel {

    private String rece;
    private String rece_id;
    private String send;
    private String send_id;
    private String dtype;
    private String data;


    public UartModel (String rece,String rece_id,String send,String send_id,String dtype,String data)
    {
        this.rece=rece;
        this.rece_id=rece_id;
        this.send=send;
        this.send_id=send_id;
        this.dtype=dtype;
        this.data=data;

    }
    public UartModel()
    {}
    public String getRece() {
        return rece;
    }

    public void setRece(String rece) {
        this.rece = rece;
    }

    public String getRece_id() {
        return rece_id;
    }

    public void setRece_id(String rece_id) {
        this.rece_id = rece_id;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public String getSend_id() {
        return send_id;
    }

    public void setSend_id(String send_id) {
        this.send_id = send_id;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String type) {
        this.dtype = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
