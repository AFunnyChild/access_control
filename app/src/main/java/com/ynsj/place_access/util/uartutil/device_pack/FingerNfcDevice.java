package com.ynsj.place_access.util.uartutil.device_pack;

import android.util.Log;

import com.ynsj.place_access.model.UartModel;
import com.ynsj.place_access.util.apiutil.JacksonObjectMapper;
import com.ynsj.place_access.util.uartutil.SerialPortManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class FingerNfcDevice {
    public static final String YTE_PRO_CCON = "ccon";
    public static final String YTE_PRO_LURU = "luru";
    public static final String YTE_PRO_CAMERA = "camera";
    public static final String YTE_PRO_GATE = "gate";
    public static final String YTE_PRO_AIRC = "airc";
    public static final String YTE_PRO_STORAGE = "storage";
    public static final String YTE_PRO_NFC= "nfc";
    public static final String YTE_PRO_FP= "FP";
    //开闸门
    public static final int YTE_COM_OPEN_GATE =1;
    public static final int YTE_COM_CLOSE_GATE =2;
    //读NFC
    public static final int YTE_COM_OPEN_NFC =3;
    public static final int YTE_COM_CLOSE_NFC =4;
    //读指纹
    public static final int YTE_COM_OPEN_FP =5;
    public static final int YTE_COM_CLOSE_FP =6;
    //开摄像头
    public static final int YTE_COM_OPEN_CAMERA =7;
    public static final int YTE_COM_CLOSE_CAMERA =8;
    //开门
    public static final int YTE_COM_OPEN_DOOR =9;
    public static final int YTE_COM_CLOSE_DOOR =10;
    //下发数据
    public static final int YTE_DOWNLOAD_DATE =11;
    public static final int YTE_UPLOAD_DATE =12;
    //开空调
    public static final int YTE_COM_OPEN_AIRC =13;
    public static final int YTE_COM_CLOSE_AIRC =14;
    //通信测试
    public static final int YTE_COM_TEST =201;
    public static final int YTE_COM_TESTACK =202;

    public static final String path ="/dev/ttyS3";
    public static final String baudrate="9600";

    /**
     * 解析jsion 读取ID
     * @param jsion
     * @return
     * @throws JSONException
     * @throws IOException
     */
    public static String Analysis(String jsion) throws JSONException, IOException {
        JSONObject jsonValue = new JSONObject(jsion);
        String ID=jsonValue.getString("opt");
        return ID;
    }



    /**
     * 解析jsion转化为对象
     * @param jsion
     * @return
     * @throws IOException
     */
    public static UartModel jsion2UartModel(String jsion) throws IOException {
        UartModel responseObject = JacksonObjectMapper.getInstance().readValue(jsion, UartModel.class);
        return  responseObject;
    }


    /**
     *
     * 1：开闸门			2：关闸门
     * 3：读NFC			4：关NFC
     * 5：读指纹			6：关指纹
     * 7：开摄像头			8：关摄像头
     * 9：开门				10：关门
     * 11：下发数据			12：上传数据
     * 13：开空调			14：关空调
     * 15：闸机进门开		16：闸机进门关
     * 17：闸机出门开		18：闸机出门关
     * 19:下发指纹数据
     * 21:指纹校验成功		22:指纹校验失败
     * 23:删除全部指纹数据
     * 发送指令
     * @param up：上位机名称
     * @param upid：上位机ID
     * @param down：下位机名称
     * @param downid：下位机ID
     * @param opt：命令编号
     * @return
     */
    public static byte[] sendCommend(String up, String upid, String down, String downid, String opt){
        String head = "YTE";
        String length = "";
        String checkCode = "";
        String endCode = "0x55";
        String enterCode = "\r\n";//
        JSONObject jsonValue = new JSONObject();
        try {
            jsonValue.put("rece", up);
            jsonValue.put("rece_id", upid);
            jsonValue.put("send", down);
            jsonValue.put("send_id", downid);
            jsonValue.put("opt", opt);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        length = String.valueOf(jsonValue.length());
        int value = 0;
        byte[] bytes = jsonValue.toString().getBytes();//去掉双引号
        for (int i = 0;i<bytes.length;i++){
            value += ~bytes[i];
        }
        checkCode = String.valueOf(value);
        String temp=head + "," + jsonValue.toString()+ enterCode;
        Log.e("==k2==", "sendCommend: "+temp );
        byte[] protocolBytes = temp.getBytes();
        return protocolBytes;
    }


    /**
     * 发送uart方法
     * 发送身份验证模块指令
     * @param serialPortManager
     * @param datas
     */
    public static void uartSend(SerialPortManager serialPortManager, byte[] datas)
    {

        if (serialPortManager != null) {
            try {
                serialPortManager.sendData(datas);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
