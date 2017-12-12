package com.work.manager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class OneActivity extends AppCompatActivity {

    private RelativeLayout activityMain;
    private ImageView imgUcsb;
    private LinearLayout llEt;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMain = (RelativeLayout) findViewById(R.id.activity_main);
        imgUcsb = (ImageView) findViewById(R.id.img_ucsb);
        llEt = (LinearLayout) findViewById(R.id.ll_et);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
    }
}
