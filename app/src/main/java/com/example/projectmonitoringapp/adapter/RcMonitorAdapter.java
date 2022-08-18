package com.example.projectmonitoringapp.adapter;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.projectmonitoringapp.Crypt2;
import com.example.projectmonitoringapp.ProjectMonitoringActivity;
import com.example.projectmonitoringapp.R;
import com.example.projectmonitoringapp.model.Freeze;
import com.example.projectmonitoringapp.model.Project;
import com.example.projectmonitoringapp.model.ProjectFreeze;
import com.example.projectmonitoringapp.model.RcUser;
import com.example.projectmonitoringapp.model.Receive;
import com.example.projectmonitoringapp.util.HttpTool;
import com.google.gson.Gson;

import java.io.IOException;
import java.security.PublicKey;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RcMonitorAdapter extends RecyclerView.Adapter<RcMonitorAdapter.ViewHolder>{
     private List<Project> mlist;
     Activity mactivity;
     static class ViewHolder extends RecyclerView.ViewHolder{
            TextView name;
             TextView introduction;
             Button bt_monitor;
             Button bt_freeze;
             TextView status;
             TextView date;
             public ViewHolder (View view){
                  super(view);
                  name=view.findViewById(R.id.tv_monitor_name);
                  introduction=view.findViewById(R.id.tv_introduction);
                  bt_freeze=view.findViewById(R.id.bt_freeze);
                  bt_monitor=view.findViewById(R.id.bt_monitor);
                  status=view.findViewById(R.id.project_monitor_status);
                  date=view.findViewById(R.id.tv_monitor_freeze_time);
             }
     }

     public RcMonitorAdapter(List<Project> list,Activity activity){
          mlist=list;
          mactivity=activity;
     }

     @NonNull
     @Override
     public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rc_monitor,parent,false);
          ViewHolder holder=new ViewHolder(view);
          return holder;
     }

     @Override
     public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
          Project monitor=mlist.get(position);
          holder.name.setText(monitor.getProjectName());
          holder.introduction.setText(monitor.getProjectDesc());

          if (monitor.getStatus().equals("-1")){
               holder.date.setVisibility(View.VISIBLE);
               holder.date.setText("冻结至："+monitor.getUnsealDate().substring(0,monitor.getUnsealDate().indexOf("T")));
          } else {holder.date.setText("");}

          if (monitor.getStatus().equals("-1")){
               holder.status.setBackgroundResource(R.drawable.background_freeze);
               holder.status.setText("冻结");
               holder.status.setTextColor(Color.parseColor("#FF5959"));
          } else if (monitor.getStatus().equals("0")){
               holder.status.setBackgroundResource(R.drawable.background_unaudited);
               holder.status.setText("未审核");
               holder.status.setTextColor(Color.parseColor("#666666"));
          } else if (monitor.getStatus().equals("1")){
               holder.status.setBackgroundResource(R.drawable.background_run);
               holder.status.setText("同意");
               holder.status.setTextColor(Color.parseColor("#10BE80"));
          }else {
               holder.status.setBackgroundResource(R.drawable.background_freeze);
               holder.status.setText("拒绝");
               holder.status.setTextColor(Color.parseColor("#FF5959"));
          }
          holder.bt_monitor.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                 mactivity.startActivity(new Intent(mactivity, ProjectMonitoringActivity.class));
               }
          });
          holder.bt_freeze.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                  setTime(monitor);
               }
          });

     }




     private void setTime(Project monitor) {
          TimePickerView pvTime = new TimePickerBuilder(mactivity, new OnTimeSelectListener() {
               @RequiresApi(api = Build.VERSION_CODES.O)
               @SuppressLint("LongLogTag")
               @Override
               public void onTimeSelect(Date date, View v) {
                    String str= String.valueOf(date.getTime());
                    String key= Crypt2.getRandomString(16);
                    String text=new Gson().toJson(new ProjectFreeze(monitor.getProjectId(),"-1",str));
                    sendFreeze(key,text);
               }
          })
                  .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                       @Override
                       public void onTimeSelectChanged(Date date) {

                       }
                  })
                  .setType(new boolean[]{true, true, true, false,false, false})
                  .setItemVisibleCount(5)
                  .setLineSpacingMultiplier(2.0f)
                  .isAlphaGradient(true)

                  .build();
          pvTime.show();

     }

     @RequiresApi(api = Build.VERSION_CODES.O)
     private void sendFreeze(String key, String text) {
          HttpTool.postProjectApplication(key, text, new Callback() {
               @Override
               public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    mactivity.runOnUiThread(new Runnable() {
                         @Override
                         public void run() {
                              Toast.makeText(mactivity,"连接失败",Toast.LENGTH_SHORT).show();
                         }
                    });
               }

               @Override
               public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    Receive receive=new Gson().fromJson(response.body().string(),Receive.class);
//                System.out.println(response.body().string());
                    mactivity.runOnUiThread(new Runnable() {
                         @Override
                         public void run() {
                              if (receive.getCode()==200) {
                                   Toast.makeText(mactivity, "冻结成功", Toast.LENGTH_SHORT).show();
                              } else {
                                   Toast.makeText(mactivity, receive.getMsg(), Toast.LENGTH_SHORT).show();
                              }
                         }
                    });
               }
          });
     }


     @Override
     public int getItemCount() {
          return mlist.size();
     }
}
