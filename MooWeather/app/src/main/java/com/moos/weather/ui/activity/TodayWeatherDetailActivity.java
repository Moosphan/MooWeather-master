package com.moos.weather.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.moos.weather.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

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
                finish();
                break;
        }
    }
}
