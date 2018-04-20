package com.qk.party.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qk.party.R;
import com.qk.party.application.MyApplication;
import com.qk.party.base.BaseActivity;
import com.qk.party.presenter.UserPresenter;
import com.qk.party.utils.ActivityManagers;
import com.qk.party.utils.ShardUtil;
import com.qk.party.viewinterface.NetworkView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @package： com.qk.party.ui
 * @class: ModificationPwActivity
 * @author: 小飞
 * @date: 2017/10/17 18:21
 * @描述：
 */
public class ModificationPwActivity extends BaseActivity implements NetworkView<String> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.newpw)
    EditText newpw;
    @BindView(R.id.againpw)
    EditText againpw;
    private String new_pw;
    private String again_pw;
    private UserPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modification_pw);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        presenter = new UserPresenter(this);
        back.setVisibility(View.VISIBLE);
        title.setText("修改密码");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @OnClick(R.id.finish)
    public void onViewClicked() {
        new_pw = newpw.getText().toString().trim();
        again_pw = againpw.getText().toString().trim();

        if (TextUtils.isEmpty(new_pw)) {
            toastMsg("请输入新密码！");
            return;
        }
        if (newpw.length() < 6) {
            toastMsg("密码长度必须不得少于6位");
            return;
        }
        if (TextUtils.isEmpty(again_pw)) {
            toastMsg("确认密码不能为空！");
            return;
        }
        if (!again_pw.equals(new_pw)) {
            toastMsg("两次输入的密码不一致!请重新输入");
            return;
        }
        presenter.updatePass(ShardUtil.getPreferenceString(ModificationPwActivity.this,"access_token"), MyApplication.userInfo.getUserId(),again_pw);
    }
    /**
     * 提示信息
     *
     * @param msg
     */
    public void toastMsg(String msg) {
        Toast.makeText(ModificationPwActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success(String s) {
        toastMsg(s);
        finish();
    }

    @Override
    public void error(String error) {
        toastMsg(error);
    }
}
