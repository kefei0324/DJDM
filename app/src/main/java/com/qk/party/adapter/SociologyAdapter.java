package com.qk.party.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qk.party.R;
import com.qk.party.bean.SociologyBean;

import java.util.List;
/**
 * @package： com.qk.party.adapter
 * @class: SociologyAdapter
 * @author:  小飞
 * @date: 2017/10/25 18:13
 * @描述：
 */
public class SociologyAdapter extends BaseQuickAdapter<SociologyBean,BaseViewHolder> {
    private Context context;
    public SociologyAdapter(@Nullable List<SociologyBean> data,Context context) {
        super(R.layout.sociology_item,data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, SociologyBean item) {
        helper.setText(R.id.title,item.getKpi());

    }
}
