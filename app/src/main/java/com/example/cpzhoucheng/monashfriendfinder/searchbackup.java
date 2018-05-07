package com.example.cpzhoucheng.monashfriendfinder;

import android.app.Fragment;

/**
 * Created by CPZHOUCHENG on 6/5/17.
 */


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


final class FragmentSearchbackup extends Fragment implements View.OnClickListener{


    protected SimpleAdapter MyListAdapter;

    View vSearchFragment;

    Button buttonsearch;
    Button buttonadd;
    Button buttonmap;
    TextView textview;
    AlertDialog.Builder alertdialogbuilder;
    String withcomma ="";
    String removedcomma = "";



    List<HashMap<String, String>> UnitListArray=new ArrayList<HashMap<String, String>>();
    HashMap<String, String> map = new HashMap<String, String>();


    String[] AlertDialogItems = new String[]{
            "Gender",
            "Course",
            "Favourite unit",
            "Favourite sport",
            "Study mode",
            "Current job",
            "Suburb",
            "Nationality",
            "Native language",
            "Monash email",
            "Favourite movie"
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
    String[] colHEAD = new String[] {
            "First Name",
            "Last Name",
            "Gender",
            "Course",
            "Favourite sport",
            "Favourite unit",
            "Current job",
            "Nationality",
            "Native language",
            "Study mode",
            "Suburb",
            "Current job",
            "Favourite movie"};
















    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //vSearchFragment = inflater.inflate(R.layout.fragment_search, container, false);

        buttonsearch = (Button)vSearchFragment.findViewById(R.id.bt_search);
        buttonadd = (Button)vSearchFragment.findViewById(R.id.bt_addf);
        buttonmap = (Button)vSearchFragment.findViewById(R.id.bt_map);
        textview = (TextView)vSearchFragment.findViewById(R.id.txt_selected);

        buttonsearch.setOnClickListener(this);
        buttonadd.setOnClickListener(this);
        buttonmap.setOnClickListener(this);
        return vSearchFragment;
    }


    public void onClick(View v)
    {

    }

    //return vSearchFragment



}
