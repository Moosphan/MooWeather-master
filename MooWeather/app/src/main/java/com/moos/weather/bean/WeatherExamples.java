package com.moos.weather.bean;

import com.moos.weather.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by moos on 2017/12/24.
 */

public class WeatherExamples {
    public static WeatherExamples get() {
        return new WeatherExamples();
    }

    private WeatherExamples() {
    }

    public List<Forecast> getForecasts() {
        return Arrays.asList(
                new Forecast("Pisa", R.drawable.pisa, "2","15", Weather.PARTLY_CLOUDY),
                new Forecast("Paris", R.drawable.paris, "6", "12",Weather.CLEAR),
                new Forecast("New York", R.drawable.new_york, "9", "20",Weather.MOSTLY_CLOUDY),
                new Forecast("Rome", R.drawable.rome, "-3","5", Weather.PARTLY_CLOUDY),
                new Forecast("London", R.drawable.london, "0", "10",Weather.PERIODIC_CLOUDS),
                new Forecast("Washington", R.drawable.washington, "15","22", Weather.CLEAR));
    }
}
