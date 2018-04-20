package com.qk.party.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.qk.party.R;
import com.qk.party.adapter.TextAdapter;
import com.qk.party.application.MyApplication;
import com.qk.party.base.BaseActivity;
import com.qk.party.bean.Paety;
import com.qk.party.presenter.RemindPresenter;
import com.qk.party.utils.ShardUtil;
import com.qk.party.viewinterface.NetworkView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @package： com.qk.party.ui
 * @class: PartyMembershipActivity
 * @author: 小飞
 * @date: 2017/11/9 14:12
 * @描述：
 */
public class PartyMembershipActivity extends BaseActivity implements NetworkView<List<Paety>> {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.head_image)
    ImageView headImage;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.department)
    TextView department;
    @BindView(R.id.data)
    TextView data;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.error)
    RelativeLayout errorLayout;
    @BindView(R.id.no_data_layout)
    RelativeLayout noDataLayout;

    private RemindPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_membership);
        ButterKnife.bind(this);

        presenter = new RemindPresenter(this);
        back.setVisibility(View.VISIBLE);
        title.setText("党费提醒");
        Glide.with(this).load(MyApplication.userInfo.getPhotos()).centerCrop().transform(new BitmapTransformation(this) {
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
        }).into(headImage);
        name.setText(MyApplication.userInfo.getXm());
        department.setText(MyApplication.userInfo.getZzmc());
        data.setText(Calendar.getInstance().get(Calendar.YEAR)+"年");

        recycler.setLayoutManager(new LinearLayoutManager(this));
        presenter.getPartyMembership(ShardUtil.getPreferenceString(PartyMembershipActivity.this,"access_token"),MyApplication.userInfo.getUserId());

        errorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);
                errorLayout.setVisibility(View.GONE);
                presenter.getPartyMembership(ShardUtil.getPreferenceString(PartyMembershipActivity.this,"access_token"),MyApplication.userInfo.getUserId());
            }
        });
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void success(List<Paety> paeties) {
        try {
            progress.setVisibility(View.GONE);
            errorLayout.setVisibility(View.GONE);
            if(paeties.size()==0){
                noDataLayout.setVisibility(View.VISIBLE);
            }else {
                noDataLayout.setVisibility(View.GONE);
            }
            recycler.setAdapter(new TextAdapter(paeties));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void error(String error) {
        progress.setVisibility(View.GONE);
        errorLayout.setVisibility(View.VISIBLE);
    }
}
