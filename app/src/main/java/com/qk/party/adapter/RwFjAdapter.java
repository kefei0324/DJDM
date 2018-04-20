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
 * @class: RwFjAdapter
 * @author:  小飞
 * @date: 2017/11/10 11:22
 * @描述：
 */

public class RwFjAdapter extends BaseQuickAdapter<TaskDetailsBean.Rwfj,BaseViewHolder> {
    public RwFjAdapter(@Nullable List<TaskDetailsBean.Rwfj> data) {
        super(R.layout.rwfj_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskDetailsBean.Rwfj item) {
            helper.setText(R.id.name,item.getReceiver());
            helper.setText(R.id.date,item.getWcsx());
            helper.setText(R.id.fj_date,item.getReport_date());
            helper.setText(R.id.state,item.getStatusString());
            TextView textView = helper.getView(R.id.rwyq);
            textView.setText(Html.fromHtml(item.getTask_content(),
                    new imageGet(mContext,textView),null));
    }
}
