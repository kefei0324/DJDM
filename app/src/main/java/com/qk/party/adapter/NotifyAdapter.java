package com.qk.party.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qk.party.R;
import com.qk.party.bean.NotifyBean;
import com.qk.party.utils.DateUtils;
import com.qk.party.utils.MultiItemPatyType;

import java.util.List;

/**
 * @package： com.qk.party.adapter
 * @class: NotifyAdapter
 * @author:  小飞
 * @date: 2017/10/27 10:29
 * @描述：
 */
public class NotifyAdapter extends BaseMultiItemQuickAdapter<NotifyBean,BaseViewHolder> {
    private Context context;
    public NotifyAdapter(Context context, @Nullable List<NotifyBean> data) {
        super(data);
        addItemType(MultiItemPatyType.RW,R.layout.notify_rw_item_layout);
        addItemType(MultiItemPatyType.HY,R.layout.notify_hy_item_layout);
        addItemType(MultiItemPatyType.TG,R.layout.notify_item_layout);
        addItemType(MultiItemPatyType.SB,R.layout.notify_sb_item_layout);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, NotifyBean item) {
        switch (item.getMultiType()){
            case MultiItemPatyType.RW:
                TextView title = helper.getView(R.id.title);
                title.setText(item.getTypeString());
                TextView content = helper.getView(R.id.content);
                content.setText(item.getBt());
                TextView dept = helper.getView(R.id.dept);
                dept.setText(item.getDept());
                TextView time = helper.getView(R.id.time);
                time.setText(item.getCjsj());
                TextView isRead = helper.getView(R.id.isRead);
                TextView content_title = helper.getView(R.id.content_title);
                TextView dept_title = helper.getView(R.id.dept_title);
                if(item.getStatus()==1){
                    title.setTextColor(Color.parseColor("#868686"));
                    content_title.setTextColor(Color.parseColor("#868686"));
                    dept_title.setTextColor(Color.parseColor("#868686"));
                    content.setTextColor(Color.parseColor("#B3B3B3"));
                    dept.setTextColor(Color.parseColor("#B3B3B3"));
                    time.setTextColor(Color.parseColor("#B3B3B3"));
                    isRead.setVisibility(View.GONE);
                }else{
                    title.setTextColor(Color.BLACK);
                    content_title.setTextColor(Color.BLACK);
                    dept_title.setTextColor(Color.BLACK);
                    content.setTextColor(Color.parseColor("#515151"));
                    dept.setTextColor(Color.parseColor("#515151"));
                    time.setTextColor(Color.parseColor("#515151"));
                    isRead.setVisibility(View.VISIBLE);
                }
                break;
            case MultiItemPatyType.HY:
                TextView title1 = helper.getView(R.id.title);
                title1.setText(item.getTypeString());
                TextView content1 = helper.getView(R.id.content);
                content1.setText(item.getBt());
                TextView address = helper.getView(R.id.address);
                address.setText(item.getHydd());
                TextView hy_time = helper.getView(R.id.hy_time);
                hy_time.setText(item.getTime());
                TextView time1 = helper.getView(R.id.time);
                time1.setText(item.getCjsj());
                TextView notice = helper.getView(R.id.notice);
                notice.setText(item.getDept());
                TextView isRead1 = helper.getView(R.id.isRead);

                TextView content_title1 = helper.getView(R.id.content_title);
                TextView address_title1 = helper.getView(R.id.address_title);
                TextView hy_time_title1 = helper.getView(R.id.hy_time_title);
                TextView notice_title = helper.getView(R.id.notice_title);
                if(item.getStatus()==1){
                    title1.setTextColor(Color.parseColor("#868686"));
                    content_title1.setTextColor(Color.parseColor("#868686"));
                    address_title1.setTextColor(Color.parseColor("#868686"));
                    hy_time_title1.setTextColor(Color.parseColor("#868686"));
                    notice_title.setTextColor(Color.parseColor("#868686"));
                    content1.setTextColor(Color.parseColor("#B3B3B3"));
                    address.setTextColor(Color.parseColor("#B3B3B3"));
                    hy_time.setTextColor(Color.parseColor("#B3B3B3"));
                    time1.setTextColor(Color.parseColor("#B3B3B3"));
                    notice.setTextColor(Color.parseColor("#B3B3B3"));
                    isRead1.setVisibility(View.GONE);
                }else{
                    title1.setTextColor(Color.BLACK);
                    content_title1.setTextColor(Color.BLACK);
                    address_title1.setTextColor(Color.BLACK);
                    hy_time_title1.setTextColor(Color.BLACK);
                    notice_title.setTextColor(Color.BLACK);
                    content1.setTextColor(Color.parseColor("#515151"));
                    address.setTextColor(Color.parseColor("#515151"));
                    hy_time.setTextColor(Color.parseColor("#515151"));
                    time1.setTextColor(Color.parseColor("#515151"));
                    notice.setTextColor(Color.parseColor("#515151"));
                    isRead1.setVisibility(View.VISIBLE);
                }
                break;
            case MultiItemPatyType.TG:
                TextView title2 = helper.getView(R.id.title);
                title2.setText(item.getTypeString());
                TextView content2 = helper.getView(R.id.content);
                content2.setText(item.getBt());
                TextView synopsis = helper.getView(R.id.synopsis);
                synopsis.setText(item.getDept());
                TextView time2 = helper.getView(R.id.time);
                time2.setText(item.getCjsj());
                TextView isRead2 = helper.getView(R.id.isRead);
                TextView content_title2 = helper.getView(R.id.content_title);
                TextView synopsis_title2 = helper.getView(R.id.synopsis_title);
                if(item.getStatus()==1){
                    title2.setTextColor(Color.parseColor("#868686"));
                    content_title2.setTextColor(Color.parseColor("#868686"));
                    synopsis_title2.setTextColor(Color.parseColor("#868686"));
                    content2.setTextColor(Color.parseColor("#B3B3B3"));
                    synopsis.setTextColor(Color.parseColor("#B3B3B3"));
                    time2.setTextColor(Color.parseColor("#B3B3B3"));
                    isRead2.setVisibility(View.GONE);
                }else{
                    title2.setTextColor(Color.BLACK);
                    content_title2.setTextColor(Color.BLACK);
                    synopsis_title2.setTextColor(Color.BLACK);
                    content2.setTextColor(Color.parseColor("#515151"));
                    synopsis.setTextColor(Color.parseColor("#515151"));
                    time2.setTextColor(Color.parseColor("#515151"));
                    isRead2.setVisibility(View.VISIBLE);
                }
                break;
            case MultiItemPatyType.SB:
                helper.setText(R.id.time, item.getCjsj());
                helper.setText(R.id.content,item.getBt());
                helper.setText(R.id.title, item.getSource());
                break;
            default:
        }

    }
}
