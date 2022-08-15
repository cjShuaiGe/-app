package com.example.projectmonitoringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
            }
        });

        Button loginButton = (Button) findViewById(R.id.bt_login);
        EditText usernameEditText = (EditText) findViewById(R.id.et_yhm);
        EditText passwordEditText = (EditText) findViewById(R.id.et_mm);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(usernameEditText.getText().toString())) {
                    Toast.makeText(UserLoginActivity.this,"用户名不能为空！",Toast.LENGTH_SHORT).show();
                } else {

                }
            }
        });

    }
}