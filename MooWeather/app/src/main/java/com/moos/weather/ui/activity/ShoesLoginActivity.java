package com.moos.weather.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.moos.weather.R;
import com.moos.weather.ui.view.MyTextView;
import com.nineoldandroids.animation.Animator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShoesLoginActivity extends AppCompatActivity {

    @Bind(R.id.shoes_main_view)
    RelativeLayout page_main;
    @Bind(R.id.shoes_login_view)
    RelativeLayout page_login;
    @Bind(R.id.shoes_register_view)
    RelativeLayout page_register;
    @Bind(R.id.shoes_go_login)
    MyTextView bt_main_go_login;
    @Bind(R.id.shoes_go_register)
    MyTextView bt_main_go_register;
    @Bind(R.id.shoes_login_back)
    ImageView bt_login_back;
    @Bind(R.id.shoes_register_back)
    ImageView bt_register_back;
    @Bind(R.id.shoes_login_login)
    MyTextView bt_go_login;
    @Bind(R.id.shoes_register_register)
    MyTextView bt_go_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoes_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.shoes_go_login, R.id.shoes_go_register, R.id.shoes_login_back, R.id.shoes_register_back,
              R.id.shoes_login_login})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.shoes_go_login:

                changeToLoginView();
                break;

            case R.id.shoes_go_register:

                changeToRegisterView();
                break;

            case R.id.shoes_login_back:

                changeToMainView(page_login);
                break;

            case R.id.shoes_register_back:

                changeToMainView(page_register);
                break;

            case R.id.shoes_login_login:
                Intent intent = new Intent(this,HomeActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * by moos on 2017/12/27
     * func:切换到登录界面
     */
    private void changeToLoginView(){
        YoYo.with(Techniques.SlideOutRight)
                .duration(100)
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        page_main.setVisibility(View.GONE);
                        YoYo.with(Techniques.SlideInLeft)
                                .duration(100)
                                .withListener(new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(Animator animation) {
                                        page_login.setVisibility(View.VISIBLE);

                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationCancel(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationRepeat(Animator animation) {

                                    }
                                })
                                .playOn(page_login);

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .playOn(page_main);
    }

    /**
     * by moos on 2017/12/27
     * func:切换到注册界面
     */
    private void changeToRegisterView(){
        YoYo.with(Techniques.SlideOutLeft)
                .duration(100)
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        page_main.setVisibility(View.GONE);
                        YoYo.with(Techniques.SlideInRight)
                                .duration(100)
                                .withListener(new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(Animator animation) {
                                        page_register.setVisibility(View.VISIBLE);

                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationCancel(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationRepeat(Animator animation) {

                                    }
                                })
                                .playOn(page_register);

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .playOn(page_main);
    }

    /**
     * by moos on 2017/12/27
     * func:切换到主界面
     */
    private void changeToMainView(final View view){
        YoYo.with(Techniques.SlideOutRight)
                .duration(100)
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(View.GONE);
                        YoYo.with(Techniques.SlideInLeft)
                                .duration(100)
                                .withListener(new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(Animator animation) {
                                        page_main.setVisibility(View.VISIBLE);

                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationCancel(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationRepeat(Animator animation) {

                                    }
                                })
                                .playOn(page_main);

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .playOn(view);
    }
}
