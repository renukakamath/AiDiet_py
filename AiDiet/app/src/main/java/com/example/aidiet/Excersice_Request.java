package com.example.aidiet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class Excersice_Request extends AppCompatActivity  implements JsonResponse {
    EditText e1;
    Button b1;

    SharedPreferences sh;

    String request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excersice_request);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        e1=(EditText) findViewById(R.id.editTextTextPersonName2);
        b1=(Button) findViewById(R.id.button4);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                request=e1.getText().toString();

                JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) Excersice_Request.this;
                String q = "/Excersice_Request?login_id=" + sh.getString("log_id", "") + "&request=" +request ;
                q = q.replace(" ", "%20");
                JR.execute(q);

            }
        });

    }

    @Override
    public void response(JSONObject jo) {
        try{
            String status=jo.getString("status");

            if(status.equalsIgnoreCase("success"))
            {
                Toast.makeText(getApplicationContext(),"Send Request..", Toast.LENGTH_LONG).show();

                startActivity(new Intent(getApplicationContext(),UserHome.class));


            }
            else
            {
                Toast.makeText(getApplicationContext(),"Failed..", Toast.LENGTH_LONG).show();
            }
        }

        catch(Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "exp : "+e, Toast.LENGTH_LONG).show();
        }
    }
}