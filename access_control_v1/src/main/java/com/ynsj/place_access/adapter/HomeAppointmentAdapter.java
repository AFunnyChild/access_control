package com.ynsj.place_access.adapter;

import android.annotation.NonNull;
import android.content.Context;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ynsj.place_access.R;
import com.ynsj.place_access.databinding.ItemHomeSiteInfoBinding;
import com.ynsj.place_access.listener.OnItemClickListener;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2018/7/20.
 */

public class HomeAppointmentAdapter extends BaseRecycleViewAdapter<String, HomeAppointmentAdapter.HomeAppointmentViewHolder> {

    private Context context;
    private List<String> mData;
    private RecyclerView mRecyclerView;
    private OnItemClickListener mOnItemClickListener;
   static Random rm=new Random();

    public HomeAppointmentAdapter(Context context, @NonNull List<String> mData, RecyclerView mRecyclerView, OnItemClickListener mClick) {
        super(context, mData);
        this.context = context;
        this.mData = mData;
        this.mRecyclerView = mRecyclerView;
        this.mOnItemClickListener=mClick;

    }


    @Override
    public HomeAppointmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeAppointmentViewHolder(LayoutInflater.from(context).inflate(R.layout.item_home_site_info, parent, false), mData, mRecyclerView, mOnItemClickListener);
    }


    public static class HomeAppointmentViewHolder extends RecycleViewHolder<String>  {
        private View itemView;
        private List<String> mData;
        private RecyclerView mRecyclerView;
        private OnItemClickListener mOnItemClickListener;
        private ItemHomeSiteInfoBinding mBinding;
        public HomeAppointmentViewHolder(View itemView, List<String> mData, RecyclerView mRecylerView, OnItemClickListener mOnItemClickListener) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
            this.mData = mData;
            this.mRecyclerView = mRecyclerView;
            this.mOnItemClickListener=mOnItemClickListener;


        }

        @Override
        public void Bind(@NonNull final String bean) {
          if (getAdapterPosition()%2==0){
              mBinding.llContent.setBackgroundColor(Color.parseColor("#66ffffff"));
          }else{
              mBinding.llContent.setBackgroundColor(Color.parseColor("#99ffffff"));
          }

        }



    }

}
