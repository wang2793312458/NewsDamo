package com.feicui.news.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.feicui.news.R;

/**
 * 点击新闻进去的界面
 */
public class WebActivity extends AppCompatActivity {
    private WebView mView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Intent intent=getIntent();
        String url = intent.getStringExtra("url");
        mView= (WebView) findViewById(R.id.webview);
        WebSettings settings=mView.getSettings();

//放大缩小
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setDisplayZoomControls(false);


        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        mView.loadUrl(url);

    }
}
