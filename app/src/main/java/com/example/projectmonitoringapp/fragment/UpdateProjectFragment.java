package com.example.projectmonitoringapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projectmonitoringapp.R;

public class UpdateProjectFragment extends Fragment {

    EditText name;
    EditText address;
    EditText introduction;
    Button update;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_project,container,false);

        name = (EditText) view.findViewById(R.id.et_mc);
        address = (EditText) view.findViewById(R.id.et_dz);
        introduction = (EditText) view.findViewById(R.id.et_jj);
        update = (Button) view.findViewById(R.id.bt_update);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(name.getText().toString())) {
                    Toast.makeText(getActivity(),"请输入名称",Toast.LENGTH_SHORT).show();
                } else if ("".equals(address.getText().toString())) {
                    Toast.makeText(getActivity(),"请输入地址",Toast.LENGTH_SHORT).show();
                } else if ("".equals(introduction.getText().toString())) {
                    Toast.makeText(getActivity(),"请输入简介",Toast.LENGTH_SHORT).show();
                } else {
                    name.setText("");
                    address.setText("");
                    introduction.setText("");
                    Toast.makeText(getActivity(),"已申请项目更新，请等待审核结果",Toast.LENGTH_SHORT).show();
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
