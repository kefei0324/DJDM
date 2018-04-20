package com.qk.party.presenter;

import com.qk.party.bean.Notice;
import com.qk.party.bean.Result;
import com.qk.party.fragment.NoticeListFragment;
import com.qk.party.service.Service;
import com.qk.party.utils.ServiceUtils;
import com.qk.party.viewinterface.MissionView;
import com.qk.party.viewinterface.NetworkView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @package： com.qk.party.presenter
 * @class: NoticeListPresenter
 * @author:  小飞
 * @date: 2017/10/25 16:44
 * @描述：
 */

public class NoticeListPresenter {
    private MissionView networkView;

    public NoticeListPresenter(MissionView networkView) {
        this.networkView = networkView;
    }

    public void getNoticeList(String access_token,int page){
        getNoticeList(0, access_token,page);
    }

    public void getNoticeList(int dlbm,String access_token,int page){
        Map<String,String> map = new HashMap<>(4);
        if(dlbm!=0){
            map.put("dlbm",dlbm+"");
        }
        map.put("access_token",access_token);
        map.put("page",page+"");
        map.put("client_id","102");
        ServiceUtils.getInstance().createService(Service.class).getNoticeList(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<List<Notice>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<Notice>> noticeResult) {
                        networkView.success(noticeResult.getResult_data());
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
    public void getNoticeDetail(int article_id,String access_token){
        ServiceUtils.getInstance().createService(Service.class).getNoticeDetail(article_id,access_token,"102")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<Notice>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<Notice> noticeResult) {
                        networkView.success(noticeResult.getResult_data());
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

    public void getBanner(String access_token){
        ServiceUtils.getInstance().createService(Service.class).getBanner(access_token,"102","l34kt43f-430if3lp2")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<List<String>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<String>> noticeResult) {
                        networkView.successMessage(noticeResult.getResult_data());
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
