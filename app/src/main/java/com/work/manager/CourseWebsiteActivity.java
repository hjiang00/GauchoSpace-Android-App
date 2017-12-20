package com.work.manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static com.work.manager.NodeConstructer.cookie;

public class CourseWebsiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_website);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookies(null);
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie("gauchospace.ucsb.edu", "MoodleSessiontng28new=" + cookie);

        WebView myWebView = (WebView) findViewById(R.id.courseweb);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl(url);
    }






}
