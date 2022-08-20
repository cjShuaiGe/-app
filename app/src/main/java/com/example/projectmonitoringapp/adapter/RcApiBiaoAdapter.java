package com.example.projectmonitoringapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmonitoringapp.R;
import com.example.projectmonitoringapp.model.ApiPort;

import java.util.List;

public class RcApiBiaoAdapter extends RecyclerView.Adapter<RcApiBiaoAdapter.ViewHolder>{
    List<ApiPort> mlist;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView uri;
        TextView rate;
        TextView time;
        public ViewHolder (View view){
            super(view);
            uri=view.findViewById(R.id.api_port_uri);
            rate=view.findViewById(R.id.api_request_success_rate);
            time=view.findViewById(R.id.api_request_time_consume);
        }
    }

    public RcApiBiaoAdapter (List<ApiPort> list){
        mlist=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_api_port,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ApiPort port=mlist.get(position);
        float f=Float.parseFloat(port.getRate())*100;
        holder.rate.setText(f+"%");
        holder.uri.setText(port.getUri());
        holder.time.setText(port.getAvgResponseTime()+"ms");
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
}
