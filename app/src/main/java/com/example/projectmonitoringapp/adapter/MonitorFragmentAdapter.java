package com.example.projectmonitoringapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.projectmonitoringapp.fragment.ApiRequestFragment;
import com.example.projectmonitoringapp.fragment.AuditFragment;
import com.example.projectmonitoringapp.fragment.JsErrorFragment;
import com.example.projectmonitoringapp.fragment.MonitorFragment;
import com.example.projectmonitoringapp.fragment.PerformanceMonitorFragment;
import com.example.projectmonitoringapp.fragment.ResourceErrorFragment;
import com.example.projectmonitoringapp.fragment.VisitDetailFragment;
import com.example.projectmonitoringapp.fragment.WhileMonitorFragment;

public class MonitorFragmentAdapter extends FragmentStateAdapter {
    public MonitorFragmentAdapter(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new JsErrorFragment();
            case 1:
                return new ApiRequestFragment();
            case 2:
                return new VisitDetailFragment();
            case 3:
                return new ResourceErrorFragment();
            default:
                return new PerformanceMonitorFragment();


        }
    }

    public int getItemCount(){
        return 5;
    }
}
