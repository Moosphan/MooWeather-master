package com.moos.weather.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.hanks.htextview.base.AnimationListener;
import com.hanks.htextview.base.HTextView;
import com.hanks.htextview.fade.FadeTextView;
import com.hanks.htextview.fall.FallTextView;
import com.hanks.htextview.scale.ScaleText;
import com.hanks.htextview.scale.ScaleTextView;
import com.hanks.htextview.typer.TyperTextView;
import com.moos.weather.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {
    /**
     * by moos on 2017/12/23
     * func：启动页面
     * @param savedInstanceState
     */
    @Bind(R.id.splash_app_name_view) ImageView imageView_name;
    @Bind(R.id.splash_center_point_view) ImageView imageView_center_point;
    @Bind(R.id.splash_outside_circle_view) ImageView imageView_outside_circle;
    @Bind(R.id.splash_lottie_view) LottieAnimationView animationView;
    @Bind(R.id.splash_logo) ImageView imageView_logo;
    @Bind(R.id.splash_words) FadeTextView splash_words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        //startAnimation();
        startLottieAnimation();
        startWordAnimation();


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        animationView.destroyDrawingCache();
        ButterKnife.unbind(this);

    }

    /**
     * by moos on 2017/12/23
     * func:开始动画
     */
    private void startAnimation(){
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(imageView_center_point,"alpha",0f,1f);
        ObjectAnimator translationAnim = ObjectAnimator.ofFloat(imageView_name,"translationX",-500f,0f);
        ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(imageView_outside_circle,"scaleX",0.5f,1f);
        ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(imageView_outside_circle,"scaleY",0.5f,1f);
        ObjectAnimator rotationAnim = ObjectAnimator.ofFloat(imageView_outside_circle,"rotation",0f,360f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleXAnim).with(scaleYAnim).with(rotationAnim)
                .before(alphaAnim).with(translationAnim);
        animatorSet.setDuration(1500);
        animatorSet.getInterpolator();
        animatorSet.start();
    }

    /**
     * by moos on 2017/12/23
     * func:设置lottie动画效果
     */
    private void startLottieAnimation(){
        animationView.playAnimation();
        final ValueAnimator animator = ValueAnimator.ofFloat(0f,1f).setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                animationView.setProgress(Float.parseFloat(animator.getAnimatedValue().toString()));
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //前往登陆界面
                Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        animator.start();
    }

    /**
     * by moos on 2017/12/23
     * func：文字动画和logo显示动画
     */
    private void startWordAnimation(){

        //logo动画
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(imageView_logo,"alpha",0f,1f);
        ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(imageView_logo,"scaleX",0.5f,1f);
        ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(imageView_logo,"scaleY",0.5f,1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(2000);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                //文字动画
                splash_words.animateText(getString(R.string.splash_words));
                splash_words.setAnimationDuration(2000);
                splash_words.animate();
            }
        });
        animatorSet.play(alphaAnim).with(scaleXAnim).with(scaleYAnim);
        animatorSet.start();






    }

}
