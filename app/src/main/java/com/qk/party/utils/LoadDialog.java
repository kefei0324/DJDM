package com.qk.party.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.qk.party.R;


/**
 * 作者：Think
 * 创建于 2017/10/18 13:15
 */

public class LoadDialog{
    private Dialog progressDialog;
    public LoadDialog(@NonNull Context context) {
        progressDialog = new Dialog(context,R.style.progress_dialog);
        progressDialog.setContentView(R.layout.progress);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
        msg.setText("加载中...");
    }
    public void show(){
        progressDialog.show();
    }
    public void dismiss(){
        progressDialog.dismiss();
    }
}
