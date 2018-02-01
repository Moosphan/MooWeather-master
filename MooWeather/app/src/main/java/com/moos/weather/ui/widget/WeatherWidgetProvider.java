package com.moos.weather.ui.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Message;
import android.util.Log;
import android.util.TimeUtils;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.moos.weather.R;
import com.moos.weather.api.CaiYunWeatherAPI;
import com.moos.weather.application.MoosApplication;
import com.moos.weather.bean.CaiYun.JsonRootBean;
import com.moos.weather.bean.CaiYun.Realtime;
import com.moos.weather.bean.CaiYun.Skycon;
import com.moos.weather.bean.CaiYun.Temperature;
import com.moos.weather.bean.Model.Forecast;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

import static com.moos.weather.application.MoosApplication.TAG;

/**
 * Created by moos on 2018/1/31.
 * func:自定义天气桌面插件
 */

public class WeatherWidgetProvider extends AppWidgetProvider {
    private static final String UPDATE_WEATHER_WIDGET = "com.moos.weather.UPDATE_WEATHER_WIDGET";
    public static final String WIDGET_CLICK_ACTION = "com.moos.weather.action.CLICK"; // 点击事件的广播ACTION
    private JsonRootBean caiYunWeatherBean;
    private Realtime realTimeWeather;                        //当天的实况天气
    private List<Temperature> temperatureList;               //预报五天的温度
    private List<Skycon> skyconList;                         //预报五天的天气


    public WeatherWidgetProvider(){
        super();
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.weather_widget_layout);
        updateWidgetView(context, remoteViews);
        for(int i = 0;i<appWidgetIds.length;i++){
            int appWidgetId = appWidgetIds[i];
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.e(TAG, "onDisabled: "+"最后一个小部件被删除了" );
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.e(TAG, "onDisabled: "+"第一次添加小部件" );
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.e(TAG, "onDisabled: "+"小部件被删除了" );
    }

    /**
     * by moos on 2018/02/01
     * func:接收action广播
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        String action = intent.getAction();
        switch (action){
            case UPDATE_WEATHER_WIDGET:
                /**
                 * 更新插件视图
                 */

                break;


        }
        if(intent.getAction().equals(WIDGET_CLICK_ACTION)){
            /**
             * 接收点击事件
             */

            Toast.makeText(context, "nice day!", Toast.LENGTH_SHORT).show();

        }
    }

    /**
     * by moos on 2018/02/01
     * func:更新视图
     * @param context
     * @param remoteViews
     */
    private void updateWidgetView(Context context, RemoteViews remoteViews){

//        String date = caiYunWeatherBean.getResult().getDaily().getAstro().get(0).getDate();
//        String year = date.substring(0,4);
//        String month = date.substring(5,7);
//        String day = date.substring(8,10);
//        remoteViews.setTextViewText(R.id.weather_widget_location, MoosApplication.mapLocation.getCity());
//        remoteViews.setTextViewText(R.id.weather_widget_temp, String.valueOf(realTimeWeather.getTemperature())+"℃");
//        remoteViews.setTextViewText(R.id.weather_widget_date, year+"年"+month+"月"+day+"日");
        Intent intent = new Intent(WIDGET_CLICK_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,R.id.weather_widget_icon,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.weather_widget_icon, pendingIntent);
    }

    /**
     * by moos on 2018/02/01
     * func:通过彩云API调取天气数据
     * @param context
     * @param lon
     * @param lat
     */
    private void getWeatherByCaiYun(final Context context, final RemoteViews remoteViews, double lon, double lat){
        OkHttpUtils
                .get()
                .url(CaiYunWeatherAPI.ROOT_URL + CaiYunWeatherAPI.CAI_YUN_KEY + "/" + lon +"," + lat + CaiYunWeatherAPI.GET_WEATHER_ALL_DATA )
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("获取彩云天气信息失败=" , e.getMessage());
                        Toast.makeText(context, context.getString(R.string.bad_net_tip), Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onResponse(String response, int id) {

                        Log.e("获取彩云天气信息成功 =" , response);
                        caiYunWeatherBean = JSONObject.parseObject(response, JsonRootBean.class);
                        if(caiYunWeatherBean.getStatus().equals("ok")){

                            realTimeWeather = caiYunWeatherBean.getResult().getRealtime();
                            Log.e(TAG,"当前实况天气为=="+realTimeWeather.toString());
                            temperatureList = caiYunWeatherBean.getResult().getDaily().getTemperature();
                            skyconList = caiYunWeatherBean.getResult().getDaily().getSkycon();

                            updateWidgetView(context,remoteViews);

                        }else {
                            Log.e(TAG,caiYunWeatherBean.getStatus()+caiYunWeatherBean.getApi_status());
                        }

                    }
                });
    }

    /**
     * by moos on 2018/01/06
     * func:通过天气编码返回对应的天气数据
     * @param weatherCode
     * @return
     */
    private String getForecastWeather(String weatherCode){
        switch (weatherCode){
            case "CLEAR_DAY":
                return "晴";
            case "CLEAR_NIGHT":
                return "晴";
            case "PARTLY_CLOUDY_DAY":
                return "多云";
            case "PARTLY_CLOUDY_NIGHT":
                return "多云";
            case "CLOUDY":
                return "阴天";
            case "RAIN":
                return "有雨";
            case "SNOW":
                return "有雪";
            case "FOG":
                return "有雾";
            case "HAZE":
                return "有霾";
            case "SLEET":
                return "冻雨";
            default:
                throw  new IllegalArgumentException("unknown weather");
        }
    }

}
