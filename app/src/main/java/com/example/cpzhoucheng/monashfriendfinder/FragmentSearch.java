package com.example.cpzhoucheng.monashfriendfinder;

import android.app.Fragment;

/**
 * Created by CPZHOUCHENG on 6/5/17.
 */


import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.support.constraint.R.id.parent;

//import static com.example.cpzhoucheng.monashfriendfinder.R.id.parent;


public class FragmentSearch extends Fragment implements View.OnClickListener{






    View vSearchFragment;
    ListView unitList;




    Button buttonsearch;
    Button buttonadd;
    Button buttonmovie;
    Button buttonmap;
    TextView textview;
    AlertDialog.Builder alertdialogbuilder;
    private static String withcomma ="";
    private static  String removedcomm = "";
    private static String haveResult = "";
    private static  int listCount;
    private static  String selectedItem = "";




    //List<HashMap<String, String>> UnitListArray=new ArrayList<HashMap<String, String>>();
    HashMap<String, String> map = new HashMap<String, String>();


    String[] AlertDialogItems = new String[]{
            "gender",
            "course",
            "favouriteunit",
            "favouritesport",
            "studymode",
            "currentjob",
            "suburb",
            "nationality",
            "nativelanguage",
            "monashemail",
            "favouritemovie"
    };

    List<String> ItemsIntoList;

    boolean[] Selectedtruefalse = new boolean[]{
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false
    };






    protected List<HashMap<String, String>> UnitListArray;
    protected SimpleAdapter MyListAdapter;











    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vSearchFragment = inflater.inflate(R.layout.fragment_search, container, false);
        unitList = (ListView)vSearchFragment.findViewById(R.id.listviewinsearch);



        //ListView unitList = (ListView) vSearchFragment.findViewById(R.id.lv_listview);
        buttonsearch = (Button)vSearchFragment.findViewById(R.id.bt_search);
        buttonadd = (Button)vSearchFragment.findViewById(R.id.bt_addf);
        buttonmap = (Button)vSearchFragment.findViewById(R.id.bt_matchingmap);
        buttonmovie = (Button)vSearchFragment.findViewById(R.id.bt_moviedetail);
        textview = (TextView)vSearchFragment.findViewById(R.id.txt_selected);

        buttonsearch.setOnClickListener(this);
        buttonadd.setOnClickListener(this);
        buttonmap.setOnClickListener(this);
        buttonmovie.setOnClickListener(this);

        return vSearchFragment;
    }














////////////////

    public void onClick(View v)
    {
        //ListView unitList = (ListView) vSearchFragment.findViewById(R.id.listviewinsearch);




        switch (v.getId())
        {
            case R.id.bt_matchingmap:
            {
                if (selectedItem.length() == 0)
                {Toast.makeText(getActivity().getApplicationContext(),"No student selected", Toast.LENGTH_LONG).show();
                    Intent  intent = new Intent(getActivity(), testpie.class);
                    startActivity(intent);
                    break;}
                else {

                    //PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString("student", selectedItem).commit();
                    SharedPreferences sp = getActivity().getSharedPreferences("student", Context.MODE_PRIVATE);
                    //String sStudent = sp.getString("student", null);
                    SharedPreferences.Editor spE = sp.edit();
                    spE.clear();
                    //String sStudent = sp.getString("student", null);
                     spE.putString("student", selectedItem);
                    spE.apply();

                    String aaselectedItem = sp.getString("student", "");

                    if (aaselectedItem != null)
                    {
                        textview.setText("aaselectedItem: not null"+aaselectedItem+",");
                    }

                    if (aaselectedItem == null)
                    {
                        textview.setText("aaselectedItem: null"+aaselectedItem+",");
                        //selectedItem="";
                    //FragmentManager fragmentManagerB = getFragmentManager();
                    //fragmentManagerB.beginTransaction().replace(R.id.content_frame,
                     //       new FragmentMovie()).commit();
                                                            }}}

                break;











            case R.id.bt_search:
                {



                textview.setText("");

                alertdialogbuilder = new AlertDialog.Builder(getActivity());

                ItemsIntoList = Arrays.asList(AlertDialogItems);

                alertdialogbuilder.setMultiChoiceItems(AlertDialogItems, Selectedtruefalse, new DialogInterface.OnMultiChoiceClickListener() {


                    //
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                    }
                });

                alertdialogbuilder.setCancelable(false);

                alertdialogbuilder.setTitle("Select Subjects Here");

                alertdialogbuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        int a = 0;
                        while (a < Selectedtruefalse.length) {
                            boolean value = Selectedtruefalse[a];

                            if (value) {

                                String abc = (textview.getText() + ItemsIntoList.get(a) + ",");
                                withcomma = (abc + ItemsIntoList.get(a) + ",").toString();


                            }

                            a++;
                        }

                        if (withcomma.length() > 0) {
                            removedcomm = withcomma.substring(0, withcomma.length() - 1).trim();
                            //textview.setText(removedcomm);

                        }
/////----


                        new AsyncTask<Void, Void, List<StudentM>>() {
                            protected List<StudentM> doInBackground(Void... params) {
                                List<StudentM> result = RestClient.findStdByAnyAttributes(removedcomm);
                                int size = result.size();
                                return result;
                            }

                            //initialize the detail list
                            //ListView matchedStudentDetailView = (ListView) vreport.findViewById(R.id.listview_studentDetail);
                            //protected List<HashMap<String, String>> UnitListArray;
                            //int size2 = result.size();
                            //


                            protected void onPostExecute(List<StudentM> input) {

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

                                int sizeinput = input.size();
                                int[] dataCell = new int[]{R.id.tv_ida, R.id.tv_fna, R.id.tv_lna, R.id.tv_gda, R.id.tv_csea, R.id.tv_spta, R.id.tv_uita,
                                        R.id.tv_nationa, R.id.tv_langa, R.id.tv_moda, R.id.tv_adda, R.id.tv_subrba, R.id.tv_sbdatea, R.id.tv_emaila, R.id.tv_doba, R.id.tv_mvea,};

                                int sizecell = dataCell.length;

                                //HashMap<String,String> map = new HashMap<String,String>();
                                // map.put("FirstName",size+"");
                                // map.put("LastName", "b");
                                // UnitListArray.add(map);
                                UnitListArray = new ArrayList<HashMap<String, String>>();
                                for (StudentM s : input) {
                                    HashMap<String, String> map = new HashMap<String, String>();
                                    //UnitListArray = new ArrayList<HashMap<String, String>>();
                                    map.put("ID", s.getStuId() + "");
                                    map.put("First Name", s.getFirstname());
                                    map.put("Last Name", s.getLastname());
                                    map.put("Gender", s.getGender());
                                    map.put("Course", s.getCourse());
                                    map.put("Favourite Sport", s.getSport());
                                    map.put("Favourite Unit", s.getUnit());
                                    map.put("Nationality", s.getNationality());
                                    map.put("Native Language", s.getLanguage());
                                    map.put("Study Mode", s.getStuMode());
                                    map.put("Address", s.getAddress());
                                    map.put("Suburb", s.getSurburb());
                                    map.put("Subscription Date", s.getSubDate() + "");
                                    map.put("Monash Email", s.getEmail());
                                    map.put("Date of Birth", s.getDob() + "");
                                    map.put("Favourite Movie", s.getMovie());
                                    String testvalue = map.get("ID");
                                    String testvalue2 = map.get("First Name");
                                    UnitListArray.add(map);  //not null
                                }
                                MyListAdapter = new SimpleAdapter(getActivity(), UnitListArray, R.layout.listview, colHEAD, dataCell);// null because of listview?
                                //unitList.setAdapter(MyListAdapter); // null
                                int size3 = UnitListArray.size(); // error
                                size3 = size3 + 1;
                                // new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1, fruits_list);

                                String[] sett = new String[UnitListArray.size()];
                                int size = UnitListArray.size();
                                int k = 0;
                                for (int i = 0; i < size; i++) {
                                    sett[i] = UnitListArray.get(i).toString();
                                }
                                final List<String> sts_list = new ArrayList<String>(Arrays.asList(sett));
                                final ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>
                                        (getActivity(), android.R.layout.simple_list_item_1, sts_list);

                                unitList.setAdapter(arrayAdapter2);

                                unitList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        selectedItem = (String) parent.getItemAtPosition(position);//work
                                        textview.setText("AYour selected student is : " + selectedItem);
                                    }
                                });

                            }
//
                        }.execute();


                        ////////////////////


                      //  new AsyncTask<Void, Void, Map>() {
                       //         @Override
                       //     protected Map doInBackground(Void... params) {
                        //        Map aStudent = RestClient.getWeathers("-37", "124");


                         //       return aStudent;
                         //   }

                        //    @Override
                        //    protected void onPostExecute(Map response) {
                        //        Map ss = response;
                         //       // String sss = response.getString("icon");


                         //   }
                       // }.execute();


                        //after click OK

                    }


                });

                alertdialogbuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = alertdialogbuilder.create();

                dialog.show();


                unitList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        selectedItem = (String) parent.getItemAtPosition(position);//work
                        textview.setText("BYour selected student is : " + selectedItem);
                        //textview.setText(selectedItem.substring(0,6));
                        Toast.makeText(getActivity().getApplicationContext(), selectedItem, Toast.LENGTH_LONG).show();

                    }
                });


                ////////////////////////////

            }
            //////////////////////////////

            break;









            case R.id.bt_addf:
            {
                if (selectedItem.length() == 0)
                {Toast.makeText(getActivity().getApplicationContext(),"No student selected", Toast.LENGTH_LONG).show();
                  Intent  intent = new Intent(getActivity(), testpie.class);


                    break;}


                else{
                    FragmentManager fragmentManagerB = getFragmentManager();
                    fragmentManagerB.beginTransaction().replace(R.id.content_frame,
                            new FragmentMovie()).commit();}

                break;
            }








            case R.id.bt_moviedetail:
            {
           //--     if (selectedItem.length() == 0)
                //--    {Toast.makeText(getActivity().getApplicationContext(),"No student selected", Toast.LENGTH_LONG).show();
                //--         break;}
                //--     else {

                    //PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString("student", selectedItem).commit();
                    SharedPreferences sp = getActivity().getSharedPreferences("student", Context.MODE_PRIVATE);
                    //String sStudent = sp.getString("student", null);
                    SharedPreferences.Editor spE = sp.edit();
                    spE.clear();
                    //String sStudent = sp.getString("student", null);
                    spE.putString("student", selectedItem);
                    spE.apply();

                    String aaselectedItem = sp.getString("student", "");

                    if (aaselectedItem != null)
                    {
                        textview.setText("aaselectedItem: not null"+aaselectedItem+",");
                        selectedItem="";
                        FragmentManager fragmentManagerB = getFragmentManager();
                        fragmentManagerB.beginTransaction().replace(R.id.content_frame,
                                new FragmentMovie()).commit();
                    }

                    if (aaselectedItem == null)
                    {
                        textview.setText("aaselectedItem: null"+aaselectedItem+",");

                    }}
                break;
            //--  }






        }

        int sizew = withcomma.length();
        int sizer = removedcomm.length();


    }

    public static   String removedcomma()
    {

        int size1 = withcomma.length();
        int size2 = removedcomm.length();
        return removedcomm;
    }

    //return vSearchFragment


    public void OneItemClick(AdapterView<?> myAdapter, View myVieww, int position, long mylng) {

        String value = unitList.getItemAtPosition(position).toString();
        Toast.makeText(getActivity(), value, Toast.LENGTH_LONG).show();

    }
}
