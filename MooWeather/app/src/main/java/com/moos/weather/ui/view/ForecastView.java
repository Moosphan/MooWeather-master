package com.moos.weather.ui.view;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Build;
import android.support.annotation.ArrayRes;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.moos.weather.R;
import com.moos.weather.bean.Forecast;
import com.moos.weather.bean.Weather;

/**
 * Created by yarolegovich on 08.03.2017.
 */

public class ForecastView extends LinearLayout {

    private Paint gradientPaint;
    private int[] currentGradient;

    private TextView weatherDescription;
    private TextView weatherTemperature;
    //private TextView weatherTemperature_max;
    private ImageView weatherImage;

    private ArgbEvaluator evaluator;

    public ForecastView(Context context) {
        super(context);
    }

    public ForecastView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ForecastView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ForecastView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    {
        evaluator = new ArgbEvaluator();

        gradientPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        setWillNotDraw(false);

        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        inflate(getContext(), R.layout.view_forecast, this);

        weatherDescription = (TextView) findViewById(R.id.weather_description);
        weatherImage = (ImageView) findViewById(R.id.weather_image);
        weatherTemperature = (TextView) findViewById(R.id.weather_temperature);
        //weatherTemperature_max = (TextView) findViewById(R.id.weather_temperature_max);
    }

    private void initGradient() {
        float centerX = getWidth() * 0.5f;
        Shader gradient = new LinearGradient(
                centerX, 0, centerX, getHeight(),
                currentGradient, null,
                Shader.TileMode.MIRROR);
        gradientPaint.setShader(gradient);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (currentGradient != null) {
            initGradient();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, getWidth(), getHeight(), gradientPaint);
        super.onDraw(canvas);
    }

    public void setForecast(Forecast forecast) {
        String weather = forecast.getWeather();
        currentGradient = weatherToGradient(weather);
        if (getWidth() != 0 && getHeight() != 0) {
            initGradient();
        }
        weatherDescription.setText(weather);
        weatherTemperature.setText(forecast.getTemperature());
        Glide.with(getContext()).load(weatherToIcon(weather)).into(weatherImage);
        invalidate();

        weatherImage.animate()
                .scaleX(1f).scaleY(1f)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(300)
                .start();
    }

    public void onScroll(float fraction, Forecast oldF, Forecast newF) {
        weatherImage.setScaleX(fraction);
        weatherImage.setScaleY(fraction);
        currentGradient = mix(fraction,
                weatherToGradient(newF.getWeather()),
                weatherToGradient(oldF.getWeather()));
        initGradient();
        invalidate();
    }

    private int[] mix(float fraction, int[] c1, int[] c2) {
        return new int[]{
                (Integer) evaluator.evaluate(fraction, c1[0], c2[0]),
                (Integer) evaluator.evaluate(fraction, c1[1], c2[1]),
                (Integer) evaluator.evaluate(fraction, c1[2], c2[2])
        };
    }

    /**
     * by moos on 2017/12/24
     * func:设置渐变色背景
     * @param weather
     * @return
     */
    private int[] weatherToGradient(String weather) {
//        switch (weather) {
//            case "晴":
//                return colors(R.array.gradientPeriodicClouds);
//            case "晴转多云":
//                return colors(R.array.gradientCloudy);
//            case "多云":
//                return colors(R.array.gradientMostlyCloudy);
//            case "小雨":
//                return colors(R.array.gradientPartlyCloudy);
//            case "阴":
//                return colors(R.array.gradientClear);
//            default:
//                throw new IllegalArgumentException();
//        }
        return colors(R.array.gradientClear);
    }

    /**
     * by moos on 2017/12/24
     * func：设置天气的icon
     * @param weather
     * @return
     */
    private int weatherToIcon(String weather) {
//        switch (weather) {
//            case "晴":
//                return R.mipmap.periodic_clouds;
//            case "晴转多云":
//                return R.mipmap.cloudy;
//            case "多云":
//                return R.mipmap.mostly_cloudy;
//            case "小雨":
//                return R.mipmap.partly_cloudy;
//            case "阴":
//                return R.mipmap.clear;
//            default:
//                throw new IllegalArgumentException();
//        }
        return R.mipmap.clear;
    }

    private int[] colors(@ArrayRes int res) {
        return getContext().getResources().getIntArray(res);
    }

}
