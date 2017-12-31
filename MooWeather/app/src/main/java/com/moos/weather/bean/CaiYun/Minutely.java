/**
  * Copyright 2017 bejson.com 
  */
package com.moos.weather.bean.CaiYun;
import java.util.List;

/**
 * Auto-generated: 2017-12-31 14:26:10
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Minutely {

    private String status;
    private String description;
    private List<Integer> probability;
    private String datasource;
    private List<int> precipitation_2h;
    private List<int> precipitation;
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

    public void setProbability(List<int> probability) {
         this.probability = probability;
     }
     public List<int> getProbability() {
         return probability;
     }

    public void setDatasource(String datasource) {
         this.datasource = datasource;
     }
     public String getDatasource() {
         return datasource;
     }

    public void setPrecipitation_2h(List<int> precipitation_2h) {
         this.precipitation_2h = precipitation_2h;
     }
     public List<int> getPrecipitation_2h() {
         return precipitation_2h;
     }

    public void setPrecipitation(List<int> precipitation) {
         this.precipitation = precipitation;
     }
     public List<int> getPrecipitation() {
         return precipitation;
     }

}