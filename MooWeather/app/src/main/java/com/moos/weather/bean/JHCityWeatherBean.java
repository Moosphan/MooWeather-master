package com.moos.weather.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;

/**
 * Created by moos on 2017/12/25.'
 * func:通过城市/gps获取对应的天气数据
 */

public class JHCityWeatherBean {
    private String resultcode;
    private String reason;
    private Result result;
    private int error_code;
    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }
    public String getResultcode() {
        return resultcode;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getReason() {
        return reason;
    }

    public void setResult(Result result) {
        this.result = result;
    }
    public Result getResult() {
        return result;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
    public int getError_code() {
        return error_code;
    }

    public static class Result {

        private Sk sk;
        private Today today;
        private List<Future> future;
        public void setSk(Sk sk) {
            this.sk = sk;
        }
        public Sk getSk() {
            return sk;
        }

        public void setToday(Today today) {
            this.today = today;
        }
        public Today getToday() {
            return today;
        }

        public void setFuture(List<Future> future) {
            this.future = future;
        }
        public List<Future> getFuture() {
            return future;
        }

        public static class Sk {

            private String temp;
            private String wind_direction;
            private String wind_strength;
            private String time;
            public void setTemp(String temp) {
                this.temp = temp;
            }
            public String getTemp() {
                return temp;
            }

            public void setWind_direction(String wind_direction) {
                this.wind_direction = wind_direction;
            }
            public String getWind_direction() {
                return wind_direction;
            }

            public void setWind_strength(String wind_strength) {
                this.wind_strength = wind_strength;
            }
            public String getWind_strength() {
                return wind_strength;
            }


            public void setTime(String time) {
                this.time = time;
            }
            public String getTime() {
                return time;
            }

        }
        public static class Future {

            private String temperature;
            private String weather;
            private Weather_id weather_id;
            private String wind;
            private String week;
            private String date;
            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }
            public String getTemperature() {
                return temperature;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }
            public String getWeather() {
                return weather;
            }

            public void setWeather_id(Weather_id weather_id) {
                this.weather_id = weather_id;
            }
            public Weather_id getWeather_id() {
                return weather_id;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }
            public String getWind() {
                return wind;
            }

            public void setWeek(String week) {
                this.week = week;
            }
            public String getWeek() {
                return week;
            }

            public void setDate(String date) {
                this.date = date;
            }
            public String getDate() {
                return date;
            }

        }

        public static class Today {

            private String temperature;
            private String weather;
            private Weather_id weather_id;
            private String wind;
            private String week;
            private String city;
            private String date_y;
            private String dressing_index;
            private String dressing_advice;
            private String uv_index;
            private String comfort_index;
            private String wash_index;
            private String travel_index;
            private String exercise_index;
            private String drying_index;
            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }
            public String getTemperature() {
                return temperature;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }
            public String getWeather() {
                return weather;
            }

            public void setWeather_id(Weather_id weather_id) {
                this.weather_id = weather_id;
            }
            public Weather_id getWeather_id() {
                return weather_id;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }
            public String getWind() {
                return wind;
            }

            public void setWeek(String week) {
                this.week = week;
            }
            public String getWeek() {
                return week;
            }

            public void setCity(String city) {
                this.city = city;
            }
            public String getCity() {
                return city;
            }

            public void setDate_y(String date_y) {
                this.date_y = date_y;
            }
            public String getDate_y() {
                return date_y;
            }

            public void setDressing_index(String dressing_index) {
                this.dressing_index = dressing_index;
            }
            public String getDressing_index() {
                return dressing_index;
            }

            public void setDressing_advice(String dressing_advice) {
                this.dressing_advice = dressing_advice;
            }
            public String getDressing_advice() {
                return dressing_advice;
            }

            public void setUv_index(String uv_index) {
                this.uv_index = uv_index;
            }
            public String getUv_index() {
                return uv_index;
            }

            public void setComfort_index(String comfort_index) {
                this.comfort_index = comfort_index;
            }
            public String getComfort_index() {
                return comfort_index;
            }

            public void setWash_index(String wash_index) {
                this.wash_index = wash_index;
            }
            public String getWash_index() {
                return wash_index;
            }

            public void setTravel_index(String travel_index) {
                this.travel_index = travel_index;
            }
            public String getTravel_index() {
                return travel_index;
            }

            public void setExercise_index(String exercise_index) {
                this.exercise_index = exercise_index;
            }
            public String getExercise_index() {
                return exercise_index;
            }

            public void setDrying_index(String drying_index) {
                this.drying_index = drying_index;
            }
            public String getDrying_index() {
                return drying_index;
            }

        }

        public static class Weather_id {

            private String fa;
            private String fb;
            public void setFa(String fa) {
                this.fa = fa;
            }
            public String getFa() {
                return fa;
            }

            public void setFb(String fb) {
                this.fb = fb;
            }
            public String getFb() {
                return fb;
            }

        }

    }
}
