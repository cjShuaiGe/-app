package com.example.projectmonitoringapp.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmonitoringapp.R;
import com.example.projectmonitoringapp.model.RcAudit;

import java.util.List;

public class RcAuditAdapter extends RecyclerView.Adapter<RcAuditAdapter.ViewHolder>{
    List<RcAudit> mlist;
    Activity mactivity;
    Dialog dialog;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView projectName;
        TextView applicationName;
        TextView applicationTime;
        Button bt_agree;
        Button bt_no_agree;
        View mview;
        public ViewHolder (View view){
            super(view);
            projectName=view.findViewById(R.id.tv_project_name);
            applicationName=view.findViewById(R.id.tv_application_name);
            applicationTime=view.findViewById(R.id.tv_application_time);
            bt_agree=view.findViewById(R.id.bt_agree);
            bt_no_agree=view.findViewById(R.id.bt_no_agree);
            mview=view;
        }
    }

    public RcAuditAdapter (List<RcAudit> list,Activity activity){
        mactivity=activity;
        mlist=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rc_audit,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RcAudit audit=mlist.get(position);

        holder.projectName.setText(audit.getProjectName());
        holder.applicationName.setText(audit.getApplicationName());
        holder.applicationTime.setText(audit.getApplicationTime());
        holder.bt_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.bt_no_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog(audit);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

        private void setDialog(RcAudit audit) {
        View view = View.inflate(mactivity, R.layout.dialog_project, null);
        TextView dialog_name=view.findViewById(R.id.tv_dialog_project_name);
        TextView dialog_address=view.findViewById(R.id.address_project);
        TextView dialog_introduction=view.findViewById(R.id.dialog_introduction);
        dialog_name.setText(audit.getProjectName());
        dialog_address.setText(audit.getAddress());
        dialog_introduction.setText(audit.getIntroduction());
         dialog = new AlertDialog.Builder(mactivity)
                .setView(view)
                .setPositiveButton("确定", (dialog, which) -> {
                    dialog.dismiss();
                })
                .create();
        dialog.show();
    }
}
