package com.moos.weather.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.moos.weather.R;
import com.moos.weather.bean.CaiYun.Astro;
import com.moos.weather.bean.CaiYun.JsonRootBean;
import com.moos.weather.ui.view.MyTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForecastDetailActivity extends AppCompatActivity {
    /**
     * by Moos on 2018/01/09
     * func:未来天气详情界面
     * @param savedInstanceState
     */
    @Bind(R.id.future_weather_back)
    ImageView bt_back;
    @Bind(R.id.future_weather_title)
    MyTextView tv_title;

    private JsonRootBean rootBean;
    private Astro astro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_detail);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        Intent intent = getIntent();
        if(intent!=null && intent.getStringExtra("goFutureDaysWeather")!=null){
            astro = (Astro) intent.getSerializableExtra("futureDate");
        }
    }

    private void initView() {

        if(astro!=null){
            String date = astro.getDate();
            String year = date.substring(0,4);
            String month = date.substring(5,7);
            String day = date.substring(8,10);
            tv_title.setText(year+"年"+month+"月"+day+"日");
        }else {
            Toast.makeText(this,"go error~",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.future_weather_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.future_weather_back:
                finishAfterTransition();//在共享元素结束后再关闭
                break;
        }
    }
}
