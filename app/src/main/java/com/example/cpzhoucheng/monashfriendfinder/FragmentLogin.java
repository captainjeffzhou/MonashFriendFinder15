package com.example.cpzhoucheng.monashfriendfinder;
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
 * Created by CPZHOUCHENG on 6/5/17.
 */

public class FragmentLogin extends Fragment implements View.OnClickListener {

    private static String email = "";
    private static String status = "off";
    View vLoginFragment;
    private EditText etemail;
    private  EditText etpassword;
    private static String name = "";
    private static int sid = 0;
    private String passwordInDB = "";

    private Button bLogin, bSkipLogin, bSubscription;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState)
    {

        // whole view
        vLoginFragment = inflater.inflate(R.layout.fragment_login, container, false);

        // 3 buttons
        bLogin = (Button) vLoginFragment.findViewById(R.id.bt_login);
        bSkipLogin = (Button) vLoginFragment.findViewById(R.id.bt_skiplogin);
        bSubscription  = (Button) vLoginFragment.findViewById(R.id.bt_registration);

        bLogin.setOnClickListener(this);
        bSubscription.setOnClickListener(this);
        bSkipLogin.setOnClickListener(this);

        // two inputs
        etemail = (EditText) vLoginFragment.findViewById(R.id.et_email);
        etpassword = (EditText) vLoginFragment.findViewById(R.id.et_password);

        return vLoginFragment;
    }

    public void onClick(View v)
    {
        // Gather user input
        email = etemail.getText().toString();
         final String passwordInput = etpassword.getText().toString();




        switch (v.getId())
        {
            case R.id.bt_login:
                // Validate user input
                if (email.isEmpty()) {
                    etemail.setError("Unit Code is required!");
                    return;
                }
                ///if (!email.contains("@student.monash.edu")){
                 ///   etemail.setError("Email format is wrong");
                 ///   return;
                ///}
                if (passwordInput.isEmpty()) {
                    etpassword.setError("Unit Name is required!");
                    return;
                }






                //create an anonymous AsyncTask // one param//////////////////////
                new AsyncTask<Void, Void, String>()
                {
                    @Override
                    protected String doInBackground(Void... params)
                    {  String aStudent = RestClient.findStdByEmail(email);
                       String[] sA = aStudent.split(",");

                        for (String s : sA)
                        {
                            if (s.contains("firstname"))
                           {
                                String sB = s.substring(13, s.length() - 1);
                                name = sB;

                                if (s.contains("password"))
                                {
                                    String sC = s.substring(12, s.length() - 1);
                                    //hashPassword
                                    //passwordInput
                                    if (passwordInput == sC)
                                    {
                                        status = "on";
                                        return "verified";
                                    }
                                }
                            }

                            //xin jia de
                            if (s.contains("sid"))
                            {
                                String sD = s.substring(6, s.length());
                                sid = Integer.valueOf(sD);
                            }

                        }
                        return "";
                    }
                    @Override
                    protected void onPostExecute(String response)
                    {
                        if (!name.isEmpty())
                        {FragmentManager fragmentManagerB = getFragmentManager();
                        fragmentManagerB.beginTransaction().replace(R.id.content_frame,
                                new FragmentMain()).commit();
                        }
                    }
                }.execute();
                break;

            //=======================















            case R.id.bt_registration:
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame,
                        new FragmentSubscription()).commit(); break;

            case R.id.bt_skiplogin:

                if (!name.isEmpty())
                {FragmentManager fragmentManagerB = getFragmentManager();
                    fragmentManagerB.beginTransaction().replace(R.id.content_frame,
                            new FragmentMain()).commit();
                }
        }


        status = "on";
    }

    ////////////Hash password
    public static String hashPassword( final char[] password, final byte[] salt, final int iterations, final int keyLength )
    {

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
            PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
            SecretKey key = skf.generateSecret( spec );
            byte[] res = key.getEncoded( );
            String resString = res.toString();
            return resString;

        } catch( NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException( e );
        }
    }

    public static String getName()
    {return name;}

    public static String getStatus()
    {return status;}

    public static int getStudentId()
    {return sid;}

    public static String getStudentEmail()
    {return email;}

}
///////
