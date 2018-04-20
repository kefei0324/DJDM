package com.qk.party.receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;
import cn.jpush.android.api.JPushInterface;
import com.orhanobut.logger.Logger;
import com.qk.party.ui.*;
import org.json.JSONObject;

/**
 * @AUTHER feona
 * @CREATE 2017/12/11  11:18
 **/
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";

    private NotificationManager nm;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (null == nm) {
            nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }

        Bundle bundle = intent.getExtras();
//        Logger.d(TAG, "onReceive - " + intent.getAction() + ", extras: " + AndroidUtil.printBundle(bundle));

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            Logger.d(TAG, "JPush用户注册成功");
            Log.d(TAG, "JPush用户注册成功");

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Logger.d(TAG, "接受到推送下来的自定义消息");
            Log.d(TAG, "接受到推送下来的自定义消息");

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Logger.d(TAG, "接受到推送下来的通知");
            Log.d(TAG, "接受到推送下来的通知");

            receivingNotification(context, bundle);

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Logger.d(TAG, "用户点击打开了通知");
            Log.d(TAG, "用户点击打开了通知");

            openNotification(context, bundle);

        } else {
            Logger.d(TAG, "Unhandled intent - " + intent.getAction());
            Log.d(TAG, "Unhandled intent - " + intent.getAction());
        }
    }

    private void receivingNotification(Context context, Bundle bundle) {
        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        Logger.d(TAG, " title : " + title);
        Log.d(TAG, " title : " + title);
        String message = bundle.getString(JPushInterface.EXTRA_ALERT);
        Logger.d(TAG, "message : " + message);
        Log.d(TAG, "message : " + message);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        Logger.d(TAG, "extras : " + extras);
        Log.d(TAG, "extras : " + extras);
    }

//    使用里面的notice_type；1 通知通告，2 会议通知，3，党费通知，4，换届通知，5三会一课通知，6，任务重点工作，7，任务追赶超越

    //    private static final String MISSION = "mission";
//    private static final String NOTICE = "notice";
    private static final int TONGZHIGONGAO = 1;
    private static final int HUIYITONGZHI = 2;
    private static final int DANGFEITONGZHI = 3;
    private static final int HUANJIETONGZHI = 4;
    private static final int SANHUIYIKETONGZHI = 5;
    private static final int RENWUZHONGDIANGONGZUO = 6;
    private static final int RENWUZHUIGANCHAOYUE = 7;


    private void openNotification(Context context, Bundle bundle) {
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
//        String myValue = "";
        int myValue = 0;
        try {
            JSONObject extrasJson = new JSONObject(new JSONObject(extras).optString("androidNotification extras key"));
//            Toast.makeText(context, "json="+extrasJson.toString(), Toast.LENGTH_LONG).show();
            myValue = extrasJson.optInt("notice_type");
            Intent mIntent = new Intent();
            switch (myValue) {
                case TONGZHIGONGAO:
                    //通知公告
                    mIntent.setClass(context, NotifyDetailsActivity.class);
                    mIntent.putExtra("is_share", false);
                    mIntent.putExtra("id", extrasJson.optInt("id"));
//                    mIntent.putExtra("smalltype", extrasJson.optInt("smalltype"));
//                    mIntent.putExtra("task_id", extrasJson.optInt("task_id"));
                    mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(mIntent);
                    break;
                case HUIYITONGZHI:
                    //会议通知
                    mIntent.setClass(context, NotifyDetailsActivity.class);
                    mIntent.putExtra("id", extrasJson.optInt("id"));
//                    mIntent.putExtra("smalltype", extrasJson.optInt("smalltype"));
//                    mIntent.putExtra("task_id", extrasJson.optInt("task_id"));
                    mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(mIntent);
                    break;
                case DANGFEITONGZHI:
                    //党费通知
                    mIntent.setClass(context, PartyMembershipActivity.class);
//                    mIntent.putExtra("bigtype", extrasJson.optInt("bigtype"));
//                    mIntent.putExtra("smalltype", extrasJson.optInt("smalltype"));
//                    mIntent.putExtra("task_id", extrasJson.optInt("task_id"));
                    mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(mIntent);
                    break;
                case HUANJIETONGZHI:
                    mIntent.setClass(context, NoticeDetailsActivity.class);
                    mIntent.putExtra("is_share", false);
                    String msg_content = extrasJson.optString("msg_content");
                    mIntent.putExtra("id", extrasJson.optInt("id"));
                    mIntent.putExtra("msg_content", msg_content);
//                    mIntent.putExtra("smalltype", extrasJson.optInt("smalltype"));
//                    mIntent.putExtra("task_id", extrasJson.optInt("task_id"));
                    mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(mIntent);


//                    Toast t = Toast.makeText(context, "请前往PC浏览器版本查看，谢谢！", Toast.LENGTH_LONG);
//                    t.setGravity(Gravity.CENTER, 0, 0);
//                    t.show();
                    break;
                case SANHUIYIKETONGZHI:
                    mIntent.setClass(context, NoticeDetailsActivity.class);
                    mIntent.putExtra("id", extrasJson.optInt("id"));
                    mIntent.putExtra("is_share", false);

//                    mIntent.putExtra("smalltype", extrasJson.optInt("smalltype"));
//                    mIntent.putExtra("task_id", extrasJson.optInt("task_id"));
                    mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(mIntent);
                    break;
                case RENWUZHONGDIANGONGZUO:
//                    Intent mIntent = new Intent(context, MissionDetailsActivity.class);//FIXME
//            mIntent.putExtras(bundle);
                    mIntent.setClass(context, MissionDetailsActivity.class);
                    mIntent.putExtra("bigtype", 2);
                    mIntent.putExtra("smalltype", 2);
                    mIntent.putExtra("task_id", extrasJson.optInt("id"));
                    mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(mIntent);
                    break;
                case RENWUZHUIGANCHAOYUE:
//                    Intent mIntent = new Intent(context, MissionDetailsActivity.class);//FIXME
//            mIntent.putExtras(bundle);
                    mIntent.setClass(context, MissionDetailsActivity.class);
                    mIntent.putExtra("bigtype", 2);
                    mIntent.putExtra("smalltype", 1);
                    mIntent.putExtra("task_id", extrasJson.optInt("id"));
                    mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(mIntent);
                    break;
                default:
                    break;
            }

//            if (MISSION.equals(myValue)) {
//                Intent mIntent = new Intent(context, MissionDetailsActivity.class);//FIXME
////            mIntent.putExtras(bundle);
//                mIntent.putExtra("bigtype", extrasJson.optInt("bigtype"));
//                mIntent.putExtra("smalltype", extrasJson.optInt("smalltype"));
//                mIntent.putExtra("task_id", extrasJson.optInt("task_id"));
//                mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(mIntent);
//            } else if (NOTICE.equals(myValue)) {
//                Intent mIntent = new Intent(context, NotifyDetailsActivity.class);//FIXME
////            mIntent.putExtras(bundle);
//                mIntent.putExtra("id", extrasJson.optInt("id"));
//                mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(mIntent);
//            } else {
//
//                Intent mIntent = new Intent(context, MainActivity.class);
//                mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                context.startActivity(mIntent);
//            }
        } catch (Exception e) {
            Logger.w(TAG, "Unexpected: extras is not a valid json", e);
            return;
        }
    }
}
