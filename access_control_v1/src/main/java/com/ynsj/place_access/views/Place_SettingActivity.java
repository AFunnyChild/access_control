package com.ynsj.place_access.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.ynsj.place_access.R;
import com.ynsj.place_access.listener.OnFpListener;
import com.ynsj.place_access.listener.OnLockerBackListener;
import com.ynsj.place_access.listener.OnNfcListener;
import com.ynsj.place_access.server.FingerNfcServer;
import com.ynsj.place_access.util.ServerConfig;
import com.ynsj.place_access.util.StaticDataUtil;
import com.ynsj.place_access.util.uartutil.device_pack.LockerDevice;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Place_SettingActivity extends AppCompatActivity implements OnFpListener, OnLockerBackListener, OnNfcListener {

    public String server_address;
    public String device_id;
    public String place_id;
    public String reboot_tim;
    public int is_chec;



    @BindView(R.id.serverIp)
    public EditText ip;
    @BindView(R.id.reboot_tim)
    public EditText reb_tim;

    @BindView(R.id.place_id)
    public EditText placeId;

    @BindView(R.id.device_id)
    public EditText deviceId;


    @BindView(R.id.enter_btn)
    public Button enter;


    @BindView(R.id.cancal_btn)
    public Button cancel;

    @BindView(R.id.is_open)
    public Switch is_open;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        FingerNfcServer.getInstance().startport(this,this,this);
        ButterKnife.bind(this);
        StaticDataUtil.getInstance().activityList.add(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ip.setText(  ServerConfig.getInstance().server_address);
        reb_tim.setText(ServerConfig.getInstance().reboot_tim+"");
        placeId.setText(ServerConfig.getInstance().place_id);
        deviceId.setText(ServerConfig.getInstance().device_id);

        if(ServerConfig.getInstance().is_check==1)
        {
            is_open.setChecked(true);
        }else

        {
            is_open.setChecked(false);
        }

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                server_address= ip.getText().toString();
                device_id=  deviceId.getText().toString();
                reboot_tim=reb_tim.getText().toString();
                place_id=placeId.getText().toString();

                ServerConfig.getInstance().setValue("server_address",server_address);
                ServerConfig.getInstance().setValue("device_id",device_id);
                ServerConfig.getInstance().setValue("reboot_tim",reboot_tim);
                ServerConfig.getInstance().setValue("place_id",place_id);

                if (is_open.isChecked())
                {
                    is_chec=1;
                    ServerConfig.getInstance().setValue("is_check",is_chec+"");
                    FingerNfcServer.getInstance().serialPortManager.sendData(LockerDevice.open_end(1, 1));

                }
                else
                {
                    is_chec=0;
                    ServerConfig.getInstance().setValue("is_check",is_chec+"");
                }
                Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });






    }


    @Override
    public void commGetFp(String Fp, String send_id) {

    }

    @Override
    public void LockBack(String id, String send_id) {

    }

    @Override
    public void commGetId(String id, String send_id) {


        Log.e("收到id", "刷卡commGetId: "+id );
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();

//       FingerNfcServer.getInstance().closeport();
    }

}
