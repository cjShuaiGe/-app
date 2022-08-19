package com.example.projectmonitoringapp.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmonitoringapp.Crypt2;
import com.example.projectmonitoringapp.R;
import com.example.projectmonitoringapp.UserLoginActivity;
import com.example.projectmonitoringapp.UserNoticeListActivity;
import com.example.projectmonitoringapp.UserPlatformAddActivity;
import com.example.projectmonitoringapp.UserPlatformListActivity;
import com.example.projectmonitoringapp.adapter.UserProjectAdapter;
import com.example.projectmonitoringapp.model.Project;
import com.example.projectmonitoringapp.model.Receive;
import com.example.projectmonitoringapp.util.HttpTool;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class UserProjectFragment extends Fragment {

    private RecyclerView recyclerView;
    List<Project> list = new ArrayList<>();
    private Button bt_home;
    private EditText et_search;
    private Button bt_search;
    private UserProjectAdapter adapter;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout notice_list;
    private RelativeLayout platform_add;
    private RelativeLayout platform_list;
    private RelativeLayout login_back;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_project,container,false);
        bt_home = view.findViewById(R.id.bt_home);
        et_search = view.findViewById(R.id.et_search_project);
        bt_search = view.findViewById(R.id.project_search);
        recyclerView = view.findViewById(R.id.project_list);
        //TODO

        adapter = new UserProjectAdapter(list,getActivity());
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        mDrawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        bt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        notice_list = (RelativeLayout) view.findViewById(R.id.rl_notice_list);
        notice_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), UserNoticeListActivity.class));
            }
        });

        platform_add = (RelativeLayout) view.findViewById(R.id.rl_platform_add);
        platform_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), UserPlatformAddActivity.class));
            }
        });

        platform_list = (RelativeLayout) view.findViewById(R.id.rl_platform_list);
        platform_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), UserPlatformListActivity.class));
            }
        });

        login_back = (RelativeLayout) view.findViewById(R.id.rl_login_back);
        login_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UserLoginActivity.class);
                intent.putExtra("loginBack","yes");
                startActivity(intent);
            }
        });

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        setData();
    }

    private void setData() {

        HttpTool.getProject(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),"连接失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Receive receive = new Gson().fromJson(response.body().string(),Receive.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (receive != null) {
                            if (receive.getCode() == 200) {
                                String data = Crypt2.decryptGet(receive.getData());
                                JSONArray jsonArray = null;
                                try {
                                    jsonArray = new JSONArray(data);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                list.clear();
                                for (int i = 0; i < jsonArray.length(); i++ ) {
                                    String s = null;
                                    try {
                                        s = jsonArray.get(i).toString();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    list.add(new Gson().fromJson(s,Project.class));
                                }
                                adapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(getActivity(),"receive.getMsg()",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });

    }

}
