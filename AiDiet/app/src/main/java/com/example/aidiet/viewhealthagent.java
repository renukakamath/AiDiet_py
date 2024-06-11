package com.example.aidiet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class viewhealthagent extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {
    ListView l1;
    SharedPreferences sh;
    String[] fname,lname,dob,gender,phone,qualification,healthagent_id,value;
    public static String amt,hid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewhealthagent);

        l1=(ListView) findViewById(R.id.list);
        l1.setOnItemClickListener(this);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) viewhealthagent.this;
        String q = "/viewhealthagent?log_id=" +sh.getString("log_id", "");
        q = q.replace(" ", "%20");
        JR.execute(q);
    }

    @Override
    public void response(JSONObject jo) {
        try {

            String status = jo.getString("status");
            Log.d("pearl", status);


            if (status.equalsIgnoreCase("success")) {
                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                fname = new String[ja1.length()];
                lname = new String[ja1.length()];
                dob = new String[ja1.length()];
                gender = new String[ja1.length()];
                phone = new String[ja1.length()];
                qualification = new String[ja1.length()];
                healthagent_id= new String[ja1.length()];
                value = new String[ja1.length()];



                String[] value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {
                    fname[i] = ja1.getJSONObject(i).getString("fname");

                    lname[i] = ja1.getJSONObject(i).getString("lname");
                    dob[i] = ja1.getJSONObject(i).getString("dob");
                    gender[i] = ja1.getJSONObject(i).getString("gender");
                    phone[i] = ja1.getJSONObject(i).getString("phone");
                    qualification[i] = ja1.getJSONObject(i).getString("qualification");
                    healthagent_id[i] = ja1.getJSONObject(i).getString("logid");



                    value[i] = "fname:" + fname[i] + "\nlname: " + lname[i] + "\n dob: " + dob[i] + "\ngender: " + gender[i]+"\nphone:"+phone+"\nqualification:"+qualification ;

                }
                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), R.layout.custtext, value);

                l1.setAdapter(ar);



            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "no vehicle", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        hid=healthagent_id[i];
        final CharSequence[] items = {"Chat","Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(viewhealthagent.this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Chat")) {


                    startActivity(new Intent(getApplicationContext(), ChatHere.class));



                }


            }

        });
        builder.show();
    }
}