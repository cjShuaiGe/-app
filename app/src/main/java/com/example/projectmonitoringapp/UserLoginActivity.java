package com.example.projectmonitoringapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectmonitoringapp.model.LoginReceive;
import com.example.projectmonitoringapp.model.UserLogin;
import com.example.projectmonitoringapp.util.HttpTool;
import com.example.projectmonitoringapp.util.HttpUtil;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class UserLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        TextView caretakerLoginTextView = (TextView) findViewById(R.id.caretaker_login);
        caretakerLoginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserLoginActivity.this,CaretakerLogin.class));
                finish();
            }
        });

        TextView registerTextView = (TextView) findViewById(R.id.register);
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserLoginActivity.this,RegisterActivity.class));
                finish();
            }
        });

        Button loginButton = (Button) findViewById(R.id.bt_login);
        EditText usernameEditText = (EditText) findViewById(R.id.et_yhm);
        EditText passwordEditText = (EditText) findViewById(R.id.et_mm);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if ("".equals(usernameEditText.getText().toString())) {
                    Toast.makeText(UserLoginActivity.this,"用户名不能为空！",Toast.LENGTH_SHORT).show();
                } else {
                    sendLogin(Crypt2.getRandomString(16),new Gson().toJson(new UserLogin(usernameEditText.getText().toString(),passwordEditText.getText().toString())));
                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendLogin(String key, String str) {
        HttpTool.caretakerLogin(key, str, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(UserLoginActivity.this,"连接失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                LoginReceive receive = new Gson().fromJson(response.body().string(),LoginReceive.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (receive.getCode() == 200) {
                            Toast.makeText(UserLoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            HttpUtil.setToken(response.header("Authorization"));
                            startActivity(new Intent(UserLoginActivity.this,UserMainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(UserLoginActivity.this, receive.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

}