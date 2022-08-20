package com.example.projectmonitoringapp.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectmonitoringapp.R;
import com.example.projectmonitoringapp.adapter.ProjectFragmentAdapter;
import com.example.projectmonitoringapp.liveData.MyLiveData;
import com.example.projectmonitoringapp.util.HttpUtil;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ProjectFragment extends Fragment {

    ViewPager2 viewPager2;
    TabLayout t1;
    Button bt_home;
    EditText et_search;
    Button bt_search;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view=inflater.inflate(R.layout.fragment_project,container,false);
            viewPager2=view.findViewById(R.id.vp2);
            t1=view.findViewById(R.id.tabs);
            bt_home=view.findViewById(R.id.bt_home);
            et_search=view.findViewById(R.id.et_search_project);
            bt_search=view.findViewById(R.id.project_search);
            setView();
            setSearch();
            return view;

    }

    private void setSearch() {
      et_search.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {

          }

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {
              MyLiveData.getMessageData().setValue(et_search.getText().toString());
          }

          @Override
          public void afterTextChanged(Editable s) {

          }
      });
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void setView() {

        viewPager2.setAdapter(new ProjectFragmentAdapter(getActivity()));
        TabLayoutMediator tab = new TabLayoutMediator(t1, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("项目监控");
                        break;

                    case 1:
                        tab.setText("发布审核");
                        break;
                }
            }
        });
        tab.attach();
    }



}