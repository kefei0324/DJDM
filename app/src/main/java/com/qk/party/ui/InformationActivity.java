package com.qk.party.ui;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.qk.party.R;
import com.qk.party.application.MyApplication;
import com.qk.party.base.BaseActivity;
import com.qk.party.bean.LoginBean;
import com.qk.party.presenter.UserPresenter;
import com.qk.party.utils.EditPopupWindow;
import com.qk.party.utils.RxBus;
import com.qk.party.utils.ShardUtil;
import com.qk.party.viewinterface.NetworkView;

import org.litepal.crud.DataSupport;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @package： com.qk.party.ui
 * @class: InformationActivity
 * @author: 小飞
 * @date: 2017/10/18 15:19
 * @描述：
 */
public class InformationActivity extends BaseActivity implements NetworkView<String>{

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.heard_img)
    ImageView heardImg;
    @BindView(R.id.nickname)
    TextView nickname;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.gender_layout)
    LinearLayout genderLayout;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.organization)
    TextView organization;
    @BindView(R.id.occupation)
    TextView occupation;
    private LoginBean userInfo;
    private UserPresenter presenter;
    private String contents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        presenter = new UserPresenter(this);
        back.setVisibility(View.VISIBLE);
        title.setText("个人信息");
        if(MyApplication.userInfo==null){
            if(DataSupport.findAll(LoginBean.class).size()!=0){
                userInfo = DataSupport.findAll(LoginBean.class).get(0);
            }
        }else {
            userInfo = MyApplication.userInfo;
        }
        nickname.setText(userInfo.getXm());
        organization.setText(userInfo.getZzmc());
        name.setText(userInfo.getName());
        phone.setText(userInfo.getSjhm());
        occupation.setText(userInfo.getZwjb());
        Glide.with(this).load(userInfo.getPhotos()).centerCrop().transform(new BitmapTransformation(this) {
            @Override
            protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
                if(toTransform == null){
                    return null;
                }
                int size = Math.min(toTransform.getWidth(), toTransform.getHeight());
                int x = (toTransform.getWidth() - size) / 2;
                int y = (toTransform.getHeight() - size) / 2;

                // TODO this could be acquired from the pool too
                Bitmap squared = Bitmap.createBitmap(toTransform, x, y, size, size);

                Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
                if (result == null) {
                    result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
                }
                Canvas canvas = new Canvas(result);
                Paint paint = new Paint();
                paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
                paint.setAntiAlias(true);
                float r = size / 2f;
                canvas.drawCircle(r, r, r, paint);
                return result;
            }

            @Override
            public String getId() {
                return getClass().getName();
            }
        }).into(heardImg);
    }

    @OnClick({R.id.back, R.id.nickname_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            default:
                new EditPopupWindow(InformationActivity.this, 0)
                        .setContent(userInfo.getXm())
                        .setListener(new EditPopupWindow.DetermineListener() {
                            @Override
                            public void onClick(String content, int type) {
                                contents = content;
                                presenter.updateXm(ShardUtil.getPreferenceString(InformationActivity.this,"access_token"),userInfo.getUserId(),content);
                            }
                        }).show(findViewById(R.id.root));
                break;
        }
    }

    @Override
    public void success(String s) {
        userInfo.setXm(contents);
        ContentValues values = new ContentValues();
        values.put("xm", contents);
        DataSupport.update(LoginBean.class, values, 1);
        nickname.setText(contents);
        RxBus.getInstance().post(contents);
        Toast.makeText(InformationActivity.this,s,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void error(String error) {
        Toast.makeText(InformationActivity.this,error,Toast.LENGTH_SHORT).show();
    }
}
