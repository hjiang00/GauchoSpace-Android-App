package com.work.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView listView;
    private TextView imgBtn;
    private ItemThreeAdapter adapter;
    private String coursename;
    private String website;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        getSupportActionBar().setTitle("Dashboard");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = (ListView) findViewById(R.id.list_view);
        imgBtn = (TextView) findViewById(R.id.img_btn);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_three,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.Grades:
                Intent intentgrades = new Intent(this, PageActivity.class);
                intentgrades.putExtra("title", "Grades");
                this.startActivity(intentgrades);
                break;
            case R.id.Deadlines:
                Intent intentdeadlines = new Intent(this, PageActivity.class);
                intentdeadlines.putExtra("title", "Deadlines");
                this.startActivity(intentdeadlines);
                break;
            case R.id.Assignments:
                Intent intentassignments = new Intent(this, PageActivity.class);
                intentassignments.putExtra("title", "Assignments");
                this.startActivity(intentassignments);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
    public void startAddcourse(View view){
        Intent intent = new Intent(this, AddCourseActivity.class);
        startActivityForResult(intent, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if(requestCode == RESULT_OK){
                //GET NAME AND WEBSITE
                coursename = data.getStringExtra("coursename");
                website = data.getStringExtra("website");
                //UPDATE Object list and save to data structure

            }
        }
    }

    //Clicking courses in the LISTVIEW
    @Override
    public void onClick(View view) {
        //Identify if it is a link or not
        Intent coursewebintent = new Intent(this, CourseWebsiteActivity.class);
        coursewebintent.putExtra("url", website);
        startActivity(coursewebintent);
    }
}
