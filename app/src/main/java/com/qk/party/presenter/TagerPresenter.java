package com.qk.party.presenter;

import com.qk.party.bean.Result;
import com.qk.party.bean.TargetBean;
import com.qk.party.bean.TargetTypeBean;
import com.qk.party.service.Service;
import com.qk.party.utils.ServiceUtils;
import com.qk.party.viewinterface.TargetView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：Think
 * 创建于 2017/11/2 17:46
 */

public class TagerPresenter {
    private TargetView targetView;
    public TagerPresenter(TargetView targetView) {
        this.targetView = targetView;
    }


    public void getTargetType(String access_token, int userid){
        ServiceUtils.getInstance().createService(Service.class).getTargetType("l34kt43f-430if3lp2","102",access_token,userid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<List<TargetTypeBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<TargetTypeBean>> listResult) {
                        if(listResult.getResult_code()==200){
                            targetView.successTarget(listResult.getResult_data());
                        }else {
                            targetView.error("请求数据失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        targetView.error("网络错误");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void getTarget(String access_token, int userid, int id, int page){
        ServiceUtils.getInstance().createService(Service.class).getTarget("l34kt43f-430if3lp2","102",access_token,userid,id,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<List<TargetBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<TargetBean>> listResult) {
                        if(listResult.getResult_code()==200){
                            targetView.success(listResult.getResult_data());
                        }else {
                            targetView.error("请求数据失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        targetView.error("网络错误");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
