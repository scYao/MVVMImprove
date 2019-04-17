package com.baisidai.mvvmimprove.base;

import com.baisidai.mvvmimprove.config.Url;
import com.baisidai.mvvmimprove.http.APIService;
import com.baisidai.mvvmimprove.http.HttpUtils;

public abstract class BaseRepository {
    protected APIService apiService;

    public BaseRepository() {
        if (apiService == null) {
            apiService = HttpUtils.getInstance().getAPIService(Url.BASE_URL);
        }
    }

}
