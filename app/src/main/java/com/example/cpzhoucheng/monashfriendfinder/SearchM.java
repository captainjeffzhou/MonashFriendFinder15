package com.example.cpzhoucheng.monashfriendfinder;


import android.app.Fragment;
import android.view.MenuItem;
import android.view.View;
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
import java.security.NoSuchAlgorithmException;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import java.security.MessageDigest;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import android.view.Menu;
import android.view.MenuItem;
/**
 * Created by CPZHOUCHENG on 10/5/17.
 */




public class SearchM extends Fragment implements View.OnClickListener{


    View vSearchM;
    protected SimpleAdapter MyListAdapter;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            View vSearchM = inflater.inflate(R.layout.fragment_search, container, false);


            return vSearchM;
        }





    public void onClick(View v)
    {
        ListView unitList = (ListView) vSearchM.findViewById(R.id.uselistview);
        switch (v.getId()) {
            case R.id.bt_search:








                new AsyncTask<Void, Void, List<StudentM>>()
                {


                    @Override
                    protected List<StudentM> doInBackground(Void... params)
                    {


                        List<HashMap<String, String>> UnitListArray=new ArrayList<HashMap<String, String>>();
                        HashMap<String, String> map = new HashMap<String, String>();

                String atts = FragmentSearch.removedcomma();
                        List<StudentM> result = RestClient.findStdByAnyAttributes(atts);;

                        return result;
                }


                protected void onPostExecute(List<StudentM> input) {


                    String[] colHEAD = new String[] {
                            "First Name",
                            "Last Name",
                            "Gender",
                            "Course",
                            "Favourite sport",
                            "Favourite unit",
                            "Nationality",
                            "Native language",
                            "Study mode",
                            "Suburb",
                            "Current job",
                            "Monash email",
                            "Favourite movie"};

                    int[] dataCell = new int[]{R.id.tv_ida, R.id.tv_fna, R.id.tv_lna, R.id.tv_gda, R.id.tv_csea, R.id.tv_spta, R.id.tv_uita,
                            R.id.tv_nationa, R.id.tv_langa, R.id.tv_moda, R.id.tv_adda, R.id.tv_subrba, R.id.tv_sbdatea, R.id.tv_emaila, R.id.tv_doba, R.id.tv_mvea,};




                    ListView unitList = (ListView) vSearchM.findViewById(R.id.uselistview);
                    MyListAdapter = new SimpleAdapter(getActivity(),new ArrayList<HashMap<String, String>>(),R.layout.listview,colHEAD,dataCell);
                    unitList.setAdapter(MyListAdapter);

                    List<HashMap<String, String>> UnitListArray=new ArrayList<HashMap<String, String>>();

                    for (StudentM s : input){
                        HashMap<String,String> map = new HashMap<String,String>();
                        map.put("First Name",s.getFirstname()+"");
                        map.put("Last Name", s.getLastname());
                        map.put("Gender", s.getGender());
                        map.put("Course", s.getCourse());
                        map.put("Favourite sport", s.getSport());
                        map.put("Favourite unit", s.getUnit());
                        map.put("Nationality", s.getNationality());
                        map.put("Native language", s.getLanguage());
                        map.put("Study mode", s.getStuMode());
                        map.put("Suburb", s.getSurburb());
                        map.put("Current job", s.getJob());
                        map.put("Monash email", s.getEmail());
                        map.put("Favourite movie", s.getMovie());




                        UnitListArray.add(map);
                    }

            }
        }.execute();
                break;
                ///////




                ////////////////////////////

            //////////////////////////////


            case R.id.bt_addf:
                break;


            case R.id.bt_map:
                break;



        }
    }

















}
