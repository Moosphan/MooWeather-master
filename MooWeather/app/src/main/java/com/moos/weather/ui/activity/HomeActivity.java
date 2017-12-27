package com.moos.weather.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.moos.weather.R;
import com.moos.weather.application.MoosApplication;
import com.moos.weather.ui.fragment.HomeFragment;
import com.moos.weather.ui.fragment.OtherFragment;
import com.moos.weather.ui.fragment.PersonalCenterFragment;
import com.moos.weather.ui.view.BottomBar;
import com.moos.weather.ui.view.BottomBarTab;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {
    @Bind(R.id.home_bottom_bar)
    BottomBar bottomBar;

    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int THIRD = 2;
    private static final String TAG = "MooWeather";
    private List<Fragment> fragments = new ArrayList<>();
    private Fragment currentFragment = new Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        fragments.add(new HomeFragment());
        fragments.add(new OtherFragment());
        fragments.add(new PersonalCenterFragment());
        //初始化第一个fragment
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.home_fragment_container, HomeFragment.newInstance());
        ft.commit();

        //设置底部导航
        bottomBar.addItem(new BottomBarTab(this,R.mipmap.splash_logo_sun, MoosApplication.mapLocation.getCity()))
                 .addItem(new BottomBarTab(this,R.mipmap.splash_logo_cloud,"其他"))
                 .addItem(new BottomBarTab(this,R.mipmap.home_icon_personal_center,"个人"));
        bottomBar.getItem(SECOND).setUnreadCount(99);
        bottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {

                Log.e(TAG, "当前tab位置为== "+position);
                showCurrentFragment(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    /**
     * by moos on 2017/12/24
     * func:显示当前的fragment，并隐藏其他fragment
     * @param position
     */
    private void showCurrentFragment(int position){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_fragment_container,fragments.get(position));
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
