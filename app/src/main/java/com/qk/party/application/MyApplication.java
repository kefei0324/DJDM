package com.qk.party.application;

import android.app.Application;
import android.content.Context;

import cn.jpush.android.api.JPushInterface;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.qk.party.bean.LoginBean;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

/**
 * @author ：Think
 * 创建于 2017/10/17 17:14
 */

public class MyApplication extends Application {
    public static LoginBean userInfo;
    public static int smalltype = 2;
    public static Context context;
    public static String APP_ID = "wxc1619753eb781b54";
    private static IWXAPI api;

    public static IWXAPI getApi() {
        return api;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init("party")
                .setMethodCount(3)
                .hideThreadInfo()
                .setLogLevel(LogLevel.NONE);
        LitePal.initialize(this);
        if (DataSupport.findAll(LoginBean.class).size() != 0) {
            userInfo = DataSupport.findAll(LoginBean.class).get(0);
        }
        context = this;

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);


        api = WXAPIFactory.createWXAPI(this, APP_ID, true);
        api.registerApp(APP_ID);
    }
}
