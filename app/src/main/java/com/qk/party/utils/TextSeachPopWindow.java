package com.qk.party.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.qk.party.R;


/**
 * 作者：Think
 * 创建于 2017/10/27 15:09
 */

public class TextSeachPopWindow extends PopupWindow {
    private Activity context;
    private View mPopView;
    private EditText search;
    private DetermineListener listener;

    public  TextSeachPopWindow(Activity context) {
        super(context);
        this.context = context;
        initView();
    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(context);
        mPopView = inflater.inflate(R.layout.seach_pop_layout, null);
        search = (EditText) mPopView.findViewById(R.id.search);
        this.setContentView(mPopView);
        this.setFocusable(true);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId== EditorInfo.IME_ACTION_SEND ||(event!=null&&event.getKeyCode()== KeyEvent.KEYCODE_ENTER))
                {
                    if(TextUtils.isEmpty(search.getText().toString().trim())){
                        if(listener!=null){
                            listener.onClick("");
                        }
                        dismiss();
                        return true;
                    }
                    if(listener!=null){
                        listener.onClick(search.getText().toString().trim());
                    }
                    dismiss();
                    return true;
                }
                return false;
            }
        });

    }
    @Override
    public void dismiss() {
        super.dismiss();
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(search.getWindowToken(), 0);
    }
    public void show(View view){
        showAtLocation(view, Gravity.BOTTOM,0,0);
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        //得到InputMethodManager的实例
        if (imm.isActive()) {
            //如果开启
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
            //关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
        }
        backgroundAlpha(0.6f);
    }
    public TextSeachPopWindow setListener(DetermineListener listener) {
        this.listener = listener;
        return this;
    }
    public interface DetermineListener{
        void onClick(String content);
    }
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        context.getWindow().setAttributes(lp);
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }
}
