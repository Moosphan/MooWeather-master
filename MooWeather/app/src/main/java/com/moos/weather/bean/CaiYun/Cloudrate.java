/**
  * Copyright 2017 bejson.com 
  */
package com.moos.weather.bean.CaiYun;
import java.io.Serializable;
import java.util.Date;

/**
 * by moos on 2018/01/06
 * func:云量
 */
public class Cloudrate implements Serializable {

    private Date date;
    private double max;
    private int avg;
    private int min;
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

    public void setAvg(int avg) {
         this.avg = avg;
     }
     public int getAvg() {
         return avg;
     }

    public void setMin(int min) {
         this.min = min;
     }
     public int getMin() {
         return min;
     }

}