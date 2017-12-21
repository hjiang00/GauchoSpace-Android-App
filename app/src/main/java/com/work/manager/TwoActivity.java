package com.work.manager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

import static com.work.manager.NodeConstructer.COMPLETE;

public class TwoActivity extends AppCompatActivity {
    public static UserNode userNode = new UserNode();
    public static ArrayList<String> coursetitle = new ArrayList<>();
    public static HashMap<String,String> coursemap = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");

        System.out.println(username);
        System.out.println(password);

        NodeConstructer nc = new NodeConstructer();
        nc.execute(username,password);

        Handler mH = new Handler();
        mH.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (COMPLETE == 1) {
                    Intent intent1 = new Intent(TwoActivity.this, DashboardActivity.class);
                    startActivity(intent1);
                    finish();
                }else{
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        },20000);
    }
}
