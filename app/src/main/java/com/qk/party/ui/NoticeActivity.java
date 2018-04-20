package com.qk.party.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qk.party.R;
import com.qk.party.adapter.WorkPageAdapter;
import com.qk.party.base.BaseActivity;
import com.qk.party.base.BaseFragment;
import com.qk.party.fragment.NoticeListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @package： com.qk.party.ui
 * @class: NoticeActivity
 * @author: 小飞
 * @date: 2017/10/18 10:47
 * @描述：新闻列表页面
 */
public class NoticeActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.notice_page)
    ViewPager noticePage;
    private String[] types;
    private int[] ids;
    private List<BaseFragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        back.setVisibility(View.VISIBLE);
        title.setText(getIntent().getStringExtra("title"));
        types = getIntent().getStringArrayExtra("types");
        ids = getIntent().getIntArrayExtra("ids");
        fragments = new ArrayList<>();
        if(ids.length>3){
            tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
        for (int i = 0; i < ids.length; i++) {
            NoticeListFragment fragment = new NoticeListFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("id", ids[i]);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        noticePage.setOffscreenPageLimit(ids.length);
        noticePage.setAdapter(new WorkPageAdapter(getSupportFragmentManager(),fragments,types));
        tab.setupWithViewPager(noticePage);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
