package com.moos.weather.bean;

/**
 * Created by moos on 2017/12/24.
 */

public enum Weather {

    SUNNY("00"),//晴
    PARTLY_CLOUDY("01"),//多云
    CLOUDY("02"),//阴
    SHOWER("03"),//阵雨
    THUNDERSTORMS("04"),//雷阵雨
    THUNDERSTORMS_WITH_HAIL("05"),//雷阵雨伴有冰雹
    SLEET("06"),//雨夹雪
    LIGHT_RAIN("07"),//小雨
    MIDDLE_RAIN("08"),//中雨
    HEAVY_RAIN("09"),//大雨
    RAINSTORM("10"),//暴雨
    BIG_HEAVY_RAIN("11"),//大暴雨
    SUPER_HEAVY_RAIN("12"),//特大暴雨
    SNOW_SHOWER("13"),//阵雪
    LIGHT_SNOW("14"),//小雪
    MIDDLE_SNOW("15"),//中雪
    BIG_SNOW("16"),//大雪
    HEAVY_SNOW("17"),//暴雪
    FROG("18"),//雾
    FREEZING_RAIN("19"),//冻雨
    SANDSTORM("20"),//沙尘暴
    RAIN_LIGHT_TO_MIDDLE("21"),//小雨-中雨
    RAIN_MIDDLE_TO_BIG("22"),//中雨-大雨
    RAIN_BIG_TO_HEAVY("23"),//大雨-暴雨
    RAIN_HEAVY_TO_LOT("24"),//暴雨-大暴雨
    RAIN_LOT_TO_SUPER("25"),//大暴雨-特大暴雨
    SNOW_LIGHT_TO_MIDDLE("26"),//小雪-中雪
    SNOW_MIDDLE_TO_BIG("27"),//中雪-大雪
    SNOW_BIG_TO_HEAVY("28"),//大雪-暴雪
    FLOATING_DIST("29"),//浮尘
    YANG_SHA("30"),//扬沙
    STRONG_SANDSTORM("31"),//强沙尘暴
    HAZE("53");//霾




    private String displayName;

    Weather(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
