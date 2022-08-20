package com.example.projectmonitoringapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmonitoringapp.R;
import com.example.projectmonitoringapp.model.RcPackage;

import java.util.List;

public class RcLogPackageAdapter extends RecyclerView.Adapter<RcLogPackageAdapter.ViewHolder>{
    List<RcPackage> mlist;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_url;
        TextView tv_visits;
        TextView tv_people;
        TextView tv_error;
        TextView tv_rate;
        public ViewHolder (View view){
            super(view);
            tv_error=view.findViewById(R.id.pack_error);
            tv_people=view.findViewById(R.id.pack_visit_people);
            tv_rate=view.findViewById(R.id.pack_rate);
            tv_url=view.findViewById(R.id.pack_url);
            tv_visits=view.findViewById(R.id.pack_visits);
        }
    }

    public RcLogPackageAdapter(List<RcPackage> list){
        mlist=list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rc_pack,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RcPackage rcPackage=mlist.get(position);
        holder.tv_visits.setText(rcPackage.getVisits());
        holder.tv_url.setText(rcPackage.getUri());
        float f= Float.parseFloat(rcPackage.getRate())*100f;
        holder.tv_rate.setText(f+"%");
        holder.tv_people.setText(rcPackage.getVisits_people());
        holder.tv_error.setText(rcPackage.getDefeatCount());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
}
