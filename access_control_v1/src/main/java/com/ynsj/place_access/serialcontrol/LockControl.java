package com.ynsj.place_access.serialcontrol;



import com.ynsj.place_access.listener.OnLockerNewListener;
import com.ynsj.place_access.util.uartutil.ComAssistant.SerialHelper;
import com.ynsj.place_access.util.uartutil.bean.ComBean;
import com.ynsj.place_access.util.uartutil.device_pack.LockerDevice;

public class LockControl extends SerialHelper {


    OnLockerNewListener onLockerNewListener = null;


    //		public NfcControl(String sPort, String sBaudRate){
//			super(sPort, sBaudRate);
//		}
    public LockControl(OnLockerNewListener onLockerNewListener) {
        this.onLockerNewListener = onLockerNewListener;
    }


    protected void onDataReceived(final ComBean ComRecData) {
        //数据接收量大或接收时弹出软键盘，界面会卡顿,可能和6410的显示性能有关
        //直接刷新显示，接收数据量大时，卡顿明显，但接收与显示同步。
        //用线程定时刷新显示可以获得较流畅的显示效果，但是接收数据速度快于显示速度时，显示会滞后。
        //最终效果差不多-_-，线程定时刷新稍好一些。
//            DispQueue.AddQueue(ComRecData);//线程定时刷新显示(推荐)

        String hexStr = new String(ComRecData.bRec);
        byte[] data=ComRecData.bRec;
        byte[] temp=new byte[data.length-1];

        int cmd_num=0;
        int status=0;
        int num=0;
        int lock_status=0;



        for(int i=0;i<temp.length;i++)
        {
            temp[i]=data[i];
        }
        if (data[data.length-1]== LockerDevice.getXor(temp))
        {
            byte[] tem=new byte[1];
            tem[0]=data[5];
            cmd_num= Integer.parseInt(LockerDevice.bytesToHexString(tem,1), 16);
            tem[0]=data[7];
            status=Integer.parseInt(LockerDevice.bytesToHexString(tem,1), 16);
            tem[0]=data[8];
            num=Integer.parseInt(LockerDevice.bytesToHexString(tem,1), 16);
            tem[0]=data[9];
           lock_status=Integer.parseInt(LockerDevice.bytesToHexString(tem,1), 16);

        }


        onLockerNewListener.getNewStatus(cmd_num,status,num,lock_status);


    }
}