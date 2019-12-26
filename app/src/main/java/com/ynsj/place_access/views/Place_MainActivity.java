package com.ynsj.place_access.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.android.tu.loadingdialog.LoadingDailog;
import com.tencent.bugly.Bugly;
import com.ynsj.place_access.R;
import com.ynsj.place_access.dialog.managerDialog;
import com.ynsj.place_access.listener.OnFpListener;
import com.ynsj.place_access.listener.OnLockerBackListener;
import com.ynsj.place_access.listener.OnNfcListener;
import com.ynsj.place_access.model.AdminModel;
import com.ynsj.place_access.model.SearchBean;
import com.ynsj.place_access.server.FingerNfcServer;
import com.ynsj.place_access.server.ShutDownDeviceService;
import com.ynsj.place_access.util.ServerConfig;
import com.ynsj.place_access.util.StaticDataUtil;
import com.ynsj.place_access.util.apiutil.ApiUtil;
import com.ynsj.place_access.util.apiutil.HttpAPIModel;
import com.ynsj.place_access.util.apiutil.Model;
import com.ynsj.place_access.util.uartutil.device_pack.LockerDevice;
import com.ys.myapi.MyManager;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Place_MainActivity extends AppCompatActivity implements OnFpListener, OnLockerBackListener, OnNfcListener, com.ynsj.place_access.dialog.managerDialog.OnMessageListener {


   public Timer timer=new Timer();


   @BindView(R.id.place_setting)
    Button button;

   //管理员设置对话框
   public managerDialog managerDialog;

    private LoadingDailog.Builder loadBuilder;

    //加载loading对话框
    private LoadingDailog dialog;


    //调试id
    public String bugly_id="34926d7df6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ApiUtil.initOkHttp(this,"");

        StaticDataUtil.getInstance().manager= MyManager.getInstance(this);
        Bugly.init(getApplicationContext(), bugly_id, false);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ApiUtil.initOkHttp(this, "");//初始化OkHttp
        StaticDataUtil.getInstance().activityList.add(this);
        FingerNfcServer.getInstance().startport(this, this, this);

        loadBuilder=new LoadingDailog.Builder(this)
                .setMessage("加载中...")
                .setCancelable(false)
                .setCancelOutside(false);
        dialog=loadBuilder.create();
        Intent intentOne = new Intent(this, ShutDownDeviceService.class);
        startService(intentOne);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
               showManager();
            }
        });




    }


    @Override
    public void commGetFp(String Fp, String send_id) {

    }

    @Override
    public void LockBack(String id, String send_id) {

    }


    public void showManager() {


        managerDialog=new managerDialog(Place_MainActivity.this,R.style.dialog,"管理员验证","请刷手环",R.mipmap.icon_manager1,this);
        managerDialog.show();
//        Intent intant = new Intent(AccessMain_Activity.this, AccessSetting_Activity.class);
//        intant.putExtra("id", 1);
//        startActivity(intant);


    }


    @Override
    public void commGetId(String id, String send_id) {



        if(ServerConfig.getInstance().is_check!=1) {
            Model.getInstance().find_user(id, listener);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(Place_MainActivity.this, "刷卡", Toast.LENGTH_SHORT).show();

                }
            });
        }else
        {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(Place_MainActivity.this, "常开模式", Toast.LENGTH_SHORT).show();

                }
            });
        }

    }
   public HttpAPIModel.HttpAPIListener<SearchBean> listener=new HttpAPIModel.HttpAPIListener<SearchBean>() {
        @Override
        public void onSuccessResponse(SearchBean responseObject) {
            opendoor(5);
        }

        @Override
        public void onFailResponse(final String msg) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(Place_MainActivity.this, msg, Toast.LENGTH_SHORT).show();

                }
            });
        }
    };




    public void opendoor(int time) {

        FingerNfcServer.getInstance().serialPortManager.sendData(LockerDevice.open_end(1, 1));
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Place_MainActivity.this, "开门", Toast.LENGTH_SHORT).show();

            }
        });


        Log.e("msg", "run: " + "开门");
        if (timer != null) {
            timer.cancel();
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    FingerNfcServer.getInstance().serialPortManager.sendData(LockerDevice.open_start(1, 1));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Place_MainActivity.this, "关门", Toast.LENGTH_SHORT).show();

                        }
                    });
                    Log.e("msg", "run: " + "关门");
                }
            }, 1000 * time);

        }
    }

    @Override
    public void GetFN(String type, String message) {

        if (type.equals("FP")) {

        } else {
            Log.e("message", "收到NFC");
            if (message.equals("E09BB4BC"))
            {
                managerDialog.cancel();
                Intent intant=new Intent(Place_MainActivity.this,Place_SettingActivity.class);
                startActivity(intant);
            }else {
                managerDialog.cancel();
                Model.getInstance().get_admin_bynfcid(message, listener1);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.show();
                    }
                });
            }
        }

    }



    @Override
    public void GetFinsh() {

        FingerNfcServer.getInstance().startport(this,this,this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //显示是启动定时
//        startAD();
        FingerNfcServer.getInstance().startport(this,this,this);



    }


    public HttpAPIModel.HttpAPIListener<AdminModel> listener1=new HttpAPIModel.HttpAPIListener<AdminModel>() {
        @Override
        public void onSuccessResponse(AdminModel responseObject) {
            dialog.cancel();

           if (1==responseObject.getInfo().get(0).getType()) {
               Intent intant = new Intent(Place_MainActivity.this, Place_SettingActivity.class);
               startActivity(intant);
           }
           Toast.makeText(Place_MainActivity.this, "权限不足", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailResponse(final String msg) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(Place_MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            });
        }
    };




}
