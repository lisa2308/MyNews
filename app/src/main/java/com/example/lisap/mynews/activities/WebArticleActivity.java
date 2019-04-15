package com.example.lisap.mynews.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.lisap.mynews.R;

public class WebArticleActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_article);
        String url = getIntent().getExtras().getString("url");

        webView = findViewById(R.id.activity_web_article_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }
}
