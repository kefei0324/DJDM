package com.qk.party.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author :Think
 * 创建于 2017/10/17 11:20
 */

public abstract class BaseFragment extends Fragment {
    public FragmentActivity mActivity;
    public View rootView;
    Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        if (rootView == null) {
            rootView = initView(inflater, container);
        }
        unbinder = ButterKnife.bind(this, rootView);
        initFindViewById(rootView);
        return rootView;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }
    /**
     * 子类实现此抽象方法返回View进行展示

     * @return
     */
    public abstract View initView(LayoutInflater inflater, ViewGroup container);

    /**
     * 初始化控件
     */
    public void initFindViewById(View view){

    }
    /**
     * 子类在此方法中实现数据的初始化
     */
    public void initData(){

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (rootView != null) {
            try {
                ((ViewGroup)rootView.getParent()).removeView(rootView);
            } catch (Exception e) {

            }
        }
    }
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = mActivity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
