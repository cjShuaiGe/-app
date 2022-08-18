package com.example.projectmonitoringapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.projectmonitoringapp.fragment.AuditPowerFragment;
import com.example.projectmonitoringapp.fragment.MonitorPowerFragment;

public class UserPowerAdapter extends FragmentStateAdapter {

    public UserPowerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                return new MonitorPowerFragment();
            default:
                return new AuditPowerFragment();
        }

    }

    public int getItemCount() { return 2; }

}
