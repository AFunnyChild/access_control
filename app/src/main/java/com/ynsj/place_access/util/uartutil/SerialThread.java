package com.ynsj.place_access.util.uartutil;


import android.os.SystemClock;
import android.util.Log;

import com.ynsj.place_access.listener.OnLockerStauteListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;



//import com.licheedev.myutils.LogPlus;

/**
 * 读串口线程
 */
public class SerialThread extends Thread {


    OnLockerStauteListener onLockerStauteListener=null;


    private static final String TAG = "SerialReadThread";

    private BufferedInputStream mInputStream;

    public SerialThread(InputStream is, OnLockerStauteListener onLockerStauteListener) {
        this.onLockerStauteListener=onLockerStauteListener;
        mInputStream = new BufferedInputStream(is);
    }

    @Override
    public void run() {
        byte[] received = new byte[1024];
        int size;
        int temp=0;
        byte[] drece = new byte[]{};
        Log.e(TAG,"开始读线程 new");
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
            try {
                int available = mInputStream.available();
                if (available > 0) {
                    temp=20;
                    size = mInputStream.read(received);
                    if (size > 0) {
                        drece=addBytes(drece,drece.length,received,size);
                    }
                } else {
                    // 暂停一点时间，免得一直循环造成CPU占用率过高
                    if (temp>0)
                    {
                        temp--;
                        if(temp==0)
                        {
                            onDataReceive(drece,drece.length);
                            drece=new byte[]{};
                        }
                    }
                    SystemClock.sleep(1);
                }
            } catch (IOException e) {
                Log.e(TAG,"读取数据失败"+ e);
            }
            //Thread.yield();
        }
        Log.e(TAG, "结束读进程" );
    }
    /**
     * 处理获取到的数据
     *
     * @param received
     * @param size
     */
    private void onDataReceive(byte[] received, int size) {
        // TODO: 2018/3/22 解决粘包、分包等
//        String hexStr = ByteUtil.bytes2HexStr(received, 0, size);
        String hexStr = new String(received);
        onLockerStauteListener.getStatus(hexStr);


    }

    /**
     * 停止读线程
     */
    public void close() {

        try {
            mInputStream.close();
        } catch (IOException e) {
            Log.e(TAG,"异常"+e);
        } finally {
            super.interrupt();
        }
    }

    /**
     * 拼接byte数组
     * @param data1
     * @param data2
     * @return
     */
    public static byte[] addBytes(byte[] data1, int data1size ,byte[] data2,int data2size) {
        byte[] data3 = new byte[data1size+ data2size];
        System.arraycopy(data1, 0, data3, 0, data1size);
        System.arraycopy(data2, 0, data3, data1size, data2size);
        return data3;
    }


    public void setOnLockerStauteListener(OnLockerStauteListener onLockerStauteListener) {
        this.onLockerStauteListener = onLockerStauteListener;
    }
}
