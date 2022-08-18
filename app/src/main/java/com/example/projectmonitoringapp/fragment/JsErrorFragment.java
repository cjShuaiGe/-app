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
import com.example.projectmonitoringapp.Crypt2;
import com.example.projectmonitoringapp.R;
import com.example.projectmonitoringapp.adapter.RcPageErrorAdapter;
import com.example.projectmonitoringapp.model.Freeze;
import com.example.projectmonitoringapp.model.RcPageError;
import com.example.projectmonitoringapp.model.RcUser;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JsErrorFragment extends Fragment {
    CombinedChart combinedChart;
    RecyclerView rc;
    RcPageErrorAdapter adapter;
    List<RcPageError> list=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_js_error,container,false);
        initView(view);
        initSpinner(view);
        setXAxis();
        setData();
        setYAxis();
        return view;

    }

    private void initView(View view) {
        combinedChart=view.findViewById(R.id.chart_js);
        rc=view.findViewById(R.id.rc_page_error);
        adapter=new RcPageErrorAdapter(list);
        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),1);
        rc.setLayoutManager(layoutManager);
        rc.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        setRcData();
    }

    private void setRcData() {
        list.clear();
        list.add(new RcPageError("xxxxxxxxxxxxxxxxxxxxxxxxxx","1"));
        list.add(new RcPageError("xxxxxxxxxxxxxxxxxxxxxxxxxx","1"));
        list.add(new RcPageError("xxxxxxxxxxxxxxxxxxxxxxxxxx","1"));
        adapter.notifyDataSetChanged();
    }

    private void setYAxis() {
        YAxis yAxis2=combinedChart.getAxisLeft();
        yAxis2.setAxisMaximum(20);
        yAxis2.setLabelCount(5);
        YAxis yAxis = combinedChart.getAxisRight();
        yAxis.setLabelCount(5);
        yAxis.setAxisMaximum(10);
        yAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                int i= (int) (value*10);
                return i+"%";
            }
        });
    }

    private void setXAxis() {
        XAxis xAxis = combinedChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);//不绘制网格线
        xAxis.setGranularity(1);//间隔1
        xAxis.setAxisMinimum(-0.5f);
        xAxis.setAxisMaximum(4 - 0.5f);

        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                switch ((int) value){
                    case 0:return "08-12 16:00";
                    case 1:return "08-12 22:00";
                    case 2:return "08-12 4:00";
                    case 3:return "08-12 10:00";
                    default:return " ";
                }
            }
        });
//        xAxis.setAxisMaximum(1);//设置最小值
//        xAxis.setAxisMinimum(5);//设置最大值

    }
    private void setData() {
        combinedChart.getDescription().setEnabled(false);


        float[] data ={4f,8f,3f,5f};
        List<Entry> entries =new ArrayList<>();
        for (int i = 0; i<data.length; i++){
            entries.add(new Entry(i,data[i]));
        }
        LineDataSet lineDataSet=new LineDataSet(entries,"错误率");

        lineDataSet.setCircleRadius(3);//设置圆点半径大小
        lineDataSet.setLineWidth(2);//设置折线的宽度
//        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER); // 设置折线类型，这里设置为贝塞尔曲线
        lineDataSet.setCircleColors(Color.parseColor("#4CDB96"));
        lineDataSet.setColor(Color.parseColor("#4CDB96"));
        LineData lineData = new LineData(lineDataSet);
        CombinedData combinedData=new CombinedData();
        combinedData.setData(lineData);
        List<BarEntry> barEntries = new ArrayList<BarEntry>();
        barEntries.add(new BarEntry(0,5));
        barEntries.add(new BarEntry(1,5));
        barEntries.add(new BarEntry(2,10));
        barEntries.add(new BarEntry(3,8));


        BarDataSet barDataSet = new BarDataSet(barEntries,"错误次数");
        BarData ba = new BarData(barDataSet);

        //设置每个柱块的宽度，取值0-1
        ba.setBarWidth(0.3f);
        //设置一组柱块与一组柱块之间的距离，当每组只有一个柱块时将会报错
//        ba.groupBars();
        combinedChart.setPinchZoom(true); // 比例缩放
        combinedData.setData(ba);
        combinedChart.setData(combinedData);
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
}
