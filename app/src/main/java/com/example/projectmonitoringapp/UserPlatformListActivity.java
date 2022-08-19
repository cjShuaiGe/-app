package com.example.projectmonitoringapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.projectmonitoringapp.adapter.UserPlatformAdapter;
import com.example.projectmonitoringapp.model.Platform;
import com.tencent.mmkv.MMKV;

import java.util.ArrayList;
import java.util.List;

public class UserPlatformListActivity extends AppCompatActivity {

    ImageView iv_back;
    RecyclerView recyclerView;
    List<Platform> list = new ArrayList<>();
    UserPlatformAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_platform_list);
        MMKV.initialize(this);
        MMKV mmkv = MMKV.mmkvWithID("platform");

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserPlatformListActivity.this,UserMainActivity.class));
                finish();
            }
        });

        int i = 1;
        for ( ; mmkv.decodeString("platform" + i) != null ; i++ ) {
            list.add(new Platform(mmkv.decodeString("platform" + i)));
        }
        recyclerView = findViewById(R.id.platform_list);
        adapter = new UserPlatformAdapter(list,UserPlatformListActivity.this);
        GridLayoutManager layoutManager = new GridLayoutManager(UserPlatformListActivity.this,1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}