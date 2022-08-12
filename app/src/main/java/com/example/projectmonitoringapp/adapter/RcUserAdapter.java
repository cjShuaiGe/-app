package com.example.projectmonitoringapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmonitoringapp.AccessActivity;
import com.example.projectmonitoringapp.R;
import com.example.projectmonitoringapp.model.RcUser;

import java.util.List;

public class RcUserAdapter extends RecyclerView.Adapter<RcUserAdapter.ViewHolder>{
    private List<RcUser> mlist;
    Activity mactivity;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_user_name;
        TextView tv_register_time;
        Button bt_access;
        ImageView img_control;
        public ViewHolder(View view){
            super(view);
            tv_register_time=view.findViewById(R.id.register_time);
            tv_user_name=view.findViewById(R.id.user_name);
            bt_access=view.findViewById(R.id.bt_access);
            img_control=view.findViewById(R.id.user_control);

        }
    }

    public RcUserAdapter(List<RcUser> list,Activity activity){
        mlist=list;
        mactivity=activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RcUser user=mlist.get(position);
        holder.tv_user_name.setText(user.getUserName());
        holder.tv_register_time.setText(user.getRegisterTime());
        holder.bt_access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              mactivity.startActivity(new Intent(mactivity, AccessActivity.class));
            }
        });

        holder.img_control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              setUserControl(v);
            }
        });
    }

    private void setUserControl(View v) {
        View view=mactivity.getLayoutInflater().inflate(R.layout.user_control_menu,null);
        Button bt_freeze=view.findViewById(R.id.bt_freeze_user);
        Button bt_down=view.findViewById(R.id.bt_down_user);
        bt_freeze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bt_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        PopupWindow window=new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setOutsideTouchable(true);
        window.showAsDropDown(v,-250,0);

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
}
