package com.example.cpzhoucheng.monashfriendfinder;

/**
 * Created by CPZHOUCHENG on 8/5/17.
 */

public class LocationMaker {
    private String locationName;


    public LocationMaker() {
    }

    public String whereIs(double lati, double longi) {

        if (-37.877151 - 0.014 < lati && lati < -37.877151 + 0.013 || 145.045725 - 0.02 < longi && longi < 145.045725 + 0.02) {
            locationName = "Caulfield Campus";
        }

        if (-37.885273 - 0.014 < lati && lati < -37.885273 + 0.013 || 145.091086 - 0.02 < longi && longi < 145.091086 + 0.02) {
            locationName = "Chadstone Shopping Centre";
        }

        if (-37.911392 - 0.014 < lati && lati < -37.911392 + 0.013 || 145.130358 - 0.02 < longi && longi < 145.130358 + 0.02) {
            locationName = "Clayton Campus";
        }

        if (-37.803471 - 0.014 < lati && lati < -37.803471 + 0.013 || 144.970787 - 0.02 < longi && longi < 144.970787 + 0.02) {
            locationName = "Melbourne Museum";
        }

        if (-37.926332 - 0.014 < lati && lati < -37.926332 + 0.013 || 144.989569 - 0.02 < longi && longi < 144.989569 + 0.02) {
            locationName = "Brighton Beach";
        }

        if (-37.888180 - 0.014 < lati && lati < -37.888180 + 0.013 || 145.164591 - 0.02 < longi && longi < 145.164591 + 0.02) {
            locationName = "Glen Waverley";
        }

        if (-37.911392 - 0.014 < lati && lati < -37.911392 + 0.013 || 145.130358 - 0.02 < longi && longi < 145.130358 + 0.02) {
            locationName = "Clayton Campus";
        }

        return locationName;
    }

}








