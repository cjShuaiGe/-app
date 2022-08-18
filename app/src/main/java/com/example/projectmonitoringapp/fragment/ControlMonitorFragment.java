package com.example.projectmonitoringapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.projectmonitoringapp.R;
import com.example.projectmonitoringapp.adapter.ProjectPowerControlAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ControlMonitorFragment extends Fragment {

    ViewPager2 viewPager2;
    TabLayout t1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_control_monitor,container,false);

        viewPager2 = view.findViewById(R.id.vp2);
        t1 = view.findViewById(R.id.tabs);
        setView();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setView() {

        viewPager2.setAdapter(new ProjectPowerControlAdapter(getActivity()));
        TabLayoutMediator tab = new TabLayoutMediator(t1, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("监控审核");
                        break;
                    case 1:
                        tab.setText("权限管理");
                        break;
                }
            }
        });
        tab.attach();

    }

}
