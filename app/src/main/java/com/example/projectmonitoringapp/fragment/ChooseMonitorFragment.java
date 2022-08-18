package com.example.projectmonitoringapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projectmonitoringapp.R;

public class ChooseMonitorFragment extends Fragment {

    Button bt_yes01;
    Button bt_no01;
    Button bt_yes02;
    Button bt_no02;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_monitor,container,false);

        bt_yes01 = (Button) view.findViewById(R.id.bt_yes01);
        bt_yes01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt_yes01.setBackgroundResource(R.drawable.grey_button_corner);
                bt_no01.setBackgroundResource(R.drawable.grey_button_corner);
                bt_no01.setClickable(false);
                bt_yes01.setClickable(false);
                Toast.makeText(getActivity(),"已同意该用户的监控申请",Toast.LENGTH_SHORT).show();
            }
        });

        bt_no01 = (Button) view.findViewById(R.id.bt_no01);
        bt_no01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt_no01.setBackgroundResource(R.drawable.grey_button_corner);
                bt_yes01.setBackgroundResource(R.drawable.grey_button_corner);
                bt_yes01.setClickable(false);
                bt_no01.setClickable(false);
                Toast.makeText(getActivity(),"已拒绝该用户的监控申请",Toast.LENGTH_SHORT).show();
            }
        });

        bt_yes02 = (Button) view.findViewById(R.id.bt_yes02);
        bt_yes02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt_yes02.setBackgroundResource(R.drawable.grey_button_corner);
                bt_no02.setBackgroundResource(R.drawable.grey_button_corner);
                bt_no02.setClickable(false);
                bt_yes02.setClickable(false);
                Toast.makeText(getActivity(),"已同意该用户的监控申请",Toast.LENGTH_SHORT).show();
            }
        });

        bt_no02 = (Button) view.findViewById(R.id.bt_no02);
        bt_no02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt_no02.setBackgroundResource(R.drawable.grey_button_corner);
                bt_yes02.setBackgroundResource(R.drawable.grey_button_corner);
                bt_yes02.setClickable(false);
                bt_no02.setClickable(false);
                Toast.makeText(getActivity(),"已拒绝该用户的监控申请",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
