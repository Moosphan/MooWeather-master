/**
  * Copyright 2017 bejson.com 
  */
package com.moos.weather.bean.CaiYun;

import java.io.Serializable;

/**
 * Auto-generated: 2017-12-31 14:26:10
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Result implements Serializable {

    private Hourly hourly;
    private Realtime realtime;
    private int primary;
    private Daily daily;
    private Minutely minutely;
    public void setHourly(Hourly hourly) {
         this.hourly = hourly;
     }
     public Hourly getHourly() {
         return hourly;
     }

    public void setRealtime(Realtime realtime) {
         this.realtime = realtime;
     }
     public Realtime getRealtime() {
         return realtime;
     }

    public void setPrimary(int primary) {
         this.primary = primary;
     }
     public int getPrimary() {
         return primary;
     }

    public void setDaily(Daily daily) {
         this.daily = daily;
     }
     public Daily getDaily() {
         return daily;
     }

    public void setMinutely(Minutely minutely) {
         this.minutely = minutely;
     }
     public Minutely getMinutely() {
         return minutely;
     }

}