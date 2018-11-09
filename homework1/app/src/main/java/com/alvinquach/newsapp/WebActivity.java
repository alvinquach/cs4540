package com.alvinquach.newsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_main);
        mWebView = findViewById(R.id.web_view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (intent.hasExtra("urlString")) {
            String urlString = intent.getStringExtra("urlString");
            mWebView.loadUrl(urlString);
        }
    }
}
