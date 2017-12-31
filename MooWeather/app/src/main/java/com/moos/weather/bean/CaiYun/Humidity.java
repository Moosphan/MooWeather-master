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
public class Humidity {

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