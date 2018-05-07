package com.example.cpzhoucheng.monashfriendfinder;

/**
 * Created by CPZHOUCHENG on 10/5/17.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by one on 20/04/2017.
 */

public class StudentM {





    private  String firstname ="";
    private  String lastname="";
    private  String address="";
    private  String favouritemovie="";
    private  String currentjob="";
    private  String monashemail="";
    private  String password="";
    //private  String passwordB;
    private  int dateofbirth=0;
    private  String gender="";
    private  String course="";
    private  String favouriteunit="";
    private  String favouritesport="";
    private  String nationality="";
    private  String nativelanguage="";
    private  String studymode="";
    private int subscriptiondate=0;
    private String suburb="";
    private int sid=0;





//    private String latitude;
//    private String longitude;

    public int getStuId() {
        return sid;
    }

    public void setStuId(int sid) {
        this.sid = sid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }



    public int getDob() {
        return dateofbirth;
    }

    public void setDob(int dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSurburb() {
        return suburb;
    }

    public void setSurburb(String surburb) {
        this.suburb = surburb;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getLanguage() {
        return nativelanguage;
    }

    public void setLanguage(String language) {
        this.nativelanguage = language;
    }

    public String getSport() {
        return favouritesport;
    }

    public void setSport(String sport) {
        this.favouritesport = sport;
    }

    public String getMovie() {
        return favouritemovie;
    }

    public void setMovie(String movie) {
        this.favouritemovie = movie;
    }

    public String getUnit() {
        return favouriteunit;
    }

    public void setUnit(String unit) {
        this.favouriteunit = unit;
    }

    public String getJob() {
        return currentjob;
    }

    public void setJob(String job) {
        this.currentjob = job;
    }

    public int getSubDate() {
        return subscriptiondate;
    }

    public void setSubDate(int subDate) {
        this.subscriptiondate = subDate;
    }


    public String getStuMode() {
        return studymode;
    }

    public void setStuMode(String stuMode) {
        this.studymode = stuMode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    //public static Creator<StudentM> getCREATOR() {
    // return CREATOR;
    //}

    public StudentM()
    {
    }
    public StudentM(String email,String password){
        this.monashemail = email;
        this.password = password;
    }
    //protected StudentM(Parcel in) {
    //   sid = in.readInt();;
    //   firstname = in.readString();
    //      lastname = in.readString();
    //     dateofbirth = in.readInt();
    ///       course = in.readString();
    //       address = in.readString();
    ///suburb = in.readString();
    //      nationality = in.readString();
    ////       nativelanguage = in.readString();
    ////       favouritesport = in.readString();
    //      favouritemovie = in.readString();
    //    favouriteunit = in.readString();
    //    currentjob = in.readString();
    //     monashemail = in.readString();
    //     password = in.readString();
    //       subscriptiondate = in.readInt();
    //       studymode = in.readString();
//        gender  = in.readString();
    //   }



    //@Override
    // public void writeToParcel(Parcel dest, int flags) {
    //     dest.writeInt(sid);
//        dest.writeString(firstname);
    //      dest.writeString(lastname);
    //    dest.writeInt(dateofbirth);
    //  dest.writeString(course);
//        dest.writeString(address);
    //      dest.writeString(suburb);
    //    dest.writeString(nationality);
    //  dest.writeString(nativelanguage);
    //dest.writeString(favouritesport);
    //       dest.writeString(favouritemovie);
    //     dest.writeString(favouriteunit);
    //   dest.writeString(currentjob);
    // dest.writeString(monashemail);
//        dest.writeString(password);
    //      dest.writeInt(subscriptiondate);
    //    dest.writeString(studymode);
    //  dest.writeString(gender);
    // }

    //public static final Creator<StudentM> CREATOR = new Creator<StudentM>() {
    //   @Override
    //   public StudentM createFromParcel(Parcel in) {
    //       return new StudentM(in);
    //    }
    //   @Override
    //    public StudentM[] newArray(int size) {
    //     return new StudentM[size];
    //  }
    // };

    //@Override
    ////  public int describeContents() {
    //    return 0;
    //  }

    public void setEmail(String email){
        this.monashemail = email;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getEmail(){
        return  this.monashemail;
    }
    public String getPassword(){
        return this.password;
    }
}