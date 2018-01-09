/**
  * Copyright 2017 bejson.com 
  */
package com.moos.weather.bean.CaiYun;
import java.io.Serializable;
import java.util.Date;

/**
 * by moos on 2018/01/06
 * func:寒冷指数
 */
public class ColdRisk implements Serializable {

    private String index;
    private String desc;
    private Date datetime;
    public void setIndex(String index) {
         this.index = index;
     }
     public String getIndex() {
         return index;
     }

    public void setDesc(String desc) {
         this.desc = desc;
     }
     public String getDesc() {
         return desc;
     }

    public void setDatetime(Date datetime) {
         this.datetime = datetime;
     }
     public Date getDatetime() {
         return datetime;
     }

}