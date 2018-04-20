package com.qk.party.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.qk.party.base.BaseFragment;

import java.util.List;

/**
 * @author ：Think
 * 创建于 2017/10/17 11:30
 */

public class HomeAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragments;
    public HomeAdapter(FragmentManager fm,List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public BaseFragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
