package com.moos.weather.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by moos on 2018/1/6.
 * func:时间的工具类
 */

public class TimeUtils {

    /**
     * func:通过具体日期来获得星期几（中式）
     * @param date 标准日期
     * @return  星期几
     */
    public static String getChineseWeekDay(String date){
        String weekTime = "星期";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {

            c.setTime(format.parse(date));

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        switch (c.get(Calendar.DAY_OF_WEEK)){
            case 1:

                weekTime += "日";
                break;
            case 2:

                weekTime += "一";
                break;
            case 3:

                weekTime += "二";
                break;
            case 4:

                weekTime += "三";
                break;
            case 5:

                weekTime += "四";
                break;
            case 6:

                weekTime += "五";
                break;
            case 7:

                weekTime += "六";
                break;

            default:
                throw new IllegalArgumentException("Illegal date format");

        }
        return weekTime;

    }

    /**
     * func:通过具体日期来获得星期几（英式）
     * @param date 标准日期
     * @return  星期几
     */
    public static String getEnglishWeekDay(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {

            c.setTime(format.parse(date));

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        switch (c.get(Calendar.DAY_OF_WEEK)){
            case 1:

                return "Sunday";
            case 2:

                return "Monday";
            case 3:

                return "Tuesday";
            case 4:

                return "Wednesday";
            case 5:

                return "Thursday";
            case 6:

                return "Friday";
            case 7:

                return "Saturday";

            default:
                throw new IllegalArgumentException("Illegal date format");

        }

    }
}
