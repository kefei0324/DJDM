package com.qk.party.adapter;

import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qk.party.R;
import com.qk.party.bean.TaskDetailsBean;
import com.qk.party.utils.imageGet;

import java.util.List;

/**
 * @package： com.qk.party.adapter
 * @class: RwHyAaapter
 * @author:  小飞
 * @date: 2017/11/10 14:12
 * @描述：
 */

public class RwHyAadapter extends BaseQuickAdapter<TaskDetailsBean.Rwhy,BaseViewHolder> {
    public RwHyAadapter(@Nullable List<TaskDetailsBean.Rwhy> data) {
        super(R.layout.rwhy_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskDetailsBean.Rwhy item) {
        helper.setText(R.id.title,item.getSubject());
        helper.setText(R.id.compere,item.getPresenter());
        helper.setText(R.id.forms,item.getTypeString());
        helper.setText(R.id.time,item.getMeeting_date());
        helper.setText(R.id.address,item.getAddress());
        TextView textView = helper.getView(R.id.content);
        textView.setText(Html.fromHtml(item.getContent(),
                new imageGet(mContext,textView),null));
        /***
         *
         * 服务器返回是null    对服务返回的进行判断null和数组长度判断 防止null和数组越界
         * */
        if(item.getUsers() !=null&&item.getUsers().size()!=0){
            StringBuffer user = new StringBuffer();
            for (TaskDetailsBean.Users users : item.getUsers()) {
                user.append(users.getXm()).append(",");
            }
            helper.setText(R.id.personnel,user.toString().subSequence(0,user.length()-1));
        }else{
            helper.setText(R.id.personnel,"无");
        }

    }
}
