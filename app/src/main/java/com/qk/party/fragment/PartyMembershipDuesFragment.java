package com.qk.party.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qk.party.R;
import com.qk.party.adapter.PartyMembershipAdapter;
import com.qk.party.adapter.WorkListAdapter;
import com.qk.party.application.MyApplication;
import com.qk.party.base.BaseFragment;
import com.qk.party.bean.PartyMemBean;
import com.qk.party.presenter.RemindPresenter;
import com.qk.party.ui.MissionDetailsActivity;
import com.qk.party.utils.ShardUtil;
import com.qk.party.viewinterface.NetworkView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @package： com.qk.party.fragment
 * @class: PartyMembershipDuesFragment
 * @author: 小飞
 * @date: 2017/10/30 15:33
 * @描述：
 */

public class PartyMembershipDuesFragment extends BaseFragment implements NetworkView<List<PartyMemBean>> {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.error)
    RelativeLayout errorLayout;
    private List<PartyMemBean> all = new ArrayList<>();
    private RemindPresenter presenter;
    private int page = 1;
    private PartyMembershipAdapter adapter;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.work_list_fragment, container, false);
    }

    @Override
    public void initFindViewById(View view) {
        super.initFindViewById(view);
        presenter = new RemindPresenter(this);
        refreshLayout.setRefreshHeader(new ClassicsHeader(mActivity));
        refreshLayout.setRefreshFooter(new ClassicsFooter(mActivity));
        refreshLayout.setBackgroundColor(mActivity.getResources().getColor(R.color.grey));
        recycler.setLayoutManager(new LinearLayoutManager(mActivity));
        presenter.getPartyMembership(ShardUtil.getPreferenceString(mActivity,"access_token")
                ,MyApplication.userInfo.getUserId());

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                presenter.getPartyMembership(ShardUtil.getPreferenceString(mActivity,"access_token")
                        ,MyApplication.userInfo.getUserId());
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                presenter.getPartyMembership(ShardUtil.getPreferenceString(mActivity,"access_token")
                        ,MyApplication.userInfo.getUserId());
            }
        });
        errorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getPartyMembership(ShardUtil.getPreferenceString(mActivity,"access_token")
                        ,MyApplication.userInfo.getUserId());
            }
        });
    }

    @Override
    public void success(List<PartyMemBean> partyMemBean) {
        try {
            if(partyMemBean.size()<10){
                refreshLayout.setEnableLoadmore(false);
            }else{
                refreshLayout.setEnableLoadmore(true);
            }
            if(page==1){
                all.clear();
                recycler.setAdapter(adapter = new PartyMembershipAdapter(all));
            }
            all.addAll(partyMemBean);
            progress.setVisibility(View.GONE);
            errorLayout.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
            page++;
            refreshLayout.finishRefresh();
            refreshLayout.finishLoadmore();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void error(String error) {
        try {
            progress.setVisibility(View.GONE);
            //判断原来集合是否有数据  没有数据显示错误页面   有数据提示错误
            if(all.size()==0){
                errorLayout.setVisibility(View.VISIBLE);
            }else {
                errorLayout.setVisibility(View.GONE);
            }
            refreshLayout.finishRefresh();
            refreshLayout.finishLoadmore();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
