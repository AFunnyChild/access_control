package com.ynsj.place_access.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;


import java.text.ParseException;
import java.util.List;

/**
 * Created by Administrator on 2018/6/23.
 */
public abstract class BaseRecycleViewAdapter<M, H extends RecycleViewHolder<M>> extends RecyclerView.Adapter<H> {
    private List<M> mData;

    public BaseRecycleViewAdapter(Context context, @NonNull List<M> mData) {
        super();
        this.mData = mData;

    }
    @Override
    public long getItemId(int position){
        return getItem(position).hashCode();
    }
    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public void onBindViewHolder(H holder, int position) {
        try {
            holder.Bind(mData.get(position));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取position位置的item数据
     *
     * @param position
     * @return
     */
    public M getItem(int position) {
        return mData.get(position);
    }

    /**
     * 更新数据
     *
     * @param mData
     */
    public void setData(@NonNull List<M> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }
}
