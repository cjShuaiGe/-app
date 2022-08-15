package com.example.projectmonitoringapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.projectmonitoringapp.adapter.RcAccessAdapter;
import com.example.projectmonitoringapp.model.RcAccess;

import java.util.ArrayList;
import java.util.List;

public class AccessActivity extends AppCompatActivity {
    private RecyclerView rc;
    private RcAccessAdapter adapter;
    private Toolbar toolbar;
    List<RcAccess> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);
        setView();
        setData();

        adapter=new RcAccessAdapter(list,AccessActivity.this);
        GridLayoutManager layoutManager=new GridLayoutManager(this,1);
        rc.setLayoutManager(layoutManager);
        rc.setAdapter(adapter);
    }

    private void setView() {
        rc=findViewById(R.id.rc_access);
        toolbar=findViewById(R.id.toolbar_access);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setData() {
        list.add(new RcAccess("一个项目","监控权限"));
        list.add(new RcAccess("一个项目","发布权限"));
        list.add(new RcAccess("一个项目","监控权限"));
    }
}