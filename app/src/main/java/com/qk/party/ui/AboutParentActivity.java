package com.qk.party.ui;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qk.party.R;
import com.qk.party.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @package： com.qk.party.ui
 * @class: AboutParentActivity
 * @author: 小飞
 * @date: 2017/11/9 13:48
 * @描述：
 */
public class AboutParentActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.version)
    TextView version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_parent);
        ButterKnife.bind(this);
        title.setText("关于我们");
        back.setVisibility(View.VISIBLE);
        version.setText("V"+getVersion());
    }

    @OnClick({R.id.back, R.id.about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            default:
                Intent intent = new Intent();
                intent.setClass(AboutParentActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
        }
    }
    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
