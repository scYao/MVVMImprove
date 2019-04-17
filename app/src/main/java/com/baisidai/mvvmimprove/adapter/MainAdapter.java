package com.baisidai.mvvmimprove.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.baisidai.mvvmimprove.R;
import com.baisidai.mvvmimprove.bean.PublicNumberBean;
import com.baisidai.mvvmimprove.databinding.ItemNumberBinding;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{
    private List<PublicNumberBean> mBeanList = new ArrayList<>();
    private Context mContext;
    private LayoutInflater inflater;


    public MainAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemNumberBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_number,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PublicNumberBean bean = mBeanList.get(position);
        if (bean !=null){
            holder.binding.setBean(bean);
        }
    }

    public void setData(List<PublicNumberBean> beanList){
        mBeanList.addAll(beanList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mBeanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemNumberBinding binding;

        public ViewHolder(ItemNumberBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
