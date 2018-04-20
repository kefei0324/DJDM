package com.qk.party.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import com.qk.party.utils.DensityUtils;

/**
 * @package： com.zyf.customview.view
 * @class: NumProgressView
 * @author:  小飞
 * @date: 2017/10/19 9:53
 * @描述：
 */

public class NumProgressView extends View {
    /**
     * 上下文对象
     * */
    private Context context;
    /**
     * 背景画笔
     * */
    private Paint mPaint;
    /**
     * 进度画笔
     * */
    private Paint progressPaint;
    /**
     * 文字画笔
     * */
    private Paint mTextPaint;
    /**
     * 宽
     * */
    private int width;
    /**
     * 高
     * */
    private int height;

    /**
     * 进度
     * */
    private int currentProgress = 100;
    /**
     * 最大步数
     * */
    private int MaxProgress = 100;
    private int steps;
    private int textheight;
    private int padding = 40;
    public NumProgressView(Context context) {
        this(context,null);
    }

    public NumProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NumProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#DFDFDF"));
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(DensityUtils.dpToPx(11));
        progressPaint = new Paint();
        progressPaint.setColor(Color.parseColor("#BA2120"));
        progressPaint.setAntiAlias(true);
        progressPaint.setStrokeWidth(DensityUtils.dpToPx(10));
        mTextPaint = new Paint();
        mTextPaint.setColor(Color.parseColor("#EB9451"));
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(DensityUtils.dpToPx(12));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = measureWidthSize(widthMeasureSpec);
        height = measureHeightSize(heightMeasureSpec);
        setMeasuredDimension(width,height);
        height  = height-20;
    }
    private int measureHeightSize(int measureSpec) {
        int length;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if(mode == MeasureSpec.EXACTLY){
            length = size;
        }else{
            length = (int) DensityUtils.dpToPx(40);
            if(mode == MeasureSpec.AT_MOST){
                length = Math.min(length,size);
            }
        }
        return length;
    }
    private int measureWidthSize(int measureSpec) {
        int length;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if(mode == MeasureSpec.EXACTLY){
            length = size;
        }else{
            WindowManager wm = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth();     // 屏幕宽度（像素）
            length = width;
            if(mode == MeasureSpec.AT_MOST){
                length = Math.min(length,size);
            }
        }
        return length;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawInside(canvas);
        drawTrapezoid(canvas);
        drawOutside(canvas,currentProgress);
        drawText(canvas,currentProgress);
    }



    /**
     * 画背景
     * */
    private void drawInside(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(DensityUtils.dpToPx(11));
        canvas.drawLine(padding,height/2,width-padding,height/2,mPaint);
    }
    /**
     * 画进度
     * */
    private void drawOutside(Canvas canvas, int progress) {
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        progressPaint.setStrokeWidth(DensityUtils.dpToPx(10));
        float pro = padding+(width-2*padding)*(progress/(MaxProgress*1.0F));
        if(progress != 0){
            canvas.drawLine(padding,height/2,pro,height/2,progressPaint);
        }

    }
    /**
     * 画字
     * */
    private void drawText(Canvas canvas, int steps) {
        String str = steps+"%";
        int textWidth = (int)mTextPaint.measureText(str);
        textheight = (int)(mTextPaint.descent() - mTextPaint.ascent());
        float pro1 = padding+(width-2*padding)*(85/(MaxProgress*1.0F));
        float pro = padding+(width-2*padding)*(70/(MaxProgress*1.0F));
        canvas.drawText(str,0,str.length(),pro+(pro1-pro)/2-textWidth/2,height/2+DensityUtils.dpToPx(15),mTextPaint);
    }
    private void drawTrapezoid(Canvas canvas) {
        float pro = padding+(width-2*padding)*(70/(MaxProgress*1.0F));
        Path path1=new Path();
        path1.moveTo(pro, height/2+DensityUtils.dpToPx(5));
        path1.lineTo(padding+(width-2*padding)*(73/(MaxProgress*1.0F)),height/2+DensityUtils.dpToPx(20));
        path1.lineTo(padding+(width-2*padding)*(82/(MaxProgress*1.0F)),height/2+DensityUtils.dpToPx(20));
        path1.lineTo(padding+(width-2*padding)*(85/(MaxProgress*1.0F)), height/2+DensityUtils.dpToPx(5));
        path1.close();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(DensityUtils.dpToPx(5));
        mPaint.setPathEffect(new CornerPathEffect(5));
        canvas.drawPath(path1,mPaint);
    }
    /**
     * 设置当前进度
     * */
    public void setCurrentProgress(int currentProgress) {
        this.currentProgress = currentProgress;
        invalidate();
    }
}
