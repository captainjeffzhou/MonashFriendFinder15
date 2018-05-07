package com.example.cpzhoucheng.monashfriendfinder;



import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
/**
 * Created by CPZHOUCHENG on 6/5/17.
 */

public class RestClient
{

    private static final String BASE_URI = "http://192.168.1.108:8080/assignmentone/webresources";


    // Method 1
    public static String findStdByEmail(String email)
    {
        final String methodPath = "/restclient.student/findByMonashemail/";
        //initialise
        URL url = null; HttpURLConnection conn = null; String textResult = "";
        //Making HTTP request
        try {
            url = new URL(BASE_URI + methodPath + email);
        //open the connection
            conn = (HttpURLConnection) url.openConnection();
        //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000); //set the connection method to GET
            conn.setRequestMethod("GET");
        //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json"); //Read the response
            Scanner inStream = new Scanner(conn.getInputStream()); //read the input steream and store it as string
            while (inStream.hasNextLine()) { textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace(); } finally {
            conn.disconnect(); }
        return textResult;
    }






///////////////////////
    public static Map<String, String>  findPositionByEmail(String email)/////inside asyntask, works!
    {
        final String methodPath = "/restclient.location/findLatiLongiwithMonashEmail/";
        //http://192.168.1.108:8080/assignmentone/webresources/restclient.location/findLatiLongiwithMonashEmail/
        //initialise
        Map<String, String> map = null;
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        //Making HTTP request
        try {
            url = new URL(BASE_URI + methodPath + email);
            //open the connection
            //url = new URL("http://192.168.1.108:8080/assignmentone/webresources/restclient.student/findAnyAttributes/10001/gender,course");
            //open the connection
            conn = (HttpURLConnection) url.openConnection();//error
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());/////////----
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }


            JSONObject position = new JSONObject(textResult);
            String latitude = position.getString("latitude");
            String longitude = position.getString("longitude");





            map = new HashMap<String, String>();
            //String timezone = jsonObject.getString("timezone");
            //String icon = item.getString("icon");
           // String temperature = item.getString("temperature");
            map.put("latitude",latitude);
            map.put("longitude",longitude);

           // latiLongi = lati +","+ longi;
//             {"latitude":-37.813713,"longitude":144.931935}

        } catch (Exception e) {
            e.printStackTrace(); } finally {
            conn.disconnect(); }
        return map;
    }














    ////Subscription
    public static void createStudent(Student student){
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        final String methodPath="/restclient.student/";
        try {

            //////////
            Gson gson =new Gson();
            String stringStudentJson=gson.toJson(student);
            url = new URL(BASE_URI + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000); //set the connection method to POST
            conn.setRequestMethod("POST"); //set the output to true
            conn.setDoOutput(true);
            //set length of the data you want to send
            conn.setFixedLengthStreamingMode(stringStudentJson.getBytes().length);
            //add HTTP headers
            conn.setRequestProperty("Content-Type", "application/json");
            //Send the POST out
            PrintWriter out= new PrintWriter(conn.getOutputStream());
            out.print(stringStudentJson);
            out.close();
            Log.i("error",new Integer(conn.getResponseCode()).toString());
        } catch (Exception e) { e.printStackTrace();
        } finally { conn.disconnect();
        } }











    public static void updateStudent(StudentUpdate student){
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        final String methodPath="/restclient.student/";
        try {

            //////////
            Gson gson =new Gson();
            String stringStudentJson=gson.toJson(student);

            //int idInt = FragmentLogin.getStudentId();
            //String idString = idInt + "";
            url = new URL(BASE_URI + methodPath + FragmentLogin.getStudentId());

            //open the connection
            conn = (HttpURLConnection) url.openConnection();

            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);

            conn.setRequestMethod("DELETE"); // Delete first, then PUT
            //set the connection method to POST
            conn.setRequestMethod("PUT");

            //set the output to true
            conn.setDoOutput(true);
            //set length of the data you want to send
            conn.setFixedLengthStreamingMode(stringStudentJson.getBytes().length);
            //add HTTP headers
            conn.setRequestProperty("Content-Type", "application/json");
            //Send the POST out
            PrintWriter out= new PrintWriter(conn.getOutputStream());
            out.print(stringStudentJson);
            out.close();
            Log.i("error",new Integer(conn.getResponseCode()).toString());
        } catch (Exception e) { e.printStackTrace();
        } finally { conn.disconnect();
        } }



    public static List<StudentM> findStdByAnyAttributes(String attributes) {
        final String methodPath = "/restclient.student/findAnyAttributes/";
        //initialise
        List<StudentM> matchingStudents = new ArrayList<StudentM>();
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        //Making HTTP request
        try {
            int a = FragmentLogin.getStudentId();
            String b = a + "";
            url = new URL(BASE_URI + methodPath + b +"/"+attributes);
            //url = new URL("http://192.168.1.108:8080/assignmentone/webresources/restclient.student/findAnyAttributes/10001/gender,course");
            //http://192.168.1.108:8080/assignmentone/webresources/restclient.student/findAnyAttributes/10001/gender,course
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000); //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json"); //Read the response
            Scanner inStream = new Scanner(conn.getInputStream()); //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }


            JSONArray jsonArray = new JSONArray(textResult);
            int n = jsonArray.length();
            for(int i = 0; i < jsonArray.length();i++){
                JSONObject m = jsonArray.getJSONObject(i);
                StudentM currentStudent = new StudentM();


                currentStudent.setStuId(Integer.parseInt(m.getString("sid").toString()));
                StudentM ss = currentStudent;
                int Id = ss.getStuId();
                currentStudent.setFirstname(m.getString("firstname").toString());
                currentStudent.setLastname(m.getString("lastname").toString());
                currentStudent.setDob(Integer.parseInt(m.getString("dateofbirth").toString()));
                currentStudent.setCourse(m.getString("course").toString());
                currentStudent.setAddress(m.getString("address").toString());//fine
                currentStudent.setSurburb(m.getString("suburb").toString());// error
                currentStudent.setNationality(m.getString("nationality").toString());
                currentStudent.setLanguage(m.getString("nativelanguage").toString());
                currentStudent.setSport(m.getString("favouritesport").toString());
                currentStudent.setMovie(m.getString("favouritemovie").toString());
                currentStudent.setUnit(m.getString("favouriteunit").toString());
                currentStudent.setJob(m.getString("currentjob").toString());
                currentStudent.setEmail(m.getString("monashemail").toString());
                currentStudent.setPassword(m.getString("password").toString());
                currentStudent.setSubDate(Integer.parseInt(m.getString("subscriptiondate").toString()));
                currentStudent.setStuMode(m.getString("studymode").toString());
                currentStudent.setGender(m.getString("gender").toString());

                matchingStudents.add(currentStudent) ;
            }



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return matchingStudents;


    }




    public static String findF(String attributes) {
        final String methodPath = "/restclient.student/findAnyAttributes/";
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        //Making HTTP request
        try {
            int a = FragmentLogin.getStudentId();
            String b = a + "";
            url = new URL("http://192.168.1.108:8080/assignmentone/webresources/restclient.student/findAnyAttributes/10001/gender,course");
            //url = new URL("http://192.168.1.108:8080/assignmentone/webresources/restclient.student/findAnyAttributes/10001/gender,course");

            //url = new URL("http://192.168.1.108:8080/assignmentone/webresources/restclient.student/findAnyAttributes/10001/gender,course");
            //http://192.168.1.108:8080/assignmentone/webresources/restclient.student/findAnyAttributes/10001/gender,course
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000); //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json"); //Read the response
            Scanner inStream = new Scanner(conn.getInputStream()); //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;


    }







    public static String findFbySID() {
        final String methodPath = "/restclient.student/findfriendsByfeiendid/";
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        //Making HTTP request
        try {
            int a = FragmentLogin.getStudentId();
            String b = a + "";
            url = new URL("http://192.168.1.108:8080/assignmentone/webresources/restclient.student/findfriendsByfeiendid/10001");
            //url = new URL("http://192.168.1.108:8080/assignmentone/webresources/restclient.student/findfriendsByfeiendid/10001");
            //http://192.168.1.108:8080/assignmentone/webresources/restclient.student/findAnyAttributes/10001/gender,course
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000); //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json"); //Read the response
            Scanner inStream = new Scanner(conn.getInputStream()); //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;


    }















    public static List<StudentM> findFriendsbyselfid(String attributes) {
        final String methodPath = "/restclient.student/findfriendsBysid2/";
        //initialise
        List<StudentM> matchingStudents = new ArrayList<StudentM>();
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        //Making HTTP request
        try {
            int a = FragmentLogin.getStudentId();
            String b = a + "";
            url = new URL(BASE_URI + methodPath + b +"/"+attributes);
            //url = new URL("http://192.168.1.108:8080/assignmentone/webresources/restclient.student/findAnyAttributes/10001/gender,course");
            //http://192.168.1.108:8080/assignmentone/webresources/restclient.student/findAnyAttributes/10001/gender,course
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000); //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json"); //Read the response
            Scanner inStream = new Scanner(conn.getInputStream()); //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }

            JSONArray jsonArray = new JSONArray(textResult);
            int n = jsonArray.length();
            for(int i = 0; i < jsonArray.length();i++){
                JSONObject m = jsonArray.getJSONObject(i);
                StudentM currentStudent = new StudentM();


                currentStudent.setStuId(Integer.parseInt(m.get("sid").toString()));
                currentStudent.setFirstname(m.getString("firstname").toString());
                currentStudent.setLastname(m.getString("lastname").toString());
                currentStudent.setDob(Integer.parseInt(m.getString("dateofbirth").toString()));
                currentStudent.setCourse(m.getString("course").toString());
                currentStudent.setAddress(m.getString("address").toString());//fine
                currentStudent.setSurburb(m.getString("suburb").toString());// error
                currentStudent.setNationality(m.getString("nationality").toString());
                currentStudent.setLanguage(m.getString("nativelanguage").toString());
                currentStudent.setSport(m.getString("favouritesport").toString());
                currentStudent.setMovie(m.getString("favouritemovie").toString());
                currentStudent.setUnit(m.getString("favouriteunit").toString());
                currentStudent.setJob(m.getString("currentjob").toString());
                currentStudent.setEmail(m.getString("monashemail").toString());
                currentStudent.setPassword(m.getString("password").toString());
                currentStudent.setSubDate(Integer.parseInt(m.getString("subscriptiondate").toString()));
                currentStudent.setStuMode(m.getString("studymode").toString());
                currentStudent.setGender(m.getString("gender").toString());

                matchingStudents.add(currentStudent) ;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return matchingStudents;
    }







    public static String getWeather(String latitude, String longitude) ///stop
    {
        //initialise
        String weather ="";
        Map<String, String> map = null;
        URL url = null;
        HttpURLConnection conn = null;
        String result = "";
        String textResult = "";
        //Making HTTP request
        try {//"latitude":-37.813713,"longitude":144.931935}
            url = new URL("https://api.darksky.net/forecast/b9733cd8fcdee110c0295784fc58ac82/-37.813713,144.931935");
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream()); ///////////////-----------
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
            JSONObject jsonObject = new JSONObject(textResult);
            JSONObject sub = jsonObject.getJSONObject("currently");
            map = new HashMap<String, String>();
            String summary = sub.getString("summary");
            String temperature = sub.getString("temperature");
            int value = Integer.parseInt(temperature);
            int formalTemp = (value - 32)*5/9;

            String temp = formalTemp+"";
            weather = summary+ "  " +temp + " 。C";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return weather;
    }













    public static String findPositionByEmail2(String email)///Stop
    {
        final String methodPath = "/restclient.location/findLatiLongiwithMonashEmail/";
        //http://192.168.1.108:8080/assignmentone/webresources/restclient.location/findLatiLongiwithMonashEmail/
        //initialise
        String latiLongi = "";
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        //Making HTTP request
        try {
            url = new URL(BASE_URI + methodPath + email);
            //open the connection
            //url = new URL("http://192.168.1.108:8080/assignmentone/webresources/restclient.student/findAnyAttributes/10001/gender,course");
            //open the connection
            conn = (HttpURLConnection) url.openConnection();//error
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());/////////----
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
            JSONObject position = new JSONObject(textResult);
            String lati = position.getString("latitude").toString();
            String longi = position.getString("longitude").toString();
            latiLongi = lati +","+ longi;
//             {"latitude":-37.813713,"longitude":144.931935}

        } catch (Exception e) {
            e.printStackTrace(); } finally {
            conn.disconnect(); }
        return latiLongi;
    }









    public static Map<String, String> getWeathers(String position) /////works
    //////
    {
        //initialise
        Map<String, String> map = null;
        URL url = null;
        HttpURLConnection conn = null;
        String result = "";
        String textResult = "";
        //Making HTTP request
        try {
            url = new URL("https://api.darksky.net/forecast/b9733cd8fcdee110c0295784fc58ac82/" + position);

            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());///////////---------
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }


            JSONObject jsonObject = new JSONObject(textResult);
            JSONObject item = jsonObject.getJSONObject("currently");
            map = new HashMap<String, String>();
            String summary = item.getString("summary");
            String temperature = item.getString("temperature");
            //map.put("icon",icon);
            map.put("temperature",temperature);
            map.put("summary",summary);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return map;
    }





    public static String getWeathersss(String latitude,String longitude) {
        //initialise
        Map<String, String> map = null;
        URL url = null;
        HttpURLConnection conn = null;
        String result = "";
        String textResult = "";
        String temperature ="";

        //Making HTTP request
        try {
            url = new URL("https://api.darksky.net/forecast/8f12328cb0390d24b207641ff78c3230/" + longitude + "," + latitude);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
            //JSONObject jsonObject = new JSONObject(textResult);
           // result= jsonObject.toString();
            //JSONObject item = jsonObject.getJSONObject("currently");
            //map = new HashMap<String, String>();
            //String timezone = jsonObject.getString("timezone");
            //String icon = item.getString("icon");
            //temperature = item.getString("temperature");
           // map.put("icon",icon);
           // map.put("temperature",temperature);
           // map.put("timezone",timezone);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }





    public static ArrayList<HashMap<String,String>> getUnitsAndFrequency()/////stop
    {
        final String methodPath = "restclient.student/findallFavouriteunitss";
        //192.168.1.108:8080/assignmentone/webresources/restclient.student/findallFavouriteunitss
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        JSONArray jsonArray = null;
        ArrayList<HashMap<String, String>> maps = new ArrayList<HashMap<String, String>>();
        //Making HTTP request
        try {
            // "192.168.1.108:8080/assignmentone/webresources/restclient.student/findallFavouriteunitss";
            // url = new URL(BASE_URI + methodPath);
            url = new URL("192.168.1.108:8080/assignmentone/webresources/restclient.student/findallFavouriteunitss/");
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
            jsonArray = new JSONArray(textResult);
            for(int i = 0; i < jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                HashMap<String,String> map = new HashMap<>();
                map.put("favouriteunit",jsonObject.getString("favouriteunit"));
                map.put("frequency",jsonObject.getString("frequency").toString());
                maps.add(map);
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return maps;
    }



    public static JSONArray getUnitsAndFrequencys()
    {
        JSONArray jsonArray = null;
        ArrayList<HashMap<String, String>> maps = new ArrayList<HashMap<String, String>>();
        final String methodPath = "restclient.student/findallFavouriteunitss";
        //192.168.1.108:8080/assignmentone/webresources/restclient.student/findallFavouriteunitss
        //initialise
        //URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        //JSONArray jsonArray;
        //JSONArray jsonArrays = new JSONArray();
        //ArrayList<HashMap<String, String>> maps = new ArrayList<HashMap<String, String>>();
        //Making HTTP request
    //-    try {// "192.168.1.108:8080/assignmentone/webresources/restclient.student/findallFavouriteunitss";
            // url = new URL(BASE_URI + methodPath);
        try {URL url = new URL("192.168.1.108:8080/assignmentone/webresources/restclient.student/findallFavouriteunitss");
            //open the connection
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }

            jsonArray = new JSONArray(textResult);
            //jsonArrays = jsonArray;
               for(int i = 0; i < jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                HashMap<String,String> map = new HashMap<>();
                map.put("favouriteunit",jsonObject.getString("favouriteunit"));
                map.put("frequency",jsonObject.getString("frequency"));
                maps.add(map);
             }
        }catch (Exception e) { e.printStackTrace();} finally {conn.disconnect();}
        return jsonArray;
    }












//error
    public static ArrayList<HashMap<String,String>> getUnitAndFrequencyF()  {
        final String methodPath = "/restclient.student/findallFavouriteunitss";
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        JSONArray jsonArray = null;
        ArrayList<HashMap<String, String>> maps = new ArrayList<HashMap<String, String>>();
        //Making HTTP request
        try {
            url = new URL(BASE_URI + methodPath);
            //-url = new URL("192.168.1.108:8080/assignmentone/webresources/restclient.student/findallFavouriteunitss");// error with url 地址有问题
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream()); //read the input steream and store it as string
            while (inStream.hasNextLine()) { textResult += inStream.nextLine();
            }
            jsonArray = new JSONArray(textResult);
            for(int i = 0; i < jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                HashMap<String,String> map = new HashMap<>();
                map.put("favouriteunit",jsonObject.getString("favouriteunit"));
                map.put("frequency",jsonObject.getString("frequency"));
                maps.add(map);
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return maps;
    }//error







/////////////////This one for test
    public static List<StudentM> findStdByAnyAttributesTest(String attributes) //Work
    {
        final String methodPath = "/restclient.student/findAnyAttributes/";
        //initialise
        List<StudentM> matchingStudents = new ArrayList<StudentM>();
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        //Making HTTP request
        try {
            int a = FragmentLogin.getStudentId();
            String b = a + "";
            url = new URL(BASE_URI + methodPath + b +"/"+attributes);
            //url = new URL("http://192.168.1.108:8080/assignmentone/webresources/restclient.student/findAnyAttributes/10001/gender,course");
            //http://192.168.1.108:8080/assignmentone/webresources/restclient.student/findAnyAttributes/10001/gender,course
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000); //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json"); //Read the response
            Scanner inStream = new Scanner(conn.getInputStream()); //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }


            JSONArray jsonArray = new JSONArray(textResult);
            int n = jsonArray.length();
            for(int i = 0; i < jsonArray.length();i++){
                JSONObject m = jsonArray.getJSONObject(i);
                StudentM currentStudent = new StudentM();


                currentStudent.setStuId(Integer.parseInt(m.get("sid").toString()));
                currentStudent.setFirstname(m.getString("firstname").toString());
                currentStudent.setLastname(m.getString("lastname").toString());
                currentStudent.setDob(Integer.parseInt(m.getString("dateofbirth").toString()));
                currentStudent.setCourse(m.getString("course").toString());
                currentStudent.setAddress(m.getString("address").toString());//fine
                currentStudent.setSurburb(m.getString("suburb").toString());// error
                currentStudent.setNationality(m.getString("nationality").toString());
                currentStudent.setLanguage(m.getString("nativelanguage").toString());
                currentStudent.setSport(m.getString("favouritesport").toString());
                currentStudent.setMovie(m.getString("favouritemovie").toString());
                currentStudent.setUnit(m.getString("favouriteunit").toString());
                currentStudent.setJob(m.getString("currentjob").toString());
                currentStudent.setEmail(m.getString("monashemail").toString());
                currentStudent.setPassword(m.getString("password").toString());
                currentStudent.setSubDate(Integer.parseInt(m.getString("subscriptiondate").toString()));
                currentStudent.setStuMode(m.getString("studymode").toString());
                currentStudent.setGender(m.getString("gender").toString());

                matchingStudents.add(currentStudent) ;
            }



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return matchingStudents;


    }














    public static HashMap<String, String> getMovie(String keyword) //works!
    {
        String API_key = "AIzaSyCCCFr9luVvEoPkuARr2V0pjogHGrsdc7M";
        String SEARCH_ID_cx = "06735978297782132617:uur_axkttwy";
        keyword = keyword.replace(" ", "");
        String snippet = "";
        String picAddress = "";
        //initialise

        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        HashMap<String,String> result = new HashMap<>();
        //Making HTTP request
        try {
            //https://www.googleapis.com/customsearch/v1?key=AIzaSyCCCFr9luVvEoPkuARr2V0pjogHGrsdc7M&cx=06735978297782132617:uur_axkttwy&q=Student&num=5
            url = new URL("https://www.googleapis.com/customsearch/v1?key=" + API_key
                    + "&cx=" + SEARCH_ID_cx + "&q=" + keyword + "&num=5");
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
//set the timeout111
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000); //set the connection method to GET
            conn.setRequestMethod("GET");
//add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json"); //Read the response
            Scanner inStream = new Scanner(conn.getInputStream()); //read the input steream and store it as string
            while (inStream.hasNextLine()) { textResult += inStream.nextLine();
            }
            JSONObject json = new JSONObject(textResult);
            JSONArray jsonarray = json.getJSONArray("items");
          //  snippet = jsonarray.getJSONObject(0).getString("snippet");
          //  //picAddress = jsonAddress.getJSONArray("cse_image").getJSONObject(0).getString("src");
          //  JSONObject jsonAddress = jsonarray.getJSONObject(0).getJSONObject("pagemap");
          //  picAddress = jsonAddress.getJSONArray("cse_image").getJSONObject(0).getString("src");


            if (jsonarray != null && jsonarray.length() > 0) {



                //JSONArray jsonarray = json.getJSONArray("items");
                snippet = jsonarray.getJSONObject(0).getString("snippet");
                //picAddress = jsonAddress.getJSONArray("cse_image").getJSONObject(0).getString("src");
                JSONObject jsonAddress = jsonarray.getJSONObject(0).getJSONObject("pagemap");
                picAddress = jsonAddress.getJSONArray("cse_image").getJSONObject(0).getString("src");


                snippet = jsonarray.getJSONObject(0).getString("snippet");




                if (snippet.length() == 0)
                {snippet = jsonarray.getJSONObject(1).getString("snippet");}
                if (snippet.length() == 0)
                {snippet = jsonarray.getJSONObject(2).getString("snippet");}
                if (snippet.length() == 0)
                {snippet = jsonarray.getJSONObject(3).getString("snippet");}
                if (snippet.length() == 0)
                {snippet = jsonarray.getJSONObject(4).getString("snippet");}
                if (snippet.length() == 0)
                {snippet = jsonarray.getJSONObject(5).getString("snippet");}
                if (snippet.length() == 0)
                {snippet = jsonarray.getJSONObject(7).getString("snippet");}
                if (snippet.length() == 0)
                {snippet = jsonarray.getJSONObject(8).getString("snippet");}
                if (snippet.length() == 0)
                {snippet = jsonarray.getJSONObject(9).getString("snippet");}
                if (snippet.length() == 0)
                {snippet = jsonarray.getJSONObject(10).getString("snippet");}
                if (snippet.length() == 0)
                {snippet = jsonarray.getJSONObject(11).getString("snippet");}
                if (snippet.length() == 0)
                {snippet = jsonarray.getJSONObject(12).getString("snippet");}

                if (picAddress.length() == 0)
                { jsonAddress = jsonarray.getJSONObject(0).getJSONObject("pagemap");
                picAddress = jsonAddress.getJSONArray("cse_image").getJSONObject(0).getString("src");
                }
                if (picAddress.length() == 0)
                {jsonAddress = jsonarray.getJSONObject(1).getJSONObject("pagemap");
                    picAddress = jsonAddress.getJSONArray("cse_image").getJSONObject(0).getString("src");
                    }
                if (picAddress.length() == 0)
                {jsonAddress = jsonarray.getJSONObject(2).getJSONObject("pagemap");
                    picAddress = jsonAddress.getJSONArray("cse_image").getJSONObject(0).getString("src");
                    }
                if (picAddress.length() == 0)
                {jsonAddress = jsonarray.getJSONObject(3).getJSONObject("pagemap");
                    picAddress = jsonAddress.getJSONArray("cse_image").getJSONObject(0).getString("src");
                   }
                if (picAddress.length() == 0)
                {jsonAddress = jsonarray.getJSONObject(0);
                    picAddress = jsonAddress.getJSONArray("cse_image").getJSONObject(0).getString("src");
                    }
                if (picAddress.length() == 0)
                {jsonAddress = jsonarray.getJSONObject(1);
                    picAddress = jsonAddress.getJSONArray("cse_image").getJSONObject(0).getString("src");
                    }
                if (picAddress.length() == 0)
                {jsonAddress = jsonarray.getJSONObject(2);
                    picAddress = jsonAddress.getJSONArray("cse_image").getJSONObject(0).getString("src");
                    }
                if (picAddress.length() == 0)
                {jsonAddress = jsonarray.getJSONObject(3);
                    picAddress = jsonAddress.getJSONArray("cse_image").getJSONObject(0).getString("src");
                    }
                if (picAddress.length() == 0)
                {jsonAddress = jsonarray.getJSONObject(4);
                    picAddress = jsonAddress.getJSONArray("cse_image").getJSONObject(0).getString("src");
                    }
                if (picAddress.length() == 0)
                {jsonAddress = jsonarray.getJSONObject(5);
                    picAddress = jsonAddress.getJSONArray("cse_image").getJSONObject(0).getString("src");
                    }
                if (picAddress.length() == 0)
                {jsonAddress = jsonarray.getJSONObject(6);
                    picAddress = jsonAddress.getJSONArray("cse_image").getJSONObject(0).getString("src");
                    }
                if (picAddress.length() == 0)
                {jsonAddress = jsonarray.getJSONObject(7);
                    picAddress = jsonAddress.getJSONArray("cse_image").getJSONObject(0).getString("src");
                    }
                result.put("detail",snippet);
                result.put("address", picAddress);

                //result.put("address", picAddress);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect(); }

        return result;
    }













    public static Bitmap getMoviePicture(String keyword) {
        Bitmap bitmap = null;
        //initialise
        List<Student> matchedStudent = new ArrayList<>();
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        String[] result = {};
        //Making HTTP request
        try {
            url = new URL(keyword);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //read the input steream and store it as nitmap
            int responseCode = conn.getResponseCode();
            //if(responseCode == 200){
           //     //ff//   InputStream is = conn.getInputStream();
          //      InputStream in = new java.net.URL(keyword).openStream();
          //      bitmap = BitmapFactory.decodeStream(in);
          //  }
            try {
               ///- InputStream in = new java.net.URL(keyword).openStream();
               ///- bitmap = BitmapFactory.decodeStream(in);
               // Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
                //        matrix, false);

              ///====  InputStream is = conn.getInputStream();
                //InputStream in = new java.net.URL(keyword).openStream();
             ///====   bitmap = BitmapFactory.decodeStream(is);



                ///==
               // Bitmap bMap = BitmapFactory.decodeFile(keyword);
                InputStream is = conn.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(is );



                bitmap = BitmapFactory.decodeStream(bufferedInputStream);







               // InputStream is = url.openConnection().getInputStream();
              //  bitmap = BitmapFactory.decodeStream(is);
             //   ByteArrayOutputStream baos =new  ByteArrayOutputStream();
             //   bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
            //    byte [] b =baos.toByteArray();
             //   String temp= Base64.encodeToString(b, Base64.DEFAULT);
                return bitmap;



            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return bitmap;
    }








    public static Bitmap getBitmapFromURLl(String src)
    {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }














    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }//not work










    public static Bitmap GetMovieBySid(int sid) {
        Bitmap bitmap = null;
        //initialise
        ///webresources/restclient.student/{id})
        List<Student> matchedStudent = new ArrayList<>();
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        String[] result = {};
        //Making HTTP request
        try {
            url = new URL("");
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //read the input steream and store it as nitmap
            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                InputStream is = conn.getInputStream();
                //bitmap = BitmapFactory.decodeStream(is);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return bitmap;
    }



    public static HashMap<String, String> getMovietxtandurl(String keyword)
    {
        String API_key = "AIzaSyBtsQkK4eGF8dFscO1it_sZe9t3CW1UPI4";
        String SEARCH_ID_cx = "016780581816838379410:zqldnqmusoc";
        keyword = keyword.replace(" ", "+");
        String snippet = "";
        String picAddress = "";
        //initialise
        List<Student> matchedStudent = new ArrayList<>();
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        HashMap<String,String> result = new HashMap<>();
        //Making HTTP request
        try {
            url = new URL("https://www.googleapis.com/customsearch/v1?key=" + API_key
                    + "&cx=" + SEARCH_ID_cx + "&q=" + keyword + "&num=5");
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
            JSONObject json = new JSONObject(textResult);
            JSONArray jsonarray = json.getJSONArray("items");
            if (jsonarray != null && jsonarray.length() > 0) {
                snippet = jsonarray.getJSONObject(0).getString("snippet");
                JSONObject jsonAddress = jsonarray.getJSONObject(0).getJSONObject("pagemap");
                picAddress = jsonAddress.getJSONArray("cse_image").getJSONObject(0).getString("src");
                result.put("detail",snippet);
                result.put("address", picAddress);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return result;
    }

    public static Bitmap getMovieimage(String keyword) {
        Bitmap bitmap = null;
        //initialise
        List<Student> matchedStudent = new ArrayList<>();
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        String[] result = {};
        //Making HTTP request
        try {
            url = new URL(keyword);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //read the input steream and store it as nitmap
            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                InputStream is = conn.getInputStream();
                bitmap = BitmapFactory.decodeStream(is);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return bitmap;
    }









    public static String gett() {

        String output= "";
        try {

            URL url = new URL("https://google.com");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            //String output= "";
            String output2;
            // System.out.println("Output from Server .... \n");
            while ((output2 = br.readLine()) != null) {
               output = output + output2;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
    return output;
    }























    public static Map<String, String> getWeatherff(String longitude,String latitude) {
        //initialise
        Map<String, String> map  = new HashMap<String, String>();
        URL url = null;
        HttpURLConnection conn = null;
        String result = "";
        String textResult = "";
        //Making HTTP request
        try {
            url = new URL("https://api.darksky.net/forecast/b9733cd8fcdee110c0295784fc58ac82/" + longitude + "," + latitude);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
            JSONObject jsonObject = new JSONObject(textResult);
            JSONObject item = jsonObject.getJSONObject("currently");
            //map = new HashMap<String, String>();
            //String timezone = jsonObject.getString("timezone");
            String summary= item.getString("summary");
            String temperature = item.getString("temperature");
            //map.put("icon",icon);
            map.put("temperature",temperature);
            map.put("summary",summary);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return map;
    }






    public static String getWeathergg(String longitude,String latitude) {
        //initialise
        Map<String, String> map  = new HashMap<String, String>();
        URL url = null;
        HttpURLConnection conn = null;
        String result = "";
        String textResult = "";
        //Making HTTP request
        try {
            url = new URL("https://api.darksky.net/forecast/b9733cd8fcdee110c0295784fc58ac82/" + longitude + "," + latitude);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }























    public static String URL2String() throws IOException {
        URL url = new URL("https://api.darksky.net/forecast/8f12328cb0390d24b2" +
                "07641ff78c3230/-33,140");
        InputStream inputStream = url.openStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }










    public static String getLocationsWithDate(String startDate, String endDate)
    {
            ///assignmentone/webresources/restclient.location/findBySidWithTwoDates
        final String methodPath = "/restclient.location/findBySidWithTwoDates/";
        //initialise
        //JSONArray bag = new JSONArray();
        String A= "";
        String B= "";
        String C = "";
        URL url = null; HttpURLConnection conn = null; String textResult = "";
        //ArrayList<HashMap<String, String>> l = new ArrayList<HashMap<String, String>>();
        //Making HTTP request
        try {

            //url = new URL("http://192.168.1.108:8080/assignmentone/webresources/restclient.student/findAnyAttributes/10001/gender,course");

            url = new URL(BASE_URI + methodPath +FragmentLogin.getStudentId()+"/"+ startDate+"/"+endDate);
          //  url = new URL("192.168.1.108:8080/assignmentone/webresources/restclient.location/findBySidWithTwoDates/10001/1702251212/1703311415");
            //http://192.168.1.108:8080/assignmentone/webresources/restclient.location/findBySidWithTwoDates/10001/1702251212/1703311415
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000); //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json"); //Read the response
            Scanner inStream = new Scanner(conn.getInputStream()); //read the input steream and store it as string
            while (inStream.hasNextLine()) { textResult += inStream.nextLine();
            }



            JSONArray jsonArray = new JSONArray(textResult);
            for(int i = 0; i < jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                HashMap<String,String> map = new HashMap<>();
                String a= jsonObject.getString("locationname").toString();
                String b= jsonObject.getString("frequency").toString();
                A = A+"," +a;
                B=B+","+b;
                //map.put("locationname",jsonObject.getString("locationname"));
               // map.put("frequency",jsonObject.getString("frequency").toString());
               // l.add(map);
            }
            C= A +B;
           // JSONArray jsonArray = new JSONArray(textResult);
            ///int size = jsonArray.length();
            //for (int i = 0; i <size; i++)
           // {
           //     //JSONObject jsonObject =
           //  String locaton = jsonArray.getJSONObject(i).getString("locationname");
           //     String f = jsonArray.getJSONObject(i).getString("frequency");
           // }
          //  bag =jsonArray;
        } catch (Exception e) {
            e.printStackTrace(); } finally {
            conn.disconnect(); }
        return C;







    }







}

