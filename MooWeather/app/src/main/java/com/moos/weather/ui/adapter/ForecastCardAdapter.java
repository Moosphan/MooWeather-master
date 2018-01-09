package com.moos.weather.ui.adapter;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.moos.weather.R;
import com.moos.weather.application.MoosApplication;
import com.moos.weather.bean.CaiYun.JsonRootBean;
import com.moos.weather.ui.activity.ForecastDetailActivity;
import com.moos.weather.ui.activity.TodayWeatherDetailActivity;
import com.moos.weather.util.TimeUtils;

/**
 * Created by moos on 2018/1/5.
 * func:天气预报卡片适配器
 */
public class ForecastCardAdapter extends RecyclerView.Adapter<ForecastCardAdapter.BroadcastViewHolder>{
    private LayoutInflater layoutInflater;
    private JsonRootBean weatherBean;
    private RecyclerView parentRecycler;
    private Context context;
    private AppCompatActivity appCompatActivity;
    public ForecastCardAdapter(AppCompatActivity activity, JsonRootBean data){//构造方法

        this.appCompatActivity = activity;
        this.weatherBean=data;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        parentRecycler = recyclerView;
    }



    @Override
    public BroadcastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.forecast_item_layout,parent,false);
        BroadcastViewHolder myViewHolder=new BroadcastViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final BroadcastViewHolder holder, final int position) {

        int iconTint = ContextCompat.getColor(holder.itemView.getContext(), R.color.grayIconTint);

        Glide.with(holder.itemView.getContext())
                .load(R.mipmap.sunrise)
                .listener(new ForecastCardAdapter.TintOnLoad(holder.imageSunrise, iconTint))
                .into(holder.imageSunrise);
        Glide.with(holder.itemView.getContext())
                .load(R.mipmap.sunset)
                .listener(new ForecastCardAdapter.TintOnLoad(holder.imageSunset, iconTint))
                .into(holder.imageSunset);
        String date = weatherBean.getResult().getDaily().getAstro().get(position).getDate();
        String year = date.substring(0,4);
        String month = date.substring(5,7);
        String day = date.substring(8,10);
        if(position == 0){
            holder.textViewWeekDay.setText("今天");
        }else if(position == 1){
            holder.textViewWeekDay.setText("明天");
        }else {
            holder.textViewWeekDay.setText(TimeUtils.getChineseWeekDay(date));
        }
        holder.textViewSunrise.setText(weatherBean.getResult().getDaily().getAstro().get(position).getSunrise().getTime());
        holder.textViewSunset.setText(weatherBean.getResult().getDaily().getAstro().get(position).getSunset().getTime());
        holder.textViewDate.setText(year+"年"+month+"月"+day+"日");
        if (myListener!=null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myListener.onItemClick(holder.itemView, position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos=holder.getLayoutPosition();
                    myListener.onItemLongClick(holder.itemView,pos);
                    return false;
                }
            });
        }

        holder.imageViewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"Go for detail～",Toast.LENGTH_SHORT).show();
            }
        });

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentRecycler.smoothScrollToPosition(position);
                //Toast.makeText(view.getContext(),"Go for detail～",Toast.LENGTH_SHORT).show();
                if(position == 0){
                    //今日天气详情
                    Intent intent = new Intent(appCompatActivity, TodayWeatherDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("weatherData",weatherBean);
                    intent.putExtras(bundle);
                    appCompatActivity.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(appCompatActivity, holder.textViewDate, "shareView").toBundle());
                }else {
                    //未来天气详情
                    Intent intent = new Intent(appCompatActivity, ForecastDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("goFutureDaysWeather","goFutureDaysWeather");
                    bundle.putSerializable("futureDate",weatherBean.getResult().getDaily().getAstro().get(position));
                    intent.putExtras(bundle);
                    appCompatActivity.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(appCompatActivity, holder.textViewDate, "shareView").toBundle());
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return weatherBean.getResult().getDaily().getCloudrate().size()>0?weatherBean.getResult().getDaily().getCloudrate().size():0;
    }

    public class BroadcastViewHolder extends RecyclerView.ViewHolder{
        ImageView imageSunrise, imageSunset;
        TextView  textViewSunrise, textViewSunset;
        TextView  textViewDate, textViewWeekDay;
        LinearLayout container;
        ImageView imageViewDetail;

        public BroadcastViewHolder(View itemView) {
            super(itemView);
            imageSunrise = itemView.findViewById(R.id.sunrise_image);
            imageSunset = itemView.findViewById(R.id.sunset_image);
            textViewSunrise = itemView.findViewById(R.id.forecast_item_sunrise_time);
            textViewSunset = itemView.findViewById(R.id.forecast_item_sunset_time);
            textViewDate = itemView.findViewById(R.id.forecast_item_date);
            textViewWeekDay = itemView.findViewById(R.id.forecast_item_week);
            imageViewDetail = itemView.findViewById(R.id.forecast_item_detail);
            container = itemView.findViewById(R.id.forecast_item_container);
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    parentRecycler.smoothScrollToPosition(getAdapterPosition());
                }
            });


        }

        /**
         * by moos on 2018/01/06
         * func:设置选中状态时卡片中图片的颜色
         */
        public void setImageSelectColor() {
            imageSunrise.setColorFilter(Color.BLACK);
            imageSunset.setColorFilter(Color.BLACK);
        }
        /**
         * by moos on 2018/01/06
         * func:设置未选中状态时卡片中图片的颜色
         */
        public void setImageUnselectColor() {
            imageSunrise.setColorFilter(ContextCompat.getColor(imageSunrise.getContext(), R.color.grayIconTint));
            imageSunset.setColorFilter(ContextCompat.getColor(imageSunset.getContext(), R.color.grayIconTint));
        }
    }

    OnBroadcastItemClickListener myListener;
    public interface OnBroadcastItemClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }
    public void setOnBroadcastItemClickListener(OnBroadcastItemClickListener onMyItemClickListener){
        this.myListener=onMyItemClickListener;
    }

    private static class TintOnLoad implements RequestListener<Integer, GlideDrawable> {

        private ImageView imageView;
        private int tintColor;

        public TintOnLoad(ImageView view, int tintColor) {
            this.imageView = view;
            this.tintColor = tintColor;
        }

        @Override
        public boolean onException(Exception e, Integer model, Target<GlideDrawable> target, boolean isFirstResource) {
            return false;
        }

        @Override
        public boolean onResourceReady(GlideDrawable resource, Integer model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            imageView.setColorFilter(tintColor);
            return false;
        }
    }

}
