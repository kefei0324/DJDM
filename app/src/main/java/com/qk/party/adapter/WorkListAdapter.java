package com.qk.party.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qk.party.R;
import com.qk.party.application.MyApplication;
import com.qk.party.bean.TaskListBean;
import com.qk.party.view.NumProgressView;

import java.util.List;
import java.util.Random;
/**
 * @package： com.qk.party.adapter
 * @class: WorkListAdapter
 * @author:  小飞
 * @date: 2017/10/28 15:44
 */
public class WorkListAdapter extends BaseQuickAdapter<TaskListBean,BaseViewHolder> {
    private Context context;
    public WorkListAdapter(Context context,List<TaskListBean> data) {
        super(R.layout.work_list_item,data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskListBean item) {
        NumProgressView progressView = helper.getView(R.id.progress);
        int progress = 0;
        try {
            if(!TextUtils.isEmpty(item.getProgress())){
                progress = (int) Float.parseFloat(item.getProgress());
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        progressView.setCurrentProgress(progress>100?100:progress);
        helper.setText(R.id.tv_title,item.getName());
        helper.setText(R.id.tv_source,"来源："+item.getSource());
        helper.setText(R.id.tv_time,"时间："+item.getCreate_at());
        if(MyApplication.smalltype==1){
            helper.setText(R.id.tv_tag,"追赶超越");
        }else {
            helper.setText(R.id.tv_tag,"党建重点工作");
        }
    }

}
