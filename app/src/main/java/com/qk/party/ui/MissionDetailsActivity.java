package com.qk.party.ui;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.*;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qk.party.R;
import com.qk.party.adapter.EnclosureAdapter;
import com.qk.party.adapter.RwDbAdapter;
import com.qk.party.adapter.RwFjAdapter;
import com.qk.party.adapter.RwHyAadapter;
import com.qk.party.adapter.RwjlAdapter;
import com.qk.party.adapter.RwsbAdapter;
import com.qk.party.application.MyApplication;
import com.qk.party.base.BaseActivity;
import com.qk.party.bean.TaskDetailsBean;
import com.qk.party.presenter.TaskPresenter;
import com.qk.party.share.TimeUtil;
import com.qk.party.utils.EditPopupWindow;
import com.qk.party.utils.ShardUtil;
import com.qk.party.viewinterface.MissionView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * @package： com.qk.party.ui
 * @class: MissionDetailsActivity
 * @author: 小飞
 * @date: 2017/10/19 9:00
 * @描述：
 */
public class MissionDetailsActivity extends BaseActivity implements MissionView<String, TaskDetailsBean> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_part)
    TextView tvPart;
    @BindView(R.id.level)
    TextView level;
    @BindView(R.id.tv_leader)
    TextView tv_leader;
    @BindView(R.id.time)
    TextView time;

    @BindView(R.id.level_part)
    TextView levelPart;
    @BindView(R.id.content)
    WebView content;


    @BindView(R.id.rwhy_rec)
    RecyclerView rwhy_rec;
    @BindView(R.id.rwjl_rec)
    RecyclerView rwjl_rec;
    @BindView(R.id.rwdb_rec)
    RecyclerView rwdb_rec;
    @BindView(R.id.rwfj_rec)
    RecyclerView rwfj_rec;
    @BindView(R.id.attach)
    RecyclerView attach;
    @BindView(R.id.comment)
    RecyclerView comment;
    @BindView(R.id.feedback)
    TextView feedback;
    @BindView(R.id.top_title)
    LinearLayout topTitle;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.root)
    LinearLayout root;
    @BindView(R.id.top_layout)
    LinearLayout topLayout;
    @BindView(R.id.error)
    RelativeLayout errorLayout;
    @BindView(R.id.no_data_layout)
    RelativeLayout noDataLayout;
    @BindView(R.id.btn_tj)
    ImageView btn_tj;
    @BindView(R.id.btn_tjrc)
    Button btn_tjrc;
    private int spitProgress;
    private int bigtype;
    private int smalltype;
    private int task_id;
    private TaskPresenter presenter;
    private EnclosureAdapter attachadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_details);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        back.setVisibility(View.VISIBLE);
        title.setText("任务详情");
        presenter = new TaskPresenter(this);
        bigtype = getIntent().getIntExtra("bigtype", 0);
        smalltype = getIntent().getIntExtra("smalltype", 0);
        if(smalltype==0){
            smalltype = MyApplication.smalltype;
        }
        task_id = getIntent().getIntExtra("task_id", 0);
        comment.setLayoutManager(new LinearLayoutManager(this));
        attach.setLayoutManager(new LinearLayoutManager(this));
        rwfj_rec.setLayoutManager(new LinearLayoutManager(this));
        rwdb_rec.setLayoutManager(new LinearLayoutManager(this));
        rwhy_rec.setLayoutManager(new LinearLayoutManager(this));
        rwjl_rec.setLayoutManager(new LinearLayoutManager(this));

        comment.setNestedScrollingEnabled(false);
        attach.setNestedScrollingEnabled(false);
        rwfj_rec.setNestedScrollingEnabled(false);
        rwdb_rec.setNestedScrollingEnabled(false);
        rwhy_rec.setNestedScrollingEnabled(false);
        rwjl_rec.setNestedScrollingEnabled(false);
        scrollView.setVisibility(View.GONE);
        progress.setVisibility(View.VISIBLE);
        errorLayout.setVisibility(View.GONE);
        noDataLayout.setVisibility(View.GONE);
        presenter.getTaskDetails(ShardUtil.getPreferenceString(MissionDetailsActivity.this, "access_token")
                , MyApplication.userInfo.getUserId(), bigtype, task_id,smalltype);
        if (bigtype == 2) {
            feedback.setVisibility(View.VISIBLE);
            feedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showFeedback();
                }
            });
        }
        errorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.setVisibility(View.GONE);
                progress.setVisibility(View.VISIBLE);
                errorLayout.setVisibility(View.GONE);
                noDataLayout.setVisibility(View.GONE);
                presenter.getTaskDetails(ShardUtil.getPreferenceString(MissionDetailsActivity.this, "access_token")
                        , MyApplication.userInfo.getUserId(), bigtype, task_id,smalltype);
            }
        });

        btn_tjrc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //添加日程

                tjrc();

            }
        });
    }

    private String title_,des_;

    private void tjrc() {
        TimeUtil.showShareDialog(MissionDetailsActivity.this, new TimeUtil.Listener() {
            @Override
            public void timeOk(long time) {
        addCalendarEvent(MissionDetailsActivity.this,TextUtils.isEmpty(title_)?"我的任务":title_,TextUtils.isEmpty(des_)?"任务描述":des_,time);

            }
        });

    }

    private static String CALANDER_URL = "content://com.android.calendar/calendars";
    private static String CALANDER_EVENT_URL = "content://com.android.calendar/events";
    private static String CALANDER_REMIDER_URL = "content://com.android.calendar/reminders";
    private static int checkCalendarAccount(Context context) {
        Cursor userCursor = context.getContentResolver().query(Uri.parse(CALANDER_URL), null, null, null, null);
        try {
            if (userCursor == null)//查询返回空值
                return -1;
            int count = userCursor.getCount();
            if (count > 0) {//存在现有账户，取第一个账户的id返回
                userCursor.moveToFirst();
                return userCursor.getInt(userCursor.getColumnIndex(CalendarContract.Calendars._ID));
            } else {
                return -1;
            }
        } finally {
            if (userCursor != null) {
                userCursor.close();
            }
        }
    }
    private static String CALENDARS_NAME = "test";
    private static String CALENDARS_ACCOUNT_NAME = "test@gmail.com";
    private static String CALENDARS_ACCOUNT_TYPE = "com.android.exchange";
    private static String CALENDARS_DISPLAY_NAME = "测试账户";
    private static long ONE_HOUR = 60000;

    private static long addCalendarAccount(Context context) {
        TimeZone timeZone = TimeZone.getDefault();
        ContentValues value = new ContentValues();
        value.put(CalendarContract.Calendars.NAME, CALENDARS_NAME);

        value.put(CalendarContract.Calendars.ACCOUNT_NAME, CALENDARS_ACCOUNT_NAME);
        value.put(CalendarContract.Calendars.ACCOUNT_TYPE, CALENDARS_ACCOUNT_TYPE);
        value.put(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, CALENDARS_DISPLAY_NAME);
        value.put(CalendarContract.Calendars.VISIBLE, 1);
        value.put(CalendarContract.Calendars.CALENDAR_COLOR, Color.BLUE);
        value.put(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL, CalendarContract.Calendars.CAL_ACCESS_OWNER);
        value.put(CalendarContract.Calendars.SYNC_EVENTS, 1);
        value.put(CalendarContract.Calendars.CALENDAR_TIME_ZONE, timeZone.getID());
        value.put(CalendarContract.Calendars.OWNER_ACCOUNT, CALENDARS_ACCOUNT_NAME);
        value.put(CalendarContract.Calendars.CAN_ORGANIZER_RESPOND, 0);

        Uri calendarUri = Uri.parse(CALANDER_URL);
        calendarUri = calendarUri.buildUpon()
                .appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true")
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, CALENDARS_ACCOUNT_NAME)
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, CALENDARS_ACCOUNT_TYPE)
                .build();

        Uri result = context.getContentResolver().insert(calendarUri, value);
        long id = result == null ? -1 : ContentUris.parseId(result);
        return id;
    }
    //检查是否已经添加了日历账户，如果没有添加先添加一个日历账户再查询
    private static int checkAndAddCalendarAccount(Context context){
        int oldId = checkCalendarAccount(context);
        if( oldId >= 0 ){
            return oldId;
        }else{
            long addId = addCalendarAccount(context);
            if (addId >= 0) {
                return checkCalendarAccount(context);
            } else {
                return -1;
            }
        }
    }
    public static void addCalendarEvent(Context context,String title, String description, long beginTime){
        // 获取日历账户的id
        int calId = checkAndAddCalendarAccount(context);
        if (calId < 0) {
            // 获取账户id失败直接返回，添加日历事件失败
            return;
        }

        ContentValues event = new ContentValues();
        event.put("title", title);
        event.put("description", description);
        // 插入账户的id
        event.put("calendar_id", calId);

        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(beginTime);//设置开始时间
        long start = mCalendar.getTime().getTime();
        mCalendar.setTimeInMillis(start + ONE_HOUR);//设置终止时间
        long end = mCalendar.getTime().getTime();

        event.put(CalendarContract.Events.DTSTART, start);
        event.put(CalendarContract.Events.DTEND, end);
        event.put(CalendarContract.Events.HAS_ALARM, 1);//设置有闹钟提醒
        event.put(CalendarContract.Events.EVENT_TIMEZONE, "Asia/Shanghai");  //这个是时区，必须有，
        //添加事件
        Uri newEvent = context.getContentResolver().insert(Uri.parse(CALANDER_EVENT_URL), event);
        if (newEvent == null) {
            // 添加日历事件失败直接返回
            return;
        }
        //事件提醒的设定
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Reminders.EVENT_ID, ContentUris.parseId(newEvent));
        // 提前10分钟有提醒
        values.put(CalendarContract.Reminders.MINUTES, 10);
        values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
        Uri uri = context.getContentResolver().insert(Uri.parse(CALANDER_REMIDER_URL), values);
        if(uri == null) {
            // 添加闹钟提醒失败直接返回
            return;
        }
    }
    public static void deleteCalendarEvent(Context context,String title){
        Cursor eventCursor = context.getContentResolver().query(Uri.parse(CALANDER_EVENT_URL), null, null, null, null);
        try {
            if (eventCursor == null)//查询返回空值
                return;
            if (eventCursor.getCount() > 0) {
                //遍历所有事件，找到title跟需要查询的title一样的项
                for (eventCursor.moveToFirst(); !eventCursor.isAfterLast(); eventCursor.moveToNext()) {
                    String eventTitle = eventCursor.getString(eventCursor.getColumnIndex("title"));
                    if (!TextUtils.isEmpty(title) && title.equals(eventTitle)) {
                        int id = eventCursor.getInt(eventCursor.getColumnIndex(CalendarContract.Calendars._ID));//取得id
                        Uri deleteUri = ContentUris.withAppendedId(Uri.parse(CALANDER_EVENT_URL), id);
                        int rows = context.getContentResolver().delete(deleteUri, null, null);
                        if (rows == -1) {
                            //事件删除失败
                            return;
                        }
                    }
                }
            }
        } finally {
            if (eventCursor != null) {
                eventCursor.close();
            }
        }
    }
    @Override
    public void success(final TaskDetailsBean taskDetailsBean) {
        scrollView.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);
        errorLayout.setVisibility(View.GONE);
        try {
            tvName.setText(taskDetailsBean.getT_name());
            title_=taskDetailsBean.getT_name();
            tvPart.setText(taskDetailsBean.getDept());
            levelPart.setText(taskDetailsBean.getT_source());

            String regex = "<img[^>]*src=\"([^>\\\"]*)\"[^>]*>";
            String str = taskDetailsBean.getT_content();
            str = str.replaceAll(regex, "<img src=\"$1\"></img>").replaceAll("font-size", "");
            content.loadDataWithBaseURL(null, "<style type=\"text/css\">img{width: 99%;</style>" + str, "text/html", "utf-8", null);

            level.setText(taskDetailsBean.getJjcdString());
            tv_leader.setText(taskDetailsBean.getLeader());
            time.setText(taskDetailsBean.getComplete_at());
            comment.setAdapter(new RwsbAdapter(taskDetailsBean.getOList()));
            rwfj_rec.setAdapter(new RwFjAdapter(taskDetailsBean.getRwfjList()));
            rwdb_rec.setAdapter(new RwDbAdapter(taskDetailsBean.getRwdbList()));
            rwhy_rec.setAdapter(new RwHyAadapter(taskDetailsBean.getRwhyList()));
            rwjl_rec.setAdapter(new RwjlAdapter(taskDetailsBean.getRwjlList()));


            attach.setAdapter(attachadapter = new EnclosureAdapter(taskDetailsBean.getAttachList()));
            attachadapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    String url = taskDetailsBean.getAttachList().get(position).getDisplayPath();
                    if (TextUtils.isEmpty(url)) {
                        Toast.makeText(MissionDetailsActivity.this, "下载地址错误", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(url);
                    intent.setData(content_url);
                    startActivity(intent);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            scrollView.setVisibility(View.GONE);
            noDataLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void error(String error) {
        errorLayout.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);
        errorLayout.setVisibility(View.GONE);
        noDataLayout.setVisibility(View.GONE);
    }

    @Override
    public void successMessage(String t) {
        Toast.makeText(this, t, Toast.LENGTH_SHORT).show();
    }

    public void showFeedback() {
        new EditPopupWindow(this, 1,spitProgress)
                .setListener(new EditPopupWindow.DetermineListener() {
                    @Override
                    public void onClick(String content, int type) {
                        presenter.getSaveup(ShardUtil.getPreferenceString(MissionDetailsActivity.this, "access_token")
                                , MyApplication.userInfo.getUserId(), bigtype, task_id, content,type);
                    }
                })
                .show(findViewById(R.id.root));
    }

    @OnClick({R.id.back, R.id.tag1, R.id.tag2, R.id.tag3, R.id.tag4, R.id.tag5, R.id.tag6, R.id.tag7, R.id.tag8})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tag1:
                switchState(topLayout);
                break;
            case R.id.tag2:
                switchState(content);
                break;
            case R.id.tag3:
                switchState(attach);
                break;
            case R.id.tag4:
                switchState(rwfj_rec);
                break;
            case R.id.tag5:
                switchState(rwhy_rec);
                break;
            case R.id.tag6:
                switchState(rwdb_rec);
                break;
            case R.id.tag7:
                switchState(rwjl_rec);
                break;
            case R.id.tag8:
                switchState(comment);
                break;
            default:
                finish();
                break;
        }
    }
    public void switchState(View view){
        if(view.getVisibility()==View.VISIBLE){
            view.setVisibility(View.GONE);
        }else {
            view.setVisibility(View.VISIBLE);
        }
    }
}
