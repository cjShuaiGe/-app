package com.example.projectmonitoringapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmonitoringapp.R;
import com.example.projectmonitoringapp.model.RcEvent;
import com.example.projectmonitoringapp.model.RcEventList;

import java.util.List;

public class RcEventFilenameAdapter extends RecyclerView.Adapter<RcEventFilenameAdapter.ViewHolder>{
    List<RcEventList> mlist;
    Activity mactivity;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        RecyclerView rc;
        public ViewHolder (View view){
            super(view);
            tv_name=view.findViewById(R.id.resource_event_name);
            rc=view.findViewById(R.id.rc_resource_event_filename);
        }
    }

    public RcEventFilenameAdapter (List<RcEventList> list,Activity activity){
        mactivity=activity;
        mlist=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rc_event,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RcEventList eventList=mlist.get(position);
        List<RcEvent> rcEventList=eventList.getList();
        holder.tv_name.setText(rcEventList.get(0).getS1());
        RcResourceEventAdapter adapter=new RcResourceEventAdapter(rcEventList);
        GridLayoutManager layoutManager=new GridLayoutManager(mactivity,1);
        holder.rc.setLayoutManager(layoutManager);
        holder.rc.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
}
