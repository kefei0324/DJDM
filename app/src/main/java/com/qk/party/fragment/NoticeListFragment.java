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
import com.qk.party.adapter.HomeRecyclerAdapter;
import com.qk.party.base.BaseFragment;
import com.qk.party.bean.Notice;
import com.qk.party.presenter.NoticeListPresenter;
import com.qk.party.ui.NoticeDetailsActivity;
import com.qk.party.utils.ShardUtil;
import com.qk.party.viewinterface.MissionView;
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
 * @author ：Think
 * 创建于 2017/10/18 11:20
 */

public class NoticeListFragment extends BaseFragment implements MissionView<String,List<Notice>> {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.error)
    RelativeLayout errorLayout;
    @BindView(R.id.no_data_layout)
    RelativeLayout noDataLayout;
    private int id;
    private NoticeListPresenter presenter;
    private HomeRecyclerAdapter adapter;
    private List<Notice> all = new ArrayList<>();
    int page = 1;
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.work_list_fragment, container, false);
    }

    @Override
    public void initFindViewById(View view) {
        super.initFindViewById(view);
        presenter = new NoticeListPresenter(this);
        id = getArguments().getInt("id");
        refreshLayout.setRefreshHeader(new ClassicsHeader(mActivity));
        refreshLayout.setRefreshFooter(new ClassicsFooter(mActivity));
        recycler.setLayoutManager(new LinearLayoutManager(mActivity));
        presenter.getNoticeList(id,ShardUtil.getPreferenceString(mActivity,"access_token"),page);
        errorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);
                errorLayout.setVisibility(View.GONE);
                presenter.getNoticeList(id,ShardUtil.getPreferenceString(mActivity,"access_token"),page);
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                presenter.getNoticeList(id,ShardUtil.getPreferenceString(mActivity,"access_token"),page);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                presenter.getNoticeList(id,ShardUtil.getPreferenceString(mActivity,"access_token"),page);
            }
        });
    }

    @Override
    public void success(List<Notice> notices) {
        try {
            if(notices.size()<10){
                refreshLayout.setEnableLoadmore(false);
            }else{
                refreshLayout.setEnableLoadmore(true);
            }
            if(page==1){
                all.clear();
                recycler.setAdapter(adapter = new HomeRecyclerAdapter(mActivity,all));
            }
            all.addAll(notices);
            if(all.size()==0){
                noDataLayout.setVisibility(View.VISIBLE);
            }else {
                noDataLayout.setVisibility(View.GONE);
            }
            progress.setVisibility(View.GONE);
            errorLayout.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    all.get(position).setYdcs(all.get(position).getYdcs()+1);
                    adapter.notifyItemChanged(position);
                    Intent intent = new Intent(mActivity, NoticeDetailsActivity.class);
                    intent.putExtra("id",all.get(position).getId());
                    startActivity(intent);
                }
            });
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

    @Override
    public void successMessage(String t) {

    }
}
