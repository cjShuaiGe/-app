package com.example.projectmonitoringapp.adapter;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmonitoringapp.ProjectMonitoringActivity;
import com.example.projectmonitoringapp.R;
import com.example.projectmonitoringapp.model.Project;
import java.security.PublicKey;
import java.util.List;

public class RcMonitorAdapter extends RecyclerView.Adapter<RcMonitorAdapter.ViewHolder>{
     private List<Project> mlist;
     Activity mactivity;
     static class ViewHolder extends RecyclerView.ViewHolder{
            TextView name;
             TextView introduction;
             Button bt_monitor;
             Button bt_freeze;
             TextView status;
             public ViewHolder (View view){
                  super(view);
                  name=view.findViewById(R.id.tv_monitor_name);
                  introduction=view.findViewById(R.id.tv_introduction);
                  bt_freeze=view.findViewById(R.id.bt_freeze);
                  bt_monitor=view.findViewById(R.id.bt_monitor);
                  status=view.findViewById(R.id.project_monitor_status);
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
               holder.status.setBackgroundResource(R.drawable.background_freeze);
               holder.status.setText("冻结");
               holder.status.setTextColor(Color.parseColor("#FF5959"));
          } else if (monitor.getStatus().equals("0")){
               holder.status.setBackgroundResource(R.drawable.background_unaudited);
               holder.status.setText("未审核");
               holder.status.setTextColor(Color.parseColor("#666666"));
          } else {
               holder.status.setBackgroundResource(R.drawable.background_run);
               holder.status.setText("running");
               holder.status.setTextColor(Color.parseColor("#10BE80"));
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

               }
          });

     }


     @Override
     public int getItemCount() {
          return mlist.size();
     }
}
