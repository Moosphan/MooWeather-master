/**
  * Copyright 2017 bejson.com 
  */
package com.moos.weather.bean.CaiYun;
import java.util.List;

/**
 * Auto-generated: 2017-12-31 14:26:10
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Daily {

    private String status;
    private List<ColdRisk> coldRisk;
    private List<Temperature> temperature;
    private List<Skycon> skycon;
    private List<Cloudrate> cloudrate;
    private List<Aqi> aqi;
    private List<Humidity> humidity;
    private List<Astro> astro;
    private List<Ultraviolet> ultraviolet;
    private List<Pm25> pm25;
    private List<Dressing> dressing;
    private List<CarWashing> carWashing;
    private List<Precipitation> precipitation;
    private List<Wind> wind;
    public void setStatus(String status) {
         this.status = status;
     }
     public String getStatus() {
         return status;
     }

    public void setColdRisk(List<ColdRisk> coldRisk) {
         this.coldRisk = coldRisk;
     }
     public List<ColdRisk> getColdRisk() {
         return coldRisk;
     }

    public void setTemperature(List<Temperature> temperature) {
         this.temperature = temperature;
     }
     public List<Temperature> getTemperature() {
         return temperature;
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

    public void setAstro(List<Astro> astro) {
         this.astro = astro;
     }
     public List<Astro> getAstro() {
         return astro;
     }

    public void setUltraviolet(List<Ultraviolet> ultraviolet) {
         this.ultraviolet = ultraviolet;
     }
     public List<Ultraviolet> getUltraviolet() {
         return ultraviolet;
     }

    public void setPm25(List<Pm25> pm25) {
         this.pm25 = pm25;
     }
     public List<Pm25> getPm25() {
         return pm25;
     }

    public void setDressing(List<Dressing> dressing) {
         this.dressing = dressing;
     }
     public List<Dressing> getDressing() {
         return dressing;
     }

    public void setCarWashing(List<CarWashing> carWashing) {
         this.carWashing = carWashing;
     }
     public List<CarWashing> getCarWashing() {
         return carWashing;
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

}