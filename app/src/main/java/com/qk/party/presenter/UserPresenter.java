package com.qk.party.presenter;

import com.qk.party.bean.Result;
import com.qk.party.service.Service;
import com.qk.party.utils.ServiceUtils;
import com.qk.party.viewinterface.NetworkView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @package： com.qk.party.presenter
 * @class: UserPresenter
 * @author:  小飞
 * @date: 2017/10/26 13:53
 * @描述：
 */
public class UserPresenter {
    private NetworkView networkView;

    public UserPresenter(NetworkView networkView) {
        this.networkView = networkView;
    }

    public void updateXm(String access_token,int userId,String xm){
        ServiceUtils.getInstance().createService(Service.class).updateXm("l34kt43f-430if3lp2","102",userId,access_token,xm)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<String> stringResult) {
                        if(stringResult.getResult_code()==200){
                            networkView.success("修改成功");
                        }else{
                            networkView.error("修改失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        networkView.success("网络错误");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void updatePass(String access_token,int userId,String pass){
        ServiceUtils.getInstance().createService(Service.class).updatePass("l34kt43f-430if3lp2","102",userId,access_token,pass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<String> stringResult) {
                        if(stringResult.getResult_code()==200){
                            networkView.success("修改成功");
                        }else{
                            networkView.error("修改失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        networkView.success("网络错误");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
