package com.qk.party.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qk.party.R;
import com.qk.party.bean.TaskDetailsBean;

import java.util.List;

/**
 * @package： com.qk.party.adapter
 * @class: EnclosureAdapter
 * @author:  小飞
 * @date: 2017/11/10 9:32
 * @描述：
 */

public class EnclosureAdapter extends BaseQuickAdapter<TaskDetailsBean.Attach,BaseViewHolder> {
    public EnclosureAdapter(@Nullable List<TaskDetailsBean.Attach> data) {
        super(R.layout.enclosure_layout,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskDetailsBean.Attach item) {
        helper.setText(R.id.enclosure_text,item.getOriginName());
    }
}
