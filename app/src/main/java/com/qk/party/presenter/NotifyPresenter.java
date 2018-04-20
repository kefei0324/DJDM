package com.qk.party.presenter;

import com.qk.party.bean.NotifyBean;
import com.qk.party.bean.Result;
import com.qk.party.service.Service;
import com.qk.party.utils.ServiceUtils;
import com.qk.party.viewinterface.NetworkView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @package： com.qk.party.presenter
 * @class: NotifyPresenter
 * @author:  小飞
 * @date: 2017/10/27 10:04
 * @描述：
 */
public class NotifyPresenter {
    private NetworkView networkView;
    public NotifyPresenter(NetworkView networkView) {
        this.networkView = networkView;
    }

    public void getNotifyList(String access_token,int userid,int type,int page){
        ServiceUtils.getInstance().createService(Service.class).getNotityList("l34kt43f-430if3lp2","102",access_token,userid,type,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<List<NotifyBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<NotifyBean>> listResult) {
                        if(listResult.getResult_code()==200){
                            networkView.success(listResult.getResult_data());
                        }else {
                            networkView.error("请求数据失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        networkView.error("网络错误");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void getNotifyDetails(String access_token,int id){
        ServiceUtils.getInstance().createService(Service.class).getNotityDetails("l34kt43f-430if3lp2","102",access_token,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<NotifyBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<NotifyBean> listResult) {
                        if(listResult.getResult_code()==200){
                            networkView.success(listResult.getResult_data());
                        }else {
                            networkView.error("请求数据失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        networkView.error("网络错误");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
