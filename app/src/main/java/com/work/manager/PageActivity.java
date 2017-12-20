package com.work.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


import java.util.Map;

import static com.work.manager.NetworkUtils.coursegrade;
import static com.work.manager.NetworkUtils.coursename;
import static com.work.manager.NetworkUtils.coursename2;
import static com.work.manager.NetworkUtils.hwcount;
import static com.work.manager.NetworkUtils.hwdeadlines;
import static com.work.manager.NetworkUtils.hwdetail;
import static com.work.manager.NetworkUtils.hwname;


public class PageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        getSupportActionBar().setTitle(title);
        TextView tv = (TextView)findViewById(R.id.tv);
        tv.setMovementMethod(new ScrollingMovementMethod());

        tv.setTextSize(20);
        int j = 0;
        int k = 0;
        switch (title) {
            case "Deadlines":
                 j = 0;
                 k = 0;
                for (int i = 0; i < coursename.size(); i++) {
                    tv.append("\n" + coursename.get(i) + "\n");
                    while (j < (hwcount.get(i) + k) ){
                        tv.append(hwname.get(j) + "\n");
                        tv.append(hwdeadlines.get(j) + "\n");
                        j++;
                    }
                    k = j;
                }
            break;

            case "Assignments":
                j = 0;
                k = 0;
                for (int i = 0; i < coursename.size(); i++) {
                    tv.append("\n" + coursename.get(i) + "\n");
                    while (j < (hwcount.get(i) + k) ){
                        tv.append(hwname.get(j) + "\n");
                        tv.append(hwdetail.get(j) + "\n");
                        j++;
                    }
                    k = j;
                }
                break;
            case "Grades":
//                for (Map.Entry<String,String> entry : coursegrades.entrySet()){
//                    String key = entry.getKey().toString();
//                    String val = entry.getValue();
//                    tv.append("\n" + key + "\n");
//                    tv.setText("\n" + val + "\n");
//                }
                for (int i = 0; i < coursename2.size(); i++) {
                        tv.append("\n" + coursename2.get(i) + "\n");
                        tv.append(coursegrade.get(i) + "\n");
                }
                break;


            default:
                break;

        }

    }
}
