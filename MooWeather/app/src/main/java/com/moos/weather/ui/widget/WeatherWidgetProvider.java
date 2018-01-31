package com.moos.weather.ui.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.moos.weather.R;

/**
 * Created by moos on 2018/1/31.
 * func:自定义天气桌面插件
 */

public class WeatherWidgetProvider extends AppWidgetProvider {
    public WeatherWidgetProvider(){
        super();
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for(int i = 0;i<appWidgetIds.length;i++){
            int appWidgetId = appWidgetIds[i];
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.weather_widget_layout);
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }
}
