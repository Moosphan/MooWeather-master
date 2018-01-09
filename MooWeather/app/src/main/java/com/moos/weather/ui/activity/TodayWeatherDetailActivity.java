package com.moos.weather.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.moos.weather.R;
import com.moos.weather.bean.CaiYun.JsonRootBean;
import com.moos.weather.ui.view.MyTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    private JsonRootBean rootBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_weather_detail);
        ButterKnife.bind(this);
        initData();
        initView();

    }

    private void initData() {
        Intent intent = getIntent();
        if(intent!=null){
            rootBean = (JsonRootBean) intent.getSerializableExtra("weatherData");
        }
    }

    private void initView() {

        String date = rootBean.getResult().getDaily().getAstro().get(0).getDate();
        String year = date.substring(0,4);
        String month = date.substring(5,7);
        String day = date.substring(8,10);
        tv_title.setText(year+"年"+month+"月"+day+"日");
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
