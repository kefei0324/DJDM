package com.qk.party.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.orhanobut.logger.Logger;
import com.qk.party.R;


/**
 * 作者：Think
 * 创建于 2017/11/2 14:52
 */

public class imageGet implements Html.ImageGetter {
    private Context context;
    private TextView textView;
    public imageGet(Context context,TextView textView) {
        this.context = context;
        this.textView = textView;
    }

    @Override
    public Drawable getDrawable(String source) {
        final URLDrawable urlDrawable = new URLDrawable();
        Glide.with(context).load(source).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                urlDrawable.bitmap = resource;
                Logger.d("加载的图片，Width：" + resource.getWidth() + "，Height：" + resource.getHeight());
                urlDrawable.setBounds(0, 0, resource.getWidth(), resource.getHeight());
                textView.invalidate();
                textView.setText(textView.getText());
            }
        });
        return urlDrawable;
    }
    public class URLDrawable extends BitmapDrawable {
        public Bitmap bitmap;

        @Override
        public void draw(Canvas canvas) {
            super.draw(canvas);
            if (bitmap != null) {
                canvas.drawBitmap(bitmap, 0, 0, getPaint());
            }
        }
    }
}
