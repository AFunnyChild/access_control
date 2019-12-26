package com.ynsj.place_access.util;
//import java.awt;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;

public class ServerConfig {
	private static ServerConfig instance = null;


	public String server_address;
	public String device_id;
	public Properties prop;
	public String reboot_tim;
	public String place_id;
	public int is_check;



	
	public static ServerConfig getInstance()
	{
		if (instance == null)
			instance = new ServerConfig();
		return instance;
	}

	public void loadnew() throws IOException {

		File path=new File("/sdcard/config");
		if  (!path .exists()  && !path .isDirectory())
		{
			path .mkdir();
		}
		File file=new File("/sdcard/config/access.properties");
		if  (!file .exists()  && !file .isDirectory())
		{
			try {
				file .createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			prop=new Properties();
			device_id="02";
			prop.setProperty ("device_id","02");
			server_address="192.168.0.13";
			prop.setProperty ("server_address","192.168.0.13");
			reboot_tim="06:00";
			prop.setProperty ("reboot_tim","11:50");
			place_id="01";
			prop.setProperty ("place_id","01");
			is_check=0;
			prop.setProperty("is_check","0");

			OutputStream fos = new FileOutputStream(file);
			prop.store(fos, "new file");
			fos.flush();
		}else{
			prop = new Properties();
			FileInputStream fis = new FileInputStream(file);//"server.properties");
			prop.load(fis);
			server_address= prop.getProperty("server_address");
			device_id=prop.getProperty("device_id");
			reboot_tim=prop.getProperty("reboot_tim");
			place_id=prop.getProperty("place_id");
			if(prop.getProperty("is_check")==null)
			{
				is_check=0;
			}else
			{
				is_check=Integer.parseInt(prop.getProperty("is_check"));
			}
		}
	}

	public void setValue(String key, String value) {
		try {
			prop.setProperty (key,value);
			File file = new File("/sdcard/config/access.properties");
			OutputStream fos = new FileOutputStream(file);
			prop.store(fos, "Update '" + key + "' value");
			fos.flush();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}




	public void log(String log_content)
	{
		Log.e("log", log_content );
	}	
}
