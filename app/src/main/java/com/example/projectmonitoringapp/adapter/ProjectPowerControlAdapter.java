package com.example.projectmonitoringapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.projectmonitoringapp.fragment.ChooseMonitorFragment;
import com.example.projectmonitoringapp.fragment.ControlPowerFragment;

public class ProjectPowerControlAdapter extends FragmentStateAdapter {

    public ProjectPowerControlAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ChooseMonitorFragment();
            default:
                return new ControlPowerFragment();
        }
    }

    public int getItemCount() { return 2; }

}
