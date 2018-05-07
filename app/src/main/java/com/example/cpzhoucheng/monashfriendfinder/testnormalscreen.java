package com.example.cpzhoucheng.monashfriendfinder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;



/**
 * Created by CPZHOUCHENG on 15/5/17.
 */

public class testnormalscreen extends AppCompatActivity{


    private TextView tvnormal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testnormalscreen);
        //setContentView(R.layout.activity_main);
        tvnormal = (TextView) findViewById(R.id.tvintestnormal);


        tvnormal.setText(" a test");
        }







}
