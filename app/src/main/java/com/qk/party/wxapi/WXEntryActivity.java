package com.qk.party.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.qk.party.application.MyApplication;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import android.app.Activity;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler{


    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.entry);

        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = MyApplication.getApi();

        //注意：
        //第三方开发者如果使用透明界面来实现WXEntryActivity，需要判断handleIntent的返回值，如果返回值为false，则说明入参不合法未被SDK处理，应finish当前透明界面，避免外部通过传递非法参数的Intent导致停留在透明界面，引起用户的疑惑
        try {
            api.handleIntent(getIntent(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
            case BaseResp.ErrCode.ERR_USER_CANCEL:
//                showToast("微信失败");
                Toast.makeText(WXEntryActivity.this,"分享失败",Toast.LENGTH_LONG).show();
                finish();
                break;

            case BaseResp.ErrCode.ERR_OK:
//                switch (baseResp.getType()) {
//                    case RETURN_MSG_TYPE_SHARE:
//                        showToast("微信分享成功");
                        Toast.makeText(WXEntryActivity.this,"微信分享成功",Toast.LENGTH_LONG).show();
                        finish();
                        break;
//                }
//                break;
        }
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
        api.handleIntent(intent, this);
    }
}