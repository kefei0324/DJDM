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
import com.qk.party.fragment.ElectionFragment;
import com.qk.party.fragment.PartyMembershipDuesFragment;
import com.qk.party.fragment.ThreeSessionsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @package： com.qk.party.ui
 * @class: RemindActivity
 * @author: 小飞
 * @date: 2017/10/30 15:17
 * @描述：
 */
public class RemindActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private List<BaseFragment> fragments;
    private String[] types = new String[]{"党费提醒"," 换届选举提醒","三会一课提醒"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        back.setVisibility(View.VISIBLE);
        title.setText("提醒");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fragments = new ArrayList<>();
        for (int i = 0; i < types.length; i++) {
            fragments.add(new PartyMembershipDuesFragment());
            fragments.add(new ElectionFragment());
            fragments.add(new ThreeSessionsFragment());
        }
        viewPager.setOffscreenPageLimit(types.length);
        viewPager.setAdapter(new WorkPageAdapter(getSupportFragmentManager(),fragments,types));
        tab.setupWithViewPager(viewPager);
    }
}
