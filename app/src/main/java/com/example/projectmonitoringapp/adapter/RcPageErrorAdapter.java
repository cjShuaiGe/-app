package com.example.projectmonitoringapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmonitoringapp.R;
import com.example.projectmonitoringapp.model.RcPageError;

import java.util.List;

public class RcPageErrorAdapter extends RecyclerView.Adapter<RcPageErrorAdapter.ViewHolder>{
    List<RcPageError> mlist;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView page;
        TextView error;
        public ViewHolder (View view){
            super(view);
            page=view.findViewById(R.id.tv_page);
            error=view.findViewById(R.id.tv_page_error);
        }
    }

    public RcPageErrorAdapter(List<RcPageError> list){
        mlist=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rc_page_error,parent,false);
       ViewHolder holder=new ViewHolder(view);
       return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            RcPageError pageError=mlist.get(position);
            holder.error.setText(pageError.getCount());
            holder.page.setText(pageError.getUrl());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
}
