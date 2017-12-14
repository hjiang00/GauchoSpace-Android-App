package com.work.manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CourseWebsiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_website);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        WebView myWebView = (WebView) findViewById(R.id.courseweb);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl(url);
    }






}
