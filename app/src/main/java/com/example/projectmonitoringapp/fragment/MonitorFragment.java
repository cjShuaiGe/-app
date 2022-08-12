package com.example.projectmonitoringapp.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectmonitoringapp.R;
import com.example.projectmonitoringapp.adapter.RcMonitorAdapter;
import com.example.projectmonitoringapp.model.RcMonitor;

import java.util.ArrayList;
import java.util.List;

public class MonitorFragment extends Fragment {
    private RecyclerView recyclerView;
    List<RcMonitor> list=new ArrayList<>();
    private RcMonitorAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.monitor_fragment,container,false);
        recyclerView=view.findViewById(R.id.rc_monitor);
        setData();
        adapter=new RcMonitorAdapter(list);
        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void setData() {
        list.add(new RcMonitor("这是一个项目简介","2","1","284","100","100"));
        list.add(new RcMonitor("这是一个项目简介","2","1","284","100","100"));
    }
}