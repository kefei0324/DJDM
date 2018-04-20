package com.qk.party.ui;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.qk.party.R;
import com.qk.party.base.BaseActivity;
import com.qk.party.bean.NotifyBean;
import com.qk.party.presenter.NotifyPresenter;
import com.qk.party.utils.ShardUtil;
import com.qk.party.utils.imageGet;
import com.qk.party.viewinterface.NetworkView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @package： com.qk.party.ui
 * @class: NotifyDetailsActivity
 * @author: 小飞
 * @date: 2017/10/27 10:54
 * @描述：
 */
public class NotifyDetailsActivity extends BaseActivity implements NetworkView<NotifyBean>{

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.news_title)
    TextView newsTitle;
    @BindView(R.id.newspaper)
    TextView newspaper;
    @BindView(R.id.data)
    TextView data;
    @BindView(R.id.huiyididian)
    TextView huiyididian;
    @BindView(R.id.web)
    WebView web;
    private NotifyPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_details);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        back.setVisibility(View.VISIBLE);
        title.setText("详情");
        web.getSettings().setJavaScriptEnabled(true);
        presenter = new NotifyPresenter(this);
        presenter.getNotifyDetails(ShardUtil.getPreferenceString(NotifyDetailsActivity.this,"access_token"),getIntent().getIntExtra("id",0));
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void success(NotifyBean notifyBean) {
        try {
            web.loadDataWithBaseURL(null, notifyBean.getTznr(), "text/html", "utf-8", null);
            newsTitle.setText(notifyBean.getBt());
            newspaper.setText(notifyBean.getSource());
            data.setText(notifyBean.getGxsj());
            huiyididian.setText(notifyBean.getHydd());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void error(String error) {

    }
}
