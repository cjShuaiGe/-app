package com.example.projectmonitoringapp.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmonitoringapp.CaretakerLogin;
import com.example.projectmonitoringapp.Crypt2;
import com.example.projectmonitoringapp.R;
import com.example.projectmonitoringapp.adapter.RcLogAdapter;
import com.example.projectmonitoringapp.model.RcLog;
import com.example.projectmonitoringapp.model.RcUser;
import com.example.projectmonitoringapp.model.Receive;
import com.example.projectmonitoringapp.util.HttpTool;
import com.example.projectmonitoringapp.util.HttpUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LogGradleFragment extends Fragment {
    RecyclerView rc;
    RcLogAdapter adapter;
    List<RcLog> list=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_log_gradle,container,false);
        rc=view.findViewById(R.id.rc_log_gradle);
        adapter=new RcLogAdapter(list,getActivity(),0);
        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),1);
        rc.setLayoutManager(layoutManager);
        rc.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setData();
    }

    private void setData() {
        HttpTool.getLog(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),"连接失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Receive receive=new Gson().fromJson(response.body().string(),Receive.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (receive!=null){
                            if (receive.getCode()==200){
                                String data= Crypt2.decryptGet(receive.getData());
                                JSONArray jsonArray= null;
                                try {
                                    jsonArray = new JSONArray(data);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                list.clear();
                                for (int i=0;i<jsonArray.length();i++){
                                    String s= null;
                                    try {
                                        s = jsonArray.get(i).toString();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    list.add(new Gson().fromJson(s, RcLog.class));
                                }
                                adapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(getActivity(),"读取失败",Toast.LENGTH_SHORT).show();}
                        }
                    }
                });
            }
        });
    }
}
