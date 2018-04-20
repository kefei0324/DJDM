package com.qk.party.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qk.party.R;
import com.qk.party.bean.Paety;

import java.util.List;

/**
 * 作者：Think
 * 创建于 2017/11/9 15:19
 */

public class TextAdapter extends BaseQuickAdapter<Paety,BaseViewHolder> {
    public TextAdapter(@Nullable List<Paety> data) {
        super(R.layout.party_members_rec_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Paety item) {
        String month = item.getMonth().split("-")[1];
        if (month.startsWith("0")) {
            month = month.replace("0", "");
        }
        helper.setText(R.id.month, month + "月");
        TextView stateView = helper.getView(R.id.state);
        TextView priceView = helper.getView(R.id.price);
        priceView.setText(item.getBcjnje());
        String status = item.getStatus();
        stateView.setText(status);
        if(status.equals("已缴纳")){
            priceView.setTextColor(Color.parseColor("#999999"));
        }else{
            priceView.setTextColor(Color.parseColor("#DE6B43"));
        }
    }
}
