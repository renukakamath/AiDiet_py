package com.example.aidiet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class todayhealths extends AppCompatActivity  implements JsonResponse {

    ListView l1,l2,l3,l4;
    SharedPreferences sh;


    String [] glass,total,date,  value1 ,steps_count,walkingdate,value2,uscount,dates,description,count,edesc,time,ename,value3,cal,totals,datee,comments,meal,food,value4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todayhealths);

        l1 =(ListView) findViewById(R.id.walking);

        l2 =(ListView) findViewById(R.id.drinking);
        l3 =(ListView) findViewById(R.id.diet);
        l4 =(ListView) findViewById(R.id.excersice);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) todayhealths.this;
        String q = "/walking?login_id=" + sh.getString("log_id", "")  ;
        q = q.replace(" ", "%20");
        JR.execute(q);


        JsonReq JR1 = new JsonReq();
        JR1.json_response = (JsonResponse) todayhealths.this;
        String q1 = "/drinking?login_id=" + sh.getString("log_id", "")  ;
        q1 = q1.replace(" ", "%20");
        JR1.execute(q1);


        JsonReq JR2 = new JsonReq();
        JR2.json_response = (JsonResponse) todayhealths.this;
        String q2 = "/diet?login_id=" + sh.getString("log_id", "")  ;
        q2 = q2.replace(" ", "%20");
        JR2.execute(q2);



        JsonReq JR3 = new JsonReq();
        JR3.json_response = (JsonResponse) todayhealths.this;
        String q3 = "/excersice?login_id=" + sh.getString("log_id", "")  ;
        q3 = q3.replace(" ", "%20");
        JR3.execute(q3);





    }

    @Override
    public void response(JSONObject jo) {
        try {

            String method=jo.getString("method");
            if(method.equalsIgnoreCase("drinking")) {

                String status = jo.getString("status");
                Log.d("pearl", status);

                if(status.equalsIgnoreCase("success")){
                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");
                    //feedback_id=new String[ja1.length()];
                    glass=new String[ja1.length()];
                    date=new String[ja1.length()];

                    total=new String[ja1.length()];

                    value1=new String[ja1.length()];

                    for(int i = 0;i<ja1.length();i++)
                    {
                        glass[i]=ja1.getJSONObject(i).getString("glass");
                        date[i]=ja1.getJSONObject(i).getString("date");

                        total[i]=ja1.getJSONObject(i).getString("total");

                        value1[i]="Glass(ml): "+glass[i]+"\nBalance (ml)  8000/ : "+total[i]+"\nDate: "+date[i];

                    }
                    ArrayAdapter<String> ar=new ArrayAdapter<String>(getApplicationContext(),R.layout.custtext,value1);
                    l2.setAdapter(ar);
                }


            }
            else if(method.equalsIgnoreCase("walking"))
            {
                String status=jo.getString("status");
                Log.d("pearl",status);


                if(status.equalsIgnoreCase("success")){
                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");
                    //feedback_id=new String[ja1.length()];
                    steps_count=new String[ja1.length()];
                    walkingdate=new String[ja1.length()];



                    value2=new String[ja1.length()];

                    for(int i = 0;i<ja1.length();i++)
                    {
                        steps_count[i]=ja1.getJSONObject(i).getString("steps_count");
                        walkingdate[i]=ja1.getJSONObject(i).getString("walkingdate");



                        value2[i]="steps_count: "+steps_count[i]+"\nwalkingdate: "+walkingdate[i];

                    }
                    ArrayAdapter<String> ar=new ArrayAdapter<String>(getApplicationContext(),R.layout.custtext,value2);
                    l1.setAdapter(ar);
                }
            }


            else if(method.equalsIgnoreCase("excersice"))
            {
                String status=jo.getString("status");
                Log.d("pearl",status);


                if(status.equalsIgnoreCase("success")){
                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");
                    //feedback_id=new String[ja1.length()];
                    uscount=new String[ja1.length()];
                    dates=new String[ja1.length()];

                    description=new String[ja1.length()];

                    count=new String[ja1.length()];

                    edesc=new String[ja1.length()];
                    time=new String[ja1.length()];
                    ename=new String[ja1.length()];

                    value3=new String[ja1.length()];

                    for(int i = 0;i<ja1.length();i++)
                    {
                        uscount[i]=ja1.getJSONObject(i).getString("uscount");
                        dates[i]=ja1.getJSONObject(i).getString("udate");

                        description[i]=ja1.getJSONObject(i).getString("description");

                        count[i]=ja1.getJSONObject(i).getString("count");
                        edesc[i]=ja1.getJSONObject(i).getString("edesc");
                        time[i]=ja1.getJSONObject(i).getString("time");

                        ename[i]=ja1.getJSONObject(i).getString("ename");
                        value3[i]="description: "+description[i]+"\nTotal count: "+count[i]+"\nCount: "+uscount[i]+"\nedesc: "+edesc[i]+"\ntime: "+time[i]+"\nename: "+ename[i]+"\nDate: "+dates[i];

                    }
                    ArrayAdapter<String> ar=new ArrayAdapter<String>(getApplicationContext(),R.layout.custtext,value3);
                    l4.setAdapter(ar);
                }
            }



            else if(method.equalsIgnoreCase("diet"))
            {
                String status=jo.getString("status");
                Log.d("pearl",status);


                if(status.equalsIgnoreCase("success")){
                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");
                    //feedback_id=new String[ja1.length()];
                    cal=new String[ja1.length()];
                    datee=new String[ja1.length()];

                    totals=new String[ja1.length()];

                    comments=new String[ja1.length()];

                    meal=new String[ja1.length()];

                    food=new String[ja1.length()];

                    value4=new String[ja1.length()];



                    for(int i = 0;i<ja1.length();i++)
                    {
                        cal[i]=ja1.getJSONObject(i).getString("cal");
                        datee[i]=ja1.getJSONObject(i).getString("sdate");

                        totals[i]=ja1.getJSONObject(i).getString("total");

                        comments[i]=ja1.getJSONObject(i).getString("comments");
                        food[i]=ja1.getJSONObject(i).getString("food");

                        meal[i]=ja1.getJSONObject(i).getString("meal");

                        value4[i]="cal: "+cal[i]+"\ntotals : "+totals[i]+"\nDate: "+datee[i]+"\ncomments: "+comments[i]+"\nfood : "+food[i]+"\nmeal: "+meal[i];

                    }
                    ArrayAdapter<String> ar=new ArrayAdapter<String>(getApplicationContext(),R.layout.custtext,value4);
                    l3.setAdapter(ar);
                }
            }

        }

        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}