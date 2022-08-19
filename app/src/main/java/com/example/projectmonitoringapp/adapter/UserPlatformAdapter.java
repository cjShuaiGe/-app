package com.example.projectmonitoringapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmonitoringapp.R;
import com.example.projectmonitoringapp.WebViewActivity;
import com.example.projectmonitoringapp.model.Platform;

import java.util.List;

public class UserPlatformAdapter extends RecyclerView.Adapter<UserPlatformAdapter.ViewHolder> {

    private List<Platform> mlist;
    Activity mactivity;

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView address;
        LinearLayout platform_into;
        public ViewHolder (View view) {
            super(view);
            name = view.findViewById(R.id.platform_id);
            address = view.findViewById(R.id.platform_address);
            platform_into = view.findViewById(R.id.platform_into);
        }

    }

    public UserPlatformAdapter(List<Platform> list, Activity activity) {
        mlist = list;
        mactivity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_platform,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Platform platform = mlist.get(position);
        holder.name.setText("平台" + position);
        holder.address.setText(platform.getAddress());
        holder.platform_into.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mactivity, WebViewActivity.class);
                intent.putExtra("address",holder.address.getText().toString());
                mactivity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
}
