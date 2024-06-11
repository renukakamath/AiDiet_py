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

public class MakePayment extends AppCompatActivity  implements JsonResponse {
    EditText e1,e2,e3,e4;
    Button b;
    String amt,card,cvv,date;
SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_payment);

        sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());






        e1=(EditText)findViewById(R.id.editText1);
        e2=(EditText)findViewById(R.id.editText2);
        e3=(EditText)findViewById(R.id.editText3);
        e4=(EditText)findViewById(R.id.editText4);
        e1.setText("500");
        e1.setEnabled(false);
        b=(Button)findViewById(R.id.button1);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                card=e2.getText().toString();
                cvv=e3.getText().toString();
                date=e4.getText().toString();


                if(card.equalsIgnoreCase(""))
                {

                    e2.setError("enter card number");
                    e2.setFocusable(true);

                }
                else if(card.length()!=16)
                {

                    e2.setError("enter valid card number(16 digit)");
                    e2.setFocusable(true);

                }
                else if(cvv.equalsIgnoreCase(""))
                {

                    e3.setError("enter your cvv");
                    e3.setFocusable(true);

                }
                else if(cvv.length()!=3)
                {

                    e3.setError("enter valid cvv (3 digit)");
                    e3.setFocusable(true);

                }
                else if(date.equalsIgnoreCase(""))
                {

                    e4.setError("enter valid date");
                    e4.setFocusable(true);

                }

                else {


//                Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!"+sid, Toast.LENGTH_LONG).show();
                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) MakePayment.this;
                    String q = "/makepayment?cardnum="+card +"&expdate="+date  +"&lid=" + sh.getString("log_id","") ;
                    q = q.replace(" ", "%20");
                    JR.execute(q);

                }
            }



        });

    }

    @Override
    public void response(JSONObject jo) {

        try{
            String status=jo.getString("status");

            if(status.equalsIgnoreCase("success"))
            {
                Toast.makeText(getApplicationContext(),"Payment Successfully..", Toast.LENGTH_LONG).show();

                startActivity(new Intent(getApplicationContext(),Request.class));


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
