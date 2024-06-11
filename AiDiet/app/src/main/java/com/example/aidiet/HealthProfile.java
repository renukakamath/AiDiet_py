package com.example.aidiet;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class HealthProfile extends Activity implements JsonResponse {

	EditText e1,e2,e3,e4,e5,e6,e7;
	Button b1;
	String name,lname,place,phone,email,vehicle,vehicledetails;
	SharedPreferences sh;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_health_profile);

		sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		e1=(EditText) findViewById(R.id.ed_bp);
		e2=(EditText) findViewById(R.id.ed_sugar);
		e3=(EditText) findViewById(R.id.ed_cholestrol);
		e4=(EditText) findViewById(R.id.ed_weight);
		e5=(EditText)findViewById(R.id.ed_height) ;


		b1=(Button) findViewById(R.id.bt_submit);
		JsonReq JR = new JsonReq();

		JR.json_response = (JsonResponse) HealthProfile.this;
		String q = "/HealthProfile?lid="+sh.getString("log_id", "");
		q = q.replace(" ", "%20");
		JR.execute(q);

		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				name=e1.getText().toString();
				lname=e2.getText().toString();
				place=e3.getText().toString();
				phone=e4.getText().toString();
				email=e5.getText().toString();


				if(name.equalsIgnoreCase(""))
				{
					e1.setError("Enter your  blood pressure");
					e1.setFocusable(true);
				}
				else if(lname.equalsIgnoreCase(""))
				{
					e2.setError("Enter your  sugar level");
					e2.setFocusable(true);
				}
				else if(place.equalsIgnoreCase(""))
				{
					e3.setError("Enter your  cholesterol level");
					e3.setFocusable(true);
				}

				else if(phone.equalsIgnoreCase(""))
				{
					e4.setError("Enter your  body weight");
					e4.setFocusable(true);
				}
				else if(email.equalsIgnoreCase(""))
				{
					e5.setError("Enter your  Height");
					e5.setFocusable(true);
				}

			else{

					JsonReq JR = new JsonReq();
					JR.json_response = (JsonResponse) HealthProfile.this;
					String q = "/update_health_profile?login_id=" + sh.getString("log_id", "") + "&blood_pressure=" + name + "&cholesterol_level=" + place + "&body_weight=" + phone + "&Height=" + email + "&sugar_level=" + lname;
					q = q.replace(" ", "%20");
					JR.execute(q);

				}
			}
		});
	}
	



	@Override
	public void response(JSONObject jo) {
		try {
			String method=jo.getString("method");

			if(method.equalsIgnoreCase("viewhealthagent")) {
				String status = jo.getString("status");
				Log.d("pearl", status);

				if (status.equalsIgnoreCase("success")) {
					JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
					e1.setText(ja1.getJSONObject(0).getString("blood_pressure"));
					e2.setText(ja1.getJSONObject(0).getString("sugar_level"));
					e3.setText(ja1.getJSONObject(0).getString("cholesterol_level"));
					e4.setText(ja1.getJSONObject(0).getString("body_weight"));
					e5.setText(ja1.getJSONObject(0).getString("Height"));






					SharedPreferences.Editor e = sh.edit();
					//e.putString("log_id", logid);
					e.commit();
				}
			}
			else if(method.equalsIgnoreCase("get_my_health_profile"))
			{
				try {
					String status=jo.getString("status");
					Log.d("pearl",status);


					if(status.equalsIgnoreCase("success")){

						Toast.makeText(getApplicationContext(), "UPDATED SUCCESSFULLY", Toast.LENGTH_LONG).show();
						startActivity(new Intent(getApplicationContext(),UserHome.class));

					}
					else
					{
						startActivity(new Intent(getApplicationContext(),HealthProfile.class));
						Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
				}

			}
//            else {
//                Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();
//                startActivity(new Intent(getApplicationContext(), Login.class));
//            }
		} catch (Exception e) {
			// TODO: handle exception

			Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
		}
	}


	@Override
	public void onBackPressed() {
		super.onBackPressed();
		startActivity(new Intent(getApplicationContext(), UserHome.class));
	}

}
