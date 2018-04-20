package com.qk.party.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.qk.party.R;
import com.qk.party.base.BaseFragment;
import com.qk.party.bean.SociologyBean;
import com.qk.party.presenter.SociologyPresenter;
import com.qk.party.utils.ShardUtil;
import com.qk.party.utils.SociologyItemUtils;
import com.qk.party.viewinterface.NetworkView;

import java.util.List;

import butterknife.BindView;
/**
 * @package： com.qk.party.fragment
 * @class: SociologyFragment
 * @author:  小飞
 * @date: 2017/10/25 17:35
 * @描述：
 */
public class SociologyFragment extends BaseFragment implements NetworkView<List<SociologyBean>>{
    @BindView(R.id.vertical_layout)
    LinearLayout vertical_layout;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.error)
    RelativeLayout errorLayout;
    private SociologyPresenter presenter;
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.sociology_fragment, container, false);
    }

    @Override
    public void initFindViewById(View view) {
        super.initFindViewById(view);
        presenter = new SociologyPresenter(this);
        presenter.getSociologyList(ShardUtil.getPreferenceString(mActivity,"access_token"));
        errorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);
                errorLayout.setVisibility(View.GONE);
                presenter.getSociologyList(ShardUtil.getPreferenceString(mActivity,"access_token"));
            }
        });
    }

    @Override
    public void success(List<SociologyBean> sociologyBeans) {
        progress.setVisibility(View.GONE);
        errorLayout.setVisibility(View.GONE);
//         for (SociologyBean sociologyBean : sociologyBeans) {
//            SociologyItemUtils utils = new SociologyItemUtils(vertical_layout,sociologyBean,mActivity);
//            utils.start();
//        }
        for (int i=0;i<sociologyBeans.size();i++){
//            if(i==0){
//                continue;
//            }
            SociologyBean sociologyBean=sociologyBeans.get(0);
            SociologyItemUtils utils = new SociologyItemUtils(vertical_layout,sociologyBean,mActivity);
            utils.start();
        }
    }

    @Override
    public void error(String error) {
        progress.setVisibility(View.GONE);
        errorLayout.setVisibility(View.VISIBLE);
        Toast.makeText(mActivity,error,Toast.LENGTH_SHORT).show();
    }
}
