package com.example.projectmonitoringapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.projectmonitoringapp.fragment.LogFragment;
import com.example.projectmonitoringapp.fragment.LogGradleFragment;
import com.example.projectmonitoringapp.fragment.ProjectFragment;
import com.example.projectmonitoringapp.fragment.UserFragment;

public class CaretakerMainFragmentAdapter extends FragmentStateAdapter {
    public CaretakerMainFragmentAdapter(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new ProjectFragment();
            case 1:
                return new UserFragment();
            default:
                return new LogGradleFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
