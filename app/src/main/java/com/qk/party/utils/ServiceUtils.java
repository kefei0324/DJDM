package com.qk.party.utils;/**
 * 作者：Think
 * 创建于 2017/7/26 08:43
 */

import android.content.Intent;

import com.orhanobut.logger.Logger;
import com.qk.party.application.MyApplication;
import com.qk.party.bean.LoginBean;
import com.qk.party.ui.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @包名： com.qk.utils
 * @文件名: ServiceUtils
 * @author : 张宇飞
 * @创建时间: 2017/7/26 8:43
 * @描述：网络请求类
 */
public class ServiceUtils {
    private static ServiceUtils instance =new ServiceUtils();
    private String API_BASE_URL = "http://101.200.35.180:8080/";
//    private String API_BASE_URL = "http://120.24.56.166:8080/";
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            Logger.e("retrofitBack = "+message);
        }
    }).setLevel(HttpLoggingInterceptor.Level.BODY);

    private OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .cookieJar(new CookieJar() {
                private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();
                @Override
                public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                    cookieStore.put(url.host(), cookies);
                }

                @Override
                public List<Cookie> loadForRequest(HttpUrl url) {
                    List<Cookie> cookies = cookieStore.get(url.host());
                    return cookies != null ? cookies : new ArrayList<Cookie>();
                }
            }).addNetworkInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Response response;
                    try {
                        response = chain.proceed(request);
                    } catch (Exception e) {
                        throw e;
                    }
                    ResponseBody responseBody = response.body();
                    long contentLength = responseBody.contentLength();
                    BufferedSource source = responseBody.source();
                    source.request(Long.MAX_VALUE); // Buffer the entire body.
                    Buffer buffer = source.buffer();
                    Charset charset = Charset.forName("UTF-8");
                    MediaType contentType = responseBody.contentType();
                    if (contentType != null) {
                        charset = contentType.charset(Charset.forName("UTF-8"));
                    }
                    if (contentLength != 0) {
                        String body = buffer.clone().readString(charset);
                        try {
                            JSONObject object = new JSONObject(body);
                            if(object.has("result_code")&&object.getInt("result_code")==4020){
                                if(DataSupport.findAll(LoginBean.class).size()==0){
                                    Logger.e("用户已退出登录");
                                    return response;
                                }
                                Logger.e("退出登录 = "+body);
                                DataSupport.deleteAll(LoginBean.class);
                                Intent intent = new Intent();
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.putExtra("type",1);
                                intent.setClass(MyApplication.context, LoginActivity.class);
                                MyApplication.context.startActivity(intent);
                                ActivityManagers.getAppManager().finishAllActivity();
                                return response;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    return response;
                }
            });
    private Retrofit builder =new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build();
    public <S> S createService(Class<S> serviceClass){
        return builder.create(serviceClass);
    }
    public static ServiceUtils getInstance(){
        return instance;
    }
    private ServiceUtils(){}

}
