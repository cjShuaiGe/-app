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

public class ControlPowerFragment extends Fragment {

    Button bt_cancel01;
    Button bt_cancel02;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_control_power,container,false);

        bt_cancel01 = (Button) view.findViewById(R.id.bt_cancel01);
        bt_cancel01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt_cancel01.setBackgroundResource(R.drawable.grey_button_corner);
                bt_cancel01.setClickable(false);
                Toast.makeText(getActivity(),"已撤销该用户的监控权限",Toast.LENGTH_SHORT).show();
            }
        });

        bt_cancel02 = (Button) view.findViewById(R.id.bt_cancel02);
        bt_cancel02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt_cancel02.setBackgroundResource(R.drawable.grey_button_corner);
                bt_cancel02.setClickable(false);
                Toast.makeText(getActivity(),"已撤销该用户的监控权限",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
