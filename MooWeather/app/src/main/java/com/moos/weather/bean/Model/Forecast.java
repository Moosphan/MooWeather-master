package com.moos.weather.bean.Model;

/**
 * Created by Moos on 2017.12.24
 */

public class Forecast {

    private final String cityName;
    private final int cityIcon;
    private final String temperature;
    private final String weather;
    private final String weatherId;//天气id

    public Forecast(String cityName, int cityIcon, String temperature,  String weather, String weatherId) {
        this.cityName = cityName;
        this.cityIcon = cityIcon;
        this.temperature = temperature;
        this.weather = weather;
        this.weatherId = weatherId;
    }

    public String getCityName() {
        return cityName;
    }

    public int getCityIcon() {
        return cityIcon;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getWeather() {
        return weather;
    }

    public String getWeatherId(){
        return weatherId;
    }
}
