package com.qk.party.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qk.party.R;
import com.qk.party.adapter.TargetAdapter;
import com.qk.party.adapter.TargetPopAdapter;
import com.qk.party.adapter.WorkListAdapter;
import com.qk.party.application.MyApplication;
import com.qk.party.base.BaseFragment;
import com.qk.party.bean.TargetBean;
import com.qk.party.bean.TargetTypeBean;
import com.qk.party.presenter.TagerPresenter;
import com.qk.party.utils.ShardUtil;
import com.qk.party.viewinterface.TargetView;
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
 * @class: CityTargetFragmet
 * @author:  小飞
 * @date: 2017/11/2 15:29
 * @描述：
 */

public class CityTargetFragmet extends BaseFragment implements TargetView<List<TargetBean>,List<TargetTypeBean>> {
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
    @BindView(R.id.top_type)
    TextView topType;
    @BindView(R.id.target_type)
    LinearLayout target_type;

    private RecyclerView target;
    /**
     * 分类的PopupWindow对象
     * */
    private PopupWindow popup = null;
    /**
     * 分类的view视图
     * */
    private View popview;

    private int id = 0;

    private TargetAdapter adapterTarget;
    private TargetPopAdapter adapterType;
    private TagerPresenter presenter;
    private List<TargetBean> all = new ArrayList<>();
    private List<TargetTypeBean> types;
    private int page = 1;
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.work_list_fragment, container, false);
    }

    @Override
    public void initFindViewById(View view) {
        topType.setVisibility(View.VISIBLE);
        super.initFindViewById(view);
        presenter = new TagerPresenter(this);
        refreshLayout.setRefreshHeader(new ClassicsHeader(mActivity));
        refreshLayout.setRefreshFooter(new ClassicsFooter(mActivity));
        recycler.setLayoutManager(new LinearLayoutManager(mActivity));
        presenter.getTargetType(ShardUtil.getPreferenceString(mActivity,"access_token")
                , MyApplication.userInfo.getUserId());

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                presenter.getTarget(ShardUtil.getPreferenceString(mActivity,"access_token")
                        , MyApplication.userInfo.getUserId(),id,page);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                presenter.getTarget(ShardUtil.getPreferenceString(mActivity,"access_token")
                        , MyApplication.userInfo.getUserId(),id,page);
            }
        });
        errorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);
                errorLayout.setVisibility(View.GONE);
                presenter.getTargetType(ShardUtil.getPreferenceString(mActivity,"access_token")
                        , MyApplication.userInfo.getUserId());
            }
        });
        target_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });
    }

    @Override
    public void success(List<TargetBean> taskListBeans) {
        try {
            if(taskListBeans.size()<10){
                refreshLayout.setEnableLoadmore(false);
            }else{
                refreshLayout.setEnableLoadmore(true);
            }
            if(page==1){
                all.clear();
                recycler.setAdapter(adapterTarget = new TargetAdapter(all));
            }
            all.addAll(taskListBeans);
            if(all.size()==0){
                noDataLayout.setVisibility(View.VISIBLE);
            }else {
                noDataLayout.setVisibility(View.GONE);
            }
            progress.setVisibility(View.GONE);
            errorLayout.setVisibility(View.GONE);
            adapterTarget.notifyDataSetChanged();
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
    public void show(){
        if(types==null){
            return;
        }
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        if(popview==null&&popup==null){
            popview = inflater.inflate(R.layout.city_targer_rec, null);
            target = (RecyclerView) popview.findViewById(R.id.target_rec);
            target.setLayoutManager(new LinearLayoutManager(mActivity));
            target.setAdapter(adapterType = new TargetPopAdapter(types));
            popup = new PopupWindow(popview, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            adapterType.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    id = types.get(position).getId();
                    topType.setText(types.get(position).getName());
                    all.clear();
                    page = 1;
                    adapterTarget.notifyDataSetChanged();
                    progress.setVisibility(View.VISIBLE);
                    errorLayout.setVisibility(View.GONE);
                    presenter.getTarget(ShardUtil.getPreferenceString(mActivity,"access_token")
                            , MyApplication.userInfo.getUserId(),id,page);
                    popup.dismiss();
                }
            });
        }
        popup.setFocusable(true);
        popup.setBackgroundDrawable(new BitmapDrawable());
        popup.showAsDropDown(topType);
        popup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });
    }

    @Override
    public void successTarget(List<TargetTypeBean> t) {
        types = t;
        if(t.size()!=0){
            id = t.get(0).getId();
            topType.setText(t.get(0).getName());
            presenter.getTarget(ShardUtil.getPreferenceString(mActivity,"access_token")
                    , MyApplication.userInfo.getUserId(),id,page);
        }
    }
}
