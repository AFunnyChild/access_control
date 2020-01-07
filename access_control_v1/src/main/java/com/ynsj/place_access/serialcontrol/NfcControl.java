package com.ynsj.place_access.serialcontrol;



import com.ynsj.place_access.listener.OnLockerListener;
import com.ynsj.place_access.util.uartutil.ComAssistant.MyFunc;
import com.ynsj.place_access.util.uartutil.ComAssistant.SerialHelper;
import com.ynsj.place_access.util.uartutil.bean.ComBean;

public class NfcControl extends SerialHelper {


    OnLockerListener onLockerListener=null;



    //		public NfcControl(String sPort, String sBaudRate){
//			super(sPort, sBaudRate);
//		}
    public NfcControl(OnLockerListener onLockerListener){
        this.onLockerListener=onLockerListener;
    }


    protected void onDataReceived(final ComBean ComRecData)
    {
        //数据接收量大或接收时弹出软键盘，界面会卡顿,可能和6410的显示性能有关
        //直接刷新显示，接收数据量大时，卡顿明显，但接收与显示同步。
        //用线程定时刷新显示可以获得较流畅的显示效果，但是接收数据速度快于显示速度时，显示会滞后。
        //最终效果差不多-_-，线程定时刷新稍好一些。
//            DispQueue.AddQueue(ComRecData);//线程定时刷新显示(推荐)

        byte[] data=ComRecData.bRec;

        StringBuilder strBuilder=new StringBuilder();
        int j=data.length;
        for (int i = j-1; i >=0; i--)
        {
            strBuilder.append(MyFunc.Byte2Hex(data[i]));
//            strBuilder.append(" ");
        }
        onLockerListener.getData(strBuilder.toString());


    }
}