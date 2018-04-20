package com.qk.party.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.qk.party.R;


/**
 *
 * @项目名： zhiqin_r
 * @包名： com.qk.utils.view
 * @文件名: NewsPopupWindow
 * @创建者: 张宇飞
 * @创建时间: 2017/8/15 13:53
 * @描述：
 */
public class EditPopupWindow extends PopupWindow{
    private Activity context;
    private View mPopView;
    private TextView title;
    private EditText content;
    private TextView determine;
    private TextView prompt;
    private LinearLayout seekBer_layout;
    private TextView progress_num;
    private SeekBar seekBer;
    private int currentProgress;
    private int type = 0; //0 昵称 1邮箱 2 简介

    private DetermineListener listener;
    private int textViewPaddingLeft = 0;

    public  EditPopupWindow(Activity context,int type) {
        this(context,type,0);
        initView();
    }
    public  EditPopupWindow(Activity context,int type,int currentProgress) {
        super(context);
        this.context = context;
        this.type = type;
        this.currentProgress = currentProgress;
        initView();
    }
    /**
     * 初始化
     * */
    @SuppressLint("WrongConstant")
    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(context);
        mPopView = inflater.inflate(R.layout.custom_popup_window, null);
        title = (TextView) mPopView.findViewById(R.id.title);
        content = (EditText) mPopView.findViewById(R.id.content);
        determine = (TextView) mPopView.findViewById(R.id.determine);
        prompt = (TextView) mPopView.findViewById(R.id.prompt);
        seekBer_layout = (LinearLayout) mPopView.findViewById(R.id.seekBer_layout);
        progress_num = (TextView) mPopView.findViewById(R.id.progress_num);
        seekBer = (SeekBar) mPopView.findViewById(R.id.seekBer);
        textViewPaddingLeft = ((RelativeLayout.LayoutParams) progress_num.getLayoutParams()).leftMargin;
        seekBer.setProgress(currentProgress);
        seekBer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int width = seekBar.getWidth() - seekBar.getPaddingLeft() - seekBar.getPaddingRight();
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)progress_num.getLayoutParams();
                layoutParams.leftMargin = (int) (((float) progress / seekBar.getMax()) * width);
                layoutParams.leftMargin += seekBar.getPaddingRight() - progress_num.getWidth() / 2;
                progress_num.setText(progress+"%");
                progress_num.setLayoutParams(layoutParams);
                currentProgress = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBer_layout.setVisibility(View.GONE);
        title.setVisibility(View.GONE);
        if(type==1){
            seekBer_layout.setVisibility(View.VISIBLE);
            prompt.setVisibility(View.INVISIBLE);
            content.setHint("输入反馈内容");
            content.setLines(3);
            content.setFilters(new InputFilter[] { new InputFilter.LengthFilter(200) });
        }

        determine.setEnabled(false);
        this.setContentView(mPopView);
        this.setFocusable(true);
        this.setOutsideTouchable(true);//获取外部触摸事件
        this.setTouchable(true);//能够响应触摸事件
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setBackgroundDrawable(new ColorDrawable(0x88000000));
        this.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
        content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==0){
                    determine.setEnabled(false);
                }else{
                    determine.setEnabled(true);
                }
            }
        });
        mPopView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(EditPopupWindow.this.isShowing()){
                    dismiss();
                }
            }
        });
        determine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onClick(content.getText().toString(),currentProgress);
                    dismiss();
                }
            }
        });
    }

    public EditPopupWindow setTitle(String titleStr) {
        title.setText(titleStr);
        return this;
    }

    public EditPopupWindow setContent(String contentStr) {
        content.setText(contentStr);
        return this;
    }

    public EditPopupWindow setPrompt(String promptStr) {
        prompt.setText(promptStr);
        return this;
    }


    @Override
    public void dismiss() {
        super.dismiss();
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(content.getWindowToken(), 0);
    }
    public interface DetermineListener{
        void onClick(String content, int type);
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
    public EditPopupWindow setListener(DetermineListener listener) {
        this.listener = listener;
        return this;
    }
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        context.getWindow().setAttributes(lp);
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }
}
