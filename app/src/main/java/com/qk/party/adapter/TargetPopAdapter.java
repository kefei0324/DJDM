package com.qk.party.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qk.party.R;
import com.qk.party.bean.TargetTypeBean;

import java.util.List;

/**
 * @package： com.qk.party.adapter
 * @class: TargetPopAdapter
 * @author:  小飞
 * @date: 2017/11/2 17:45
 * @描述：
 */

public class TargetPopAdapter extends BaseQuickAdapter<TargetTypeBean,BaseViewHolder> {
    public TargetPopAdapter(@Nullable List<TargetTypeBean> data) {
        super(R.layout.target_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TargetTypeBean item) {
        helper.setText(R.id.target_text,item.getName());
    }
}
