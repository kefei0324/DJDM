package com.qk.party.fragment;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.qk.party.R;
import com.qk.party.adapter.WorkPageAdapter;
import com.qk.party.base.BaseFragment;
import com.qk.party.utils.TextSeachPopWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author ：Think
 *         创建于 2017/10/17 13:23
 */

public class WorkFragment extends BaseFragment {
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.work_page)
    ViewPager workPage;
    @BindView(R.id.top_title)
    LinearLayout top_title;
    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.root)
    LinearLayout root;


    private WorkListFragment fragment1;
    private WorkListFragment fragment2;
    private String[] titles = new String[]{"任务列表","我的任务"};
    private List<BaseFragment> fragments = new ArrayList<>();

    /**
     * 分类的PopupWindow对象
     * */
    private PopupWindow popup = null;
    /**
     * 分类的view视图
     * */
    private View popview;

    private int select = 2;
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.work_fragment, container, false);
    }

    @Override
    public void initFindViewById(View view) {
        super.initFindViewById(view);

        fragment1 = new WorkListFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putInt("bigtype",1);
        fragment1.setArguments(bundle1);

        fragment2 = new WorkListFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putInt("bigtype",2);
        fragment2.setArguments(bundle2);

        fragments.add(fragment1);
        fragments.add(fragment2);
        workPage.setAdapter(new WorkPageAdapter(getChildFragmentManager(),fragments,titles));
        tab.setupWithViewPager(workPage);
        top_title.setPadding(0, getStatusBarHeight() , 0, 0);
        title.setText("工作任务");
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopWindow();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TextSeachPopWindow(mActivity)
                        .setListener(new TextSeachPopWindow.DetermineListener() {
                    @Override
                    public void onClick(String content) {
                        fragment1.changesearch(content);
                        fragment2.changesearch(content);
                    }
                }).show(root);
            }
        });
    }

    /**
     * 显示pop窗口
     * */
    private void showPopWindow(){
        title.setText("工作任务");
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        if(popview==null&&popup==null){
            popview = inflater.inflate(R.layout.work_title_popview, null);
            popup = new PopupWindow(popview, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        }

        TextView btn1 = (TextView) popview.findViewById(R.id.rg_btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select = 1;
                fragment1.changeSmalltype(select);
                fragment2.changeSmalltype(select);
                popup.dismiss();
            }
        });
        TextView btn2 = (TextView) popview.findViewById(R.id.rg_btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select = 2;
                fragment1.changeSmalltype(select);
                fragment2.changeSmalltype(select);
                popup.dismiss();
            }
        });
        btn1.setTextColor(mActivity.getResources().getColor(R.color.hui_text));
        btn2.setTextColor(mActivity.getResources().getColor(R.color.hui_text));
        if(select==1){
            btn1.setTextColor(Color.BLACK);
        }else {
            btn2.setTextColor(Color.BLACK);
        }
        popup.setFocusable(true);
        popup.setBackgroundDrawable(new BitmapDrawable());
        popup.showAsDropDown(top_title);
        popup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });
    }

}
