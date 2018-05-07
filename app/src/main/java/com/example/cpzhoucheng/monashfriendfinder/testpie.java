package com.example.cpzhoucheng.monashfriendfinder;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by one on 4/05/2017.
 */

public class testpie  extends AppCompatActivity {
    private PieChart mChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.piechartfiles);


        mChart = (PieChart) findViewById(R.id.spread_pie_chart);

        new AsyncTask<Void, Void, ArrayList<HashMap<String,String>> >() {

            @Override
            protected ArrayList<HashMap<String,String>>  doInBackground(Void... params) {
                return  RestClient.getUnitsAndFrequency();
            }
            @Override
            protected void onPostExecute(ArrayList<HashMap<String,String>>  input) {
                PieData mPieData = getPieData(input);
                showChart(mChart, mPieData);
            }
        }.execute();

    }

    private void showChart(PieChart pieChart, PieData pieData) {
        pieChart.setHoleColor(Color.TRANSPARENT);;
        pieChart.setHoleRadius(60f);
        pieChart.setTransparentCircleRadius(64f); // transparrent pie chart
        Description description = new Description();
        description.setText("Unit Frequency");
        pieChart.setDescription(description);

        pieChart.setDrawCenterText(true);  //pie middle can set tex

        pieChart.setDrawHoleEnabled(true);

        pieChart.setRotationAngle(90); //initial angle

        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true); // can be spinned

        // display percentage values
        pieChart.setUsePercentValues(true);  //show percentage

        pieChart.setCenterText("Unit Frequency");  //center text

        pieChart.setData(pieData);

        Legend mLegend = pieChart.getLegend();  //get legend

        mLegend.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);  //show under the chart

        mLegend.setXEntrySpace(7f);
        mLegend.setYEntrySpace(5f);
        pieChart.setUsePercentValues(true);//show in percentage
        pieChart.animateXY(1000, 1000);  //set animate

    }


    private PieData getPieData(ArrayList<HashMap<String,String>> units) {
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();


        for (HashMap<String,String> unit : units) {
            String counts = unit.get("count");
            String name = unit.get("unit");
            float count = Float.parseFloat(counts);
            entries.add(new PieEntry(count,name));
        }



        PieDataSet pieDataSet = new PieDataSet(entries, "Unit Frequency");
        pieDataSet.setSliceSpace(0f); //distance between each part
        ArrayList<Integer> colors = new ArrayList<Integer>();


        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        pieDataSet.setColors(colors);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = 5 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px); // extra length when item is choosen

        PieData pieData = new PieData(pieDataSet);

        return pieData;
    }



}