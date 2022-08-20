package com.example.projectmonitoringapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmonitoringapp.R;
import com.example.projectmonitoringapp.adapter.RcLogAdapter;
import com.example.projectmonitoringapp.model.RcLog;

import java.util.ArrayList;
import java.util.List;

public class LogApiFragment extends Fragment {
    RecyclerView rc;
    RcLogAdapter adapter;
    List<RcLog> list=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_log_api,container,false);
        rc=view.findViewById(R.id.rc_log_api);
        adapter=new RcLogAdapter(list,getActivity(),1);
        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),1);
        rc.setLayoutManager(layoutManager);
        rc.setAdapter(adapter);

        setData();
        return view;
    }

    private void setData() {
        list.add(new RcLog("XXX","48998","24568","0","100"));
        adapter.notifyDataSetChanged();
    }
}
