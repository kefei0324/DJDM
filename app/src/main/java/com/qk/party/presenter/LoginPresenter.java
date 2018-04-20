package com.qk.party.presenter;

import com.qk.party.application.MyApplication;
import com.qk.party.bean.LoginBean;
import com.qk.party.bean.Result;
import com.qk.party.service.Service;
import com.qk.party.utils.ServiceUtils;
import com.qk.party.viewinterface.LoginView;

import org.litepal.crud.DataSupport;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ：Think
 * 创建于 2017/10/17 16:44
 */

public class LoginPresenter {
    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    public void login(String username, String password){
        ServiceUtils.getInstance().createService(Service.class).login(102,username,password,"l34kt43f-430if3lp2")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Result<LoginBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Result<LoginBean> loginBeanResult) {
                if(200==loginBeanResult.getResult_code()){
                    DataSupport.deleteAll(LoginBean.class);
                    MyApplication.userInfo = loginBeanResult.getResult_data();
                    loginBeanResult.getResult_data().save();
                    loginView.success(loginBeanResult.getResult_data());
                }else {
                    loginView.error("账号或密码错误");
                }

            }

            @Override
            public void onError(Throwable e) {
                loginView.error("网络错误");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
