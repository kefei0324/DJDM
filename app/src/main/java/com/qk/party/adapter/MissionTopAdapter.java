package com.qk.party.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qk.party.R;
import com.qk.party.bean.TaskDetailsBean;
import com.qk.party.utils.DateUtils;
import com.qk.party.utils.imageGet;
import java.util.List;


/**
 * @package： com.qk.party.adapter
 * @class: MissionTopAdapter
 * @author:  小飞
 * @date: 2017/10/31 14:54
 * @描述：
 */

public class MissionTopAdapter extends BaseQuickAdapter<TaskDetailsBean.OListBean,BaseViewHolder> {
        public MissionTopAdapter(@Nullable List<TaskDetailsBean.OListBean> data) {
            super(R.layout.comment_top_layout,data);
        }
        @Override
        protected void convert(BaseViewHolder helper, final TaskDetailsBean.OListBean item) {
                helper.setText(R.id.feedback_name,item.getSbr());
                helper.setText(R.id.feedback_date, DateUtils.time(item.getSbsj()));
                TextView textView = helper.getView(R.id.feedback_content);
                textView.setText(Html.fromHtml(item.getJznr(),
                        new imageGet(mContext,textView),null));
                RecyclerView recyclerView = helper.getView(R.id.comment);
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                recyclerView.setAdapter(new MissionAdapter(item.getRwshList()));

        }

}
