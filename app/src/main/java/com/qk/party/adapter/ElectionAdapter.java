package com.qk.party.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qk.party.R;
import com.qk.party.bean.ElectionBean;

import java.util.List;

/**
 * @package： com.qk.party.adapter
 * @class: ElectionAdapter
 * @author:  小飞
 * @date: 2017/10/30 16:53
 * @描述：
 */

public class ElectionAdapter extends BaseQuickAdapter<ElectionBean,BaseViewHolder> {
    public ElectionAdapter(@Nullable List<ElectionBean> data) {
        super(R.layout.election_item_layout,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ElectionBean item) {
        helper.setText(R.id.time,item.getHjsj());
        helper.setText(R.id.number,item.getJs()+"");
        helper.setText(R.id.name,item.getRqnx()+"");
        helper.setText(R.id.meeting,item.getYdhrs()+"");
        helper.setText(R.id.no_meeting,item.getSdhrs()+"");
        helper.setText(R.id.modality,item.getHjxjxs());
        helper.setText(R.id.situation,item.getXjqk());
        helper.setText(R.id.count,item.getCqry());
        helper.setText(R.id.no_count,item.getQqry());
    }
}
