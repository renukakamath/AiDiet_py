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

public class Foodsuggestions extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {
    ListView l1;
    SharedPreferences sh;
    String[] meal,food,quantity,calories,comments,date,value,suggested_id;
    public  static  String sid,cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodsuggestions);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1 = (ListView) findViewById(R.id.lv_suggestions);

l1.setOnItemClickListener(this);

        JsonReq JR = new JsonReq(getApplicationContext());
        JR.json_response = (com.example.aidiet.JsonResponse) Foodsuggestions.this;
        String q = "view_foodsuggestions/?login_id=" + sh.getString("log_id", "0")+"&mid="+ViewSuggestions.mid;
        JR.execute(q);
    }

    @Override
    public void response(JSONObject jo) {
        try {

            String status = jo.getString("status");
            Log.d("pearl", status);


            if (status.equalsIgnoreCase("success")) {
                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                food = new String[ja1.length()];
                suggested_id = new String[ja1.length()];
                quantity = new String[ja1.length()];
                calories = new String[ja1.length()];
                comments = new String[ja1.length()];
                date = new String[ja1.length()];




                meal = new String[ja1.length()];

                value = new String[ja1.length()];



                String[] value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {

                    suggested_id[i] = ja1.getJSONObject(i).getString("suggested_id");
                    food[i] = ja1.getJSONObject(i).getString("food");
                    quantity[i] = ja1.getJSONObject(i).getString("quantity");
                    calories[i] = ja1.getJSONObject(i).getString("calories");
                    comments[i] = ja1.getJSONObject(i).getString("comments");
                    date[i] = ja1.getJSONObject(i).getString("date");

                    meal[i] = ja1.getJSONObject(i).getString("meal");




                    value[i] = "Meal Type:" + meal[i] +"\nFood:" + food[i]  +"\nQuantity:" + quantity[i] +"\nCalories:" + calories[i] +"\n" + comments[i]   ;

                }
                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), R.layout.custtext, value);

                l1.setAdapter(ar);



            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), " suggestions", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        sid=suggested_id[i];
        cal=calories[i];
        final CharSequence[] items = {"Add","Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Foodsuggestions.this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Add")) {


                    JsonReq JR = new JsonReq(getApplicationContext());
                    JR.json_response = (com.example.aidiet.JsonResponse) Foodsuggestions.this;
                    String q = "ADD/?login_id=" + sh.getString("log_id", "")+"&suggested_id="+sid+"&calories="+cal;
                    JR.execute(q);

                    Toast.makeText(getApplicationContext(), " Added", Toast.LENGTH_LONG).show();


                }


            }

        });
        builder.show();
    }
    }
