package com.ynsj.place_access.server;

import android.util.Log;

import com.ynsj.place_access.listener.OnFpListener;
import com.ynsj.place_access.listener.OnLockerBackListener;
import com.ynsj.place_access.listener.OnLockerListener;
import com.ynsj.place_access.listener.OnLockerNewListener;
import com.ynsj.place_access.listener.OnLockerStauteListener;
import com.ynsj.place_access.listener.OnNfcListener;
import com.ynsj.place_access.model.UartModel;
import com.ynsj.place_access.serialcontrol.LockControl;
import com.ynsj.place_access.serialcontrol.NfcControl;
import com.ynsj.place_access.util.uartutil.SerialPortManager;
import com.ynsj.place_access.util.uartutil.device_pack.FingerNfcDevice;

import java.io.IOException;


public class FingerNfcServer implements OnLockerListener, OnLockerStauteListener, OnLockerNewListener {

    private static FingerNfcServer instance = null;

    public NfcControl COMA;


    public LockControl COMB;
    public SerialPortManager serialPortManager;


    OnNfcListener onNfcListener = null;
    OnFpListener onFpListener = null;
    OnLockerBackListener onLockerBackListener = null;
    OnLockerStauteListener onLockerStauteListener = null;

    public static FingerNfcServer getInstance() {
        if (instance == null)
            instance = new FingerNfcServer();
        return instance;
    }

    public FingerNfcServer() {
        try {
            COMA = new NfcControl(this);
            COMA.setPort("/dev/ttyS3");
            COMA.setBaudRate(9600);
            COMB = new LockControl(this);
            COMB.setPort("/dev/ttyS0");
            COMB.setBaudRate(9600);
            serialPortManager = new SerialPortManager(COMA, COMB, this, this, this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startport(OnNfcListener onNfcListener, OnFpListener onFpListener, OnLockerBackListener onLockerBackListener) {
        this.onNfcListener = onNfcListener;
        this.onFpListener = onFpListener;
        this.onLockerBackListener = onLockerBackListener;
//        closeport();
        Boolean mOpened = serialPortManager.openDevice();
        if (mOpened) {
            Log.e("port", "打开串口" + FingerNfcDevice.path + "成功");
        } else {
            Log.e("port", "打开串口" + FingerNfcDevice.path + "失败");
        }


    }


    public void closeport() {
        if (serialPortManager != null) {
            serialPortManager.closeDevice();
            Log.e("port", "关闭串口" + FingerNfcDevice.path + "成功");
        }
    }





    @Override
    public void getData(String msg) {

        String temp = msg;
        onNfcListener.commGetId(msg, "1");

    }

    @Override
    public void getStatus(String msg) {
        String temp = msg;
        if (!msg.contains("YTE,"))
            return;
        temp = msg.substring(4);
        try {
            UartModel uartModel = FingerNfcDevice.jsion2UartModel(temp);
            if (uartModel.getDtype().equals("fp") && onFpListener != null) {
                onFpListener.commGetFp(uartModel.getData(), uartModel.getSend_id());
            } else if (uartModel.getDtype().equals("nfc") && onNfcListener != null) {
                Log.e("id", "收到中控的nfcid");
                onNfcListener.commGetId(uartModel.getData(), uartModel.getSend_id());
            } else if (uartModel.getDtype().equals("state") && onLockerBackListener != null) {
                onLockerBackListener.LockBack(uartModel.getData(), uartModel.getSend_id());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void getNewStatus(int address, int status, int num, int lock_num) {

            onLockerBackListener.LockBack(num + "", 1 + "");
    }


}
