package com.qk.party.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;
import android.widget.RadioGroup;

import com.qk.party.R;
import com.qk.party.adapter.HomeAdapter;
import com.qk.party.base.BaseActivity;
import com.qk.party.base.BaseFragment;
import com.qk.party.fragment.HomeFragment;
import com.qk.party.fragment.PersonalFragment;
import com.qk.party.fragment.SociologyParentFragment;
import com.qk.party.fragment.WorkFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author : 张宇飞
 * @包名： com.qk.party.ui
 * @文件名: MainActivity
 * @创建时间: 2017/10/17 9:09
 * @描述：主页
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.rg_btn)
    RadioGroup rgBtn;
    private List<BaseFragment> fragments;
    private HomeFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        fragments = new ArrayList<>();
        fragment = new HomeFragment();
        fragments.add(fragment);
        fragments.add(new SociologyParentFragment());
        fragments.add(new WorkFragment());
        fragments.add(new PersonalFragment());
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(new HomeAdapter(getSupportFragmentManager(),fragments));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                checkedItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rgBtn.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.dynamic:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.sociology:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.work:
                        viewPager.setCurrentItem(2);
                        break;
                    default:
                        viewPager.setCurrentItem(3);
                        break;
                }
            }
        });
    }
    private void checkedItem(int page){
        switch (page){
            case 0:
                rgBtn.check(R.id.dynamic);
                break;
            case 1:
                rgBtn.check(R.id.sociology);
                break;
            case 2:
                rgBtn.check(R.id.work);
                break;
            default:
                rgBtn.check(R.id.personal);
                break;
        }
    }
}
