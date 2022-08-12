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
import com.example.projectmonitoringapp.adapter.RcUserAdapter;
import com.example.projectmonitoringapp.model.RcUser;

import java.util.ArrayList;
import java.util.List;

public class UserFragment extends Fragment {
    private RecyclerView rc;
    private RcUserAdapter adapter;
    List<RcUser> list=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_user,container,false);
        rc=view.findViewById(R.id.rc_user);
        setData();
        adapter=new RcUserAdapter(list,getActivity());
        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),1);
        rc.setLayoutManager(layoutManager);
        rc.setAdapter(adapter);
        return view;
    }

    private void setData() {
        list.add(new RcUser("一个用户","2022-8-9"));
        list.add(new RcUser("一个用户","2022-8-9"));
        list.add(new RcUser("一个用户","2022-8-9"));
    }
}