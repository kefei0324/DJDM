package com.qk.party.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qk.party.R;
import com.qk.party.bean.ThreeSessionsBean;

import java.util.List;

/**
 * @package： com.qk.party.adapter
 * @class: ThreeSessionsAdapter
 * @author:  小飞
 * @date: 2017/10/31 8:59
 * @描述：
 */

public class ThreeSessionsAdapter extends BaseQuickAdapter<ThreeSessionsBean,BaseViewHolder> {
    public ThreeSessionsAdapter(@Nullable List<ThreeSessionsBean> data) {
        super(R.layout.three_sessions_item_layout,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ThreeSessionsBean item) {
        helper.setText(R.id.organization,item.getZzmc());
        helper.setText(R.id.name,item.getDydhwcqk());
        helper.setText(R.id.meeting,item.getDdhwcqk());
        helper.setText(R.id.no_meeting,item.getDxzhwcqk());
        helper.setText(R.id.modality,item.getDkwcqk());
    }
}
