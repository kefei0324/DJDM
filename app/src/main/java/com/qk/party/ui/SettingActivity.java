package com.qk.party.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qk.party.R;
import com.qk.party.base.BaseActivity;
import com.qk.party.bean.LoginBean;
import com.qk.party.utils.ActivityManagers;
import com.qk.party.utils.DataCleanManager;

import org.litepal.crud.DataSupport;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static java.util.ResourceBundle.clearCache;

/**
 * @package： com.qk.party.ui
 * @class: SettingActivity
 * @author: 小飞
 * @date: 2017/10/17 17:46
 * @描述：设置
 */
public class SettingActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.cache)
    TextView cache;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        back.setVisibility(View.VISIBLE);
        title.setText("设置");
        try {
            cache.setText(DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
            cache.setText("0K");
        }
    }

    @OnClick({R.id.back, R.id.updatepw, R.id.clean, R.id.exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.updatepw:
                startActivity(new Intent(SettingActivity.this,ModificationPwActivity.class));
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_right);
                break;
            case R.id.clean:
                if("0K".equals(cache.getText().toString())){
                    Toast.makeText(this,"缓存已清空",Toast.LENGTH_SHORT).show();
                    return;
                }
                alertClearCache();
                break;
            default:
                DataSupport.deleteAll(LoginBean.class);
                startActivity(new Intent(SettingActivity.this,LoginActivity.class));
                ActivityManagers.getAppManager().finishAllActivity();
                break;
        }
    }
    @SuppressLint("NewApi")
    private void alertClearCache() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
        builder.setTitle("确认清除缓存吗?").setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DataCleanManager.clearAllCache(SettingActivity.this);
                        cache.setText("0K");
                    }
                });
        builder.show();
    }
}
