package com.qk.party.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.jpush.android.api.JPushInterface;
import com.qk.party.R;
import com.qk.party.bean.LoginBean;
import com.qk.party.presenter.LoginPresenter;
import com.qk.party.utils.LoadDialog;
import com.qk.party.utils.ScreenUtils;
import com.qk.party.utils.ShardUtil;
import com.qk.party.utils.StatusBarUtil;
import com.qk.party.viewinterface.LoginView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @package： com.qk.party.ui
 * @class: LoginActivity
 * @author: 小飞
 * @date: 2017/10/17 9:40
 * @描述：登陆页面
 */
public class LoginActivity extends AppCompatActivity implements LoginView<LoginBean> {

    @BindView(R.id.user_name)
    EditText userName;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.login_logo)
    ImageView loginLogo;
    @BindView(R.id.login_layout)
    LinearLayout loginLayout;
    @BindView(R.id.root)
    RelativeLayout root;
    @BindView(R.id.year)
    TextView year;

    private LoginPresenter presenter;
    LoadDialog loadDialog;
    private int height = 0;
    private int navigationHeight = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
        presenter = new LoginPresenter(this);
        StatusBarUtil.StatusBarLightMode(this);
        if(getIntent().getIntExtra("type",0)==1){
            Toast.makeText(LoginActivity.this,"登录已过期",Toast.LENGTH_SHORT).show();
        }
        if(ScreenUtils.hasNavBar(this)){
            navigationHeight = ScreenUtils.getNavigationBarHeight(this);
        }
    }



    private void initView() {
        btnLogin.setEnabled(LoginBtnEnable());
        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                btnLogin.setEnabled(LoginBtnEnable());
            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                btnLogin.setEnabled(LoginBtnEnable());
            }
        });
        loadDialog = new LoadDialog(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDialog.show();
                btnLogin.setEnabled(false);
                presenter.login(userName.getText().toString().trim(),password.getText().toString().trim());
            }
        });

        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                //获取当前界面可视部分
                LoginActivity.this.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                //获取屏幕的高度
                int screenHeight =  LoginActivity.this.getWindow().getDecorView().getRootView().getHeight();
                //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
                int heightDifference = screenHeight - r.bottom;
                if(heightDifference>navigationHeight){
                    height = heightDifference;
                    openAnimation();
                }else{
                    if(height!=0){
                        height = 0;
                        closeAnimation();
                    }
                }
            }
        });
        loginAnimation();
    }

    private void openAnimation() {
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(loginLogo,"scaleX",1f,0f);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(loginLogo, "scaleY", 1f, 0);
        ObjectAnimator scaleYLayoutAnimator = ObjectAnimator.ofFloat(loginLayout, "translationY", 0f,-300f);
        AnimatorSet set = new AnimatorSet();
        set.play(scaleXAnimator).with(scaleYAnimator).with(scaleYLayoutAnimator);
        set.setDuration(500);
        set.start();
    }
    private void closeAnimation() {
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(loginLogo,"scaleX",0f,1f);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(loginLogo, "scaleY", 0f,1f);
        ObjectAnimator scaleYLayoutAnimator = ObjectAnimator.ofFloat(loginLayout, "translationY", -300f,0f);
        AnimatorSet set = new AnimatorSet();
        set.play(scaleXAnimator).with(scaleYAnimator).with(scaleYLayoutAnimator);
        set.setDuration(500);
        set.start();
    }
    private void loginAnimation() {
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(loginLogo, "translationY", -600f,1f);
        ObjectAnimator scaleYLayoutAnimator = ObjectAnimator.ofFloat(loginLayout, "translationY", 600f,0f);
        AnimatorSet set = new AnimatorSet();
        set.play(scaleYAnimator).with(scaleYLayoutAnimator);
        set.setDuration(1000);
        set.start();
    }

    private boolean LoginBtnEnable() {
        return !TextUtils.isEmpty(userName.getText().toString().trim())&&!TextUtils.isEmpty(password.getText().toString().trim());
    }

    @Override
    public void success(LoginBean loginBean) {
        //登录成功 注册JPUSH 2017年12月11日14:37:50  by feona
        JPushInterface.setAlias(this,0x11,String.valueOf(loginBean.getUserId()));
        Log.d("jpush","user alias = "+String.valueOf(loginBean.getUserId()));


        loadDialog.dismiss();
        ShardUtil.savePreferenceInt(LoginActivity.this,"user_id",loginBean.getUserId());
        ShardUtil.savePreferenceString(LoginActivity.this,"access_token",loginBean.getAccess_token());
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }

    @Override
    public void error(String error) {
        loadDialog.dismiss();
        btnLogin.setEnabled(true);
        Toast.makeText(LoginActivity.this,error,Toast.LENGTH_SHORT).show();
    }
}
