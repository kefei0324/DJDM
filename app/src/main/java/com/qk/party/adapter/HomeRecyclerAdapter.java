package com.qk.party.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qk.party.R;
import com.qk.party.bean.Notice;
import com.qk.party.utils.MultiItemType;

import java.util.List;

/**
 * @author ：Think
 * 创建于 2017/10/17 12:26
 */

public class HomeRecyclerAdapter extends BaseMultiItemQuickAdapter<Notice,BaseViewHolder> {
    private Context context;
    public HomeRecyclerAdapter(Context context,List<Notice> data) {
        super(data);
        addItemType(MultiItemType.SINGLE, R.layout.item_text_image);
        addItemType(MultiItemType.THREE, R.layout.item_image);
        addItemType(MultiItemType.TEXT, R.layout.item_text);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Notice item) {
        switch (item.getItemType()){
            case MultiItemType.SINGLE:
                helper.setText(R.id.title,item.getTitle());
                helper.setText(R.id.source,item.getOptionUser());
                helper.setText(R.id.date,item.getPublicTime());
                helper.setText(R.id.comment_num,"浏览"+item.getYdcs());
                Glide.with(context).load(item.getImgList().get(0)).centerCrop().placeholder(R.mipmap.loading_icon).thumbnail(0.1f).into((ImageView) helper.getView(R.id.item_img));
                break;
            case MultiItemType.THREE:
                helper.setText(R.id.title,item.getTitle());
                helper.setText(R.id.source,item.getOptionUser());
                helper.setText(R.id.date,item.getPublicTime());
                helper.setText(R.id.comment_num,"浏览"+item.getYdcs());
                Glide.with(context).load(item.getImgList().get(0)).centerCrop().placeholder(R.mipmap.loading_icon).thumbnail(0.1f).into((ImageView) helper.getView(R.id.image1));
                Glide.with(context).load(item.getImgList().get(1)).centerCrop().placeholder(R.mipmap.loading_icon).thumbnail(0.1f).into((ImageView) helper.getView(R.id.image2));
                Glide.with(context).load(item.getImgList().get(2)).centerCrop().placeholder(R.mipmap.loading_icon).thumbnail(0.1f).into((ImageView) helper.getView(R.id.image3));
                break;
            default:
                helper.setText(R.id.title,item.getTitle());
                helper.setText(R.id.source,item.getOptionUser());
                helper.setText(R.id.date,item.getPublicTime());
                helper.setText(R.id.comment_num,"浏览"+item.getYdcs());
                break;
        }
    }
}
