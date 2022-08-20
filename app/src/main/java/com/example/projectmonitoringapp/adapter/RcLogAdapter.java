package com.example.projectmonitoringapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmonitoringapp.LogDetailActivity;
import com.example.projectmonitoringapp.R;
import com.example.projectmonitoringapp.model.RcLog;

import java.util.List;

public class RcLogAdapter extends RecyclerView.Adapter<RcLogAdapter.ViewHolder>{
    private List<RcLog> mlist;
    Activity mactivity;
    int judgment;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView visitNumber;
        TextView visitPeople;
        TextView rate;
        TextView error;
        TextView detail;
        public ViewHolder (View view){
            super(view);
            name=view.findViewById(R.id.log_name);
            visitNumber=view.findViewById(R.id.visit_number);
            visitPeople=view.findViewById(R.id.visit_number_people);
            rate=view.findViewById(R.id.log_success_rate);
            error=view.findViewById(R.id.log_error);
            detail=view.findViewById(R.id.log_details);
        }
    }

    public RcLogAdapter(List<RcLog> list,Activity activity,int judgment){
        mlist=list;mactivity=activity;
        this.judgment=judgment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rc_log,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RcLog log=mlist.get(position);
        holder.name.setText(log.getPackageName());
        holder.visitNumber.setText(log.getVisits());
        holder.visitPeople.setText(log.getVisits_people());
        holder.error.setText(log.getDefeatCount());
        float f= Float.parseFloat(log.getRate())*100f;
        holder.rate.setText(f+"%");
        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mactivity, LogDetailActivity.class);
                intent.putExtra("packName",log.getPackageName());
             mactivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
}
