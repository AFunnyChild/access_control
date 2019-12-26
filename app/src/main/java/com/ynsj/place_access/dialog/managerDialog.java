package com.ynsj.place_access.dialog;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


import com.ynsj.place_access.R;
import com.ynsj.place_access.listener.OnFpListener;
import com.ynsj.place_access.listener.OnLockerBackListener;
import com.ynsj.place_access.listener.OnNfcListener;
import com.ynsj.place_access.server.FingerNfcServer;


public class managerDialog extends Dialog implements OnNfcListener, OnFpListener, OnLockerBackListener {

    private TextView title;
    private TextView message;
    private ImageView imageView;
    private String str_title;
    private String str_message;
    private int imgid;
    private int type;

    private OnMessageListener onMessageListener;
    private Context mContext;


    public managerDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public managerDialog(Context context, int themeResId , String title, String message, int imgid,OnMessageListener onMessageListener) {
        super(context, themeResId);
        this.mContext = context;
        str_title=title;
        str_message=message;

        this.onMessageListener=onMessageListener;
        this.imgid=imgid;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_dialog);
        FingerNfcServer.getInstance().startport(this,this,this);

        setCanceledOnTouchOutside(true);
        initView();
        imageView.setImageResource(imgid);
        title.setText(str_title);
        message.setText(str_message);
    }

    private void initView(){
        title=(TextView) findViewById(R.id.manager_titile);
        message=(TextView)findViewById(R.id.manager_msg);
        imageView=(ImageView)findViewById(R.id.logo);
        super.setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {

                FingerNfcServer.getInstance().closeport();
                onMessageListener.GetFinsh();

            }
        });

    }



    @Override
    public void commGetFp(String Fp, String send_id) {


    }

    @Override
    public void commGetId(String id, String send_id) {

            onMessageListener.GetFN("id",id);

    }

    @Override
    public void LockBack(String id, String send_id) {

    }


    public interface OnMessageListener{
        void GetFN(String type, String message);

        void GetFinsh();
    }


}
