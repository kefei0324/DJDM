package com.qk.party.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qk.party.R;
import com.qk.party.adapter.HomeRecyclerAdapter;
import com.qk.party.base.BaseFragment;
import com.qk.party.bean.Notice;
import com.qk.party.presenter.NoticeListPresenter;
import com.qk.party.ui.NoticeActivity;
import com.qk.party.ui.NoticeDetailsActivity;
import com.qk.party.utils.ShardUtil;
import com.qk.party.viewinterface.MissionView;
import com.qk.party.viewinterface.NetworkView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * @author ：Think
 *         创建于 2017/10/17 11:19
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener,MissionView<List<String>,List<Notice>> {
    @BindView(R.id.banner)
    XBanner banner;
    List<String> imageUrl;
    @BindView(R.id.strictly)
    TextView strictly;
    @BindView(R.id.reform)
    TextView reform;
    @BindView(R.id.mechanism)
    TextView mechanism;
    @BindView(R.id.transcend)
    TextView transcend;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.error)
    RelativeLayout errorLayout;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.top_title)
    LinearLayout top_title;
    List<Notice> data;
    private NoticeListPresenter presenter;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.home_fragmet, container, false);
    }

    @Override
    public void initFindViewById(View view) {
        super.initFindViewById(view);
        presenter = new NoticeListPresenter(this);
        strictly.setOnClickListener(this);
        reform.setOnClickListener(this);
        mechanism.setOnClickListener(this);
        transcend.setOnClickListener(this);
        presenter.getNoticeList(ShardUtil.getPreferenceString(mActivity,"access_token"),1);
        recycler.setLayoutManager(new LinearLayoutManager(mActivity));
        errorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);
                errorLayout.setVisibility(View.GONE);
                presenter.getNoticeList(ShardUtil.getPreferenceString(mActivity,"access_token"),1);
                presenter.getBanner(ShardUtil.getPreferenceString(mActivity,"access_token"));
            }
        });
        presenter.getBanner(ShardUtil.getPreferenceString(mActivity,"access_token"));
        recycler.setNestedScrollingEnabled(false);
        title.setText("党建动态");
        top_title.setPadding(0, getStatusBarHeight() , 0, 0);
        scrollView.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClass(mActivity, NoticeActivity.class);
        switch (v.getId()){
            case R.id.strictly:
                intent.putExtra("title","从严治党");
                intent.putExtra("ids",new int[]{11,12,13});
                intent.putExtra("types",new String[]{"纪规锦集","实践落实","巡察工作"});
                break;
            case R.id.reform:
                intent.putExtra("title","深化改革");
                intent.putExtra("ids",new int[]{10,6,7});
                intent.putExtra("types",new String[]{"改革创新","制度建设","工作动态"});
                break;
            case R.id.mechanism:
                intent.putExtra("title","三项机制");
                intent.putExtra("ids",new int[]{1,2,3});
                intent.putExtra("types",new String[]{"制度体系","创新实践","案例"});
                break;
            default:
                intent.putExtra("title","追赶超越");
                intent.putExtra("ids",new int[]{4,5});
                intent.putExtra("types",new String[]{"制度体系","通报"});
                break;
        }
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

    @Override
    public void success(final List<Notice> notices) {
        try {
            progress.setVisibility(View.GONE);
            errorLayout.setVisibility(View.GONE);
            HomeRecyclerAdapter adapter = new HomeRecyclerAdapter(mActivity,notices);
            recycler.setAdapter(adapter);
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    notices.get(position).setYdcs(notices.get(position).getYdcs()+1);
                    adapter.notifyItemChanged(position);
                    Intent intent = new Intent(mActivity, NoticeDetailsActivity.class);
                    intent.putExtra("id",notices.get(position).getId());
                    startActivity(intent);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void error(String error) {
        try {
            progress.setVisibility(View.GONE);
            errorLayout.setVisibility(View.VISIBLE);
            Toast.makeText(mActivity,error,Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void successMessage(List<String> t) {
        imageUrl = t;
        banner.setData(imageUrl, null);
        banner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(mActivity).load(imageUrl.get(position)).centerCrop().into((ImageView) view);
            }
        });
    }
}
