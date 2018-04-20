package com.qk.party.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qk.party.R;
import com.qk.party.bean.TaskDetailsBean;
import com.qk.party.ui.MissionDetailsActivity;
import com.qk.party.utils.DateUtils;
import com.qk.party.utils.imageGet;

import java.util.List;

/**
 * @package： com.qk.party.adapter
 * @class: RwsbAdapter
 * @author:  小飞
 * @date: 2017/11/13 11:05
 * @描述：
 */

public class RwsbAdapter extends BaseQuickAdapter<TaskDetailsBean.OListBean,BaseViewHolder> {
    public RwsbAdapter(@Nullable List<TaskDetailsBean.OListBean> data) {
        super(R.layout.rwsb_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final TaskDetailsBean.OListBean item) {
        helper.setText(R.id.name,item.getSbr());
        helper.setText(R.id.department,item.getSbdw());
        helper.setText(R.id.state,"");
        if(!TextUtils.isEmpty(item.getWcjd())){
            helper.setText(R.id.state,item.getWcjd()+"%");
        }

        helper.setText(R.id.date, item.getCreated_at());
        TextView textView = helper.getView(R.id.content);
        textView.setText(Html.fromHtml(item.getJznr(),
                new imageGet(mContext,textView),null));

        RecyclerView recyclerView = helper.getView(R.id.attach);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        EnclosureAdapter adapter = new EnclosureAdapter(item.getAttachList());
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String url = item.getAttachList().get(position).getDisplayPath();
                if (TextUtils.isEmpty(url)) {
                    Toast.makeText(mContext, "下载地址错误", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(url);
                intent.setData(content_url);
                mContext.startActivity(intent);
            }
        });

    }
}
