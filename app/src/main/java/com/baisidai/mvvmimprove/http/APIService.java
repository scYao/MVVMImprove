package com.baisidai.mvvmimprove.http;

import com.baisidai.mvvmimprove.base.BaseBean;
import com.baisidai.mvvmimprove.bean.PublicNumberBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface APIService {

    @GET("wxarticle/chapters/json")
    Observable<BaseBean<List<PublicNumberBean>>> getList();
}
