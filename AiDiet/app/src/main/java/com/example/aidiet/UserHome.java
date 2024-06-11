package com.example.aidiet;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class UserHome extends Activity  implements JsonResponse {


	TextView t1;
	Button b1;
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private com.example.dietapp.ShakeDetector mShakeDetector;

	SharedPreferences sh;

	Button bt_health_profile, bt_food_intake, bt_diet_suggestion, bt_view_discussion, bt_feedbacks, bt_logout,bmi,health;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_home);


		sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		bt_health_profile = (Button) findViewById(R.id.bt_health_profile);
		bt_food_intake = (Button) findViewById(R.id.bt_food_intake);
		bt_diet_suggestion = (Button) findViewById(R.id.bt_diet_suggestion);
		bmi = (Button) findViewById(R.id.bmi);

		health = (Button) findViewById(R.id.health);
		bt_view_discussion = (Button) findViewById(R.id.bt_view_discussion);
		bt_feedbacks = (Button) findViewById(R.id.bt_feedbacks);

		bt_logout = (Button) findViewById(R.id.bt_logout);



		JsonReq JR = new JsonReq();
		JR.json_response = (JsonResponse) UserHome.this;
		String q = "/viewpayment?log_id=" +sh.getString("log_id", "") ;
		q = q.replace(" ", "%20");
		JR.execute(q);


		t1=(TextView)findViewById(R.id.textView1);
		//////////////////////////////////////////////////////////////////
		// ShakeDetector initialization
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mShakeDetector = new com.example.dietapp.ShakeDetector();
		mShakeDetector.setOnShakeListener(new com.example.dietapp.ShakeDetector.OnShakeListener() {

			public void onShake(int count) {
				// TODO Auto-generated method stub


				t1.setText(count+" Step");

				String counts = t1.getText().toString();

				//Toast.makeText(getApplicationContext(), "value"+q, Toast.LENGTH_LONG).show();


				JsonReq JR = new JsonReq();
				JR.json_response = (JsonResponse) UserHome.this;
				String q = "/stepcount?count=" + count +"&log_id=" +sh.getString("log_id", "") ;
				q = q.replace(" ", "%20");
				JR.execute(q);


			}
		});


		bt_health_profile.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(getApplicationContext(), com.example.aidiet.HealthProfile.class));
			}
		});


		health.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(getApplicationContext(), todayhealths.class));
			}
		});

		bmi.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(getApplicationContext(), Bmi.class));
			}
		});


		
		bt_food_intake.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(getApplicationContext(), com.example.aidiet.Viewfood.class));
			}
		});









		bt_diet_suggestion.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(getApplicationContext(), com.example.aidiet.MakePayment.class));
			}
		});



		bt_view_discussion.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(getApplicationContext(), com.example.aidiet.Request.class));
			}
		});









		
		bt_feedbacks.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(getApplicationContext(), Feedbacks.class));
			}
		});
		


		
		bt_logout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(getApplicationContext(), Login.class));
			}
		});
	}



	@Override
	public void onBackPressed() {
		super.onBackPressed();
		startActivity(new Intent(getApplicationContext(), UserHome.class));
	}


	@Override
	public void onResume() {
		super.onResume();
		// Add the following line to register the Session Manager Listener onResume
		mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
	}

	@Override
	public void onPause() {
		// Add the following line to unregister the Sensor Manager onPause
		mSensorManager.unregisterListener(mShakeDetector);
		super.onPause();
	}


	@Override
	public void response(JSONObject jo) {
		try {

			String method = jo.getString("method");
			Log.d("pearl", method);

			if (method.equalsIgnoreCase("viewpayment")) {
				String status = jo.getString("status");

				Log.d("pearl", status);

				if (status.equalsIgnoreCase("success")) {

					bt_view_discussion.setVisibility(View.VISIBLE);
					bt_diet_suggestion.setVisibility(View.GONE);


				}

				else  {

					bt_diet_suggestion.setVisibility(View.VISIBLE);
					bt_view_discussion.setVisibility(View.GONE);


				}
			}


			if (method.equalsIgnoreCase("stepcount")) {

				String status = jo.getString("status");
				Log.d("pearl", status);

				if (status.equalsIgnoreCase("success")) {

					Toast.makeText(getApplicationContext(), " Successful", Toast.LENGTH_SHORT).show();


				}
			}

		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
		}
	}
}
