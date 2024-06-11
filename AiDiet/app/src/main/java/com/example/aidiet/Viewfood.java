package com.example.aidiet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Viewfood extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {
    ListView l1;
    EditText e1;
    SharedPreferences sh;
    String[] fooddetails,Quantity,intime,Calories,in_id,value;
    String search;

    public  static  String fid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewfood);

        l1=(ListView) findViewById(R.id.list);

        e1=(EditText)findViewById(R.id.search);
        l1.setOnItemClickListener(this);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Viewfood.this;
        String q = "/Viewfood?log_id=" +sh.getString("log_id", "");
        q = q.replace(" ", "%20");
        JR.execute(q);



        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                search=e1.getText().toString();


                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Viewfood.this;
                    String q = "/searchfood?&search=" + search;
                    q = q.replace(" ", "%20");
                    JR.execute(q);



            }
        });

    }

    @Override
    public void response(JSONObject jo) {
        try {

            String status = jo.getString("status");
            Log.d("pearl", status);


            if (status.equalsIgnoreCase("success")) {
                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                in_id = new String[ja1.length()];
                fooddetails = new String[ja1.length()];
                Quantity = new String[ja1.length()];
                intime = new String[ja1.length()];
                Calories = new String[ja1.length()];

                value = new String[ja1.length()];



                String[] value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {
                    in_id[i] = ja1.getJSONObject(i).getString("in_id");

                    fooddetails[i] = ja1.getJSONObject(i).getString("fooddetails");
                    Quantity[i] = ja1.getJSONObject(i).getString("Quantity");
                    intime[i] = ja1.getJSONObject(i).getString("intime");
                    Calories[i] = ja1.getJSONObject(i).getString("Calories");




                    value[i] = "fooddetails:" + fooddetails[i] + "\nQuantity: " + Quantity[i] + "\n Intime: " + intime[i] + "\nCalories: " + Calories[i] ;

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
        fid=in_id[i];
        final CharSequence[] items = {"Confirm","Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Viewfood.this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Confirm")) {


                    startActivity(new Intent(getApplicationContext(), FoodIntake.class));



                }


            }

        });
        builder.show();
    }
    }
