/**
  * Copyright 2017 bejson.com 
  */
package com.moos.weather.bean.CaiYun;

import java.io.Serializable;

/**
 * by moos on 2018/01/06
 * func:实时播报
 */
public class Realtime implements Serializable {

    private String status;
    private int temperature;
    private String skycon;
    private int cloudrate;
    private int aqi;
    private double humidity;
    private int pm25;
    private Precipitation precipitation;
    private Wind wind;
    public void setStatus(String status) {
         this.status = status;
     }
     public String getStatus() {
         return status;
     }

    public void setTemperature(int temperature) {
         this.temperature = temperature;
     }
     public int getTemperature() {
         return temperature;
     }

    public void setSkycon(String skycon) {
         this.skycon = skycon;
     }
     public String getSkycon() {
         return skycon;
     }

    public void setCloudrate(int cloudrate) {
         this.cloudrate = cloudrate;
     }
     public int getCloudrate() {
         return cloudrate;
     }

    public void setAqi(int aqi) {
         this.aqi = aqi;
     }
     public int getAqi() {
         return aqi;
     }

    public void setHumidity(double humidity) {
         this.humidity = humidity;
     }
     public double getHumidity() {
         return humidity;
     }

    public void setPm25(int pm25) {
         this.pm25 = pm25;
     }
     public int getPm25() {
         return pm25;
     }

    public void setPrecipitation(Precipitation precipitation) {
         this.precipitation = precipitation;
     }
     public Precipitation getPrecipitation() {
         return precipitation;
     }

    public void setWind(Wind wind) {
         this.wind = wind;
     }
     public Wind getWind() {
         return wind;
     }

    @Override
    public String toString() {
        return "Realtime{" +
                "status='" + status + '\'' +
                ", temperature=" + temperature +
                ", skycon='" + skycon + '\'' +
                ", cloudrate=" + cloudrate +
                ", aqi=" + aqi +
                ", humidity=" + humidity +
                ", pm25=" + pm25 +
                ", precipitation=" + precipitation +
                ", wind=" + wind +
                '}';
    }
}