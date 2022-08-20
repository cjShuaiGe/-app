package com.example.projectmonitoringapp.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.projectmonitoringapp.Crypt2;
import com.example.projectmonitoringapp.R;
import com.example.projectmonitoringapp.adapter.RcMonitorAdapter;
import com.example.projectmonitoringapp.liveData.MyLiveData;
import com.example.projectmonitoringapp.model.Project;

import com.example.projectmonitoringapp.model.Receive;
import com.example.projectmonitoringapp.util.HttpTool;
import com.example.projectmonitoringapp.util.HttpUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleBiFunction;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MonitorFragment extends Fragment {
    private RecyclerView recyclerView;
    List<Project> list=new ArrayList<>();
    private RcMonitorAdapter adapter;
    List<Project> plist=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_monitor,container,false);
        recyclerView=view.findViewById(R.id.rc_monitor);
        //TODO

        adapter=new RcMonitorAdapter(list,getActivity());
        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        setSearch();
        return view;
    }

    private void setSearch() {
        MyLiveData.getMessageData().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                list.clear();
                list.addAll(plist);
                List<Project> mlist = new ArrayList<>(list);
                if (!s.equals("")){
                list.clear();
                for (int i=0;i<mlist.size();i++){
                    for (int j=0;j<mlist.get(i).getProjectName().length();j++){

                    if (mlist.get(i).getProjectName().substring(0,j+1).equals(s)){
                        list.add(mlist.get(i));

                    }
                    }
                }
            }adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setData();
    }

    private void setData() {
       HttpTool.getProject(new Callback() {
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
                Receive   receive = new Gson().fromJson(response.body().string(), Receive.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (receive!=null){
                            if (receive.getCode()==200){
//                                HttpUtil.setToken(response.header("Authorization"));
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
                                    list.add(new Gson().fromJson(s,Project.class));
                                }
                                plist.clear();
                                plist.addAll(list);
                                adapter.notifyDataSetChanged();
//                            } else {Toast.makeText(getActivity(),receive.getMsg(),Toast.LENGTH_SHORT).show();
                            } else {Toast.makeText(getActivity(),"receive.getMsg()",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

            }
        });

    }
}