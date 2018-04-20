package com.qk.party.ui;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.qk.party.R;
import com.qk.party.base.BaseActivity;
import com.qk.party.utils.ShardUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @package： com.qk.party.ui
 * @class: AboutActivity
 * @author: 小飞
 * @date: 2017/10/17 18:39
 * @描述：
 */
public class AboutActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.web)
    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        back.setVisibility(View.VISIBLE);
        title.setText("软件介绍");
        web.loadUrl("http://101.200.35.180:8080/api/aboutus");
    }

    @OnClick({R.id.back})
    public void onViewClicked(View view) {
        finish();
    }
}
