package com.example.projectmonitoringapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.projectmonitoringapp.fragment.UserProjectFragment;

public class UserMainFragmentAdapter extends FragmentStateAdapter {

    public UserMainFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                return new UserProjectFragment();
            case 1:
                return new issueFragment();
            default:
                return new powerFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
