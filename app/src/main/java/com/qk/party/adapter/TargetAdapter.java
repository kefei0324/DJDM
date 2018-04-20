package com.qk.party.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qk.party.R;
import com.qk.party.bean.TargetBean;

import java.util.List;

/**
 * @package： com.qk.party.adapter
 * @class: TargetAdapter
 * @author:  小飞
 * @date: 2017/11/2 18:11
 * @描述：
 */

public class TargetAdapter extends BaseQuickAdapter<TargetBean,BaseViewHolder> {
    public TargetAdapter(@Nullable List<TargetBean> data) {
        super(R.layout.target_city_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TargetBean item) {
        helper.setText(R.id.tv_title,item.getContent()+"："+item.getKpi());
        helper.setText(R.id.score,"分值："+item.getScore());
        helper.setText(R.id.target,"目标："+item.getTarget()+item.getUnit());
        helper.setText(R.id.tv_time,"主管单位："+item.getDw());
        helper.setText(R.id.tv_tag,"主管领导："+item.getUser());
    }
}
