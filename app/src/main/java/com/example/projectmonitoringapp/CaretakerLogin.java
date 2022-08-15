package com.example.projectmonitoringapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectmonitoringapp.model.LoginReceive;
import com.example.projectmonitoringapp.model.UserLogin;
import com.example.projectmonitoringapp.util.HttpTool;
import com.example.projectmonitoringapp.util.HttpUtil;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CaretakerLogin extends AppCompatActivity {
   private Button bt;
   private EditText et;
   private EditText et_mm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caretaker_login);
       bt=findViewById(R.id.bt_login);
       et_mm=findViewById(R.id.et_mm);
       et=findViewById(R.id.et_zh);
       et.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
               if(s.toString().length()<1){
                   bt.setEnabled(false);
               }else {
                   bt.setEnabled(true);
                   bt.setBackgroundResource(R.drawable.shape_click);
               }
           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });
       bt.setOnClickListener(new View.OnClickListener() {
           @RequiresApi(api = Build.VERSION_CODES.O)
           @Override
           public void onClick(View v) {
                    String s=et.getText().toString();
                    String mm=et_mm.getText().toString();
                    String key=Crypt2.getRandomString(16);
               String str=new Gson().toJson(new UserLogin(s,mm));
              HttpTool.caretakerLogin(key,str, new Callback() {
                   @Override
                   public void onFailure(@NonNull Call call, @NonNull IOException e) {
                       runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               Toast.makeText(CaretakerLogin.this,"连接失败",Toast.LENGTH_SHORT).show();
                           }
                       });
                   }

                   @Override
                   public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                       LoginReceive receive=new Gson().fromJson(response.body().string(),LoginReceive.class);
                       runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               if (receive.getCode()==200) {
                                   Toast.makeText(CaretakerLogin.this, "登录成功", Toast.LENGTH_SHORT).show();
                                   HttpUtil.setToken(response.header("Authorization"));
                                   startActivity(new Intent(CaretakerLogin.this, CaretakerMainActivity.class));
                               } else {
                                   Toast.makeText(CaretakerLogin.this, receive.getMsg(), Toast.LENGTH_SHORT).show();
                               }
                           }
                       });



                   }
               });
           }
       });

    }
}