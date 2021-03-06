package com.work.manager;

import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.speech.RecognitionService;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;


public class AddCourseActivity extends AppCompatActivity {

    private TextView coursename;
    private TextView website;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        getSupportActionBar().setTitle("Add a course");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        coursename = (TextView) findViewById(R.id.etname);
        website = (TextView) findViewById(R.id.etsite);
        ImageView addbutton = (ImageView) findViewById(R.id.search);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = "" + coursename.getText();
                String webaddress = "" + website.getText();
                Intent intent = new Intent();
                intent.putExtra("coursename", name);
                intent.putExtra("website", webaddress);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }




}
