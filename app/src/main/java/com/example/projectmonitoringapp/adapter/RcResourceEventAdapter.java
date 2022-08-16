package com.example.projectmonitoringapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmonitoringapp.R;
import com.example.projectmonitoringapp.model.RcEvent;

import java.util.List;

public class RcResourceEventAdapter extends RecyclerView.Adapter<RcResourceEventAdapter.ViewHolder>{
    private List<RcEvent> mlist;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        TextView tv_s1;
        TextView tv_s2;
        public ViewHolder (View view){
            super(view);
            tv_name=view.findViewById(R.id.resource_event_name);
            tv_s1=view.findViewById(R.id.resource_event_s1);
            tv_s2=view.findViewById(R.id.resource_event_s2);
        }
    }

    public RcResourceEventAdapter(List<RcEvent> list){
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
        RcEvent event=mlist.get(position);
        holder.tv_name.setText(event.getName());
        holder.tv_s1.setText(event.getS1());
        holder.tv_s2.setText(event.getS2());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
}
