package com.example.cpzhoucheng.monashfriendfinder;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
//import android.icu.util.Calendar;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by CPZHOUCHENG on 6/5/17.
 */

public class FragmentSubscription extends Fragment implements View.OnClickListener {

    private View vSubscription;
    private EditText etfirstname, etlastname, etaddress, etstudid, etpsdA, etpsdB, etmovie, etemail, etjob;
    private RadioButton genderA, genderB;
    private Spinner spcourse, spsport, spunit, spnation, splanguage, spstudymode;
    private Button btsubmit;
    //private TextView datepicked;

    private String currentDate;
    private String inputString;
    //tvdatetime.setText(currentDateTime);

    private static final String TAG = "FragmentSubscription";
    private TextView displayDate;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        super.onCreate(savedInstanceState);

        vSubscription = inflater.inflate(R.layout.fragment_subscription, container, false);

        etfirstname = (EditText) vSubscription.findViewById(R.id.et_fname);
        etlastname = (EditText) vSubscription.findViewById(R.id.et_lname);
        etaddress = (EditText) vSubscription.findViewById(R.id.et_address);
        etstudid = (EditText) vSubscription.findViewById(R.id.et_sid);
        etpsdA = (EditText) vSubscription.findViewById(R.id.et_psd1);
        etpsdB = (EditText) vSubscription.findViewById(R.id.et_psd2);
        etmovie = (EditText) vSubscription.findViewById(R.id.et_favouriatemovie);
        etjob = (EditText) vSubscription.findViewById(R.id.et_currentjob);
        etemail = (EditText) vSubscription.findViewById(R.id.et_email);

        //currentDate = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        genderA = (RadioButton) vSubscription.findViewById(R.id.rb_male);
        genderB = (RadioButton) vSubscription.findViewById(R.id.rb_female);

        spcourse = (Spinner) vSubscription.findViewById(R.id.sp_course);
        spsport = (Spinner) vSubscription.findViewById(R.id.sp_favoursport);
        spunit = (Spinner) vSubscription.findViewById(R.id.sp_favourunit);
        spnation = (Spinner) vSubscription.findViewById(R.id.sp_nationality);
        splanguage = (Spinner) vSubscription.findViewById(R.id.sp_language);
        spstudymode = (Spinner) vSubscription.findViewById(R.id.sp_studymode);

        //datepicked = (TextView) vSubscription.findViewById(R.id.sp_studymode);

        displayDate = (TextView) vSubscription.findViewById(R.id.tv_date);
        displayDate.setOnClickListener(this);

        btsubmit = (Button) vSubscription.findViewById(R.id.button_submit);
        btsubmit.setOnClickListener(this);



        return vSubscription;
    }//----------------------------onCreatView


    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.tv_date:
                Calendar cal = Calendar.getInstance();// there are two libraries, maybe i imported wrong
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                //Intent myIntent = new Intent(this, FragmentSubscription.class);
                //startActivity(myIntent);

                DatePickerDialog dlog = new DatePickerDialog(getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener, year, month, day
                );

                dlog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dlog.show();

                dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datepicker, int year, int month, int day) {
                        month = month + 1;
                        Log.d(TAG, "onDateSet: mm/dd/yyyy: " + month + "/" + year);
                        String date = month + "/" + day + "/" + year;
                        displayDate.setText(date);
                    }

                };
                break;

            case R.id.button_submit:
                String fname = etfirstname.getText().toString();
                String lname = etlastname.getText().toString();
                String address = etaddress.getText().toString();
                String studid = etstudid.getText().toString();
                String passwordA = etpsdA.getText().toString();
                String passwordB = etpsdB.getText().toString();
                String movie = etmovie.getText().toString();
                String job = etjob.getText().toString();
                String email = etemail.getText().toString();
                currentDate = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                String datePicked = displayDate.getText().toString();

                String course = spcourse.getSelectedItem().toString();
                String sport = spsport.getSelectedItem().toString();
                String unit = spunit.getSelectedItem().toString();
                String nationality = spnation.getSelectedItem().toString();
                String language = splanguage.getSelectedItem().toString();
                String studymode = spstudymode.getSelectedItem().toString();

                String gender = "";
                if (genderA.isChecked()) {
                    gender = "Male";
                } else if (genderB.isChecked()) {
                    gender = "Sem 2";
                }




                ////A test
                ///etfirstname.setText(inputString);




                // Validate user input
                if (email.isEmpty()) {
                    etemail.setError("Unit Code is required!");
                    return;
                }
                if (fname.isEmpty()) {
                    etfirstname.setError("Name cannot be empty!");
                    return;
                }
                if (lname.isEmpty()) {
                    etlastname.setError("Name cannot be empty!");
                    return;
                }
                if (studid.isEmpty()) {
                    etstudid.setError("Student ID cannot be empty!");
                    return;
                }
                if (address.isEmpty()) {
                    etaddress.setError("Address cannot be empty!");
                    return;
                }
                //if (!email.contains("@student.monash.edu")) {
                 //   etemail.setError("Email format is wrong");
                 //   return;
               // }
                if (passwordA.isEmpty() || passwordB.isEmpty() || !passwordA.equals(passwordB)) {
                    etpsdA.setError("Passwords should not be empty, two passwords must match!");
                    return;
                }



                // change format of "datePicked"
                // /
                //
                String[] dateSet = datePicked.split("/");
                String monthInSet = dateSet[0];
                String monthInSetA;
                if(monthInSet.length()==1)
                {monthInSetA ="0"+ monthInSet;}
                else
                    {monthInSetA =monthInSet;}
                String dayInSet = dateSet[1];
                String dayInSetA;
                if(dayInSet.length()==1)
                {dayInSetA ="0"+ monthInSet;}
                else{dayInSetA =monthInSet;}
                String yearInSet = dateSet[2];
                String finalDobString =yearInSet+monthInSetA+dayInSetA;
                //int finalDob = Integer.valueOf(finalDobString);

                //// change format of "currentDate"
                String[] dateSetB = currentDate.split("\\.");
                String yearB= dateSetB[0];
                String monthB = dateSetB[1];
                String dayB= dateSetB[2];
                String dateB = yearB + monthB + dayB;
                //int finalSubsDate = Integer.valueOf(dateB);


               inputString = address+ "," +course + "," + job + "," + finalDobString+ "," +movie+ "," +sport+ "," +
                        unit+ "," + fname+ "," + gender+ "," +lname+ "," +email+ "," +nationality+ "," +language+ "," +
                        passwordB+ "," +studid+ "," +studymode+ "," +dateB;


                //inputString = "aaaaaa";

                etfirstname.setText(inputString);
                //break;


               // Student student = new Student(inputString);
               // RestClient.createStudent(student);//###################
               // etlastname.setText("aaaaaa");
                //break;
               // FragmentManager fragmentManagerB = getFragmentManager();
               // fragmentManagerB.beginTransaction().replace(R.id.content_frame,
               //         new FragmentLogin()).commit();


                new AsyncTask<String, Void, String>() {

                    @Override
                    protected String doInBackground(String... params) {
                        //inputString
                        Student student = new Student(inputString);
                        RestClient.createStudent(student);
                        return "Student was added";
                        }
//
                    @Override
                    protected void onPostExecute(String response) {
                        {
                            //etfirstname.setText(inputString);
                            FragmentManager fragmentManagerB = getFragmentManager();
                            fragmentManagerB.beginTransaction().replace(R.id.content_frame,
                                   new FragmentLogin()).commit();
                        }
                    }
                }.execute();break;   //.execute(courseId.getText().toString(), courseTitle.getText().toString());
        }

        //dateSetListener = new DatePickerDialog.OnDateSetListener() {
         //   @Override
         //   public void onDateSet(DatePicker datepicker, int year, int month, int day) {
          //      month = month + 1;
          //      Log.d(TAG, "onDateSet: mm/dd/yyyy: " + month + "/" + year);
          //      String date = month + "/" + day + "/" + year;
          //      displayDate.setText(date);
          //  }


        }

}



