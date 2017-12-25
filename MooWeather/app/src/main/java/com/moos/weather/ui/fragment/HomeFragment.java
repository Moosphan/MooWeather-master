package com.moos.weather.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.moos.weather.R;
import com.moos.weather.api.JuHeWeatherAPI;
import com.moos.weather.application.MoosApplication;
import com.moos.weather.bean.Forecast;
import com.moos.weather.bean.JHCityWeatherBean;
import com.moos.weather.bean.WeatherExamples;
import com.moos.weather.ui.adapter.ForecastAdapter;
import com.moos.weather.ui.view.ForecastView;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

import static com.moos.weather.application.MoosApplication.TAG;

/**
 * Created by moos on 2017/12/24.
 * func: home page fragment
 */

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, DiscreteScrollView.OnItemChangedListener<ForecastAdapter.ViewHolder>, DiscreteScrollView.ScrollStateChangeListener<ForecastAdapter.ViewHolder> {
    private static final String ARG_PARAM1 = "param1";

    private int mParam1;
    private List<Forecast> forecasts;
    private JHCityWeatherBean.Result.Today todayWeather;     //今日天气
    private List<JHCityWeatherBean.Result.Future> futureWeatherList;
    private JHCityWeatherBean.Result.Sk currentWeatherBean;  //当前实况天气

    @Bind(R.id.fragment_home_refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.fragment_home_recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.fragment_home_back)
    ImageView bt_back;
    @Bind(R.id.fragment_home_forecast_date_picker)
    DiscreteScrollView datePicker;
    @Bind(R.id.fragment_home_forecast_view)
    ForecastView forecastView;


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment MemoryPhotoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(int param1) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    private void initView(){

        getCurrentCityWeather(MoosApplication.mapLocation.getLongitude(),MoosApplication.mapLocation.getLatitude());

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        //初始化测试
        //forecasts = WeatherExamples.get().getForecasts();

        Toast.makeText(getContext(),"当前位置为："+ MoosApplication.mapLocation.getLongitude()+","+MoosApplication.mapLocation.getLatitude(),Toast.LENGTH_SHORT);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {

        //refresh data
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        },3000);
    }

    @Override
    public void onCurrentItemChanged(@Nullable ForecastAdapter.ViewHolder holder, int position) {
        if (holder != null) {
            forecastView.setForecast(forecasts.get(position));
            holder.showText();
        }

    }

    @Override
    public void onScrollStart(@NonNull ForecastAdapter.ViewHolder holder, int adapterPosition) {

        holder.hideText();
    }

    @Override
    public void onScrollEnd(@NonNull ForecastAdapter.ViewHolder currentItemHolder, int adapterPosition) {

    }

    @Override
    public void onScroll(float scrollPosition, int currentPosition, int newPosition, @Nullable ForecastAdapter.ViewHolder currentHolder, @Nullable ForecastAdapter.ViewHolder newCurrent) {

        Forecast current = forecasts.get(currentPosition);
        if (newPosition >= 0 && newPosition < datePicker.getAdapter().getItemCount()) {
            Forecast next = forecasts.get(newPosition);
            forecastView.onScroll(1f - Math.abs(scrollPosition), current, next);
        }
    }

    /**
     * by moos on 2017.12.25
     * func:初始化天气界面
     */
    private void initWeatherViews(){
        datePicker.setSlideOnFling(true);
        datePicker.setAdapter(new ForecastAdapter(forecasts));
        datePicker.addOnItemChangedListener(this);
        datePicker.addScrollStateChangeListener(this);
        datePicker.scrollToPosition(2);
        datePicker.setItemTransitionTimeMillis(800);
        datePicker.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());

        forecastView.setForecast(forecasts.get(0));
    }

    /**
     * by moos on 2017/12/25
     * func:通过当前位置获取天气信息
     * @param lon
     * @param lat
     */
    private void getCurrentCityWeather(double lon,double lat){

        JSONObject json = new JSONObject();
        OkHttpUtils
                .postString()
                .url(JuHeWeatherAPI.SCAN_WEATHER_BY_GPS + "key="+JuHeWeatherAPI.JUHE_KEY + "&lon=" + lon +"&lat=" + lat )
                .mediaType(MediaType.parse("application/json;charset=utf-8"))
                .content(json.toString())
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("获取天气信息失败=" , e.getMessage());
                        Toast.makeText(getContext(), getString(R.string.bad_net_tip), Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onResponse(String response, int id) {

                        Log.e("获取天气信息成功 =" , response);
                        JHCityWeatherBean cityWeatherBean = JSONObject.parseObject(response, JHCityWeatherBean.class);
                        if(cityWeatherBean.getResultcode().equals("200")){

                            currentWeatherBean = cityWeatherBean.getResult().getSk();
                            futureWeatherList = cityWeatherBean.getResult().getFuture();
                            todayWeather = cityWeatherBean.getResult().getToday();
                            forecasts = new ArrayList<>();
                            for(int i=0;i<futureWeatherList.size();i++){
                                Forecast forecast = new Forecast(todayWeather.getCity(),R.drawable.new_york,futureWeatherList.get(i).getTemperature(),futureWeatherList.get(i).getWeather());
                                forecasts.add(forecast);
                            }
                            Log.e(TAG,"预报数量为=="+forecasts.size());

                            initWeatherViews();
                        }else {
                            Log.e(TAG,cityWeatherBean.getReason());
                        }

                    }
                });
    }
}
