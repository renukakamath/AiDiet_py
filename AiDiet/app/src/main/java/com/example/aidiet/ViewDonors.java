package com.example.aidiet;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ViewDonors extends Activity implements com.example.aidiet.JsonResponse {
	
	ListView lv_donors;
	SharedPreferences sh;
	String[] donors;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_donors);
		
		sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		lv_donors = (ListView) findViewById(R.id.lv_donors);
		
		com.example.aidiet.JsonReq JR = new com.example.aidiet.JsonReq(getApplicationContext());
        JR.json_response = (com.example.aidiet.JsonResponse) ViewDonors.this;
        String q = "view_donors/";
        JR.execute(q);
	}


	@Override
	public void response(JSONObject jo) {
		try {
			String status = jo.getString("status");
			if (status.equalsIgnoreCase("success")) {
	    		JSONArray ja = jo.getJSONArray("data");
	    		if (ja.length() > 0) {
	    			donors = new String[ja.length()];
	    			
	    			for (int i = 0; i < ja.length(); i++) {
	    				donors[i] = ja.getJSONObject(i).getString("fname") + " " + ja.getJSONObject(i).getString("lname")
	    						+ "\nBlood group : " + ja.getJSONObject(i).getString("blood_group")
	    						+ "\nPhone : " + ja.getJSONObject(i).getString("phone") + "\n";
					}
	    			
	    			lv_donors.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.cust_list, donors));
	    		}
	    	}
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Exc : " + e, Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		startActivity(new Intent(getApplicationContext(), com.example.aidiet.UserHome.class));
	}
}
