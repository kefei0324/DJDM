package com.qk.party.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qk.party.R;
import com.qk.party.bean.TaskDetailsBean;
import com.qk.party.utils.DateUtils;

import java.util.List;

/**
 * @package： com.qk.party.adapter
 * @class: MissionAdapter
 * @author:  小飞
 * @date: 2017/10/19 9:28
 * @描述：
 */
public class MissionAdapter extends BaseQuickAdapter<TaskDetailsBean.RwshListBean,BaseViewHolder>{
    public MissionAdapter(@Nullable List<TaskDetailsBean.RwshListBean> data) {
        super(R.layout.mission_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskDetailsBean.RwshListBean item) {
        helper.setText(R.id.name,item.getShld()+"  回复了:");
        helper.setText(R.id.time, DateUtils.time(item.getCreated_at()));
        helper.setText(R.id.content,item.getSjshyj());
    }
}
