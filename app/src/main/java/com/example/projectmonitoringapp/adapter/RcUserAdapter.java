package com.example.projectmonitoringapp.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.projectmonitoringapp.AccessActivity;
import com.example.projectmonitoringapp.CaretakerLogin;
import com.example.projectmonitoringapp.CaretakerMainActivity;
import com.example.projectmonitoringapp.Crypt2;
import com.example.projectmonitoringapp.R;
import com.example.projectmonitoringapp.model.ForceDown;
import com.example.projectmonitoringapp.model.Freeze;
import com.example.projectmonitoringapp.model.RcUser;
import com.example.projectmonitoringapp.model.Receive;
import com.example.projectmonitoringapp.util.HttpTool;
import com.example.projectmonitoringapp.util.HttpUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RcUserAdapter extends RecyclerView.Adapter<RcUserAdapter.ViewHolder>{
    private List<RcUser> mlist;
    Activity mactivity;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_user_name;
        TextView tv_register_time;
        Button bt_access;
        ImageView img_control;
        TextView onLive;
        TextView tv_freeze_time;
        public ViewHolder(View view){
            super(view);
            tv_register_time=view.findViewById(R.id.register_time);
            tv_user_name=view.findViewById(R.id.user_name);
            bt_access=view.findViewById(R.id.bt_access);
            img_control=view.findViewById(R.id.user_control);
            onLive=view.findViewById(R.id.user_onlive);
            tv_freeze_time=view.findViewById(R.id.user_freeze_time);
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RcUser user=mlist.get(position);

        if (user.getUsername()!=null){
        holder.tv_user_name.setText(user.getUsername());
        } else {holder.tv_user_name.setText("null");}
        if (user.getRegisterDate()!=null){
        holder.tv_register_time.setText(user.getRegisterDate().substring(0,user.getRegisterDate().indexOf("T")));
        } else {holder.tv_register_time.setText("null");}

        if (user.getPosition().equals("-1")){
            System.out.println(user.getUsername());
            holder.tv_freeze_time.setVisibility(View.VISIBLE);
            holder.tv_freeze_time.setText("冻结到："+user.getUnsealDate().substring(0,user.getUnsealDate().indexOf("T")));
        }else {holder.tv_freeze_time.setText("");

        }

        if (user.getOnLive().equals("0")){
            if (user.getPosition().equals("-1")){
//                System.out.println(user.getUsername());
//                holder.tv_freeze_time.setVisibility(View.VISIBLE);
//                holder.tv_freeze_time.setText("冻结到："+user.getUnsealDate().substring(0,user.getUnsealDate().indexOf("T")));
                holder.onLive.setText("冻结");
                holder.onLive.setBackgroundResource(R.drawable.background_freeze);
                holder.onLive.setTextColor(Color.parseColor("#FF5959"));
            }else {
            holder.onLive.setText("离线");
            holder.onLive.setBackgroundResource(R.drawable.background_unaudited);
            holder.onLive.setTextColor(Color.parseColor("#666666"));
            }
        } else {
            holder.onLive.setText("在线");
            holder.onLive.setBackgroundResource(R.drawable.background_run);
            holder.onLive.setTextColor(Color.parseColor("#10BE80"));
        }


        holder.bt_access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              mactivity.startActivity(new Intent(mactivity, AccessActivity.class));
            }
        });



        holder.img_control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              setUserControl(v,user);
            }
        });
    }



    private void setUserControl(View v,RcUser user) {
        View view=mactivity.getLayoutInflater().inflate(R.layout.user_control_menu,null);
        Button bt_freeze=view.findViewById(R.id.bt_freeze_user);
        Button bt_down=view.findViewById(R.id.bt_down_user);
        bt_freeze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               setTime(user);
            }
        });


        bt_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key=Crypt2.getRandomString(16);
                String text=new Gson().toJson(new ForceDown(user.getUserId()));
                sendDown(key,text);

            }
        });

        PopupWindow window=new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setOutsideTouchable(true);
        window.showAsDropDown(v,-250,0);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendDown(String key, String text) {
        HttpTool.postDown(key, text, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Receive receive=new Gson().fromJson(response.body().string(),Receive.class);
//                System.out.println(response.body().string());
                mactivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (receive.getCode()==200) {
                            Toast.makeText(mactivity, "强制下线成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mactivity, receive.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }


    @Override
    public int getItemCount() {
        return mlist.size();
    }


    private void setTime(RcUser user) {
        TimePickerView pvTime = new TimePickerBuilder(mactivity, new OnTimeSelectListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @SuppressLint("LongLogTag")
            @Override
            public void onTimeSelect(Date date, View v) {
                String str= String.valueOf(date.getTime());
                System.out.println(str);
                String key= Crypt2.getRandomString(16);
                String text=new Gson().toJson(new Freeze(user.getUserId(),str));
                System.out.println(text);
                sendFreeze(key,text);
            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {

                    }
                })
                .setType(new boolean[]{true, true, true, false,false, false})
                .setItemVisibleCount(5)
                .setLineSpacingMultiplier(2.0f)
                .isAlphaGradient(true)

                .build();
        pvTime.show();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendFreeze(String key, String text) {
        HttpTool.postFreeze(key, text, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                mactivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                     Toast.makeText(mactivity,"冻结失败",Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(mactivity, "冻结成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mactivity, receive.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private String getTime(Date date) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);

    }
}
