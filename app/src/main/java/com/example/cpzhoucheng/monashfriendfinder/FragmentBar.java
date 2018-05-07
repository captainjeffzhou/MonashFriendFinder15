package com.example.cpzhoucheng.monashfriendfinder;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
//import com.xxmassdeveloper.mpchartexample.notimportant.DemoBase;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by CPZHOUCHENG on 12/5/17.
 */

public class FragmentBar extends Fragment{



    View vFragmentBar;
    BarChart bchart ;
    ArrayList<BarEntry> BARENTRY ;
    ArrayList<String> BarEntryLabels ;
    BarDataSet Bardataset ;
    BarData BARDATA ;
    BarData BARDATA2 ;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState)
    {
        vFragmentBar = inflater.inflate(R.layout.barchartfile, container, false);



        bchart = (BarChart) vFragmentBar.findViewById(R.id.bar_chart);

        BARENTRY = new ArrayList<>();

        BarEntryLabels = new ArrayList<String>();
        ArrayList<String> labels = new ArrayList<String>();

        AddValuesToBARENTRY();

        //AddValuesToBarEntryLabels();
        BarEntryLabels.add("January");
        BarEntryLabels.add("February");
        BarEntryLabels.add("March");
        BarEntryLabels.add("April");
        BarEntryLabels.add("May");
        BarEntryLabels.add("June");

        Bardataset = new BarDataSet(BARENTRY, "Units");

        //BARDATA = new BarData(<BarEntryLabels>, Bardataset);
        BARDATA = new BarData(Bardataset,Bardataset);
        //BARDATA2 = new BarData(Bardataset,Bardataset);

        Bardataset.setColors(ColorTemplate.COLORFUL_COLORS);

        bchart.setData(BARDATA);
        //bchart.setData(BARDATA2);

        bchart.animateY(3000);

        return vFragmentBar;

    }

    public void AddValuesToBARENTRY(){

        BARENTRY.add(new BarEntry(2f, 0));
        BARENTRY.add(new BarEntry(4f, 1));
        BARENTRY.add(new BarEntry(6f, 20));
        BARENTRY.add(new BarEntry(8f, 3));
        BARENTRY.add(new BarEntry(1, 1));
        BARENTRY.add(new BarEntry(3f, 5));

    }

    public void AddValuesToBarEntryLabels() {

        BarEntryLabels.add("J");
        BarEntryLabels.add("q");
        BarEntryLabels.add("q");
        BarEntryLabels.add("q");
        BarEntryLabels.add("q");
        BarEntryLabels.add("q");
    }














}
