/**
  * Copyright 2017 bejson.com 
  */
package com.moos.weather.bean.CaiYun;
import java.io.Serializable;
import java.util.Date;

/**
 /**
 * by moos on 2018/01/06
 * func:相对湿度
 */
public class Humidity implements Serializable {

    private Date date;
    private double max;
    private double avg;
    private double min;
    public void setDate(Date date) {
         this.date = date;
     }
     public Date getDate() {
         return date;
     }

    public void setMax(double max) {
         this.max = max;
     }
     public double getMax() {
         return max;
     }

    public void setAvg(double avg) {
         this.avg = avg;
     }
     public double getAvg() {
         return avg;
     }

    public void setMin(double min) {
         this.min = min;
     }
     public double getMin() {
         return min;
     }

}