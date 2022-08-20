package com.example.projectmonitoringapp.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmonitoringapp.Crypt2;
import com.example.projectmonitoringapp.R;
import com.example.projectmonitoringapp.adapter.RcApiBiaoAdapter;
import com.example.projectmonitoringapp.model.ApiPicture;
import com.example.projectmonitoringapp.model.ApiPort;
import com.example.projectmonitoringapp.model.CountView;
import com.example.projectmonitoringapp.model.PictureNewPost;
import com.example.projectmonitoringapp.model.ProjectNamePost;
import com.example.projectmonitoringapp.model.RcPageError;
import com.example.projectmonitoringapp.model.Receive;
import com.example.projectmonitoringapp.util.HttpTool;
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

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ApiRequestFragment extends Fragment {
    List<ApiPicture> mlist=new ArrayList<>();
    CombinedChart combinedChart;
    RecyclerView rc;
    List<ApiPort> list=new ArrayList<>();
    RcApiBiaoAdapter adapter;
    Intent intent;
    TextView tv_week;
    TextView tv_add;
    TextView tv_count;
    TextView tv_rate;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_api_request,container,false);
        initView(view);
        setCountView();
        initSpinner(view);
        setBiao();
        return view;
    }

    private void initView(View view) {
        rc=view.findViewById(R.id.rc_api_request);
        combinedChart=view.findViewById(R.id.api_chart);
        intent=getActivity().getIntent();
        tv_add=view.findViewById(R.id.api_add);
        tv_count=view.findViewById(R.id.api_error_count);
        tv_rate=view.findViewById(R.id.api_error_rate);
        tv_week=view.findViewById(R.id.api_week);
        adapter=new RcApiBiaoAdapter(list);
        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),1);
        rc.setLayoutManager(layoutManager);
        rc.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setCountView() {
        String key=Crypt2.getRandomString(16);
        String text=new Gson().toJson(new ProjectNamePost(intent.getStringExtra("projectName")));
        HttpTool.postMonitorCount(key, text, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Receive receive=new Gson().fromJson(response.body().string(),Receive.class);
//              System.out.println(Crypt2.decryptECB(receive.getData(),key));
                if (getActivity()!=null){
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (receive!=null){
                            if (receive.getCode()==200){
                                String data= Crypt2.decryptECB(receive.getData(),key);
                                CountView countView=new Gson().fromJson(data,CountView.class);
                                if (countView.getApiThisWeekDefeatRate()!=null){
                                float f=Float.parseFloat(countView.getApiThisWeekDefeatRate())*100f;
                                tv_rate.setText(f+"%");}
                                if (countView.getJsCountIncreRate()==null){
                                    tv_week.setText("- -% ⬆");
                                }else {
                                    float i=Float.parseFloat(countView.getApiCountIncreRate())*100f;
                                    tv_week.setText(i+"% ⬆");
                                }
                                tv_count.setText(countView.getApiThisWeekCount());

                                tv_add.setText(countView.getApiCountIncre());
                            } else {
                                Toast.makeText(getActivity(),"获取失败",Toast.LENGTH_SHORT).show();}
                        }
                    }
                });}else {setCountView();}
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setBiao() {
        String key=Crypt2.getRandomString(16);
        String text=new Gson().toJson(new ProjectNamePost(intent.getStringExtra("projectName")));
        HttpTool.postApiBiao(key, text, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            Receive receive=new Gson().fromJson(response.body().string(),Receive.class);
                if (receive!=null){
                    if (getActivity()!=null){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (receive!=null){
                                if (receive.getCode()==200){
                                    String data= Crypt2.decryptECB(receive.getData(),key);
                                    JSONArray jsonArray= null;
                                    try {
                                        jsonArray = new JSONArray(data);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    list.clear();
                                    for (int i=0;i<jsonArray.length();i++){
                                        String s= null;
                                        try {
                                            s = jsonArray.get(i).toString();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        list.add(new Gson().fromJson(s, ApiPort.class));
                                    }
                                    adapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(getActivity(),"读取失败",Toast.LENGTH_SHORT).show();}
                            }
                        }
                    });}else {setBiao();}
                }
            }
        });
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

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//            Toast.makeText(getContext(),"您选择的是："+starArray[i],Toast.LENGTH_SHORT).show();
            setJsPictureData(i+1);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }

    }
    private void setYAxis() {
        YAxis yAxis2=combinedChart.getAxisLeft();
        yAxis2.setAxisMaximum(100);
        yAxis2.setAxisMinimum(0);
        yAxis2.setLabelCount(10);
        YAxis yAxis = combinedChart.getAxisRight();
        yAxis.setLabelCount(10);
        yAxis.setAxisMaximum(100);
        yAxis.setAxisMinimum(0);
        yAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {

                return value+"%";
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
                    case 0:return mlist.get(3).getDateStr();
                    case 1:return mlist.get(2).getDateStr();
                    case 2:return mlist.get(1).getDateStr();
                    case 3:return mlist.get(0).getDateStr();
                    default:return "";
                }
            }
        });
//        xAxis.setAxisMaximum(1);//设置最小值
//        xAxis.setAxisMinimum(5);//设置最大值

    }
    private void setData(List<Entry> entries, List<BarEntry> barEntries,List<BarEntry> barEntries2) {
        combinedChart.getDescription().setEnabled(false);


//        float[] data ={4f,8f,3f,5f};
//        List<Entry> entries =new ArrayList<>();
//        for (int i = 0; i<data.length; i++){
//            entries.add(new Entry(i,data[i]));
//        }




        LineDataSet lineDataSet=new LineDataSet(entries,"成功率");
        lineDataSet.setCircleRadius(3);//设置圆点半径大小
        lineDataSet.setLineWidth(2);//设置折线的宽度
//        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER); // 设置折线类型，这里设置为贝塞尔曲线
        lineDataSet.setCircleColors(Color.parseColor("#4CDB96"));
        lineDataSet.setColor(Color.parseColor("#4CDB96"));
        LineData lineData = new LineData();
        lineData.addDataSet(lineDataSet);


        CombinedData combinedData=new CombinedData();
//        combinedData.setData(lineData2);
        combinedData.setData(lineData);
//        List<BarEntry> barEntries = new ArrayList<BarEntry>();
//        barEntries.add(new BarEntry(0,5));
//        barEntries.add(new BarEntry(1,5));
//        barEntries.add(new BarEntry(2,10));
//        barEntries.add(new BarEntry(3,8));
        BarDataSet barDataSet2 = new BarDataSet(barEntries2,"成功次数");
//        BarData ba2 = new BarData(barDataSet2);
        barDataSet2.setColor(Color.parseColor("#FFC0CB"));
        BarDataSet barDataSet = new BarDataSet(barEntries,"错误次数");
        barDataSet.setColor(Color.parseColor("#FFD700"));
//        BarData ba = new BarData(barDataSet);
           BarData ba=new BarData();
           ba.addDataSet(barDataSet);
           ba.addDataSet(barDataSet2);
        //设置每个柱块的宽度，取值0-1
        ba.setBarWidth(0.3f);
        //设置一组柱块与一组柱块之间的距离，当每组只有一个柱块时将会报错
        ba.groupBars(-0.35f,0.35f,0);
        combinedChart.setPinchZoom(true); // 比例缩放
//        combinedData.setData(ba2);
        combinedData.setData(ba);
        combinedChart.setData(combinedData);
        combinedChart.notifyDataSetChanged();
        combinedChart.invalidate();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setJsPictureData(int i) {
        String key= Crypt2.getRandomString(16);
        String text=new Gson().toJson(new PictureNewPost(intent.getStringExtra("projectName"),String.valueOf(i)));
//        System.out.println(text);
        HttpTool.postApiPicture(key, text, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),"连接失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Receive receive=new Gson().fromJson(response.body().string(),Receive.class);
                if (receive!=null){
                    if (getActivity()!=null){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (receive!=null){
                                if (receive.getCode()==200){
                                    String data= Crypt2.decryptECB(receive.getData(),key);
                                    System.out.println("data:"+data);
                                    JSONArray jsonArray= null;
                                    try {
                                        jsonArray = new JSONArray(data);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        if (!jsonArray.getJSONObject(0).isNull("percent")){

                                        mlist.clear();
                                        for (int i=0;i<jsonArray.length()-1;i++){
                                            String s= null;
                                            try {
                                                s = jsonArray.get(i).toString();
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            mlist.add(new Gson().fromJson(s, ApiPicture.class));

                                        }

                                        List<BarEntry> barEntries2=new ArrayList<>();
                                        List<BarEntry> barEntries=new ArrayList<>();
                                        List<Entry> entries=new ArrayList<>();
                                        for (int j=0;j<mlist.size();j++){
                                            ApiPicture picture=mlist.get(mlist.size()-1-j);
    //                                    System.out.println(picture.getPercent());
                                            entries.add(new Entry(j,picture.getPercent()));
                                            barEntries.add(new BarEntry(j,picture.getDefeatCount()));
                                            barEntries2.add(new BarEntry(j,picture.getCount()));
                                        }
                                        setXAxis();
                                        setYAxis();
                                        setData(entries,barEntries,barEntries2);}else {setJsPictureData(i);}
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    Toast.makeText(getActivity(),"获取失败",Toast.LENGTH_SHORT).show();}
                            }
                        }
                    });}else {setJsPictureData(i);}
                }
            }
        });




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
