package com.qk.party.base;

/**
 * 作者：Think
 * 创建于 2017/10/17 09:20
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.qk.party.R;
import com.qk.party.utils.ActivityManagers;

/**
 * @包名： com.qk.party.base
 * @文件名: BaseActivity
 * @author : 张宇飞
 * @创建时间: 2017/10/17 9:20
 * @描述：Activity基类
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManagers.getAppManager().addActivity(this);
    }
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_right);
    }
    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_right);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_right);
    }
}
