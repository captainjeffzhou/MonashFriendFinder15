package com.example.cpzhoucheng.monashfriendfinder;

/**
 * Created by CPZHOUCHENG on 7/5/17.
 */

public class StudentUpdate {
    private  String firstname;
    private  String lastname;
    private  String address;
    private  String favouritemovie;
    private  String currentjob;
    private  String monashemail;
    private  String password;
    //private  String passwordB;
    private  int dateofbirth;

    private  int sid;
    private  String gender;
    private  String course;
    private  String favouriteunit;
    private  String favouritesport;
    private  String nationality;
    private  String nativelanguage;
    private  String studymode;
    private String subscriptiondate;
    private String suburb;

    //public Student(String firstName,String lastname,String address,String favouriteMovie,
    //              String currentjob,String email,String password,int studentID, String gender,
    //             String favouriteCourse,String favouriteUnit, String favouriteSport,String nationality,
    //             String language,String studyMode
    //)

    public StudentUpdate(String inputS)
    //public Student(int a, String b)
    {

        //this.studentID = a;
        //this.firstName = b;

        String[] s = inputS.split(",");
        address= s[0];
        course= s[1];
        currentjob= s[2];
        String s3 = s[3];
        dateofbirth = Integer.valueOf(s3);
        favouritemovie= s[4];
        favouritesport= s[5];
        favouriteunit= s[6];


        firstname = s[7];
        gender= s[8];
        lastname = s[9];
        monashemail = s[10];

        nationality= s[11];
        nativelanguage = s[12];
        password = s[13];
        sid = FragmentLogin.getStudentId();
        studymode = s[15];
        subscriptiondate = s[16];
        suburb= "test";

    }

    public StudentUpdate()
    {}




}