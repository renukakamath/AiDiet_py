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
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class ViewSuggestions extends Activity implements JsonResponse, AdapterView.OnItemClickListener {
	
	ListView l1;
	SharedPreferences sh;
	String[] meal,meal_id,value;
	public  static  String mid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_suggestions);
		
		sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		l1 = (ListView) findViewById(R.id.lv_suggestions);

		l1.setOnItemClickListener(this);
		
		JsonReq JR = new JsonReq(getApplicationContext());
        JR.json_response = (com.example.aidiet.JsonResponse) ViewSuggestions.this;
        String q = "view_suggestions/?login_id=" + sh.getString("log_id", "");
        JR.execute(q);
	}



	@Override
	public void response(JSONObject jo) {
		try {

			String status = jo.getString("status");
			Log.d("pearl", status);


			if (status.equalsIgnoreCase("success")) {
				JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
				meal_id = new String[ja1.length()];
				meal = new String[ja1.length()];

				value = new String[ja1.length()];



				String[] value = new String[ja1.length()];

				for (int i = 0; i < ja1.length(); i++) {
					meal_id[i] = ja1.getJSONObject(i).getString("meal_id");

					meal[i] = ja1.getJSONObject(i).getString("meal");




					value[i] =  meal[i]  ;

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
	public void onBackPressed() {
		super.onBackPressed();
		startActivity(new Intent(getApplicationContext(), UserHome.class));
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
		mid=meal_id[i];
		final CharSequence[] items = {"Food Suggestions","Cancel"};

		AlertDialog.Builder builder = new AlertDialog.Builder(ViewSuggestions.this);
		builder.setItems(items, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int item) {

				if (items[item].equals("Food Suggestions")) {


					startActivity(new Intent(getApplicationContext(), Foodsuggestions.class));



				}


			}

		});
		builder.show();
	}
	}
