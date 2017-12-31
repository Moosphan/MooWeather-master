/**
  * Copyright 2017 bejson.com 
  */
package com.moos.weather.bean.CaiYun;
import java.util.Date;

/**
 * Auto-generated: 2017-12-31 14:26:10
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Astro {

    private Date date;
    private Sunset sunset;
    private Sunrise sunrise;
    public void setDate(Date date) {
         this.date = date;
     }
     public Date getDate() {
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