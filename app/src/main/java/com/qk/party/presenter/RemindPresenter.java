package com.qk.party.presenter;

import com.qk.party.bean.ElectionBean;
import com.qk.party.bean.Paety;
import com.qk.party.bean.Result;
import com.qk.party.bean.ThreeSessionsBean;
import com.qk.party.service.Service;
import com.qk.party.utils.ServiceUtils;
import com.qk.party.viewinterface.NetworkView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：Think
 * 创建于 2017/10/30 15:48
 */

public class RemindPresenter {
    private NetworkView networkView;

    public RemindPresenter(NetworkView networkView) {
        this.networkView = networkView;
    }

    public void getPartyMembership(String access_token,int userid){
        ServiceUtils.getInstance().createService(Service.class).getPartyMembership("l34kt43f-430if3lp2","102",access_token,userid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<List<Paety>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<Paety>> listResult) {
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
    public void getElectionList(String access_token,int userid,int page){
        ServiceUtils.getInstance().createService(Service.class).getElectionList("l34kt43f-430if3lp2","102",access_token,page,userid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<List<ElectionBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<ElectionBean>> listResult) {
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
    public void getThreeSessions(String access_token,int userid,int page){
        ServiceUtils.getInstance().createService(Service.class).getThreeSessions("l34kt43f-430if3lp2","102",access_token,page,userid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<List<ThreeSessionsBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<ThreeSessionsBean>> listResult) {
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
