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

public class ControlAuditFragment extends Fragment {

    Button bt_invite;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_control_audit,container,false);

        bt_invite = (Button) view.findViewById(R.id.bt_invite);
        bt_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "无权限！请先申请共享发布者的权限", Toast.LENGTH_SHORT).show();
            }
        });

        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
