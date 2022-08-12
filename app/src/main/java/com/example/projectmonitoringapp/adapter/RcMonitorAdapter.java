package com.example.projectmonitoringapp.adapter;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmonitoringapp.R;
import com.example.projectmonitoringapp.model.RcMonitor;

import java.security.PublicKey;
import java.util.List;

public class RcMonitorAdapter extends RecyclerView.Adapter<RcMonitorAdapter.ViewHolder>{
     private List<RcMonitor> mlist;
     static class ViewHolder extends RecyclerView.ViewHolder{
             TextView introduction;
             TextView pv;
             TextView uv;
             TextView render;
             TextView js;
             TextView api;
             Button bt_monitor;
             Button bt_freeze;
             public ViewHolder (View view){
                  super(view);
                  introduction=view.findViewById(R.id.tv_introduction);
                  pv=view.findViewById(R.id.tv_pv);
                  uv=view.findViewById(R.id.tv_uv);
                  render=view.findViewById(R.id.tv_render_time);
                  js=view.findViewById(R.id.tv_js);
                  api=view.findViewById(R.id.tv_api);
                  bt_freeze=view.findViewById(R.id.bt_freeze);
                  bt_monitor=view.findViewById(R.id.bt_monitor);
             }
     }

     public RcMonitorAdapter(List<RcMonitor> list){
          mlist=list;
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
          RcMonitor monitor=mlist.get(position);
          holder.introduction.setText(monitor.getIntroduction());
          holder.pv.setText("PV:"+monitor.getPv());
          holder.uv.setText("UV:"+monitor.getUv());
          holder.render.setText(monitor.getRender()+"ms");
          holder.js.setText(monitor.getJs()+"%");
          holder.api.setText(monitor.getApi()+"%");
          holder.bt_monitor.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

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
