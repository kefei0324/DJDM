package com.qk.party.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.qk.party.R;
import com.qk.party.application.MyApplication;
import com.qk.party.base.BaseFragment;
import com.qk.party.bean.LoginBean;
import com.qk.party.ui.AboutActivity;
import com.qk.party.ui.AboutParentActivity;
import com.qk.party.ui.InformationActivity;
import com.qk.party.ui.NotifyListActivity;
import com.qk.party.ui.PartyMembershipActivity;
import com.qk.party.ui.RemindActivity;
import com.qk.party.ui.SettingActivity;
import com.qk.party.utils.RxBus;

import org.litepal.crud.DataSupport;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * @author ：Think
 *         创建于 2017/10/17 14:16
 */

public class PersonalFragment extends BaseFragment {
    @BindView(R.id.head_image)
    ImageView headImage;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.department)
    TextView department;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.top_title)
    LinearLayout top_title;
    private LoginBean userInfo;
    @BindView(R.id.remind)
    LinearLayout remind;
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.personal_fragment, container, false);
    }

    @Override
    public void initFindViewById(View view) {
        super.initFindViewById(view);
        if(MyApplication.userInfo==null){
            if(DataSupport.findAll(LoginBean.class).size()!=0){
                userInfo = DataSupport.findAll(LoginBean.class).get(0);
            }
        }else{
            userInfo = MyApplication.userInfo;
        }
        name.setText(userInfo.getXm());
        department.setText(userInfo.getZzmc());
        phone.setText(userInfo.getSjhm());
        top_title.setPadding(0, getStatusBarHeight() , 0, 0);
        title.setText("个人中心");
        RxBus.getInstance().toObservable(String.class).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                name.setText(s);
            }
        });
        Glide.with(mActivity).load(userInfo.getPhotos()).centerCrop().transform(new BitmapTransformation(mActivity) {
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
        if(userInfo.isIsgeneraldy()){
            remind.setVisibility(View.VISIBLE);
        }else {
            remind.setVisibility(View.GONE);
        }
    }
    @OnClick({R.id.my,R.id.about, R.id.setting,R.id.task, R.id.meeting, R.id.presentation, R.id.report, R.id.remind})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.my:
                intent.setClass(mActivity, InformationActivity.class);
                startActivity(intent);
                break;
            case R.id.setting:
                intent.setClass(mActivity, SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.about:
                intent.setClass(mActivity, AboutParentActivity.class);
                startActivity(intent);
                break;
            case R.id.task:
                intent.setClass(mActivity, NotifyListActivity.class);
                intent.putExtra("title","任务通知");
                intent.putExtra("userid",userInfo.getUserId());
                intent.putExtra("type",1);
                startActivity(intent);
                break;
            case R.id.meeting:
                intent.setClass(mActivity, NotifyListActivity.class);
                intent.putExtra("title","会议通知");
                intent.putExtra("userid",userInfo.getUserId());
                intent.putExtra("type",3);
                startActivity(intent);
                break;
            case R.id.presentation:
                intent.setClass(mActivity, NotifyListActivity.class);
                intent.putExtra("title","通知公告");
                intent.putExtra("userid",userInfo.getUserId());
                intent.putExtra("type",4);
                startActivity(intent);
                break;
            case R.id.report:
                intent.setClass(mActivity, NotifyListActivity.class);
                intent.putExtra("title","上报通知");
                intent.putExtra("userid",userInfo.getUserId());
                intent.putExtra("type",5);
                startActivity(intent);
                break;
            default:
                intent.setClass(mActivity, PartyMembershipActivity.class);
                startActivity(intent);
                break;
        }

    }
}
