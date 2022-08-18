package com.example.projectmonitoringapp.fragment;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.projectmonitoringapp.R;
import com.example.projectmonitoringapp.model.RcUser;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ApiRequestFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_api_request,container,false);
        initSpinner(view);
        return view;
    }


    private String[] starArray = {"今日","今月","今年"};
    private void initSpinner(View view){
        //声明一个下拉列表的数组适配器
        ArrayAdapter<String> starAdapter = new ArrayAdapter<String>(getContext(),R.layout.item_date_select,starArray);
        //设置数组适配器的布局样式
        starAdapter.setDropDownViewResource(R.layout.item_dropdown);
        //从布局文件中获取名叫sp_dialog的下拉框
        Spinner sp = view.findViewById(R.id.js_error_spinner);
        //设置下拉框的标题，不设置就没有难看的标题了
//        sp.setPrompt("请选择行星");
        //设置下拉框的数组适配器
        sp.setAdapter(starAdapter);
        //设置下拉框默认的显示第一项
        sp.setSelection(0);
        //给下拉框设置选择监听器，一旦用户选中某一项，就触发监听器的onItemSelected方法
        sp.setOnItemSelectedListener(new MySelectedListener());
    }
    class MySelectedListener implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(getContext(),"您选择的是："+starArray[i],Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }

    }
    //    private void setTime(RcUser user) {
//        TimePickerView pvTime = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
//            @RequiresApi(api = Build.VERSION_CODES.O)
//            @SuppressLint("LongLogTag")
//            @Override
//            public void onTimeSelect(Date date, View v) {
//
//            }
//        })
//                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
//                    @Override
//                    public void onTimeSelectChanged(Date date) {
//
//                    }
//                })
//                .setType(new boolean[]{true, true, true, false,false, false})
//                .setItemVisibleCount(5)
//                .setLineSpacingMultiplier(2.0f)
//                .isAlphaGradient(true)
//
//                .build();
//        pvTime.show();
//
//    }
//
//    private String getTime(Date date) {
//        @SuppressLint("SimpleDateFormat")
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        return format.format(date);
//
//    }

}
