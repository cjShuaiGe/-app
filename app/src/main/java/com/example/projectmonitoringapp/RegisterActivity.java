package com.example.projectmonitoringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
            @Override
            public void onClick(View view) {
                if ("".equals(usernameEditText.getText().toString())) {
                    Toast.makeText(RegisterActivity.this,"用户名不能为空！",Toast.LENGTH_SHORT).show();
                } else if ("".equals(phoneEditText.getText().toString())) {
                    Toast.makeText(RegisterActivity.this,"手机号不能为空！",Toast.LENGTH_SHORT).show();
                } else if ("".equals(mailEditText.getText().toString())) {
                    Toast.makeText(RegisterActivity.this,"邮箱不能为空！",Toast.LENGTH_SHORT).show();
                } else {

                }
            }
        });

    }
}