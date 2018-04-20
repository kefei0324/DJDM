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
import com.qk.party.adapter.WorkListAdapter;
import com.qk.party.application.MyApplication;
import com.qk.party.base.BaseFragment;
import com.qk.party.bean.TaskListBean;
import com.qk.party.presenter.TaskPresenter;
import com.qk.party.ui.MissionDetailsActivity;
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
 * @package： com.qk.party.fragment
 * @class: WorkListFragment
 * @author:  小飞
 * @date: 2017/11/6 14:10
 */
public class WorkListFragment extends BaseFragment implements MissionView<String,List<TaskListBean>> {
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

    private WorkListAdapter adapter;
    private TaskPresenter presenter;
    private List<TaskListBean> all = new ArrayList<>();
    private int bigtype = 1;
    private String str = "";
    private int page = 1;
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.work_list_fragment, container, false);
    }

    @Override
    public void initFindViewById(View view) {
        super.initFindViewById(view);
        presenter = new TaskPresenter(this);
        bigtype = getArguments().getInt("bigtype");
        refreshLayout.setRefreshHeader(new ClassicsHeader(mActivity));
        refreshLayout.setRefreshFooter(new ClassicsFooter(mActivity));
        recycler.setLayoutManager(new LinearLayoutManager(mActivity));
        presenter.getTaskList(ShardUtil.getPreferenceString(mActivity,"access_token")
                ,MyApplication.userInfo.getUserId(),bigtype,MyApplication.smalltype,page,str);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                presenter.getTaskList(ShardUtil.getPreferenceString(mActivity,"access_token")
                        ,MyApplication.userInfo.getUserId(),bigtype,MyApplication.smalltype,page,str);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                presenter.getTaskList(ShardUtil.getPreferenceString(mActivity,"access_token")
                        ,MyApplication.userInfo.getUserId(),bigtype,MyApplication.smalltype,page,str);
            }
        });
        errorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);
                errorLayout.setVisibility(View.GONE);
                presenter.getTaskList(ShardUtil.getPreferenceString(mActivity,"access_token")
                        ,MyApplication.userInfo.getUserId(),bigtype,MyApplication.smalltype,page,str);
            }
        });
    }

    @Override
    public void success(List<TaskListBean> taskListBeans) {
        try {
            if(taskListBeans.size()<10){
                refreshLayout.setEnableLoadmore(false);
            }else{
                refreshLayout.setEnableLoadmore(true);
            }
            if(page==1){
                all.clear();
                recycler.setAdapter(adapter = new WorkListAdapter(mActivity,all));
            }
            all.addAll(taskListBeans);
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
                    Intent intent =new Intent(mActivity, MissionDetailsActivity.class);
                    intent.putExtra("bigtype",bigtype);
                    intent.putExtra("task_id",all.get(position).getTask_id());
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

    public void changeSmalltype(int smalltype){
        MyApplication.smalltype = smalltype;
        page = 1;
        progress.setVisibility(View.VISIBLE);
        presenter.getTaskList(ShardUtil.getPreferenceString(mActivity,"access_token")
                ,MyApplication.userInfo.getUserId(),bigtype,smalltype,page,str);
    }
    public void changesearch(String search){
        page = 1;
        str = search;
        progress.setVisibility(View.VISIBLE);
        presenter.getTaskList(ShardUtil.getPreferenceString(mActivity,"access_token")
                ,MyApplication.userInfo.getUserId(),bigtype,MyApplication.smalltype,page,search);
    }

    @Override
    public void successMessage(String t) {}
}
