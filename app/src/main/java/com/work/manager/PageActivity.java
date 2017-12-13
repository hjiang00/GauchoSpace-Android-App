package com.work.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class PageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        getSupportActionBar().setTitle(title);
    }
}
