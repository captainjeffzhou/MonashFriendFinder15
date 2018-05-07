package com.example.cpzhoucheng.monashfriendfinder;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.cpzhoucheng.monashfriendfinder.R.id.textView;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.mapquest.mapping.maps.OnMapReadyCallback;
import com.mapquest.mapping.maps.MapboxMap;
import com.mapquest.mapping.maps.MapView;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
/**
 * Created by CPZHOUCHENG on 6/5/17.
 */

public class FragmentMain extends Fragment {
    View vMainFragment;
    TextView tvdatetime,tvname,tvweather;
    private static String mail;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState)
    {
        vMainFragment = inflater.inflate(R.layout.fragment_main, container, false);

        tvdatetime = (TextView) vMainFragment.findViewById(R.id.tv_date);//
        String currentDateTime = DateFormat.getDateTimeInstance().format(new Date());
        tvdatetime.setText(currentDateTime);
        tvname = (TextView) vMainFragment.findViewById(R.id.tv_studname);//
        tvname.setText(FragmentLogin.getName());
        tvweather = (TextView) vMainFragment.findViewById(R.id.tv_weather);
        mail = FragmentLogin.getStudentEmail();

       // Map m =  RestClient.getWeathers("-37.813713","144.931935");
        //String aaa = RestClient.getWeathersss("-37.813713","144.931935").substring(0,8);
        //String aaa = RestClient.gett().substring(0,2);
        //Map aaaaa =  RestClient.getWeatherff("-37.813713","144.931935");
       /// String c = RestClient.getWeathergg("-37.813713","144.931935");
       // String bbb = aaaaa.get("temperature").toString();

        //String ddd = RestClient.findPositionByEmail("adz170@student.monash.edu");

        //tvweather.setText(ddd);




        //----new AsyncTask<Void, Void, String>()
        //----{
        //----   protected String doInBackground(Void... params) {

        //----     String position = RestClient.findPositionByEmail(mail);//------------------------ error

        //----     return position;
        //----  }





        new AsyncTask<Void, Void, Map<String,String>>()//works!
        {
            //Map<String,String> result = new Map<String, String>();

            protected Map<String,String> doInBackground(Void... params) {
                Map<String,String> position = RestClient.findPositionByEmail("adz170@student.monash.edu");
                int size = position.size();
                return position;
            }

            //initialize the detail list
            //ListView matchedStudentDetailView = (ListView) vreport.findViewById(R.id.listview_studentDetail);
            //protected List<HashMap<String, String>> UnitListArray;
            //int size2 = result.size();
            //


            protected void onPostExecute(Map<String,String> input)   //works!
            {

                String a = input.get("latitude");
                String b =input.get("longitude");

                String c= a+","+b;

                SharedPreferences sp = getActivity().getSharedPreferences("position", Context.MODE_PRIVATE);
                //String sStudent = sp.getString("student", null);
                SharedPreferences.Editor spE = sp.edit();
                spE.clear();
                //String sStudent = sp.getString("student", null);
                spE.putString("position", c);
                spE.apply();
                //tvweather.setText(a);

            }


//
        }.execute();








        new AsyncTask<Void, Void, Map<String,String>>()// works
        {
            //Map<String,String> result = new Map<String, String>();


            protected Map<String,String> doInBackground(Void... params)
            {
                SharedPreferences sp = getActivity().getSharedPreferences("position",
                        Context.MODE_PRIVATE);

                String pos = sp.getString("position", "");
                // 时间地位顺序可能颠倒
                Map<String,String> mapp = RestClient.getWeathers(pos);

                return mapp;
            }

            protected void onPostExecute(Map<String,String> input)   //works!
            {

                String a = input.get("summary");
                String b =input.get("temperature");
                //int c = Int
                //int formalTemp = (value - 32)*5/9;
                float f = Float.parseFloat(b);
                float formalTemp = (f - 32)*5/9;

                String c= a+" ---  "+formalTemp+"   。C";
                tvweather.setText(c);


            }


//
        }.execute();


        //----  protected void onPostExecute(String input)
        //----  {


        //----String[] posi = input.split(",");


        //----Map m =  RestClient.getWeathers("-37.813713","144.931935");
        //---- String l = m.get("icon").toString();
        //---- String lo = m.get("temperature").toString();
        //----  String lastP = l + " " + lo;
        //---- tvweather.setText(lastP);



        //final TextView studnameTextView = (TextView) findViewById(R.id.tv_studname);
       // new AsyncTask<Void, Void, String>() {
        //    @Override
        //    protected String doInBackground(Void... params) { return RestClient.findAllCourses();
        //    }
        //    @Override
        //    protected void onPostExecute(String courses) { studnameTextView.setText(courses);
       //     } }.execute();
    //}
        //----    }

    //----}.execute();


        return vMainFragment;
    }
    //String currentDateTime = DateFormat.getDateTimeInstance().format(new Date());

// textView is the TextView view that should display it







}







