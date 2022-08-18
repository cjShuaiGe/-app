package com.example.projectmonitoringapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmonitoringapp.R;
import com.example.projectmonitoringapp.model.Project;

import java.util.List;

public class UserProjectAdapter extends RecyclerView.Adapter<UserProjectAdapter.ViewHolder> {

    private List<Project> mlist;
    Activity mactivity;

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView introduction;
        Button bt_monitor;
        public ViewHolder (View view) {
            super(view);
            name = view.findViewById(R.id.user_project_name);
            introduction = view.findViewById(R.id.tv_introduction);
            bt_monitor = view.findViewById(R.id.bt_monitor);
        }

    }

    public UserProjectAdapter(List<Project> list, Activity activity) {
        mlist = list;
        mactivity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_project,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Project userProject = mlist.get(position);
        holder.name.setText(userProject.getProjectName());
        holder.introduction.setText(userProject.getProjectDesc());
        holder.bt_monitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mactivity,"申请成功，请等待审核",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

}
