package com.example.projectmonitoringapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.projectmonitoringapp.adapter.RcLogPackageAdapter;
import com.example.projectmonitoringapp.model.LogPack;
import com.example.projectmonitoringapp.model.RcLog;
import com.example.projectmonitoringapp.model.RcPackage;
import com.example.projectmonitoringapp.model.Receive;
import com.example.projectmonitoringapp.util.HttpTool;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LogDetailActivity extends AppCompatActivity {
Toolbar toolbar;
Intent intent;
RecyclerView rc;
RcLogPackageAdapter adapter;
List<RcPackage> list=new ArrayList<>();
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_detail);
        initView();
        setData();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setData() {
        intent=getIntent();
        String packName=intent.getStringExtra("packName");
        String text=new Gson().toJson(new LogPack(packName));
        String key=Crypt2.getRandomString(16);
        HttpTool.postPack(key, text, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LogDetailActivity.this,"连接失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Receive receive=new Gson().fromJson(response.body().string(),Receive.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (receive!=null){
                            if (receive.getCode()==200){
                                String data= Crypt2.decryptECB(receive.getData(),key);
                                JSONArray jsonArray= null;
                                try {
                                    jsonArray = new JSONArray(data);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                list.clear();
                                for (int i=0;i<jsonArray.length();i++){
                                    String s= null;
                                    try {
                                        s = jsonArray.get(i).toString();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    list.add(new Gson().fromJson(s, RcPackage.class));
                                }
                                adapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(LogDetailActivity.this,"读取失败",Toast.LENGTH_SHORT).show();}
                        }
                    }
                });

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initView() {
        rc=findViewById(R.id.rc_log_pack);
        toolbar=findViewById(R.id.toolbar_log_detail);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        adapter=new RcLogPackageAdapter(list);
        GridLayoutManager layoutManager=new GridLayoutManager(LogDetailActivity.this,1);
        rc.setLayoutManager(layoutManager);
        rc.setAdapter(adapter);
        setData();
    }




}