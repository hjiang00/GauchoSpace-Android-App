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
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by lijinfeng on 2017/12/5.
 */

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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
