package com.example.aidiet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class water extends AppCompatActivity  implements JsonResponse {
    EditText e1;
    Button b1;

    SharedPreferences sh;

    String request;

    ListView l1;

    String []  glass,date,value,total,balance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        e1=(EditText) findViewById(R.id.editTextTextPersonName2);
        b1=(Button) findViewById(R.id.button4);

        l1=(ListView) findViewById(R.id.list);


        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) water.this;
        String q = "/Viewwater?login_id=" + sh.getString("log_id", "")  ;
        q = q.replace(" ", "%20");
        JR.execute(q);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                request=e1.getText().toString();

                JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) water.this;
                String q = "/water?login_id=" + sh.getString("log_id", "") + "&water=" +request ;
                q = q.replace(" ", "%20");
                JR.execute(q);

            }
        });


    }

    @Override
    public void response(JSONObject jo) {
        try {

            String method=jo.getString("method");
            if(method.equalsIgnoreCase("water")) {

                String status = jo.getString("status");
                Log.d("pearl", status);


                if (status.equalsIgnoreCase("success")) {
                    Toast.makeText(getApplicationContext(), "ADDED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), UserHome.class));

                } else {

                    Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
                }
            }
            else if(method.equalsIgnoreCase("Viewwater"))
            {
                String status=jo.getString("status");
                Log.d("pearl",status);


                if(status.equalsIgnoreCase("success")){
                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");
                    //feedback_id=new String[ja1.length()];
                    glass=new String[ja1.length()];
                    date=new String[ja1.length()];

                    total=new String[ja1.length()];

                    value=new String[ja1.length()];

                    for(int i = 0;i<ja1.length();i++)
                    {
                        glass[i]=ja1.getJSONObject(i).getString("glass");
                        date[i]=ja1.getJSONObject(i).getString("date");

                        total[i]=ja1.getJSONObject(i).getString("total");

                        value[i]="Glass(ml): "+glass[i]+"\nBalance (ml)  8000/ : "+total[i]+"\nDate: "+date[i];

                    }
                    ArrayAdapter<String> ar=new ArrayAdapter<String>(getApplicationContext(),R.layout.custtext,value);
                    l1.setAdapter(ar);
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
