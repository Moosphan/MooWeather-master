/**
  * Copyright 2017 bejson.com 
  */
package com.moos.weather.bean.CaiYun;
import java.io.Serializable;
import java.util.Date;

/**
 * by moos on 2018/01/06
 * func:空气质量指数
 */
public class Aqi implements Serializable{

    private Date date;
    private int max;
    private double avg;
    private int min;
    public void setDate(Date date) {
         this.date = date;
     }
     public Date getDate() {
         return date;
     }

    public void setMax(int max) {
         this.max = max;
     }
     public int getMax() {
         return max;
     }

    public void setAvg(double avg) {
         this.avg = avg;
     }
     public double getAvg() {
         return avg;
     }

    public void setMin(int min) {
         this.min = min;
     }
     public int getMin() {
         return min;
     }

}