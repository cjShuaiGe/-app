package com.example.projectmonitoringapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectmonitoringapp.adapter.UserProjectActivityAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class UserProjectMonitorActivity extends AppCompatActivity {

    Intent intent = getIntent();
    ImageView im_back;
    TextView tv_name;
    ViewPager2 viewPager2;
    TabLayout t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_project_monitor);

        tv_name.setText(intent.getStringExtra("name"));

        im_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserProjectMonitorActivity.this,UserMainActivity.class));
                finish();
            }
        });

        viewPager2.setAdapter(new UserProjectActivityAdapter(UserProjectMonitorActivity.this));
        TabLayoutMediator tab = new TabLayoutMediator(t1, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("监控管理");
                        break;
                    case 1:
                        tab.setText("更新项目");
                        break;
                    default:
                        tab.setText("发布管理");
                        break;
                }
            }
        });
        tab.attach();

    }
}