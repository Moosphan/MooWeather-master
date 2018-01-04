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
import com.moos.weather.bean.Model.Forecast;
import com.moos.weather.bean.JuHe.JuHeWeatherKind;

/**
 * Created by yarolegovich on 08.03.2017.
 */

public class ForecastView extends LinearLayout {

    private Paint gradientPaint;
    private int[] currentGradient;

    private TextView weatherDescription;
    private TextView weatherTemperature;
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
        String weatherId = forecast.getWeatherId();
        currentGradient = caiYunWeatherToGradient(weatherId);
        if (getWidth() != 0 && getHeight() != 0) {
            initGradient();
        }
        weatherDescription.setText(weather);
        weatherTemperature.setText(forecast.getTemperature());
        Glide.with(getContext()).load(caiYunWeatherToIcon(weatherId)).into(weatherImage);
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
                caiYunWeatherToGradient(newF.getWeatherId()),
                caiYunWeatherToGradient(oldF.getWeatherId()));
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
     * func:根据聚合天气的类型设置渐变色背景
     * @param weather
     * @return
     */
    private int[] weatherToGradient(String weather) {
        switch (weather) {
            case JuHeWeatherKind.SUNNY:            //晴天.
                return colors(R.array.gradientSunnyDay);

            case JuHeWeatherKind.PARTLY_CLOUDY:    //多云.
                return colors(R.array.gradientMostlyCloudy);

            case JuHeWeatherKind.CLOUDY:           //阴天.
                return colors(R.array.gradientCloudy);

            case JuHeWeatherKind.SHOWER:           //阵雨.
                return colors(R.array.gradientLittleRain);

            case JuHeWeatherKind.THUNDERSTORMS:    //雷阵雨.
                return colors(R.array.gradientLittleRain);

            case JuHeWeatherKind.THUNDERSTORMS_WITH_HAIL://雷阵雨伴有冰雹.
                return colors(R.array.gradientLittleRain);

            case JuHeWeatherKind.SLEET:            //雨夹雪.
                return colors(R.array.gradientRainAndSnow);

            case JuHeWeatherKind.LIGHT_RAIN:       //小雨.
                return colors(R.array.gradientLittleRain);

            case JuHeWeatherKind.MIDDLE_RAIN:      //中雨 .
                return colors(R.array.gradientRainyDay);

            case JuHeWeatherKind.HEAVY_RAIN:       //大雨 .
                return colors(R.array.gradientRainyDay);

            case JuHeWeatherKind.RAINSTORM:        //暴雨.
                return colors(R.array.gradientStrongRain);

            case JuHeWeatherKind.BIG_HEAVY_RAIN:   //大暴雨.
                return colors(R.array.gradientStrongRain);

            case JuHeWeatherKind.SUPER_HEAVY_RAIN: //特大暴雨.(待完善)
                return colors(R.array.gradientStrongRain);

            case JuHeWeatherKind.SNOW_SHOWER:      //阵雪.
                return colors(R.array.gradientSnowLittleDay);

            case JuHeWeatherKind.LIGHT_SNOW:       //小雪.
                return colors(R.array.gradientSnowLittleDay);

            case JuHeWeatherKind.MIDDLE_SNOW:      //中雪.
                return colors(R.array.gradientSnowDay);

            case JuHeWeatherKind.BIG_SNOW:         //大雪.
                return colors(R.array.gradientBigSnow);

            case JuHeWeatherKind.HEAVY_SNOW:       //暴雪.
                return colors(R.array.gradientBigSnow);

            case JuHeWeatherKind.FROG:             //雾天.
                return colors(R.array.gradientFog);

            case JuHeWeatherKind.FREEZING_RAIN:    //冻雨.
                return colors(R.array.gradientRainAndSnow);

            case JuHeWeatherKind.SANDSTORM:        //沙尘暴.
                return colors(R.array.gradientMistDay);

            case JuHeWeatherKind.RAIN_LIGHT_TO_MIDDLE://小雨转中雨. (待完善)
                return colors(R.array.gradientLittleRain);

            case JuHeWeatherKind.RAIN_MIDDLE_TO_BIG://中雨转大雨.
                return colors(R.array.gradientRainyDay);

            case JuHeWeatherKind.RAIN_BIG_TO_HEAVY: //大雨转暴雨.
                return colors(R.array.gradientStrongRain);

            case JuHeWeatherKind.RAIN_HEAVY_TO_LOT: //暴雨转大暴雨
                return colors(R.array.gradientStrongRain);

            case JuHeWeatherKind.RAIN_LOT_TO_SUPER: //大暴雨转特大暴雨
                return colors(R.array.gradientStrongRain);

            case JuHeWeatherKind.SNOW_LIGHT_TO_MIDDLE://小雪转中雪
                return colors(R.array.gradientSnowLittleDay);

            case JuHeWeatherKind.SNOW_MIDDLE_TO_BIG:  //中雪转大雪
                return colors(R.array.gradientSnowDay);

            case JuHeWeatherKind.SNOW_BIG_TO_HEAVY:   //大雪转暴雪
                return colors(R.array.gradientBigSnow);

            case JuHeWeatherKind.FLOATING_DIST:       //浮尘.
                return colors(R.array.gradientMistDay);

            case JuHeWeatherKind.YANG_SHA:            //扬沙.
                return colors(R.array.gradientMistDay);

            case JuHeWeatherKind.STRONG_SANDSTORM:    //强沙尘暴
                return colors(R.array.gradientSandstorm);

            case JuHeWeatherKind.HAZE:                //霾.
                return colors(R.array.gradientFog);

            default:
                //throw new IllegalArgumentException();
                return colors(R.array.gradientPartlyCloudy);
        }
    }

    /**
     * by moos on 2017/12/24
     * func：根据聚合天气的天气类型设置天气的icon
     * @param weather
     * @return
     */
    private int weatherToIcon(String weather) {
        switch (weather) {
            case JuHeWeatherKind.SUNNY:            //晴天.
                return R.mipmap.clear_day;

            case JuHeWeatherKind.PARTLY_CLOUDY:    //多云.
                return R.mipmap.mostly_cloudy;

            case JuHeWeatherKind.CLOUDY:           //阴天.
                return R.mipmap.cloudy_weather;

            case JuHeWeatherKind.SHOWER:           //阵雨.
                return R.mipmap.shower_day;

            case JuHeWeatherKind.THUNDERSTORMS:    //雷阵雨.
                return R.mipmap.thunderstorm_weather;

            case JuHeWeatherKind.THUNDERSTORMS_WITH_HAIL://雷阵雨伴有冰雹.
                return R.mipmap.thunderstorm_weather;

            case JuHeWeatherKind.SLEET:            //雨夹雪.
                return R.mipmap.rain_snow;

            case JuHeWeatherKind.LIGHT_RAIN:       //小雨.
                return R.mipmap.weather_small_rain_day;

            case JuHeWeatherKind.MIDDLE_RAIN:      //中雨 .
                return R.mipmap.weather_big_rain;

            case JuHeWeatherKind.HEAVY_RAIN:       //大雨 .
                return R.mipmap.weather_big_rain;

            case JuHeWeatherKind.RAINSTORM:        //暴雨.
                return R.mipmap.strong_rainy_weather;

            case JuHeWeatherKind.BIG_HEAVY_RAIN:   //大暴雨.
                return R.mipmap.strong_rainy_weather;

            case JuHeWeatherKind.SUPER_HEAVY_RAIN: //特大暴雨.(待完善)
                return R.mipmap.strong_rainy_weather;

            case JuHeWeatherKind.SNOW_SHOWER:      //阵雪.
                return R.mipmap.weather_snows_scattered_day;

            case JuHeWeatherKind.LIGHT_SNOW:       //小雪.
                return R.mipmap.weather_snows_scattered_day;

            case JuHeWeatherKind.MIDDLE_SNOW:      //中雪.
                return R.mipmap.weather_big_snow;

            case JuHeWeatherKind.BIG_SNOW:         //大雪.
                return R.mipmap.weather_big_snow;

            case JuHeWeatherKind.HEAVY_SNOW:       //暴雪.
                return R.mipmap.strong_snow_weather;

            case JuHeWeatherKind.FROG:             //雾天.
                return R.mipmap.weather_fog;

            case JuHeWeatherKind.FREEZING_RAIN:    //冻雨.
                return R.mipmap.weather_snow_rain;

            case JuHeWeatherKind.SANDSTORM:        //沙尘暴.
                return R.mipmap.weather_mist;

            case JuHeWeatherKind.RAIN_LIGHT_TO_MIDDLE://小雨转中雨. (待完善)
                return R.mipmap.weather_small_rain_day;

            case JuHeWeatherKind.RAIN_MIDDLE_TO_BIG://中雨转大雨.
                return R.mipmap.weather_big_rain;

            case JuHeWeatherKind.RAIN_BIG_TO_HEAVY: //大雨转暴雨.
                return R.mipmap.weather_big_rain;

            case JuHeWeatherKind.RAIN_HEAVY_TO_LOT: //暴雨转大暴雨
                return R.mipmap.strong_rainy_weather;

            case JuHeWeatherKind.RAIN_LOT_TO_SUPER: //大暴雨转特大暴雨
                return R.mipmap.strong_rainy_weather;

            case JuHeWeatherKind.SNOW_LIGHT_TO_MIDDLE://小雪转中雪
                return R.mipmap.weather_snows_scattered_day;

            case JuHeWeatherKind.SNOW_MIDDLE_TO_BIG:  //中雪转大雪
                return R.mipmap.weather_big_snow;

            case JuHeWeatherKind.SNOW_BIG_TO_HEAVY:   //大雪转暴雪
                return R.mipmap.strong_snow_weather;

            case JuHeWeatherKind.FLOATING_DIST:       //浮尘.
                return R.mipmap.big_haze_weather;

            case JuHeWeatherKind.YANG_SHA:            //扬沙.
                return R.mipmap.big_haze_weather;

            case JuHeWeatherKind.STRONG_SANDSTORM:    //强沙尘暴
                return R.mipmap.partly_cloudy;

            case JuHeWeatherKind.HAZE:                //霾.
                return R.mipmap.haze_day;

            default:
                //throw new IllegalArgumentException();
                return R.mipmap.clear;
        }

    }

    /**
     * by moos on 2018/01/04
     * func:根据彩云天气的类型设置渐变色背景
     * @param weather
     * @return
     */
    private int[] caiYunWeatherToGradient(String weather) {
        switch (weather) {
            case "CLEAR_DAY":                        //晴天.
                return colors(R.array.gradientSunnyDay);

            case "CLEAR_NIGHT":                      //晴天(night).
                return colors(R.array.gradientSunnyDay);

            case "PARTLY_CLOUDY_DAY":                //多云.
                return colors(R.array.gradientPartlyCloudy);

            case "PARTLY_CLOUDY_NIGHT":              //多云(night).
                return colors(R.array.gradientCloudy);

            case "CLOUDY":                           //阴天
                return colors(R.array.gradientCloudy);

            case "RAIN":                             //雨 .
                return colors(R.array.gradientRainyDay);

            case "SNOW":                             //雪.
                return colors(R.array.gradientSnowDay);

            case "FOG":                              //雾天.
                return colors(R.array.gradientFog);


            case "WIND":                             //风.
                return colors(R.array.gradientMistDay);


            case "SLEET":                            //冻雨.
                return colors(R.array.gradientRainAndSnow);

            case "HAZE":                             //霾.
                return colors(R.array.gradientFog);

            default:
                //throw new IllegalArgumentException();
                return colors(R.array.gradientPartlyCloudy);
        }
    }

    /**
     * by moos on 2018/01/04
     * func：根据彩云天气的天气类型设置天气的icon
     * @param weather
     * @return
     */
    private int caiYunWeatherToIcon(String weather) {
        switch (weather) {
            case "CLEAR_DAY":              //晴天.
                return R.mipmap.clear_day;

            case "CLEAR_NIGHT":            //晴天(NIGHT).
                return R.mipmap.clear_day;

            case "PARTLY_CLOUDY_DAY":      //多云.
                return R.mipmap.partly_cloudy;

            case "PARTLY_CLOUDY_NIGHT":    //多云(night).
                return R.mipmap.partly_cloudy;

            case "CLOUDY":                 //阴天.
                return R.mipmap.cloudy_weather;

            case "RAIN":                   //雨 .
                return R.mipmap.weather_big_rain;

            case "SNOW":                   //雪.
                return R.mipmap.weather_big_snow;

            case "FOG":                    //雾天.
                return R.mipmap.weather_fog;

            case "SLEET":                  //冻雨.
                return R.mipmap.weather_snow_rain;

            case "WIND":                   //风.
                return R.mipmap.big_haze_weather;

            case "HAZE":                   //霾.
                return R.mipmap.haze_day;

            default:
                //throw new IllegalArgumentException();
                return R.mipmap.clear;
        }

    }

    private int[] colors(@ArrayRes int res) {
        return getContext().getResources().getIntArray(res);
    }

}
