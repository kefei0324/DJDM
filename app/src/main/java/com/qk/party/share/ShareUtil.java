package com.qk.party.share;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.qk.party.R;
import com.qk.party.application.MyApplication;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;

/**
 * @AUTHER feona
 * @CREATE 2017/12/11  16:31
 **/
public class ShareUtil {

    /**
     *
     * @param txt
     * @param type 微信分享朋友圈=1    微信分享朋友=2     微信收藏=3
     */
    public static void shareTxt(String txt,int type){
        WXTextObject object = new WXTextObject();
        object.text = txt;

        WXMediaMessage msg= new WXMediaMessage();
        msg.mediaObject = object;
        msg.description = txt;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction  = buildTransaction("text");
        req.message = msg;
        req.scene  = type==1? SendMessageToWX.Req.WXSceneTimeline : (type==3?SendMessageToWX.Req.WXSceneFavorite:SendMessageToWX.Req.WXSceneSession);
        MyApplication.getApi().sendReq(req);
    }
    private static final int THUMB_SIZE = 150;
    /**
     * 分享网页
     * @param url
     * @param title
     * @param des
     * @param type
     */
    public static void shareHtml(String url,String title,String des,int type){
        WXWebpageObject object = new WXWebpageObject();
        object.webpageUrl = url;

        WXMediaMessage msg = new WXMediaMessage(object);
        msg.title  = title;
        msg.description = des;
        Bitmap bmp = BitmapFactory.decodeResource(c.getResources(), R.mipmap.ic_launcher);
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
        bmp.recycle();
        msg.thumbData = Util.bmpToByteArray(thumbBmp, true);
//        msg.thumbData = null;//FIXME

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        req.scene = type==1? SendMessageToWX.Req.WXSceneTimeline : (type==3?SendMessageToWX.Req.WXSceneFavorite:SendMessageToWX.Req.WXSceneSession);
        MyApplication.getApi().sendReq(req);
    }
    private static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    private static View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.session:
                    shareHtml(url,t,"党建",2);
//                    shareTxt("ceshi",2);
                    break;
                case R.id.timeline:
                    shareHtml(url,t,"党建",1);
                    break;
                case R.id.favorite:
                    shareHtml(url,t,"党建",3);
                    break;
            }

            bottomDialog.dismiss();

        }
    };
    static  Dialog bottomDialog;
    static String url;
    static String t;
    static Context c;
    public static void showShareDialog(Context context,String shareUrl,String title){
        c=context;
        url=shareUrl;
        t=title;
         bottomDialog = new Dialog(context, R.style.BottomDialog);
        View contentView = LayoutInflater.from(context).inflate(R.layout.shareitem, null);

        contentView.findViewById(R.id.session).setOnClickListener(listener);
        contentView.findViewById(R.id.timeline).setOnClickListener(listener);
        contentView.findViewById(R.id.favorite).setOnClickListener(listener);

        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = context.getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.show();

    }

}
