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
import com.example.projectmonitoringapp.adapter.RcEventFilenameAdapter;
import com.example.projectmonitoringapp.model.BinPicture;
import com.example.projectmonitoringapp.model.CountView;
import com.example.projectmonitoringapp.model.JsPicture;
import com.example.projectmonitoringapp.model.PictureDouble;
import com.example.projectmonitoringapp.model.ProjectNamePost;
import com.example.projectmonitoringapp.model.RcEvent;
import com.example.projectmonitoringapp.model.RcEventList;
import com.example.projectmonitoringapp.model.Receive;
import com.example.projectmonitoringapp.util.HttpTool;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
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

public class ResourceErrorFragment extends Fragment {
    CombinedChart combinedChart;
    private String[] starArray = {"<link>错误","<script>错误","<img>错误","<object>错误"};
    PieChart pc;
    RecyclerView rc;
    RcEventFilenameAdapter adapter;
    List<RcEventList> list=new ArrayList<>();
    Intent intent;
    List<BinPicture> binlist=new ArrayList<>();
    List<JsPicture> mlist=new ArrayList<>();
    TextView tv_week;
    TextView tv_add;
    TextView tv_count;

    int type=1;
    int option=1;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_resource_error,container,false);
       initView(view);
       setCountView();
        setRcView();
        initSpinner(view);
        initSpinner2(view);
//        setLegendBin();
        setBinData();

//        loadData();
        return view;
    }

    private void initView(View view) {
        pc=view.findViewById(R.id.resource_error_pieChart);
        rc=view.findViewById(R.id.rc_resource_event);
        intent=getActivity().getIntent();
        combinedChart=view.findViewById(R.id.resource_error_chart);
        tv_add=view.findViewById(R.id.resource_add);
        tv_week=view.findViewById(R.id.resource_week);
        tv_count=view.findViewById(R.id.resource_error_count);
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
                                if (countView.getJsCountIncreRate()==null){
                                    tv_week.setText("- -% ⬆");
                                }else {
                                    float i=Float.parseFloat(countView.getResourceCountIncreRate())*100f;
                                    tv_week.setText(i+"% ⬆");
                                }
                                tv_count.setText(countView.getResourceThisWeekCount());

                                tv_add.setText(countView.getResourceCountIncre());
                            } else {
                                Toast.makeText(getActivity(),"获取失败",Toast.LENGTH_SHORT).show();}
                        }
                    }
                });
            } else{setCountView();}
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setPictureData(int t,int o) {
        String key=Crypt2.getRandomString(16);
        String text=new Gson().toJson(new PictureDouble(intent.getStringExtra("projectName"),String.valueOf(t),String.valueOf(o)));
        System.out.println(text);
        HttpTool.postResourcePicture(key, text, new Callback() {
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
                                    mlist.clear();
                                    for (int i=0;i<jsonArray.length();i++){
                                        String s= null;
                                        try {
                                            s = jsonArray.get(i).toString();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        mlist.add(new Gson().fromJson(s, JsPicture.class));

                                    }
                                    List<BarEntry> barEntries=new ArrayList<>();
                                    List<Entry> entries=new ArrayList<>();
                                    for (int j=0;j<mlist.size();j++){
                                        JsPicture picture=mlist.get(mlist.size()-1-j);
//                                    System.out.println(picture.getPercent());
                                        entries.add(new Entry(j,picture.getPercent()));
                                        barEntries.add(new BarEntry(j,picture.getCount()));
                                    }
                                    setXAxis();
                                    setYAxis();
                                    setData(entries,barEntries);
                                } else {
                                    Toast.makeText(getActivity(),"获取失败",Toast.LENGTH_SHORT).show();}
                            }
                        }
                    });}else {setPictureData(t,o);}
                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setBinData() {
         String key=Crypt2.getRandomString(16);
         String text=new Gson().toJson(new ProjectNamePost(intent.getStringExtra("projectName")));
         HttpTool.postResourceBin(key, text, new Callback() {
             @Override
             public void onFailure(@NonNull Call call, @NonNull IOException e) {

             }

             @Override
             public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                 Receive receive=new Gson().fromJson(response.body().string(),Receive.class);
//                 System.out.println(Crypt2.decryptECB(receive.getData(),key));
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
                                     binlist.clear();
                                     for (int i=0;i<jsonArray.length();i++){
                                         String s= null;
                                         try {
                                             s = jsonArray.get(i).toString();
                                         } catch (JSONException e) {
                                             e.printStackTrace();
                                         }
                                         binlist.add(new Gson().fromJson(s, BinPicture.class));

                                     }

                                     List<PieEntry> entries=new ArrayList<>();
                                     for (int j=0;j<binlist.size();j++){
                                         BinPicture picture=binlist.get(j);
                                         entries.add(new PieEntry(picture.getPercent(),picture.getTagname()));
                                     }
                                    setLegendBin();
                                     loadData(entries);
                                     pc.notifyDataSetChanged();
                                     pc.invalidate();
                                 } else {
                                     Toast.makeText(getActivity(),"获取失败",Toast.LENGTH_SHORT).show();}
                             }
                         }
                     });}else {setBinData();}
                 }
             }
         });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setRcView() {
        adapter=new RcEventFilenameAdapter(list,getActivity());
        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),1);
        rc.setLayoutManager(layoutManager);
        rc.setAdapter(adapter);
        setRcData();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setRcData() {
       String key= Crypt2.getRandomString(16);
       String text=new Gson().toJson(new ProjectNamePost(intent.getStringExtra("projectName")));

        HttpTool.postResourceRc(key, text, new Callback() {
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
//                System.out.println(Crypt2.decryptECB(receive.getData(),key));
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
                                    JSONArray jsonArray1= null;
                                    try {
                                        jsonArray1 = new JSONArray(s);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    List<RcEvent> mmlist=new ArrayList<>();
                                    for (int j=0;j<jsonArray1.length();j++){
                                        String ss= null;
                                        try {
                                            ss = jsonArray1.get(j).toString();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
//                                        System.out.println(ss);
                                        RcEvent event=new Gson().fromJson(ss,RcEvent.class);
                                        mmlist.add(event);
                                    }
                                    list.add(new RcEventList(mmlist));

                                }
                                adapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(getActivity(),"读取失败",Toast.LENGTH_SHORT).show();}
                        }
                    }
                });}else {setRcData();}
            }
        });
    }

    public void setLegendBin(){
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

    private void loadData(List<PieEntry> entries) {

        //不显示描述/标题
        pc.getDescription().setEnabled(false);
        //不显示图例
//        pc.getLegend().setEnabled(false);
        //设置上边和下边的偏移量
        pc.setExtraTopOffset(20);
        pc.setExtraBottomOffset(20);
        //数据源
//        List<PieEntry> entries = new ArrayList<PieEntry>();
//        entries.add(new PieEntry(86.1f,"1"));
//        entries.add(new PieEntry(13.8f,"2"));


        //添加到PieDataSet中
        PieDataSet pieDataSet = new PieDataSet(entries,"");
        pieDataSet.setColors(Color.parseColor("#47A7FF"),Color.parseColor("#10BE80"),Color.parseColor("#999999"),
                Color.parseColor("#FF5959"),Color.parseColor("#FFFACD"),Color.parseColor("#FFF8DC"),Color.parseColor("#FFF0F5"),
                Color.parseColor("#FFC0CB"));
        //自定义描述
//        final String strs[] = {"无重复违章：","有重复违章："};
        List<String> strs=new ArrayList<>();
        for (int i=0;i<entries.size();i++){
              strs.add(binlist.get(i).getTagname()+"：");
        }
        pieDataSet.setValueFormatter(new ValueFormatter() {
            private int indd = -1;
            @Override
            public String getFormattedValue(float value) {
                indd ++;
                if(indd >= strs.size()){
                    indd = 0;
                }
                return strs.get(indd) + value + "%";
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

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//            Toast.makeText(getContext(),"您选择的是："+starArray[i],Toast.LENGTH_SHORT).show();
            option=i+1;
            setPictureData(type,i+1);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }


    private String[] starArray2 = {"今日","今月","今年"};
    private void initSpinner2(View view){
        //声明一个下拉列表的数组适配器
        ArrayAdapter<String> starAdapter = new ArrayAdapter<String>(getContext(),R.layout.item_date_select,starArray2);
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
        sp.setOnItemSelectedListener(new MySelectedListener2());
    }

    class MySelectedListener2 implements AdapterView.OnItemSelectedListener{

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//            Toast.makeText(getContext(),"您选择的是："+starArray2[i],Toast.LENGTH_SHORT).show();
            type=i+1;
            setPictureData(i+1,option);
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
    private void setData(List<Entry> entries,List<BarEntry> barEntries) {
        combinedChart.getDescription().setEnabled(false);


//        float[] data ={4f,8f,3f,5f};
//        List<Entry> entries =new ArrayList<>();
//        for (int i = 0; i<data.length; i++){
//            entries.add(new Entry(i,data[i]));
//        }
        LineDataSet lineDataSet=new LineDataSet(entries,"错误率");

        lineDataSet.setCircleRadius(3);//设置圆点半径大小
        lineDataSet.setLineWidth(2);//设置折线的宽度
//        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER); // 设置折线类型，这里设置为贝塞尔曲线
        lineDataSet.setCircleColors(Color.parseColor("#4CDB96"));
        lineDataSet.setColor(Color.parseColor("#4CDB96"));
        LineData lineData = new LineData(lineDataSet);
        CombinedData combinedData=new CombinedData();
        combinedData.setData(lineData);
//        List<BarEntry> barEntries = new ArrayList<BarEntry>();
//        barEntries.add(new BarEntry(0,5));
//        barEntries.add(new BarEntry(1,5));
//        barEntries.add(new BarEntry(2,10));
//        barEntries.add(new BarEntry(3,8));


        BarDataSet barDataSet = new BarDataSet(barEntries,"错误次数");
        BarData ba = new BarData(barDataSet);

        //设置每个柱块的宽度，取值0-1
        ba.setBarWidth(0.3f);
        //设置一组柱块与一组柱块之间的距离，当每组只有一个柱块时将会报错
//        ba.groupBars();
        combinedChart.setPinchZoom(true); // 比例缩放
        combinedData.setData(ba);
        combinedChart.setData(combinedData);
        combinedChart.notifyDataSetChanged();
        combinedChart.invalidate();
    }
}
