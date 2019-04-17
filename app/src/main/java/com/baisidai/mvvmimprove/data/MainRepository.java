package com.baisidai.mvvmimprove.data;

import android.arch.lifecycle.MutableLiveData;

import com.baisidai.mvvmimprove.base.BaseBean;
import com.baisidai.mvvmimprove.base.BaseRepository;
import com.baisidai.mvvmimprove.bean.PublicNumberBean;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainRepository extends BaseRepository {

    public MutableLiveData<BaseBean<List<PublicNumberBean>>> getList() {
        final MutableLiveData<BaseBean<List<PublicNumberBean>>> data = new MutableLiveData<>();

        apiService.getList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<List<PublicNumberBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<List<PublicNumberBean>> listBaseBean) {
                            if (listBaseBean !=null){
                                data.setValue(listBaseBean);
                            }
                    }

                    @Override
                    public void onError(Throwable e) {
                            data.setValue(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return data;
    }

}
