package com.example.projectmonitoringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.tencent.mmkv.MMKV;

public class UserPlatformAddActivity extends AppCompatActivity {

    ImageView iv_back;
    EditText et_add;
    Button bt_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_platform_add);
        MMKV.initialize(this);

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserPlatformAddActivity.this,UserMainActivity.class));
                finish();
            }
        });

        et_add = (EditText) findViewById(R.id.et_add);
        bt_add = (Button) findViewById(R.id.bt_add);
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(et_add.getText().toString())) {
                    Toast.makeText(UserPlatformAddActivity.this,"请输入平台地址",Toast.LENGTH_SHORT).show();
                } else {
                    MMKV mmkv = MMKV.mmkvWithID("platform");
                    int i = 1;
                    for ( ; mmkv.decodeString("platform" + i) != null ; i++ ) {
                    }
                    mmkv.encode("platform" + i,et_add.getText().toString());
                    et_add.setText("");
                    Toast.makeText(UserPlatformAddActivity.this,"平台添加成功",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}