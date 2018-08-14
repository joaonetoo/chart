package com.example.virtus.chats.presenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.virtus.chats.R;
import com.example.virtus.chats.model.ChartModel;
import com.example.virtus.chats.model.MarkerViewChart;
import com.example.virtus.chats.model.ValueFormatter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BarChart mConsumptionChart;
    private List<ChartModel> listChartModel = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            JSONArray jArray = new JSONArray(readJSONFromAsset("data.json"));
            for (int i = 0; i < jArray.length(); ++i) {
                Double fromPvToConsumption = jArray.getJSONObject(i).getDouble("fromPVToConsumption");
                Double fromStorageToConsumptionValue = jArray.getJSONObject(i).getDouble("fromStorageToConsumption");
                Double fromPublicGridToConsumption = jArray.getJSONObject(i).getDouble("fromPublicGridToConsumption");
                Double fromGridToStorage = jArray.getJSONObject(i).getDouble("fromGridToStorage");
                Double fromDieselGridToConsumption = jArray.getJSONObject(i).getDouble("fromDieselGridToConsumption");
                ChartModel chartModel = new ChartModel(fromPvToConsumption, fromStorageToConsumptionValue,
                        fromPublicGridToConsumption, fromGridToStorage,
                        fromDieselGridToConsumption);


                listChartModel.add(chartModel);
            }
            mConsumptionChart = findViewById(R.id.consumptionChart);
            setupVerticalChart(mConsumptionChart);
            mConsumptionChart.setTouchEnabled(true);

            MarkerViewChart mv = new MarkerViewChart(this, R.layout.marker_view_chart_layout);
            mConsumptionChart.setMarker(mv);
            setDataChart();


        } catch (JSONException e) {
            e.printStackTrace();

        }


    }

    public void setupVerticalChart(BarChart barChart) {
//        barChart.setHighlightFullBarEnabled(true);
//        barChart.getDescription().setEnabled(false);
        barChart.getLegend().setEnabled(false);
        barChart.getDescription().setEnabled(false);
        barChart.getAxisLeft().setDrawGridLines(true);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.setDrawGridBackground(false);
        barChart.setDrawBarShadow(false);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.setPinchZoom(false);
        barChart.setScaleEnabled(false);
        barChart.setHighlightFullBarEnabled(true);

//        YAxis leftAxis = barChart.getAxisLeft();
//        leftAxis.setLabelCount(4, true);
//        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
//        leftAxis.enableGridDashedLine(10, 10, 10);
//        leftAxis.setSpaceTop(0);
//        leftAxis.setAxisMinimum(0);

//        YAxis rightAxis = barChart.getAxisRight();
//        rightAxis.setLabelCount(3, true);
//        rightAxis.setAxisMinimum(0);
//        rightAxis.enableGridDashedLine(10, 10, 10);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(1);
        xAxis.setDrawLabels(true);
        xAxis.setLabelCount(15, false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
    }

    private void setDataChart() {
        ArrayList<BarEntry> yValues = new ArrayList<>();
        for (int i = 0; i < listChartModel.size(); i++) {
            ChartModel chartModel = listChartModel.get(i);
            float fromPVToConsumption = chartModel.getfromPVToConsumption().floatValue();
            float fromStorageToConsumption = chartModel.getfromStorageToConsumption().floatValue();
            float fromPublicGridToConsumption = chartModel.getfromPublicGridToConsumption().floatValue();
            float fromGridToStorage = chartModel.getfromGridToStorage().floatValue();
            float fromDieselGridToConsumption = chartModel.getfromDieselGridToConsumption().floatValue();

            BarEntry entry = new BarEntry(i, new float[]{fromPVToConsumption, fromStorageToConsumption,
                    fromPublicGridToConsumption, fromGridToStorage,
                    fromDieselGridToConsumption});

            entry.setData(new float[]{fromPVToConsumption, fromStorageToConsumption,
                    fromPublicGridToConsumption, fromGridToStorage,
                    fromDieselGridToConsumption});

            yValues.add(entry);
        }
        BarDataSet set;
        set = new BarDataSet(yValues, "");
        set.setDrawValues(false);
        set.setColors(ColorTemplate.JOYFUL_COLORS);
//        set.setHighlightEnabled(false);
        BarData data = new BarData(set);
        data.setValueFormatter(new ValueFormatter());
        mConsumptionChart.setData(data);
        mConsumptionChart.animateY(1000);
        mConsumptionChart.setFitBars(true);
        mConsumptionChart.invalidate();

    }

    private String readJSONFromAsset(String nameFile) {
        String json = null;
        try {
            InputStream is = getAssets().open(nameFile);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
