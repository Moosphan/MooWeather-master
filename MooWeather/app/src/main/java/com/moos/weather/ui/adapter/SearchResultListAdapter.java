package com.moos.weather.ui.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.amap.api.services.help.Tip;
import com.arlib.floatingsearchview.util.Util;
import com.moos.weather.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moos on 2018/1/24.
 * func:搜索结果列表的适配器
 */

public class SearchResultListAdapter extends RecyclerView.Adapter<SearchResultListAdapter.ViewHolder> {

    private List<Tip> mDataSet = new ArrayList<>();

    private int mLastAnimatedItemPosition = -1;

    public interface OnItemClickListener{
        void onClick(View view,int position);
    }

    private OnItemClickListener mItemsOnClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView cityName;
        public final TextView cityDetail;
        public final View mTextContainer;

        public ViewHolder(View view) {
            super(view);
            cityName = (TextView) view.findViewById(R.id.search_result_item_name);
            cityDetail = (TextView) view.findViewById(R.id.search_result_item_detail);
            mTextContainer = view.findViewById(R.id.search_result_item_container);
        }
    }

    public void swapData(List<Tip> mNewDataSet) {
        mDataSet = mNewDataSet;
        notifyDataSetChanged();
    }

    public void setItemsOnClickListener(OnItemClickListener onClickListener){
        this.mItemsOnClickListener = onClickListener;
    }

    @Override
    public SearchResultListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_results_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchResultListAdapter.ViewHolder holder, final int position) {

        final Tip tip = mDataSet.get(position);
        holder.cityName.setText(tip.getName());
        holder.cityDetail.setText(tip.getAddress());

        if(mLastAnimatedItemPosition < position){
            animateItem(holder.itemView);
            mLastAnimatedItemPosition = position;
        }

        if(mItemsOnClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemsOnClickListener.onClick(v,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    private void animateItem(View view) {
        view.setTranslationY(Util.getScreenHeight((Activity) view.getContext()));
        view.animate()
                .translationY(0)
                .setInterpolator(new DecelerateInterpolator(3.f))
                .setDuration(700)
                .start();
    }
}

