package com.qk.party.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.qk.party.base.BaseFragment;
import com.qk.party.fragment.WorkListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：Think
 * 创建于 2017/10/17 13:45
 */

public class WorkPageAdapter extends FragmentPagerAdapter {
    private String[] title;
    private List<BaseFragment> fragments;
    public WorkPageAdapter(FragmentManager fm,List<BaseFragment> fragment,String[] title) {
        super(fm);
        this.fragments = fragment;
        this.title = title;
    }

    @Override
    public BaseFragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
