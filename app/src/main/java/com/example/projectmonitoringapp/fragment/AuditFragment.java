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
import com.example.projectmonitoringapp.adapter.RcAuditAdapter;
import com.example.projectmonitoringapp.model.RcAudit;

import java.util.ArrayList;
import java.util.List;

public class AuditFragment extends Fragment {
   private RecyclerView rc;
   private RcAuditAdapter adapter;
   List<RcAudit> list=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.audit_fragment,container,false);
        rc=view.findViewById(R.id.rc_audit);
        setData();
        adapter=new RcAuditAdapter(list,getActivity());
        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),1);
        rc.setLayoutManager(layoutManager);
        rc.setAdapter(adapter);
        return view;
    }

    private void setData() {
        list.add(new RcAudit("一个项目","一位用户","2022-8-9","xxxxxxxxxxxxxxxxxxxx","这是一个项目简介这是一个项目简介"));
        list.add(new RcAudit("一个项目","一位用户","2022-8-9","xxxxxxxxxxxxxxxxxxxx","这是一个项目简介这是一个项目简介"));
        list.add(new RcAudit("一个项目","一位用户","2022-8-9","xxxxxxxxxxxxxxxxxxxx","这是一个项目简介这是一个项目简介"));
    }
}