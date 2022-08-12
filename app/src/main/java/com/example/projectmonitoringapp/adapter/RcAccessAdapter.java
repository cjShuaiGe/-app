package com.example.projectmonitoringapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmonitoringapp.AccessActivity;
import com.example.projectmonitoringapp.R;
import com.example.projectmonitoringapp.model.RcAccess;

import java.util.List;

public class RcAccessAdapter extends RecyclerView.Adapter<RcAccessAdapter.ViewHolder>{
    private List<RcAccess> mlist;
    Activity mactivity;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView projectName;
        TextView access;
        public ViewHolder (View view){
            super(view);
            projectName=view.findViewById(R.id.user_project_name);
            access=view.findViewById(R.id.user_access);
        }
    }

    public RcAccessAdapter(List<RcAccess> list,Activity activity){
        mlist=list;mactivity=activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rc_access,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RcAccess access=mlist.get(position);
        holder.projectName.setText(access.getProjectName());
        holder.access.setText(access.getAccess());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
}
