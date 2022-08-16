package com.example.projectmonitoringapp.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.projectmonitoringapp.R;
import com.example.projectmonitoringapp.adapter.RcResourceEventAdapter;
import com.example.projectmonitoringapp.model.RcEvent;
import com.example.projectmonitoringapp.model.RcUser;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResourceErrorFragment extends Fragment {
    private String[] starArray = {"全部","<link>错误","<script>错误","<img>错误","<object>错误"};
    PieChart pc;
    RecyclerView rc;
    RcResourceEventAdapter adapter;
    List<RcEvent> list=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_resource_error,container,false);
        pc=view.findViewById(R.id.resource_error_pieChart);
        rc=view.findViewById(R.id.rc_resource_event);
        setRcView();
        initSpinner(view);
        setLegend();
        loadData();
        return view;
    }

    private void setRcView() {
        adapter=new RcResourceEventAdapter(list);
        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),1);
        rc.setLayoutManager(layoutManager);
        rc.setAdapter(adapter);
        setRcData();
    }

    private void setRcData() {
        list.clear();
        list.add(new RcEvent("事例1","xxxxxxxxxxxxxxxxxxxxxxxx","xxxxxxxxxxxxxxxxxxxxxxxx"));
        list.add(new RcEvent("事例2","xxxxxxxxxxxxxxxxxxxxxxxx","xxxxxxxxxxxxxxxxxxxxxxxx"));
        adapter.notifyDataSetChanged();
    }

    public void setLegend(){
        Legend legend = pc.getLegend();
        legend.setTextSize(16f);
//        legend.setTextColor(Color.BLUE);
        //设置图例是在文字的左边还是右边
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
        //设置图例为圆形，默认为方形
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setForm(Legend.LegendForm.CIRCLE);
//        legend.setFormSize(20);
//        legend.setFormLineWidth(20);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
//        legend.setFormToTextSpace(15);
//        legend.setXOffset(40);
    }

    private void loadData() {

        //不显示描述/标题
        pc.getDescription().setEnabled(false);
        //不显示图例
//        pc.getLegend().setEnabled(false);
        //设置上边和下边的偏移量
        pc.setExtraTopOffset(20);
        pc.setExtraBottomOffset(20);
        //数据源
        List<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry(86.1f,"1"));
        entries.add(new PieEntry(13.8f,"2"));


        //添加到PieDataSet中
        PieDataSet pieDataSet = new PieDataSet(entries,"");
        pieDataSet.setColors(Color.parseColor("#47A7FF"),Color.parseColor("#10BE80"),Color.parseColor("#999999"));
        //自定义描述
        final String strs[] = {"无重复违章：","有重复违章："};
        pieDataSet.setValueFormatter(new ValueFormatter() {
            private int indd = -1;
            @Override
            public String getFormattedValue(float value) {
                indd ++;
                if(indd >= strs.length){
                    indd = 0;
                }
                return strs[indd] + value + "%";
            }
        });
//        pieDataSet.setDrawValues(false);//是否绘制文字
        //设置饼块之间的间隔
//        pieDataSet.setSliceSpace(2f);
        //设置点击某一饼快多出来的距离
        pieDataSet.setSelectionShift(10f);
        pieDataSet.setValueTextSize(10f);//设置在饼图上显示文字的大小
        //设置连接线显示在外面
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        //设置连接线距离饼图的距离，为百分数
        pieDataSet.setValueLinePart1OffsetPercentage(100f);
        //定义连接线的长度
        pieDataSet.setValueLinePart1Length(0.7f);

        //不绘制空洞
        pc.setDrawHoleEnabled(false);
        //不可旋转
        pc.setRotationEnabled(false);
        PieData pieData = new PieData(pieDataSet);
        //设置数据
        pc.setData(pieData);


    }





    private void initSpinner(View view){
        //声明一个下拉列表的数组适配器
        ArrayAdapter<String> starAdapter = new ArrayAdapter<String>(getContext(),R.layout.item_select,starArray);
        //设置数组适配器的布局样式
        starAdapter.setDropDownViewResource(R.layout.item_dropdown);
        //从布局文件中获取名叫sp_dialog的下拉框
        Spinner sp = view.findViewById(R.id.resource_error_spinner);
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

    private void setTime(RcUser user) {
        TimePickerView pvTime = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @SuppressLint("LongLogTag")
            @Override
            public void onTimeSelect(Date date, View v) {

            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {

                    }
                })
                .setType(new boolean[]{true, true, true, false,false, false})
                .setItemVisibleCount(5)
                .setLineSpacingMultiplier(2.0f)
                .isAlphaGradient(true)

                .build();
        pvTime.show();

    }

    private String getTime(Date date) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);

    }


}
