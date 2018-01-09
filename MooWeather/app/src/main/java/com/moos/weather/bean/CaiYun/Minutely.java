/**
  * Copyright 2017 bejson.com 
  */
package com.moos.weather.bean.CaiYun;
import java.io.Serializable;
import java.util.List;

/**
 * Auto-generated: 2017-12-31 14:26:10
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Minutely implements Serializable {

    private String status;
    private String description;
    private List<Integer> probability;
    private String datasource;
    private List<Integer> precipitation_2h;
    private List<Integer> precipitation;
    public void setStatus(String status) {
         this.status = status;
     }
     public String getStatus() {
         return status;
     }

    public void setDescription(String description) {
         this.description = description;
     }
     public String getDescription() {
         return description;
     }

    public void setProbability(List<Integer> probability) {
         this.probability = probability;
     }
     public List<Integer> getProbability() {
         return probability;
     }

    public void setDatasource(String datasource) {
         this.datasource = datasource;
     }
     public String getDatasource() {
         return datasource;
     }

    public void setPrecipitation_2h(List<Integer> precipitation_2h) {
         this.precipitation_2h = precipitation_2h;
     }
     public List<Integer> getPrecipitation_2h() {
         return precipitation_2h;
     }

    public void setPrecipitation(List<Integer> precipitation) {
         this.precipitation = precipitation;
     }
     public List<Integer> getPrecipitation() {
         return precipitation;
     }

}