package com.example.cpzhoucheng.monashfriendfinder;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by CPZHOUCHENG on 6/5/17.
 */

public class FragmentFriends extends Fragment implements View.OnClickListener {






    View vfriendFragment;
    ListView unitList;




    Button buttonsearch;
    Button buttonadd;
    Button buttonmap;
    TextView textview;
    AlertDialog.Builder alertdialogbuilder;
    private static String withcomma ="";
    private static  String removedcomm = "";



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

        vfriendFragment = inflater.inflate(R.layout.fragment_search, container, false);
        unitList = (ListView)vfriendFragment.findViewById(R.id.listviewinsearch);



        //ListView unitList = (ListView) vSearchFragment.findViewById(R.id.lv_listview);
        buttonsearch = (Button)vfriendFragment.findViewById(R.id.bt_search);
        buttonadd = (Button)vfriendFragment.findViewById(R.id.bt_addf);
        buttonmap = (Button)vfriendFragment.findViewById(R.id.bt_map);
        textview = (TextView)vfriendFragment.findViewById(R.id.txt_selected);

        buttonsearch.setOnClickListener(this);
        buttonadd.setOnClickListener(this);
        buttonmap.setOnClickListener(this);
        return vfriendFragment;
    }


    public void onClick(View v)
    {
        //ListView unitList = (ListView) vSearchFragment.findViewById(R.id.listviewinsearch);




        switch (v.getId())
        {
            case R.id.bt_search:
                //}
                //}
                //
                //buttonsearch.setOnClickListener(new View.OnClickListener() {
                //   @Override
                // public void onClick(View v) {













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


                        removedcomm = withcomma.substring(0, withcomma.length() - 1).trim();
                        textview.setText(removedcomm);









                        new AsyncTask<Void, Void, List<StudentM>>()
                        {
                            protected List<StudentM> doInBackground(Void... params)
                            {List<StudentM> result = RestClient.findStdByAnyAttributes(removedcomm);
                                int size = result.size();
                                return result;}

                            //initialize the detail list
                            //ListView matchedStudentDetailView = (ListView) vreport.findViewById(R.id.listview_studentDetail);
                            //protected List<HashMap<String, String>> UnitListArray;
                            //int size2 = result.size();
                            //


                            protected void onPostExecute(List<StudentM> input)
                            {

                                //UnitListArray = new ArrayList<HashMap<String, String>>();
                                //HashMap<String,String> map = new HashMap<String,String>();
                                String[] colHEAD = new String[] {
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
                                int[] dataCell = new int[] {R.id.tv_ida,R.id.tv_fna,R.id.tv_lna,R.id.tv_gda,R.id.tv_csea,R.id.tv_spta,R.id.tv_uita,
                                        R.id.tv_nationa,R.id.tv_langa,R.id.tv_moda,R.id.tv_adda,R.id.tv_subrba,R.id.tv_sbdatea,R.id.tv_emaila,R.id.tv_doba,R.id.tv_mvea,};

                                int sizecell = dataCell.length;

                                //HashMap<String,String> map = new HashMap<String,String>();
                                // map.put("FirstName",size+"");
                                // map.put("LastName", "b");
                                // UnitListArray.add(map);
                                UnitListArray = new ArrayList<HashMap<String, String>>();
                                for (StudentM s : input)
                                {
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
                                    String testvalue = map.get("Favourite Movie");
                                    UnitListArray.add(map);  //not null
                                }
                                MyListAdapter = new SimpleAdapter(getActivity(),UnitListArray,R.layout.listview,colHEAD,dataCell);// null because of listview?
                                unitList.setAdapter(MyListAdapter); // null
                                int size3 = UnitListArray.size(); // error
                                size3=size3 +1;
                            }

                        }.execute();































                        //String withcomma = attributes;
                        //String removedcomma = withcomma.substring(0,withcomma.length()-1);
                        //textview.setText(removedcomma);
                    }







                });


                alertdialogbuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = alertdialogbuilder.create();

                dialog.show();







                ///////




                ////////////////////////////
                break;
            //////////////////////////////


            case R.id.bt_addf:


                break;


            case R.id.bt_map:
                break;



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



}