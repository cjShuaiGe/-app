package com.example.projectmonitoringapp.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmonitoringapp.Crypt2;
import com.example.projectmonitoringapp.R;
import com.example.projectmonitoringapp.model.Project;
import com.example.projectmonitoringapp.model.ProjectApplication;
import com.example.projectmonitoringapp.model.ProjectFreeze;
import com.example.projectmonitoringapp.model.RcAudit;
import com.example.projectmonitoringapp.model.Receive;
import com.example.projectmonitoringapp.util.HttpTool;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RcAuditAdapter extends RecyclerView.Adapter<RcAuditAdapter.ViewHolder>{
    List<Project> mlist;
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

    public RcAuditAdapter (List<Project> list, Activity activity){
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
        Project audit=mlist.get(position);

        holder.projectName.setText(audit.getProjectName());
        holder.applicationName.setText(audit.getProjectName());
        holder.applicationTime.setText(audit.getRegisterDate().substring(0,audit.getRegisterDate().indexOf("T")));
        holder.bt_agree.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String key= Crypt2.getRandomString(16);
                String text=new Gson().toJson(new ProjectApplication(audit.getProjectId(),"1"));
                sendPostStatus(key,text);
            }
        });
        holder.bt_no_agree.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String key= Crypt2.getRandomString(16);
                String text=new Gson().toJson(new ProjectApplication(audit.getProjectId(),"2"));
                sendPostStatus(key,text);
            }
        });
        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog(audit);
            }
        });
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendPostStatus(String key, String text) {
        HttpTool.postProjectApplication(key, text, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                mactivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mactivity,"连接失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Receive receive=new Gson().fromJson(response.body().string(),Receive.class);
//                System.out.println(response.body().string());
                mactivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (receive.getCode()==200) {
                            Toast.makeText(mactivity, "审核成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mactivity, receive.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }





    private void setDialog(Project audit) {
        View view = View.inflate(mactivity, R.layout.dialog_project, null);
        TextView dialog_name=view.findViewById(R.id.tv_dialog_project_name);
        TextView dialog_address=view.findViewById(R.id.address_project);
        TextView dialog_introduction=view.findViewById(R.id.dialog_introduction);
        dialog_name.setText(audit.getProjectName());
        dialog_address.setText(audit.getProjectUrl());
        dialog_introduction.setText(audit.getProjectDesc());
         dialog = new AlertDialog.Builder(mactivity)
                .setView(view)
                .setPositiveButton("确定", (dialog, which) -> {
                    dialog.dismiss();
                })
                .create();
        dialog.show();
    }



    @Override
    public int getItemCount() {
        return mlist.size();
    }
}
