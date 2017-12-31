/**
  * Copyright 2017 bejson.com 
  */
package com.moos.weather.bean.CaiYun;

/**
 * Auto-generated: 2017-12-31 14:26:10
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Realtime {

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

}