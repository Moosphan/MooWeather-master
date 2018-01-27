package com.moos.weather.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.moos.weather.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BlogActivity extends AppCompatActivity {
    /**
     * by moos on 2018/01/27
     * func:个人博客页面
     * @param savedInstanceState
     */

    @Bind(R.id.blog_back)
    ImageView bt_back;
    @Bind(R.id.blog_progress_bar)
    ProgressBar progressBar;
    @Bind(R.id.blog_webView)
    WebView webView;

    private static final String BLOG_URL = "http://www.moos.club/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webView.loadUrl(BLOG_URL);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if(newProgress<100){
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                }else {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    @OnClick({R.id.blog_back})
    public void doClick(View view){
        if(view.getId() == R.id.blog_back){
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == event.KEYCODE_BACK){
            if(webView.canGoBack()){
                webView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
