package com.example.projectmonitoringapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.projectmonitoringapp.fragment.ApiRequestFragment;
import com.example.projectmonitoringapp.fragment.JsErrorFragment;
import com.example.projectmonitoringapp.fragment.LogApiFragment;
import com.example.projectmonitoringapp.fragment.LogGradleFragment;
import com.example.projectmonitoringapp.fragment.ResourceErrorFragment;
import com.example.projectmonitoringapp.fragment.VisitDetailFragment;

public class LogFragmentAdapter extends FragmentStateAdapter {
    public LogFragmentAdapter(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new LogGradleFragment();
            default:
                return new LogApiFragment();

        }
    }

    public int getItemCount(){
        return 2;
    }
}
