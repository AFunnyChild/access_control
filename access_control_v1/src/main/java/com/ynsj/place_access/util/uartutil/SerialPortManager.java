package com.ynsj.place_access.util.uartutil;

import android.serialport.SerialPort;
import android.util.Log;


import com.ynsj.place_access.listener.OnLockerListener;
import com.ynsj.place_access.listener.OnLockerNewListener;
import com.ynsj.place_access.listener.OnLockerStauteListener;
import com.ynsj.place_access.serialcontrol.LockControl;
import com.ynsj.place_access.serialcontrol.NfcControl;

import java.io.OutputStream;

public class SerialPortManager {

    public String TAG = "SerialPortManager";
    public Device mDevice;
    private SerialPort mSerialPort;
    private OutputStream mOutputStream;
    private SerialThread mReadThread;
    private OnLockerListener onLockerListener;
    private OnLockerStauteListener onLockerStauteListener;
    private OnLockerNewListener onLockerNewListener;

    //用于接收nfc刷卡信息
    private NfcControl COMA;
    //用于操作门禁
    private LockControl COMB;


    public SerialPortManager(NfcControl COMA, LockControl COMB,OnLockerListener onLockerListener,OnLockerStauteListener onLockerStauteListener,OnLockerNewListener onLockerNewListener) {
        this.COMA = COMA;
        this.COMB =COMB;
        this.onLockerListener = onLockerListener;
        this.onLockerStauteListener=onLockerStauteListener;
        this.onLockerNewListener=onLockerNewListener;

    }

    /**
     * 初始化串口设备
     */
    public Boolean openDevice() {
        if (COMA.isOpen()) {
            COMA.close();
        }
        if (COMB.isOpen()) {
            COMB.close();
        }


        try {
            COMB=new LockControl(this.onLockerNewListener);
            COMB.setPort("/dev/ttyS0");
            COMB.setBaudRate(9600);
            COMB.open();
        } catch (Throwable tr) {
            Log.e(TAG, "COMB打开串口失败" + tr);
            COMB.close();
        }

        try {
            COMA=new NfcControl(this.onLockerListener);
            COMA.setPort("/dev/ttyS3");
            COMA.setBaudRate(9600);
            COMA.open();
        } catch (Throwable tr) {
            Log.e(TAG, "COMA打开串口失败" + tr);
            COMA.close();

        }



        if (COMA.isOpen()&&COMB.isOpen())
        {
            return true;
        }else
        {
            return false;
        }

    }

    /**
     * 关闭串口设备
     */
    public void closeDevice() {

        if (COMA.isOpen()) {
            COMA.close();
        }
        if (COMB.isOpen()) {
            COMB.close();
        }


    }


    /**
     * 发送设备信息
     * @param data
     */
    public void sendData(byte[] data) {
        COMB.send(data);

    }


}
