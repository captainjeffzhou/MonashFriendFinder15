package com.example.cpzhoucheng.monashfriendfinder;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.icu.util.ULocale;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.DisplayMetrics;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;


/**
 * Created by CPZHOUCHENG on 12/5/17.
 */


public class FragmentPie extends Fragment
{
    View vFragmentPie;
    private PieChart pChart;

    private float[] yData = { 5, 10, 3, 2, 8 };
    private String[] xData = { "FIT9132", "FIT5032", "FIT5036", "FIT9131", "FIT5171" };
    private static float[] yDatas;
    private static String[] xDatas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState)
    {
        vFragmentPie = inflater.inflate(R.layout.piechartfile, container, false);
        //return vFragmentPie;

        pChart = (PieChart) vFragmentPie.findViewById(R.id.pichartinner);
        // add pie chart to main layout
        //vFragmentPie.addView(pChart);
        vFragmentPie.setBackgroundColor(Color.parseColor("#55656C"));

        // configure pie chart
        pChart.setUsePercentValues(true);
        //==
        Description description = new Description();
        description.setText("Unit Frequency");
        pChart.setDescription(description);






        ///
        //pChart.setD















        // enable hole and configure
        pChart.setDrawHoleEnabled(true);
        pChart.setTransparentCircleRadius(64f);
        pChart.setHoleRadius(7);
        pChart.setTransparentCircleRadius(10);
        pChart.calculateOffsets();//me
        //pChart.getDataSetIndexForIndex(0);
        // pChart.isUsePercentValuesEnabled();
        //-pChart.getCenterText();//me

        // enable rotation of the chart by touch
        pChart.setRotationAngle(0);
        pChart.setRotationEnabled(true);

        // set a chart value selected listener
        //pChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

        //  //@Override
        // public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        //     // display msg when value selected
        //     if (e == null)
        //         return;

        //     // Toast.makeText(getActivity(),xData[e.getXIndex()] + " = " + e.getValue() + "%", Toast.LENGTH_SHORT).show();
        //  }

        //  @Override
        //  public void onNothingSelected() {

        //  }
        //});









        //-pChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {


        //-  @Override
        //-  public void onNothingSelected() {

        //-  }

        //-   //@Override
        //-   public void onValueSelected(Entry e, Highlight h) {
        //-       if (e == null)
        //-           return;
               // Toast.makeText(getActivity(),
                //        xData[(int)e.getX()] + " = " + e.getData() + "%", Toast.LENGTH_SHORT).show();

        //-   }


        //- });





////////////me, get data














        loaddata();
        // add data
        addData();

        // customize legends
        Legend l = pChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);
        return vFragmentPie;
    }




//    private void addData() {
//        ArrayList<PieEntry> yVals1 = new ArrayList<PieEntry>();

 //       for (int i = 0; i < yData.length; i++)
 //           yVals1.add(new PieEntry(yData[i], i));

//        ///
 //       ArrayList<String> xVals = new ArrayList<String>();

//        for (int i = 0; i < xData.length; i++)
 //           xVals.add(xData[i]);

 //       // create pie data set
//


//        //Description description = new Description();
//        //description.setText("Unit Frequency");
//        PieDataSet dataSet = new PieDataSet(yVals1, "All Favourite Units");
 //       dataSet.setSliceSpace(3);
 //       dataSet.setSelectionShift(5);






 //       // add many colors
 //       ArrayList<Integer> colors = new ArrayList<Integer>();

 //       for (int c : ColorTemplate.VORDIPLOM_COLORS)
//            colors.add(c);

 //       for (int c : ColorTemplate.JOYFUL_COLORS)
 //           colors.add(c);

//        for (int c : ColorTemplate.COLORFUL_COLORS)
 //           colors.add(c);

 //       for (int c : ColorTemplate.LIBERTY_COLORS)
 //           colors.add(c);

 //       for (int c : ColorTemplate.PASTEL_COLORS)
 //           colors.add(c);

 //       colors.add(ColorTemplate.getHoloBlue());
 //       dataSet.setColors(colors);






 //       // instantiate pie data object now
//        PieData data = new PieData(dataSet);
//        data.setValueFormatter(new PercentFormatter());
//        data.setValueTextSize(11f);
//        data.setValueTextColor(Color.GRAY);

//        pChart.setData(data);

        // undo all highlights
//        pChart.highlightValues(null);
//
        // update pie chart
 //       pChart.invalidate();
//    }




   private void loaddata()
   {


       //+ new AsyncTask<Void, Void, ArrayList<HashMap<String, String>>>()
       //+ {
       //+    @Override
       //+   protected ArrayList<HashMap<String, String>> doInBackground(Void... params)
       //+   {
              //- JSONArray jsonArray = RestClient.getUnitsAndFrequencys();


       //+   ArrayList<HashMap<String, String>> maps= RestClient.getUnitAndFrequencyF();


              //- return jsonArray;
               //-return maps;
       //+   yDatas = new float[maps.size()];
       //+    xDatas = new String[maps.size()];
       //+      return maps;

       //+    }

       //+   protected void onPostExecute(ArrayList<HashMap<String, String>> mapss)
//+
       //+   {

              // yDatas = new float[maps.size()];
              // xDatas = new String[maps.size()];

              //- int size = response.length();
       //+       int size = maps.size();

               //-  for (int i = 0; i < size; i++) {
               //-      try {
               //-          JSONObject object = response.getJSONObject(i);
               //-         yDatas[i] = Float.parseFloat(object.getString("frequency"));
               //-         xDatas[i] = object.getString("favouriteunit");
               //-     } catch (JSONException e) {
               //-        Log.e("MYAPP", "unexpected JSON exception", e);
               //-        break;
               //-    }
               //- }
       //+       for (int i = 0; i < size; i++) {
       //+            yDatas[i] = Float.parseFloat(maps.get(i).get("frequency"));
       //+           xDatas[i] = maps.get(i).get("favouriteunit");

       //+       }













               /// now set data



       //+ }  }.execute();//else

   }




    private void addData() {
        ArrayList<PieEntry> yVals1 = new ArrayList<PieEntry>();

        for (int i = 0; i < yData.length; i++)
            yVals1.add(new PieEntry(yData[i], i));

        ///
        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < xData.length; i++)
            xVals.add(xData[i]);

        // create pie data set



        //Description description = new Description();
        //description.setText("Unit Frequency");
        PieDataSet dataSet = new PieDataSet(yVals1, "All Favourite Units");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);






        // add many colors
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);






        // instantiate pie data object now
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.GRAY);

        pChart.setData(data);

        // undo all highlights
        pChart.highlightValues(null);

        // update pie chart
        pChart.invalidate();
    }











}










