package com.example.projectmonitoringapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.projectmonitoringapp.fragment.ControlAuditFragment;
import com.example.projectmonitoringapp.fragment.ControlMonitorFragment;
import com.example.projectmonitoringapp.fragment.UpdateProjectFragment;

public class UserProjectActivityAdapter extends FragmentStateAdapter {

    public UserProjectActivityAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ControlMonitorFragment();
            case 1:
                return new UpdateProjectFragment();
            default:
                return new ControlAuditFragment();
        }
    }

    public int getItemCount() { return 3; }

}
