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
import com.example.projectmonitoringapp.model.Register;
import com.example.projectmonitoringapp.util.HttpTool;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView userLoginTextView = (TextView) findViewById(R.id.user_login);
        userLoginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,UserLoginActivity.class));
            }
        });

        TextView caretakerLoginTextView = (TextView) findViewById(R.id.caretaker_login);
        caretakerLoginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,CaretakerLogin.class));
            }
        });

        Button registerButton = (Button) findViewById(R.id.bt_register);
        EditText usernameEditText = (EditText) findViewById(R.id.et_yhm);
        EditText phoneEditText = (EditText) findViewById(R.id.et_sjh);
        EditText mailEditText = (EditText) findViewById(R.id.et_yx);
        EditText passwordEditText = (EditText) findViewById(R.id.et_mm);
        EditText passwordSureEditText = (EditText) findViewById(R.id.et_qrmm);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if ("".equals(usernameEditText.getText().toString())) {
                    Toast.makeText(RegisterActivity.this,"用户名不能为空！",Toast.LENGTH_SHORT).show();
                } else if ("".equals(phoneEditText.getText().toString())) {
                    Toast.makeText(RegisterActivity.this,"手机号不能为空！",Toast.LENGTH_SHORT).show();
                } else if ("".equals(mailEditText.getText().toString())) {
                    Toast.makeText(RegisterActivity.this,"邮箱不能为空！",Toast.LENGTH_SHORT).show();
                } else {
                    sendRegister(Crypt2.getRandomString(16),new Gson().toJson(new Register(usernameEditText.getText().toString(),passwordEditText.getText().toString(),phoneEditText.getText().toString())));
                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendRegister(String key, String str) {
        HttpTool.postRegister(key, str, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RegisterActivity.this,"连接失败",Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, receive.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

}