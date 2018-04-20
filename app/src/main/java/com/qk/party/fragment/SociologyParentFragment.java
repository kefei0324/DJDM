package com.qk.party.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qk.party.R;
import com.qk.party.adapter.WorkPageAdapter;
import com.qk.party.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @package： com.qk.party.fragment
 * @class: SociologyParentFragment
 * @author:  小飞
 * @date: 2017/11/2 15:27
 * @描述：
 */

public class SociologyParentFragment extends BaseFragment {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.top_title)
    LinearLayout topTitle;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private String[] titles = new String[]{"市级目标","完成情况"};
    private List<BaseFragment> fragments = new ArrayList<>();
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.sociology_parent_fragment, container, false);
    }
    @Override
    public void initFindViewById(View view) {
        super.initFindViewById(view);
        topTitle.setPadding(0, getStatusBarHeight() , 0, 0);
        title.setText("经济社会");
        fragments.add(new CityTargetFragmet());
        fragments.add(new SociologyFragment());
        viewPager.setAdapter(new WorkPageAdapter(getChildFragmentManager(),fragments,titles));
        tab.setupWithViewPager(viewPager);
    }
}
