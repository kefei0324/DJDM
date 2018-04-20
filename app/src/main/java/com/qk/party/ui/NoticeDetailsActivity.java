package com.qk.party.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qk.party.R;
import com.qk.party.base.BaseActivity;
import com.qk.party.bean.Notice;
import com.qk.party.presenter.NoticeListPresenter;
import com.qk.party.share.ShareUtil;
import com.qk.party.utils.ShardUtil;
import com.qk.party.viewinterface.MissionView;
import com.qk.party.viewinterface.NetworkView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @package： com.qk.party.ui
 * @class: NoticeDetailsActivity
 * @author: 小飞
 * @date: 2017/10/18 13:44
 * @描述：
 */
public class NoticeDetailsActivity extends BaseActivity implements MissionView<String, Notice>, View.OnClickListener {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.feedback)
    TextView feedback;
    @BindView(R.id.web)
    WebView web;
    @BindView(R.id.news_title)
    TextView newsTitle;
    @BindView(R.id.newspaper)
    TextView newspaper;
    @BindView(R.id.data)
    TextView data;
    @BindView(R.id.ll_over_web)
    LinearLayout ll;
    private NoticeListPresenter presenter;
    private Notice notice;
    private String shareUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_details);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        back.setVisibility(View.VISIBLE);
        title.setText("详情");

        boolean isNotice = getIntent().getBooleanExtra("is_share", true);
        if (isNotice) {
            feedback.setVisibility(View.VISIBLE);
            feedback.setText("分享");
            feedback.setOnClickListener(this);
        }

        web.getSettings().setJavaScriptEnabled(true);
        presenter = new NoticeListPresenter(this);
        String msg_content=getIntent().getStringExtra("msg_content");
        if(!TextUtils.isEmpty(msg_content))
        {
            ll.setVisibility(View.GONE);
            title.setText("换届选举");
            web.loadDataWithBaseURL(null, "<style type=\"text/css\">img{width: 99%;</style>" + msg_content, "text/html", "utf-8", null);
        }else {

            presenter.getNoticeDetail(getIntent().getIntExtra("id", 0), ShardUtil.getPreferenceString(this, "access_token"));
        }

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void success(Notice notice) {
        try {
            this.notice = notice;
            String regex = "<img[^>]*src=\"([^>\\\"]*)\"[^>]*>";
            String content = notice.getContent();
            content = content.replaceAll(regex, "<img src=\"$1\"></img>").replaceAll("font-size", "");
            web.loadDataWithBaseURL(null, "<style type=\"text/css\">img{width: 99%;</style>" + content, "text/html", "utf-8", null);
            newsTitle.setText(notice.getTitle());
            newspaper.setText(notice.getOptionUser());
            data.setText(notice.getPublicTime());
            shareUrl=notice.getShareUrl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void error(String error) {

    }

    @Override
    public void successMessage(String t) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.feedback:
                showShare();
                break;
        }
    }

    private void showShare() {

        if(!TextUtils.isEmpty(shareUrl)) {
            ShareUtil.showShareDialog(NoticeDetailsActivity.this, shareUrl,notice.getTitle());
        }
    }
}
