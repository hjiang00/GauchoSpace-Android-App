package com.work.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


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

    public void setBtnLogin(View view){
        Intent intent = new Intent(this, TwoActivity.class);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        if (username.length()==0) etUsername.setError("Please enter username!");
        else if (password.length()==0) etPassword.setError("Please enter password!");
        else {
            intent.putExtra("username", username);
            intent.putExtra("password", password);
            startActivityForResult(intent, 2);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2){
            if(resultCode == TwoActivity.RESULT_OK){
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
       android.os.Process.killProcess(android.os.Process.myPid());
    }
}
