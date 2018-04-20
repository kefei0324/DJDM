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
 * 作者：Think
 * 创建于 2017/11/10 14:13
 */

public class RwjlAdapter extends BaseQuickAdapter<TaskDetailsBean.Rwjl,BaseViewHolder>{
    public RwjlAdapter(@Nullable List<TaskDetailsBean.Rwjl> data) {
        super(R.layout.rwjl_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskDetailsBean.Rwjl item) {
        helper.setText(R.id.date,item.getCreate_time());
        TextView textView = helper.getView(R.id.content);
        textView.setText(Html.fromHtml(item.getContent(),
                new imageGet(mContext,textView),null));
        helper.setText(R.id.personnel,item.getName());
    }
}
