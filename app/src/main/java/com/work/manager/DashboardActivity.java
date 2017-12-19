package com.work.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class DashboardActivity extends AppCompatActivity{

    private ListView listView;
    private TextView imgBtn;
    private String coursename;
    private String website;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> coursetitle = new ArrayList<>();
    private HashMap<String,String> coursemap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        getSupportActionBar().setTitle("Dashboard");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = (ListView) findViewById(R.id.list_view);
        imgBtn = (TextView) findViewById(R.id.img_btn);
        coursetitle.add("CS184");
        coursemap.put(coursetitle.get(0),"http://www.cs.ucsb.edu/~holl/CS184/");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, coursetitle);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClickListener);
        listView.setOnItemLongClickListener(onItemLongClickListener);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");

        System.out.println(username);
        System.out.println(password);

        NodeConstructer nc = new NodeConstructer();
        nc.execute(username,password);

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
            if(resultCode == DashboardActivity.RESULT_OK){
                Toast.makeText(this, "A new course has been added to your Dashboard", Toast.LENGTH_SHORT).show();
                //GET NAME AND WEBSITE
                coursename = data.getStringExtra("coursename") + "(Self Added)";
                website = data.getStringExtra("website");
                //UPDATE Object list and save to data structure
                coursemap.put(coursename,website);
                coursetitle.add(coursename);
                adapter.notifyDataSetChanged();
            }
        }
    }


    public AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String checktype = adapterView.getItemAtPosition(i).toString();
            if (checktype.contains("Self")){
            Intent coursewebintent = new Intent(DashboardActivity.this, CourseWebsiteActivity.class);
            coursewebintent.putExtra("url", coursemap.get(checktype));
            Log.d("url", coursemap.get(checktype));
            startActivity(coursewebintent);
            }
        }
    };

    public AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            String checktype = adapterView.getItemAtPosition(i).toString();
            if (checktype.contains("Self")){
                coursetitle.remove(checktype);
                coursemap.remove(checktype);
                adapter.notifyDataSetChanged();
                Toast.makeText(DashboardActivity.this, "The course you selected has been deleted", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(DashboardActivity.this, "This course can not be deleted", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    };

}
