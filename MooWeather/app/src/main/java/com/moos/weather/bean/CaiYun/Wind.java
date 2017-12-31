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
public class Wind {

    private Date date;
    private Max max;
    private Avg avg;
    private Min min;
    public void setDate(Date date) {
         this.date = date;
     }
     public Date getDate() {
         return date;
     }

    public void setMax(Max max) {
         this.max = max;
     }
     public Max getMax() {
         return max;
     }

    public void setAvg(Avg avg) {
         this.avg = avg;
     }
     public Avg getAvg() {
         return avg;
     }

    public void setMin(Min min) {
         this.min = min;
     }
     public Min getMin() {
         return min;
     }

}