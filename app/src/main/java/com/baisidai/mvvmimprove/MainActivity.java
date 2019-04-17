package com.baisidai.mvvmimprove;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.baisidai.mvvmimprove.adapter.MainAdapter;
import com.baisidai.mvvmimprove.base.BaseActivity;
import com.baisidai.mvvmimprove.base.BaseBean;
import com.baisidai.mvvmimprove.bean.PublicNumberBean;
import com.baisidai.mvvmimprove.databinding.ActivityMainBinding;
import com.baisidai.mvvmimprove.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends BaseActivity {
    private MainViewModel viewModel;
    private LinearLayoutManager layoutManager;
    private MainAdapter adapter;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();
    }

    private void initView() {
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recyclerView.setLayoutManager(layoutManager);
        adapter = new MainAdapter(this);
        binding.recyclerView.setAdapter(adapter);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        showWaitDialog();//显示加载动画
        viewModel.getList().observe(this, new Observer<BaseBean<List<PublicNumberBean>>>() {
            @Override
            public void onChanged(@Nullable BaseBean<List<PublicNumberBean>> listBaseBean) {
                if (listBaseBean.getErrorCode() ==0){
                    dismissWaitDialog();
                    adapter.setData(listBaseBean.getData());
                }

            }
        });
    }

}
