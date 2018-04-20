package com.qk.party.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qk.party.R;
import com.qk.party.adapter.NotifyAdapter;
import com.qk.party.base.BaseActivity;
import com.qk.party.bean.NotifyBean;
import com.qk.party.presenter.NotifyPresenter;
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
import butterknife.ButterKnife;

/**
 * @package： com.qk.party.ui
 * @class: NotifyListActivity
 * @author: 小飞
 * @date: 2017/10/27 9:23
 * @描述：
 */
public class NotifyListActivity extends BaseActivity implements NetworkView<List<NotifyBean>>{

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.error)
    RelativeLayout errorLayout;
    @BindView(R.id.no_data_layout)
    RelativeLayout noDataLayout;
    private int userid;
    private NotifyPresenter presenter;
    private int type = 1;
    private int page = 1;
    private NotifyAdapter adapter;
    private List<NotifyBean> all = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_list);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        presenter = new NotifyPresenter(this);
        userid = getIntent().getIntExtra("userid",0);
        title.setText(getIntent().getStringExtra("title"));
        type = getIntent().getIntExtra("type",1);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        refreshLayout.setRefreshFooter(new ClassicsFooter(this));
        recycler.setLayoutManager(new LinearLayoutManager(this));
        presenter.getNotifyList(ShardUtil.getPreferenceString(this,"access_token"),userid,type,page);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                presenter.getNotifyList(ShardUtil.getPreferenceString(NotifyListActivity.this,"access_token"),userid,type,page);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                presenter.getNotifyList(ShardUtil.getPreferenceString(NotifyListActivity.this,"access_token"),userid,type,page);
            }
        });
        errorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getNotifyList(ShardUtil.getPreferenceString(NotifyListActivity.this,"access_token"),userid,type,page);
            }
        });
    }

    @Override
    public void success(List<NotifyBean> notifyBeans) {
        for (NotifyBean bean : notifyBeans) {
            bean.setMultiType(type);
        }
        try {
            if(notifyBeans.size()<10){
                refreshLayout.setEnableLoadmore(false);
            }else{
                refreshLayout.setEnableLoadmore(true);
            }
            if(page==1){
                all.clear();
                recycler.setAdapter(adapter = new NotifyAdapter(this,all));
            }
            all.addAll(notifyBeans);
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
                    all.get(position).setStatus(1);
                    adapter.notifyDataSetChanged();
                    if(type==1){
                        int id = 0;
                        try {
                            id = Integer.parseInt(all.get(position).getOrigin_id());
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(NotifyListActivity.this, MissionDetailsActivity.class);
                        intent.putExtra("task_id",id);
                        intent.putExtra("bigtype",2);
                        intent.putExtra("smalltype",all.get(position).getType()==1 ? 2:1);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(NotifyListActivity.this, NotifyDetailsActivity.class);
                        intent.putExtra("id",all.get(position).getId());
                        startActivity(intent);
                    }

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
}
