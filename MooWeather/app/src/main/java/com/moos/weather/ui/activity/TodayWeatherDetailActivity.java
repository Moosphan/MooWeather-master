package com.moos.weather.ui.activity;

import android.content.Entity;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import com.moos.weather.R;
import com.moos.weather.bean.CaiYun.Hourly;
import com.moos.weather.bean.CaiYun.JsonRootBean;
import com.moos.weather.bean.CaiYun.Temperature;
import com.moos.weather.ui.view.MyTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class TodayWeatherDetailActivity extends AppCompatActivity {
    /**
     * by Moos on 2018/01/08
     * func:今日天气详情界面
     * @param savedInstanceState
     */
    @Bind(R.id.today_weather_back)
    ImageView bt_back;
    @Bind(R.id.today_weather_title)
    MyTextView tv_title;
    @Bind(R.id.today_weather_chart_hours)
    LineChartView chartHours;

    private JsonRootBean rootBean;
    private Hourly hourlyWeather;
    private List<Hourly.Temperature> hourlyTemperatures;
    List<PointValue> pointValues = new ArrayList<>();
    List<AxisValue> axisValues = new ArrayList<>();
    private final static String TAG = "Weather detail";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_weather_detail);
        ButterKnife.bind(this);
        initData();
        initView();

    }

    private void initData() {
        Intent intent = getIntent();
        if(intent!=null){
            rootBean = (JsonRootBean) intent.getSerializableExtra("weatherData");
            hourlyWeather = rootBean.getResult().getHourly();
            hourlyTemperatures = hourlyWeather.getTemperature();
        }
    }

    private void initView() {

        String date = rootBean.getResult().getDaily().getAstro().get(0).getDate();
        String year = date.substring(0,4);
        String month = date.substring(5,7);
        String day = date.substring(8,10);
        tv_title.setText(year+"年"+month+"月"+day+"日");
        setUpChart();
    }

    /**
     * 初始化图表
     */
    private void setUpChart(){

        initChartData();

        Line line = new Line(pointValues).setColor(getResources().getColor(R.color.cornflower_blue));
        List<Line> lines = new ArrayList<>();
        line.setShape(ValueShape.CIRCLE);      // 设置点的形状
        line.setCubic(true);                   // 是曲线还是折线
        line.setFilled(false);                 // 是否填充曲线区域
        line.setHasLabels(true);               // 是否显示点备注
        line.setHasLabelsOnlyForSelected(false);// 点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);                // 是否用线条表示
        line.setHasPoints(true);               // 是否显示坐标圆点
        lines.add(line);
        LineChartData chartData = new LineChartData();
        chartData.setLines(lines);
        /**
         * 初始化x坐标轴
         */
        Axis axisX = new Axis();
        axisX.setHasTiltedLabels(true); // x轴文字是斜的还是直的（true是斜的）
        axisX.setTextColor(getResources().getColor(R.color.orchid));
        axisX.setTextSize(10);
        axisX.setMaxLabelChars(8);
        axisX.setValues(axisValues);
        chartData.setAxisXBottom(axisX);// x轴设置在底部展示
        axisX.setHasLines(true);        // 是否显示x轴的刻度
        /**
         * 初始化y坐标轴
         */
        Axis axisY = new Axis();
        axisY.setName("时间");              // y轴标注
        axisY.setTextSize(10);
        chartData.setAxisYLeft(axisY);     // y轴设置在左边
        /**
         * 设置图表行为属性
         */
        chartHours.setInteractive(true);
        chartHours.setZoomType(ZoomType.HORIZONTAL);
        chartHours.setMaxZoom(6f);
        chartHours.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        chartHours.setLineChartData(chartData);
        chartHours.setVisibility(View.VISIBLE);

        /**
         * 展示x轴刻度个数
         */
        Viewport v = new Viewport(chartHours.getMaximumViewport());
        v.left = 0;
        v.right= 9;
        chartHours.setCurrentViewport(v);


    }

    /**
     * by moos on 2018/01/31
     * func:初始化图表数据
     */
    private void initChartData(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i<hourlyTemperatures.size();i++){
                    Hourly.Temperature temperature = new Hourly.Temperature();
                    temperature = hourlyTemperatures.get(i);
                    String hourTime = temperature.getDatetime().substring(11,16);
                    Log.e(TAG, "run: "+hourTime );
                    axisValues.add(new AxisValue(i).setLabel(hourTime));
                    pointValues.add(new PointValue(i, temperature.getValue()));
                }
            }
        }).start();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.today_weather_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.today_weather_back:
                finishAfterTransition();//在共享元素结束后再关闭
                break;
        }
    }
}
