package com.example.aidiet;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IPsettings extends Activity {
	EditText ed_ip;
	Button bt_ip;
	public static String ip;
	SharedPreferences sh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ipsettings);
		
		sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		
		ed_ip = (EditText) findViewById(R.id.editText1);
		bt_ip = (Button) findViewById(R.id.button1);
		
		ed_ip.setText(sh.getString("ip","192.168.1.1"));
		
		bt_ip.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				
				ip=ed_ip.getText().toString();
				if (ip.equals("")) {
					ed_ip.setError("Enter IP address");
					ed_ip.setFocusable(true);
					
				}else {
					Editor ed= sh.edit();
					ed.putString("ip",ip);
					ed.commit();
					startActivity(new Intent(getApplicationContext(),Login.class));
				}
			}
				
			
		});
		
		
	}


}
