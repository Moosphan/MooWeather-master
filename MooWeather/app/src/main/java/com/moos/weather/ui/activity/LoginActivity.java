package com.moos.weather.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.moos.weather.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.login_logo_words)
    TextView textView_logo_word;
    @Bind(R.id.login_logo_view)
    ImageView imageView_logo_view;
    @Bind(R.id.login_logo_cloud_view)
    ImageView imageView_cloud_view;
    @Bind(R.id.login_logo_sun_view)
    ImageView imageView_sun_view;
    @Bind(R.id.login_page_button_login)
    Button button_login;
//    @Bind(R.id.login_page_button_progress_login)
//    CircularProgressButton login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        Typeface type= Typeface.createFromAsset(getAssets(),"font/RTWSYueRoudGoG0v1-Regular.ttf");
        textView_logo_word.setTypeface(type);
        initAnimation();
//        login_button.setIndeterminateProgressMode(true);
//        login_button.setProgress(-1);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus /*&&Build.VERSION.SDK_INT >= 19*/) {
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
        ButterKnife.unbind(this);
    }

    /**
     * by moos on 2017/12/23
     * func:初始化logo出场动画
     */
    private void initAnimation(){
        ObjectAnimator alphaAnim_cloud = ObjectAnimator.ofFloat(imageView_cloud_view,"alpha",0f,1f);
        ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(imageView_cloud_view,"scaleX",0.5f,1f);
        ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(imageView_cloud_view,"scaleY",0.5f,1f);
        ObjectAnimator translationAnim = ObjectAnimator.ofFloat(imageView_sun_view,"translationY",45,0f);
        ObjectAnimator alphaAnim_sun = ObjectAnimator.ofFloat(imageView_sun_view,"alpha",0f,1f);
        ObjectAnimator rotationAnim = ObjectAnimator.ofFloat(imageView_sun_view,"rotation",0f,360f);

        alphaAnim_cloud.setDuration(1000);
        scaleXAnim.setDuration(1000);
        scaleYAnim.setDuration(1000);
        translationAnim.setDuration(1000);
        alphaAnim_sun.setDuration(1000);
        rotationAnim.setDuration(1000);
        alphaAnim_cloud.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

                imageView_sun_view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(alphaAnim_cloud).with(scaleXAnim).with(scaleYAnim)
                .before(translationAnim).before(alphaAnim_sun).before(rotationAnim);
        animatorSet.setDuration(2000);
        animatorSet.getInterpolator();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                imageView_cloud_view.setVisibility(View.GONE);
                imageView_sun_view.setVisibility(View.GONE);
                imageView_logo_view.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animatorSet.start();
    }

    @OnClick({R.id.login_page_button_login})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.login_page_button_login:
                Intent intent = new Intent(this,HomeActivity.class);
                startActivity(intent);
                break;
        }
    }
}
