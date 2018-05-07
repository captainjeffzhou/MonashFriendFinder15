package com.example.cpzhoucheng.monashfriendfinder;

import android.app.Fragment;
import android.database.SQLException;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.support.v7.app.AppCompatActivity;
//import com.androidbuts.multispinnerfilter.KeyPairBoolData;
//import com.androidbuts.multispinnerfilter.MultiSpinnerSearch;
//import com.androidbuts.multispinnerfilter.SingleSpinner;
//import com.androidbuts.multispinnerfilter.SingleSpinnerSearch;
//import com.androidbuts.multispinnerfilter.SpinnerListener;

/**
 * Created by CPZHOUCHENG on 6/5/17.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FragmentReport extends Fragment {

    View vreport;
    ListView unitList;
    TextView tv;

    protected List<HashMap<String, String>> UnitListArray;
    protected SimpleAdapter MyListAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState)
    {

        vreport=inflater.inflate(R.layout.fragment_report, container, false);


        tv = (TextView)vreport.findViewById(R.id.txt_selected);
        new AsyncTask<Void, Void, List<StudentM>>()
        {
            protected List<StudentM> doInBackground(Void... params)
            {List<StudentM> result = RestClient.findStdByAnyAttributes("course,gender");
                int size = result.size();
                return result;}

            protected void onPostExecute(List<StudentM> input)
            {
                //initialize the detail list
                //ListView matchedStudentDetailView = (ListView) vreport.findViewById(R.id.listview_studentDetail);
                //protected List<HashMap<String, String>> UnitListArray;
                int size2 = input.size();
                //
                unitList = (ListView)vreport.findViewById(R.id.uselistview);
               //UnitListArray = new ArrayList<HashMap<String, String>>();
                //HashMap<String,String> map = new HashMap<String,String>();
                String[] colHEAD = new String[]{
                        "ID",
                        "First Name",
                        "Last Name",
                        "Gender",
                        "Course",
                        "Favourite Sport",
                        "Favourite Unit",
                        "Nationality",
                        "Native Language",
                        "Study Mode",
                        "Address",
                        "Suburb",
                        "Subscription Date",
                        "Monash Email",
                        "Date of Birth",
                        "Favourite Movie",
                };
                int[] dataCell = new int[]{R.id.tv_ida, R.id.tv_fna, R.id.tv_lna, R.id.tv_gda, R.id.tv_csea, R.id.tv_spta, R.id.tv_uita,
                        R.id.tv_nationa, R.id.tv_langa, R.id.tv_moda, R.id.tv_adda, R.id.tv_subrba, R.id.tv_sbdatea, R.id.tv_emaila, R.id.tv_doba, R.id.tv_mvea,};
//HashMap<String,String> map = new HashMap<String,String>();
                // map.put("FirstName",size+"");
                // map.put("LastName", "b");
                // UnitListArray.add(map);
                UnitListArray = new ArrayList<HashMap<String, String>>();
                for (StudentM s : input){
                    HashMap<String,String> map = new HashMap<String,String>();
                    //UnitListArray = new ArrayList<HashMap<String, String>>();
                    map.put("ID",s.getStuId()+"");
                    map.put("First Name",s.getFirstname());
                    map.put("Last Name",s.getLastname());
                    map.put("Gender",s.getGender());
                    map.put("Course",s.getCourse());
                    map.put("Favourite Sport",s.getSport());
                    map.put("Favourite Unit",s.getUnit());
                    map.put("Nationality",s.getNationality());
                    map.put("Native Language",s.getLanguage());
                    map.put("Study Mode",s.getStuMode());
                    map.put("Address",s.getAddress());
                    map.put("Suburb",s.getSurburb());
                    map.put("Subscription Date",s.getSubDate()+"");
                    map.put("Monash Email",s.getEmail());
                    map.put("Date of Birth",s.getDob()+"");
                    map.put("Favourite Movie",s.getMovie());
                    UnitListArray.add(map);
                }
                MyListAdapter = new SimpleAdapter(getActivity(),UnitListArray,R.layout.listview2,colHEAD,dataCell);
                    unitList.setAdapter(MyListAdapter);
                int size3 = UnitListArray.size();
                size3=size3 +1;




                unitList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selectedItem = (String) parent.getItemAtPosition(position);//work
                        tv.setText("Your selected fruit is : " + selectedItem);
                        tv.setText(selectedItem.substring(0,6));




                        //lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        //    @Override
                        //    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        //       String itemString=lv.getSelectedItem().toString();

                        //       Object item = lv.getItemAtPosition(position);
                        //      String myitem = item.toString();
                        //      tv.setText(itemString.substring(0,6));
                        //      tv.setText(myitem.substring(0,6));
//
                        //  }
                        // });
                    }
                });

            }

        }.execute();














    return vreport;
    }






}
