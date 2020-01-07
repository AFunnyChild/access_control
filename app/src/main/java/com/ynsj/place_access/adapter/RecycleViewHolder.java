package com.ynsj.place_access.adapter;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.view.View;



import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.ynsj.place_access.R;


import java.text.ParseException;

/**
 * ViewHolder 基类
 */
public abstract class RecycleViewHolder<M> extends BaseViewHolder {
    private Dialog loadingDialog;
    public RecycleViewHolder(View itemView) {
        super(itemView);
    }
    public abstract void Bind(@NonNull M m) throws ParseException;
    /**
     * 显示加载对话框
     **/
    protected void showLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = new Dialog(getContext(), R.style.Transparent_Dialog);
            loadingDialog.setContentView(R.layout.dialog_loading);
            loadingDialog.setCanceledOnTouchOutside(false);
        }
        if (!loadingDialog.isShowing())
            loadingDialog.show();
    }
    /**
     * 隐藏加载对话框
     **/
    protected void hideLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing())
            loadingDialog.dismiss();
    }
}
