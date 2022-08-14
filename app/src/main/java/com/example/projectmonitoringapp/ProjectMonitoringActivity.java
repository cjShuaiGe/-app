package com.example.projectmonitoringapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.example.projectmonitoringapp.adapter.MonitorFragmentAdapter;
import com.example.projectmonitoringapp.adapter.ProjectFragmentAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ProjectMonitoringActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    TabLayout t1;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_monitoring);
        initView();

        setView();
    }

    private void initView() {
        viewPager2=findViewById(R.id.vp3);
        t1=findViewById(R.id.tabs2);
        toolbar=findViewById(R.id.toolbar_monitor);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setView() {

        viewPager2.setAdapter(new MonitorFragmentAdapter(ProjectMonitoringActivity.this));
        TabLayoutMediator tab = new TabLayoutMediator(t1, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("JS错误");
                        break;

                    case 1:
                        tab.setText("API请求");
                        break;
                    case 2:
                        tab.setText("访问明细");
                        break;
                    case 3:
                        tab.setText("资源错误");
                        break;
                }
            }
        });
        tab.attach();

    }
}