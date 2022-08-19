package com.example.projectmonitoringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class UserNoticeListActivity extends AppCompatActivity {

    ImageView iv_back;
    Button bt_yes01;
    Button bt_no01;
    Button bt_yes02;
    Button bt_no02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_notice_list);

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserNoticeListActivity.this,UserMainActivity.class));
                finish();
            }
        });

        bt_yes01 = (Button) findViewById(R.id.bt_yes01);
        bt_yes01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt_yes01.setBackgroundResource(R.drawable.grey_button_corner);
                bt_no01.setBackgroundResource(R.drawable.grey_button_corner);
                bt_no01.setClickable(false);
                bt_yes01.setClickable(false);
                Toast.makeText(UserNoticeListActivity.this,"已同意该申请",Toast.LENGTH_SHORT).show();
            }
        });

        bt_no01 = (Button) findViewById(R.id.bt_no01);
        bt_no01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt_no01.setBackgroundResource(R.drawable.grey_button_corner);
                bt_yes01.setBackgroundResource(R.drawable.grey_button_corner);
                bt_yes01.setClickable(false);
                bt_no01.setClickable(false);
                Toast.makeText(UserNoticeListActivity.this,"已拒绝该申请",Toast.LENGTH_SHORT).show();
            }
        });

        bt_yes02 = (Button) findViewById(R.id.bt_yes02);
        bt_yes02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt_yes02.setBackgroundResource(R.drawable.grey_button_corner);
                bt_no02.setBackgroundResource(R.drawable.grey_button_corner);
                bt_no02.setClickable(false);
                bt_yes02.setClickable(false);
                Toast.makeText(UserNoticeListActivity.this,"已同意该申请",Toast.LENGTH_SHORT).show();
            }
        });

        bt_no02 = (Button) findViewById(R.id.bt_no02);
        bt_no02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt_no02.setBackgroundResource(R.drawable.grey_button_corner);
                bt_yes02.setBackgroundResource(R.drawable.grey_button_corner);
                bt_yes02.setClickable(false);
                bt_no02.setClickable(false);
                Toast.makeText(UserNoticeListActivity.this,"已拒绝该申请",Toast.LENGTH_SHORT).show();
            }
        });

    }

}