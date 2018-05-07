package com.example.cpzhoucheng.monashfriendfinder;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.mapquest.mapping.maps.OnMapReadyCallback;
import com.mapquest.mapping.maps.MapboxMap;
import com.mapquest.mapping.maps.MapView;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;

import retrofit2.http.PUT;

/**
 * Created by CPZHOUCHENG on 6/5/17.
 */

public class FragmentMatchingMap extends Fragment {

    View mView;
    GoogleMap mGoogleMap;
    MapView mMapView;

    public  FragmentMatchingMap()
    {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    protected  View  onCreate(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_matchingmap, container, false);
        return mView;
        //View MapFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {super.onViewCreated(view, savedInstanceState);


        mMapView = (MapView) mView.findViewById(R.id.mapp);
        if (mMapView != null)
        {
            mMapView.onCreate(null);
            mMapView.onResume();
            //mMapView.getMapAsync(this);


        }


    }




    public  void onMapReady(GoogleMap googleMap){

        MapsInitializer.initialize(getActivity());
        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);



    }






}
