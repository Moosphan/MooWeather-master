/**
  * Copyright 2017 bejson.com 
  */
package com.moos.weather.bean.CaiYun;
import java.util.Date;

/**
 * by moos on 2018/01/06
 * func:日落日出的时间
 */
public class Astro {

    private String date;
    private Sunset sunset;
    private Sunrise sunrise;
    public void setDate(String date) {
         this.date = date;
     }
     public String getDate() {
         return date;
     }

    public void setSunset(Sunset sunset) {
         this.sunset = sunset;
     }
     public Sunset getSunset() {
         return sunset;
     }

    public void setSunrise(Sunrise sunrise) {
         this.sunrise = sunrise;
     }
     public Sunrise getSunrise() {
         return sunrise;
     }

}