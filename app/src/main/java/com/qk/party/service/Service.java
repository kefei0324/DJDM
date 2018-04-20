package com.qk.party.service;

import com.qk.party.bean.ElectionBean;
import com.qk.party.bean.LoginBean;
import com.qk.party.bean.Notice;
import com.qk.party.bean.NotifyBean;
import com.qk.party.bean.Paety;
import com.qk.party.bean.Result;
import com.qk.party.bean.SociologyBean;
import com.qk.party.bean.SociologyItemBean;
import com.qk.party.bean.TargetBean;
import com.qk.party.bean.TargetTypeBean;
import com.qk.party.bean.TaskDetailsBean;
import com.qk.party.bean.TaskListBean;
import com.qk.party.bean.ThreeSessionsBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @author ：Think
 * 创建于 2017/10/17 16:31
 */

public interface Service {
    /**
     * 登录
     * */
    @POST("api/login")
    Observable<Result<LoginBean>> login(@Query("client_id")int client_id,
                                        @Query("username")String username,
                                        @Query("password")String password,
                                        @Query("client_secret")String client_secret);
    /**
     * 获取公告列表
     * */
    @GET("api/article/pagelist")
    Observable<Result<List<Notice>>> getNoticeList(@QueryMap Map<String,String> map);
    /**
     * 获取轮播图
     * */
    @GET("api/index/carousellist")
    Observable<Result<List<String>>> getBanner(@Query("access_token")String access_token,
                                         @Query("client_id")String client_id,
                                         @Query("client_secret")String client_secret);
    /**
     * 获取公告详情
     * */
    @GET("api/article/details")
    Observable<Result<Notice>> getNoticeDetail(@Query("article_id")int article_id,
                                               @Query("access_token")String access_token,
                                               @Query("client_id")String client_id);

    /**
     * 获取图标列表
     * */
    @GET("api/economicsociety/list")
    Observable<Result<List<SociologyBean>>> getSociologyList(@Query("statue")String statue,
                                                             @Query("access_token")String access_token,
                                                             @Query("client_id")String client_id,
                                                             @Query("level")int level);

    /**
     * 获取图标详情
     * */
    @GET("api/economicsociety/up/find")
    Observable<Result<List<SociologyItemBean>>> getSociologyDetail(@Query("statue")String statue,
                                                                   @Query("access_token")String access_token,
                                                                   @Query("client_id")String client_id,
                                                                   @Query("kpiId")int kpiId);

    /**
     * 修改用户姓名
     * */
    @POST("api/user/updatexm")
    Observable<Result<String>> updateXm(@Query("client_secret")String client_secret,
                                        @Query("client_id")String client_id,
                                        @Query("userid")int userid,
                                        @Query("access_token")String access_token,
                                        @Query("xm")String xm);

    /**
     * 修改用户密码
     * */
    @POST("api/user/updatepassword")
    Observable<Result<String>> updatePass(@Query("client_secret")String client_secret,
                                          @Query("client_id")String client_id,
                                          @Query("userid")int userid,
                                          @Query("access_token")String access_token,
                                          @Query("password")String password);

    /**
     * 获取通告列表
     * */
    @GET("api/meetingnotice/list")
    Observable<Result<List<NotifyBean>>> getNotityList(@Query("client_secret")String client_secret,
                                                       @Query("client_id")String client_id,
                                                       @Query("access_token")String access_token,
                                                       @Query("userid")int userid,
                                                       @Query("type")int type,
                                                       @Query("page")int page);

    /**
     * 获取通告详情
     * */
    @GET("api/meetingnotice/details")
    Observable<Result<NotifyBean>> getNotityDetails(@Query("client_secret")String client_secret,
                                                       @Query("client_id")String client_id,
                                                       @Query("access_token")String access_token,
                                                       @Query("id")int id);

    /**
     * 获取任务列表
     * */
    @GET("api/task/list")
    Observable<Result<List<TaskListBean>>> getTaskList(@Query("client_secret")String client_secret,
                                                 @Query("client_id")String client_id,
                                                 @Query("access_token")String access_token,
                                                 @Query("userid")int userid,
                                                 @Query("bigtype")int bigtype,
                                                 @Query("smalltype")int smalltype,
                                                 @Query("page")int page,
                                                 @Query("rwmc")String rwmc);
    /**
     * 获取任务详情
     * */
    @GET("api/task/details")
    Observable<Result<TaskDetailsBean>> getTaskDetails(@Query("client_secret")String client_secret,
                                                       @Query("client_id")String client_id,
                                                       @Query("access_token")String access_token,
                                                       @Query("userid")int userid,
                                                       @Query("bigtype")int bigtype,
                                                       @Query("smalltype")int smalltype,
                                                       @Query("task_id")int task_id);

    /**
     * 获取党费提醒
     * */
    @GET("api/remind/dues/list")
    Observable<Result<List<Paety>>> getPartyMembership(@Query("client_secret")String client_secret,
                                                       @Query("client_id")String client_id,
                                                       @Query("access_token")String access_token,
                                                       @Query("userid")int userid);

    /**
     * 获取换届选举
     * */
    @GET("api/remind/groupchange/list")
    Observable<Result<List<ElectionBean>>> getElectionList(@Query("client_secret")String client_secret,
                                                           @Query("client_id")String client_id,
                                                           @Query("access_token")String access_token,
                                                           @Query("page")int page,
                                                           @Query("userid")int userid);

    /**
     * 三会一课统计列表
     * */
    @GET("api/remind/pGroupLife/donelist")
    Observable<Result<List<ThreeSessionsBean>>> getThreeSessions(@Query("client_secret")String client_secret,
                                                                 @Query("client_id")String client_id,
                                                                 @Query("access_token")String access_token,
                                                                 @Query("page")int page,
                                                                 @Query("userid")int userid);
    /**
     * 获取市级目标分类
     * */
    @GET("api/economicsociety/typelist")
    Observable<Result<List<TargetTypeBean>>> getTargetType(@Query("client_secret")String client_secret,
                                                           @Query("client_id")String client_id,
                                                           @Query("access_token")String access_token,
                                                           @Query("userid")int userid);

    /**
     * 获取市级目标分类
     * */
    @GET("api/economicsociety/targetlist")
    Observable<Result<List<TargetBean>>> getTarget(@Query("client_secret")String client_secret,
                                                   @Query("client_id")String client_id,
                                                   @Query("access_token")String access_token,
                                                   @Query("userid")int userid,
                                                   @Query("kpiType")int typeid,
                                                   @Query("page")int page);

    /**
     * 任务反馈
     * */
    @POST("api/task/saveup")
    Observable<Result<String>> getSaveup(@Query("client_secret")String client_secret,
                                         @Query("client_id")String client_id,
                                         @Query("access_token")String access_token,
                                         @Query("userid")int userid,
                                         @Query("bigtype")int bigtype,
                                         @Query("jznr")String jznr,
                                         @Query("task_id")int task_id,
                                         @Query("wcjd")int wcjd);


}
