package com.qk.party.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qk.party.R;
import com.qk.party.bean.PartyMemBean;

import java.util.List;

/**
 * @package： com.qk.party.adapter
 * @class: PartyMembershipAdapter
 * @author:  小飞
 * @date: 2017/10/30 15:59
 * @描述：
 */
public class PartyMembershipAdapter extends BaseQuickAdapter<PartyMemBean,BaseViewHolder> {

    public PartyMembershipAdapter(@Nullable List<PartyMemBean> data) {
        super(R.layout.membership_layout,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PartyMemBean item) {
        helper.setText(R.id.time,item.getJnsj());
        helper.setText(R.id.title,"党费提醒");
        helper.setText(R.id.price,item.getJnje()+"元");
        helper.setText(R.id.name,item.getXm());
        helper.setText(R.id.wages,item.getGz());
        helper.setText(R.id.start_month,item.getStart_month());
        helper.setText(R.id.end_month,item.getEnd_month());
        helper.setText(R.id.text_time,item.getJnsj());
        helper.setText(R.id.proportion,item.getJnbl());
        helper.setText(R.id.price1,item.getHdjnje());
        helper.setText(R.id.price2,item.getBcjnje());
    }
}
