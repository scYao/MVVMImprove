package com.baisidai.mvvmimprove.http;

import android.text.TextUtils;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtils {

    public static RequestBody buildBody(JSONObject jsonObject) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        return body;
    }

    private static volatile HttpUtils instance;


    private HttpUtils() {

    }

    public static HttpUtils getInstance() {
        if (instance == null) {
            synchronized (HttpUtils.class) {
                if (instance == null) {
                    instance = new HttpUtils();
                }
            }
        }

        return instance;
    }


    /**
     * 初始化OKHttpClient,设置缓存,设置超时时间,设置打印日志
     *
     * @return
     */
    public  APIService getAPIService(String baseUrl) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder mBuilder = new OkHttpClient.Builder()
//                                .cache(cache)
                .addInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS);

        Retrofit retrofit;
        APIService service = null;
        if (!TextUtils.isEmpty(baseUrl)) {
            retrofit = new Retrofit.Builder()
                    .client(mBuilder.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl).build();
            service = retrofit.create(APIService.class);
        }
        return service;
    }


}
