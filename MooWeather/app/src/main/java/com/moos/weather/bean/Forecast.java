package com.moos.weather.bean;

/**
 * Created by Moos on 2017.12.24
 */

public class Forecast {

    private final String cityName;
    private final int cityIcon;
    private final String minTemperature;
    private final String maxTemperature;
    private final Weather weather;

    public Forecast(String cityName, int cityIcon, String minTemperature, String maxTemperature, Weather weather) {
        this.cityName = cityName;
        this.cityIcon = cityIcon;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.weather = weather;
    }

    public String getCityName() {
        return cityName;
    }

    public int getCityIcon() {
        return cityIcon;
    }

    public String getMinTemperature() {
        return minTemperature;
    }

    public String getMaxTemperature(){
        return maxTemperature;
    }

    public Weather getWeather() {
        return weather;
    }
}
