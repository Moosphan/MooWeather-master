package com.moos.weather.api;

/**
 * Created by moos on 2017/12/25.
 * FUNC:聚合数据提供的全国天气预报api
 */

public class JuHeWeatherAPI {
    //KEY
    public static final String JUHE_KEY = "f1941b65eda2e93d4f854ee093e65f57";
    //根据城市名或者id查询天气
    public static final String SCAN_WEATHER_BY_CITY = "http://v.juhe.cn/weather/index?format=2&";
    //获取天气种类及标识列表
    public static final String WEATHER_KINDS = "http://v.juhe.cn/weather/uni";
    //根据IP查询天气
    public static final String SCAN_WEATHER_BY_IP = "http://v.juhe.cn/weather/ip";
    //根据GPS坐标查询天气
    public static final String SCAN_WEATHER_BY_GPS = "http://v.juhe.cn/weather/geo?format=2&";
    //获取支持城市列表
    public static final String GET_SUPPORT_CITY = "http://v.juhe.cn/weather/citys";

}
