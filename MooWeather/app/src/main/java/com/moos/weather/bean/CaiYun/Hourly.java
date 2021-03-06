/**
  * Copyright 2017 bejson.com 
  */
package com.moos.weather.bean.CaiYun;
import java.io.Serializable;
import java.util.List;

/**
 * by moos on 2018/01/06
 * func:小时预报48
 */
public class Hourly implements Serializable {

    private String status;
    private String description;
    private List<Skycon> skycon;
    private List<Cloudrate> cloudrate;
    private List<Aqi> aqi;
    private List<Humidity> humidity;
    private List<Pm25> pm25;
    private List<Precipitation> precipitation;
    private List<Wind> wind;
    private List<Temperature> temperature;
    public void setStatus(String status) {
         this.status = status;
     }
     public String getStatus() {
         return status;
     }

    public void setDescription(String description) {
         this.description = description;
     }
     public String getDescription() {
         return description;
     }

    public void setSkycon(List<Skycon> skycon) {
         this.skycon = skycon;
     }
     public List<Skycon> getSkycon() {
         return skycon;
     }

    public void setCloudrate(List<Cloudrate> cloudrate) {
         this.cloudrate = cloudrate;
     }
     public List<Cloudrate> getCloudrate() {
         return cloudrate;
     }

    public void setAqi(List<Aqi> aqi) {
         this.aqi = aqi;
     }
     public List<Aqi> getAqi() {
         return aqi;
     }

    public void setHumidity(List<Humidity> humidity) {
         this.humidity = humidity;
     }
     public List<Humidity> getHumidity() {
         return humidity;
     }

    public void setPm25(List<Pm25> pm25) {
         this.pm25 = pm25;
     }
     public List<Pm25> getPm25() {
         return pm25;
     }

    public void setPrecipitation(List<Precipitation> precipitation) {
         this.precipitation = precipitation;
     }
     public List<Precipitation> getPrecipitation() {
         return precipitation;
     }

    public void setWind(List<Wind> wind) {
         this.wind = wind;
     }
     public List<Wind> getWind() {
         return wind;
     }

    public void setTemperature(List<Temperature> temperature) {
         this.temperature = temperature;
     }
     public List<Temperature> getTemperature() {
         return temperature;
     }

     public static class Temperature implements Serializable{
        private String datetime;
        private float value;

         public String getDatetime() {
             return datetime;
         }

         public void setDatetime(String datetime) {
             this.datetime = datetime;
         }

         public float getValue() {
             return value;
         }

         public void setValue(float value) {
             this.value = value;
         }
     }

}