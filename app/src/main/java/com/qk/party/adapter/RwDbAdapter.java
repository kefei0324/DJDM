package com.qk.party.adapter;

import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qk.party.R;
import com.qk.party.bean.TaskDetailsBean;
import com.qk.party.utils.imageGet;

import java.util.List;

/**
 * @package： com.qk.party.adapter
 * @class: RwDbAdapter
 * @author:  小飞
 * @date: 2017/11/10 13:52
 * @描述：
 */

public class RwDbAdapter extends BaseQuickAdapter<TaskDetailsBean.Rwdb,BaseViewHolder>{
    public RwDbAdapter(@Nullable List<TaskDetailsBean.Rwdb> data) {
        super(R.layout.rwdb_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskDetailsBean.Rwdb item) {
        helper.setText(R.id.date,item.getCk_date());
        helper.setText(R.id.member,item.getCk_leader());
        helper.setText(R.id.leader,item.getCk_member());

        TextView textView = helper.getView(R.id.content);
        textView.setText(Html.fromHtml(item.getContent(),
                new imageGet(mContext,textView),null));

        TextView textView1 = helper.getView(R.id.result);
        textView1.setText(Html.fromHtml(item.getResult(),
                new imageGet(mContext,textView1),null));

        TextView textView2 = helper.getView(R.id.opinion);
        textView2.setText(Html.fromHtml(item.getFeedback(),
                new imageGet(mContext,textView2),null));

    }
}
