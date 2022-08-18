package com.example.projectmonitoringapp.fragment;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projectmonitoringapp.JoinMonitorActivity;
import com.example.projectmonitoringapp.R;

public class issueFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_issue,container,false);

        TextView joinTextView = (TextView) view.findViewById(R.id.tv_join);
        joinTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), JoinMonitorActivity.class));
            }
        });

        Button bt_issue = (Button) view.findViewById(R.id.bt_issue);
        EditText name = (EditText) view.findViewById(R.id.et_mc);
        EditText command = (EditText) view.findViewById(R.id.et_kl);
        EditText address = (EditText) view.findViewById(R.id.et_dz);
        EditText introduction = (EditText) view.findViewById(R.id.et_jj);
        bt_issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(name.getText().toString())) {
                    Toast.makeText(getActivity(),"请输入名称",Toast.LENGTH_SHORT).show();
                } else if ("".equals(command.getText().toString())) {
                    Toast.makeText(getActivity(),"请设置项目口令",Toast.LENGTH_SHORT).show();
                } else if ("".equals(address.getText().toString())) {
                    Toast.makeText(getActivity(),"请输入地址",Toast.LENGTH_SHORT).show();
                } else if ("".equals(introduction.getText().toString())) {
                    Toast.makeText(getActivity(),"请输入简介",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(),"项目发布已申请，请等待审核结果",Toast.LENGTH_SHORT).show();
                    name.setText("");
                    command.setText("");
                    address.setText("");
                    introduction.setText("");
                }
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
