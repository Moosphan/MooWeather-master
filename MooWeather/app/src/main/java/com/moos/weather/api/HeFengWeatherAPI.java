package com.moos.weather.api;

/**
 * Created by moos on 2017/12/24.
 * func:和风天气API
 */

public class HeFengWeatherAPI {
    //key
    public static final String WEATHER_KEY = "90a866ed0bec4dc59463dbc29f232369";
    //基地址
    public static final String ROOT_URL = "https://free-api.heweather.com";
    //获取三天的天气情况
    public static final String GET_THREE_DAY_WEATHER = "/s6/weather/forecast?";
    //获取当前时间点的天气状况
    public static final String GET_CURRENT_WEATHER_CONDITION = "/s6/weather/now?";
    //获取当前的生活建议
    public static final String GET_LIFE_SUGGESTIONS = "/s6/weather/lifestyle?";

}
