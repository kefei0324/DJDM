package com.qk.party.presenter;

import com.qk.party.application.MyApplication;
import com.qk.party.bean.Result;
import com.qk.party.bean.TaskDetailsBean;
import com.qk.party.bean.TaskListBean;
import com.qk.party.service.Service;
import com.qk.party.utils.ServiceUtils;
import com.qk.party.viewinterface.MissionView;
import com.qk.party.viewinterface.NetworkView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @package： com.qk.party.presenter
 * @class: TaskPresenter
 * @author:  小飞
 * @date: 2017/10/27 11:43
 * @描述：
 */

public class TaskPresenter {
    private MissionView networkView;

    public TaskPresenter(MissionView networkView) {
        this.networkView = networkView;
    }

    public void getTaskList(String access_token,int userid,int bigtype,int smalltype,int page,String rwmc){
        ServiceUtils.getInstance()
                .createService(Service.class)
                .getTaskList("l34kt43f-430if3lp2","102",access_token,userid,bigtype,smalltype,page,rwmc)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<List<TaskListBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<TaskListBean>> taskListBeanResult) {
                        if(taskListBeanResult.getResult_code()==200){
                            networkView.success(taskListBeanResult.getResult_data());
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
    public void getTaskDetails(String access_token,int userid,int bigtype,int task_id,int smalltype){
        ServiceUtils.getInstance()
                .createService(Service.class)
                .getTaskDetails("l34kt43f-430if3lp2","102",access_token,userid,bigtype, smalltype,task_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<TaskDetailsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<TaskDetailsBean> taskListBeanResult) {
                        if(taskListBeanResult.getResult_code()==200){
                            networkView.success(taskListBeanResult.getResult_data());
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
    public void getSaveup(String access_token,int userid,int bigtype,int task_id,String jznr,int wcjd){
        ServiceUtils.getInstance()
                .createService(Service.class)
                .getSaveup("l34kt43f-430if3lp2","102",access_token,userid,bigtype,jznr,task_id,wcjd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<String> taskListBeanResult) {
                        if(taskListBeanResult.getResult_code()==200){
                            networkView.successMessage("反馈成功");
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
