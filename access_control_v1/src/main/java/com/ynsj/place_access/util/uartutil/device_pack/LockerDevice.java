package com.ynsj.place_access.util.uartutil.device_pack;

import android.util.Log;

import com.google.gson.Gson;
import com.ynsj.place_access.model.UartModel;
import com.ynsj.place_access.util.apiutil.JacksonObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class LockerDevice {

    public static final String path = "/dev/ttyS1";
    public static final String baudrate = "115200";


    public static UartModel jsion2UartModel(String jsion) throws IOException {
        UartModel responseObject = JacksonObjectMapper.getInstance().readValue(jsion, UartModel.class);
        return responseObject;
    }

    /**
     * 指令协议CMD
     *
     * @param up
     * @param upid
     * @param down
     * @param downid
     * @param opt
     * @return
     */
    public static byte[] sendOpt(String up, String upid, String down, String downid, String opt) {
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
        for (int i = 0; i < bytes.length; i++) {
            value += ~bytes[i];
        }
        checkCode = String.valueOf(value);
        String temp = head + "," + jsonValue.toString() + enterCode;
        Log.e("==k2==", "sendOpt: " + temp);
        byte[] protocolBytes = temp.getBytes();
        return protocolBytes;
    }


    /**
     * 发送指令
     * dtype ：
     * opt    开门   "5,1"
     * 参数1：柜子编号   参数2：开关指令 1开门
     * <p>
     * state  查询状态  "0"
     * 参数1：0 表示查询所有柜子状态
     * N 表示查询特定编号的柜子
     *
     * @param up     上位机类型
     * @param upid   上位机编号
     * @param down   下位机类型
     * @param downid 下位机编号
     * @param dtype  数据类型
     * @param data   数据内容
     * @return
     */
    public static byte[] sendCommend(String up, String upid, String down, String downid, String dtype, String data) {
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
            jsonValue.put("dtype", dtype);
            jsonValue.put("data", data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        length = String.valueOf(jsonValue.length());
        int value = 0;
        byte[] bytes = jsonValue.toString().getBytes();//去掉双引号
        for (int i = 0; i < bytes.length; i++) {
            value += ~bytes[i];
        }
        checkCode = String.valueOf(value);
        String temp = head + "," + jsonValue.toString() + enterCode;
        Log.e("==Locker==", "sendCommend: " + temp);
        byte[] protocolBytes = temp.getBytes();
        return protocolBytes;
    }


    /**
     * 开锁命令，单开一下
     * @param cmd_number
     * @param box_number
     * @return
     */
    public static byte[] open(int cmd_number,int box_number) {
        byte[] head = {(byte) 0x57, (byte) 0x4B, (byte) 0x4C, (byte) 0x59};
        byte[]length=hex2Bytes(9);
        byte[]cmd_num=hex2Bytes(cmd_number);
        byte[]cmd={(byte)0x82};
        byte[]box_num=hex2Bytes(box_number);
        byte[]cmd_byte=byteMergerAll(head,length,cmd_num,cmd,box_num);
        byte xor=getXor(cmd_byte);
        byte[]xorf=new byte[1];
        xorf[0]=xor;
        cmd_byte=byteMergerAll(cmd_byte,xorf);
         String aa=  bytesToHexString(cmd_byte,cmd_byte.length);
        Log.e("send", "open: "+aa );
        return cmd_byte;
    }


    /**
     * 持续输出，用于开门禁
     * @param cmd_number
     * @param box_number
     * @return
     */
    public static byte[] open_start(int cmd_number,int box_number) {
        byte[] head = {(byte) 0x57, (byte) 0x4B, (byte) 0x4C, (byte) 0x59};
        byte[]length=hex2Bytes(9);
        byte[]cmd_num=hex2Bytes(cmd_number);
        byte[]cmd={(byte)0x88};
        byte[]box_num=hex2Bytes(box_number);
        byte[]cmd_byte=byteMergerAll(head,length,cmd_num,cmd,box_num);
        byte xor=getXor(cmd_byte);
        byte[]xorf=new byte[1];
        xorf[0]=xor;
        cmd_byte=byteMergerAll(cmd_byte,xorf);
        String aa=  bytesToHexString(cmd_byte,cmd_byte.length);
        Log.e("send", "start: "+aa );
        return cmd_byte;
    }



    /**
     * 关闭持续输出，用于关门禁
     * @param cmd_number
     * @param box_number
     * @return
     */
    public static byte[] open_end(int cmd_number,int box_number) {
        byte[] head = {(byte) 0x57, (byte) 0x4B, (byte) 0x4C, (byte) 0x59};
        byte[]length=hex2Bytes(9);
        byte[]cmd_num=hex2Bytes(cmd_number);
        byte[]cmd={(byte)0x89};
        byte[]box_num=hex2Bytes(box_number);
        byte[]cmd_byte=byteMergerAll(head,length,cmd_num,cmd,box_num);
        byte xor=getXor(cmd_byte);
        byte[]xorf=new byte[1];
        xorf[0]=xor;
        cmd_byte=byteMergerAll(cmd_byte,xorf);
        String aa=  bytesToHexString(cmd_byte,cmd_byte.length);
        Log.e("send", "end: "+aa );
        return cmd_byte;
    }


    public static String bytesToHexString(byte[] bArray, int length) {
        StringBuffer sb = new StringBuffer(length);
        String sTemp;
        for (int i = 0; i < length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }
    /**
     * 将int数值转换为占四个字节的byte数组，本方法适用于(低位在前，高位在后)的顺序。 和bytesToInt（）配套使用
     * @param value
     *            要转换的int值
     * @return byte数组
     */
    public static byte[] intToBytes( int value )
    {
        byte[] src = new byte[4];
        src[3] =  (byte) ((value>>24) & 0xFF);
        src[2] =  (byte) ((value>>16) & 0xFF);
        src[1] =  (byte) ((value>>8) & 0xFF);
        src[0] =  (byte) (value & 0xFF);
        return src;
    }
    /**
     * 将int数值转换为占四个字节的byte数组，本方法适用于(高位在前，低位在后)的顺序。  和bytesToInt2（）配套使用
     */
    public static byte[] intToBytes2(int value)
    {
        byte[] src = new byte[4];
        src[0] = (byte) ((value>>24) & 0xFF);
        src[1] = (byte) ((value>>16)& 0xFF);
        src[2] = (byte) ((value>>8)&0xFF);
        src[3] = (byte) (value & 0xFF);
        return src;
    }

    public static byte[] hex2Bytes(int num){


        String src = Long.toHexString(num);
      if(src.length()%2!=0)
      {
        src="0"+src;
      }
        byte[] res = new byte[src.length()/2];
        char[] chs = src.toCharArray();
        for(int i=0,c=0; i<chs.length; i+=2,c++){
            res[c] = (byte) (Integer.parseInt(new String(chs,i,2), 16));
        }

        return res;
    }

    public static byte getXor(byte[] datas) {

        byte temp = datas[0];

        for (int i = 1; i < datas.length; i++) {
            temp ^= datas[i];
        }
        return temp;
    }

    private static byte[] byteMergerAll(byte[]... values) {
        int length_byte = 0;
        for (int i = 0; i < values.length; i++) {
            length_byte += values[i].length;
        }
        byte[] all_byte = new byte[length_byte];
        int countLength = 0;
        for (int i = 0; i < values.length; i++) {
            byte[] b = values[i];
            System.arraycopy(b, 0, all_byte, countLength, b.length);
            countLength += b.length;
        }
        return all_byte;
    }

    /**
     * 发送指令
     * dtype ：
     * opt    开门   "5,1"
     * 参数1：柜子编号   参数2：开关指令 1开门
     * <p>
     * state  查询状态  "0"
     * 参数1：0 表示查询所有柜子状态
     * N 表示查询特定编号的柜子
     *
     * @param up     上位机类型
     * @param upid   上位机编号
     * @param down   下位机类型
     * @param downid 下位机编号
     * @param dtype  数据类型
     * @param data   数据内容
     * @return
     */
    public static byte[] UartModel2json(String up, String upid, String down, String downid, String dtype, String data) {
        String head = "YTE";
        String enterCode = "\r\n";//
        UartModel example1 = new UartModel(up, upid, down, downid, dtype, data);
        Gson gson = new Gson();
        String json = gson.toJson(example1);
        String temp = head + "," + json + enterCode;
        Log.e("==Locker==", "sendCommend: " + temp);
        byte[] protocolBytes = json.getBytes();
        return protocolBytes;
//        return null;
    }


}
