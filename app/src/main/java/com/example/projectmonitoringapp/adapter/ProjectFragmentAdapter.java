package com.example.projectmonitoringapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.projectmonitoringapp.fragment.AuditFragment;
import com.example.projectmonitoringapp.fragment.MonitorFragment;

public class ProjectFragmentAdapter extends FragmentStateAdapter {
    public ProjectFragmentAdapter(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new MonitorFragment();
            default:
                return new AuditFragment();

        }
    }

    public int getItemCount(){
        return 2;
    }
}
