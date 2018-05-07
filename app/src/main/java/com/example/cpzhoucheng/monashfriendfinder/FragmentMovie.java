package com.example.cpzhoucheng.monashfriendfinder;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
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
import java.util.HashMap;

/**
 * Created by CPZHOUCHENG on 13/5/17.
 */

public class FragmentMovie extends Fragment implements View.OnClickListener {

    View fragmentmovie;
    private    String selectedItem;
    TextView tvdetail;
    TextView tvactualDetail;
    TextView tvstudent;
    Button btadd;
    Button btback;
    ImageView imageView;
    String url ="";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        fragmentmovie = inflater.inflate(R.layout.movieinfolvfile, container, false);

        btadd = (Button) fragmentmovie.findViewById(R.id.bt_add);
        btback = (Button) fragmentmovie.findViewById(R.id.bt_back);

        btadd.setOnClickListener(this);
        btback.setOnClickListener(this);


        // two inputs
        tvdetail = (TextView) fragmentmovie.findViewById(R.id.tv_movieDetail);
        tvactualDetail = (TextView) fragmentmovie.findViewById(R.id.tv_actualDetail);
        tvstudent = (TextView) fragmentmovie.findViewById(R.id.tv_studetailinmovie);
        imageView = (ImageView) fragmentmovie.findViewById(R.id.imageView_movie);

        SharedPreferences sp = getActivity().getSharedPreferences("student",
                Context.MODE_PRIVATE);

        selectedItem = sp.getString("student","");
        if (selectedItem.length()==0)
        {tvstudent.setText("No student selected");}

        else
        {
            tvstudent.setText(selectedItem);
            new AsyncTask<Void, Void, HashMap<String,String>>()
            {
                @Override
                protected HashMap<String,String> doInBackground(Void... params)
                {
                    String movie = "";
                    String moviee = "";
                    String[] elements =selectedItem.split(",");
                    for (String e : elements)
                    {
                        if (e.contains("Favourite Movie"))
                        {movie = e.substring(16,e.length());
                        moviee=movie.trim();
                        }
                    }
                    HashMap<String,String> result = RestClient.getMovie(moviee);
                    return result;
                }

                protected void onPostExecute(HashMap<String,String> response)

                {
                    HashMap<String,String> result = response;

                    url = result.get("address");
                    String detail = result.get("detail");
                   // Bitmap bitmap = RestClient.getMoviePicture(url);
                   // imageView.setImageBitmap(bitmap);
                    tvactualDetail.setText(detail);


                }
            }.execute();






        new AsyncTask<Void, Void, Bitmap>()
        {
            @Override
            protected Bitmap doInBackground(Void... params)
            {



            Bitmap bitmap = RestClient.getBitmapFromURLl(url);

                return bitmap;
            }

            protected void onPostExecute(Bitmap response)

            {
                //Bitmap bitmapp = response;

                imageView.setImageBitmap(response);
                //tvactualDetail.setText(detail);


            }
        }.execute();//else
        }//else



        return fragmentmovie;
    }







    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.bt_back:


                Intent  intent = new Intent(getActivity(), testnormalscreen.class);
                startActivity(intent);



                //FragmentManager fragmentManagerB = getFragmentManager();
                //fragmentManagerB.beginTransaction().replace(R.id.content_frame,
                //        new FragmentSearch()).commit();
                break;



            case R.id.bt_addf:

                Intent  intentt = new Intent(getActivity(), testnormalscreen.class);
                startActivity(intentt);

                break;







        }







    }










}
