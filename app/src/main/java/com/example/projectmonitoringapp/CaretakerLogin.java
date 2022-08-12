package com.example.projectmonitoringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CaretakerLogin extends AppCompatActivity {
   private Button bt;
   private EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caretaker_login);
       bt=findViewById(R.id.bt_login);
       et=findViewById(R.id.et_kl);
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
           @Override
           public void onClick(View v) {
//               if (et.getText().toString().equals("")){
                   startActivity(new Intent(CaretakerLogin.this,CaretakerMainActivity.class));
                   finish();
//               }
           }
       });

    }
}