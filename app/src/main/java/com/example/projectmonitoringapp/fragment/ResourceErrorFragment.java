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
    private String[] starArray = {"<link>??????","<script>??????","<img>??????","<object>??????"};
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
                                    tv_week.setText("- -% ???");
                                }else {
                                    float i=Float.parseFloat(countView.getResourceCountIncreRate())*100f;
                                    tv_week.setText(i+"% ???");
                                }
                                tv_count.setText(countView.getResourceThisWeekCount());

                                tv_add.setText(countView.getResourceCountIncre());
                            } else {
                                Toast.makeText(getActivity(),"????????????",Toast.LENGTH_SHORT).show();}
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
                                    Toast.makeText(getActivity(),"????????????",Toast.LENGTH_SHORT).show();}
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
                                     Toast.makeText(getActivity(),"????????????",Toast.LENGTH_SHORT).show();}
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
                        Toast.makeText(getActivity(),"????????????",Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(getActivity(),"????????????",Toast.LENGTH_SHORT).show();}
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
        //?????????????????????????????????????????????
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
        //???????????????????????????????????????
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

        //???????????????/??????
        pc.getDescription().setEnabled(false);
        //???????????????
//        pc.getLegend().setEnabled(false);
        //?????????????????????????????????
        pc.setExtraTopOffset(20);
        pc.setExtraBottomOffset(20);
        //?????????
//        List<PieEntry> entries = new ArrayList<PieEntry>();
//        entries.add(new PieEntry(86.1f,"1"));
//        entries.add(new PieEntry(13.8f,"2"));


        //?????????PieDataSet???
        PieDataSet pieDataSet = new PieDataSet(entries,"");
        pieDataSet.setColors(Color.parseColor("#47A7FF"),Color.parseColor("#10BE80"),Color.parseColor("#999999"),
                Color.parseColor("#FF5959"),Color.parseColor("#FFFACD"),Color.parseColor("#FFF8DC"),Color.parseColor("#FFF0F5"),
                Color.parseColor("#FFC0CB"));
        //???????????????
//        final String strs[] = {"??????????????????","??????????????????"};
        List<String> strs=new ArrayList<>();
        for (int i=0;i<entries.size();i++){
              strs.add(binlist.get(i).getTagname()+"???");
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





//        pieDataSet.setDrawValues(false);//??????????????????
        //???????????????????????????
//        pieDataSet.setSliceSpace(2f);
        //??????????????????????????????????????????
        pieDataSet.setSelectionShift(10f);
        pieDataSet.setValueTextSize(10f);//???????????????????????????????????????
        //??????????????????????????????
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        //???????????????????????????????????????????????????
        pieDataSet.setValueLinePart1OffsetPercentage(100f);
        //????????????????????????
        pieDataSet.setValueLinePart1Length(0.7f);

        //???????????????
        pc.setDrawHoleEnabled(false);
        //????????????
        pc.setRotationEnabled(false);
        PieData pieData = new PieData(pieDataSet);
        //????????????
        pc.setData(pieData);


    }





    private void initSpinner(View view){
        //??????????????????????????????????????????
        ArrayAdapter<String> starAdapter = new ArrayAdapter<String>(getContext(),R.layout.item_select,starArray);
        //????????????????????????????????????
        starAdapter.setDropDownViewResource(R.layout.item_dropdown);
        //??????????????????????????????sp_dialog????????????
        Spinner sp = view.findViewById(R.id.resource_error_spinner);
        //???????????????????????????????????????????????????????????????
//        sp.setPrompt("???????????????");
        //?????????????????????????????????
        sp.setAdapter(starAdapter);
        //???????????????????????????????????????
        sp.setSelection(0);
        //???????????????????????????????????????????????????????????????????????????????????????onItemSelected??????
        sp.setOnItemSelectedListener(new MySelectedListener());
    }

    class MySelectedListener implements AdapterView.OnItemSelectedListener{

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//            Toast.makeText(getContext(),"??????????????????"+starArray[i],Toast.LENGTH_SHORT).show();
            option=i+1;
            setPictureData(type,i+1);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }


    private String[] starArray2 = {"??????","??????","??????"};
    private void initSpinner2(View view){
        //??????????????????????????????????????????
        ArrayAdapter<String> starAdapter = new ArrayAdapter<String>(getContext(),R.layout.item_date_select,starArray2);
        //????????????????????????????????????
        starAdapter.setDropDownViewResource(R.layout.item_dropdown);
        //??????????????????????????????sp_dialog????????????
        Spinner sp = view.findViewById(R.id.js_error_spinner);
        //???????????????????????????????????????????????????????????????
//        sp.setPrompt("???????????????");
        //?????????????????????????????????
        sp.setAdapter(starAdapter);
        //???????????????????????????????????????
        sp.setSelection(0);
        //???????????????????????????????????????????????????????????????????????????????????????onItemSelected??????
        sp.setOnItemSelectedListener(new MySelectedListener2());
    }

    class MySelectedListener2 implements AdapterView.OnItemSelectedListener{

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//            Toast.makeText(getContext(),"??????????????????"+starArray2[i],Toast.LENGTH_SHORT).show();
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
        xAxis.setDrawGridLines(false);//??????????????????
        xAxis.setGranularity(1);//??????1
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
//        xAxis.setAxisMaximum(1);//???????????????
//        xAxis.setAxisMinimum(5);//???????????????

    }
    private void setData(List<Entry> entries,List<BarEntry> barEntries) {
        combinedChart.getDescription().setEnabled(false);


//        float[] data ={4f,8f,3f,5f};
//        List<Entry> entries =new ArrayList<>();
//        for (int i = 0; i<data.length; i++){
//            entries.add(new Entry(i,data[i]));
//        }
        LineDataSet lineDataSet=new LineDataSet(entries,"?????????");

        lineDataSet.setCircleRadius(3);//????????????????????????
        lineDataSet.setLineWidth(2);//?????????????????????
//        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER); // ???????????????????????????????????????????????????
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


        BarDataSet barDataSet = new BarDataSet(barEntries,"????????????");
        BarData ba = new BarData(barDataSet);

        //????????????????????????????????????0-1
        ba.setBarWidth(0.3f);
        //?????????????????????????????????????????????????????????????????????????????????????????????
//        ba.groupBars();
        combinedChart.setPinchZoom(true); // ????????????
        combinedData.setData(ba);
        combinedChart.setData(combinedData);
        combinedChart.notifyDataSetChanged();
        combinedChart.invalidate();
    }
}
