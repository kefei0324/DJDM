package com.qk.party.presenter;

import com.qk.party.bean.Result;
import com.qk.party.bean.SociologyBean;
import com.qk.party.bean.SociologyItemBean;
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
 * @class: SociologyPresenter
 * @author:  小飞
 * @date: 2017/10/25 17:53
 * @描述：
 */
public class SociologyPresenter {
    private NetworkView networkView;

    public SociologyPresenter(NetworkView networkView) {
        this.networkView = networkView;
    }

    public void getSociologyList(String access_token){
        ServiceUtils.getInstance().createService(Service.class).getSociologyList("1",access_token,"102",2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<List<SociologyBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<SociologyBean>> listResult) {
                        if(listResult.getResult_code()==200){
                            networkView.success(listResult.getResult_data());
                        }else{
                            networkView.error("网络错误");
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
    public void getSociologyDetail(String access_token,int kpiId){
        ServiceUtils.getInstance().createService(Service.class).getSociologyDetail("1",access_token,"102",kpiId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<List<SociologyItemBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<SociologyItemBean>> listResult) {
                        if(listResult.getResult_code()==200){
                            networkView.success(listResult.getResult_data());
                        }else{
                            networkView.error("网络错误");
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
