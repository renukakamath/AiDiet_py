
package com.example.aidiet;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Customchat extends ArrayAdapter<String> 
{ 
	//needs to extend ArrayAdapter

	private String[] message;         //for custom view name item
	private String[] date;
	private String[] s_id;	//for custom view photo items
	private Activity context;       //for to get current activity context
	SharedPreferences sh;



	public Customchat(Activity context, String[] message, String[] s_id, String[] date)
	{
		//constructor of this class to get the values from main_activity_class

		super(context, R.layout.customlist_chat, message);
		this.context = context;
		this.message = message;
		this.date= date;
		this.s_id= s_id;
	}

	@SuppressLint("ResourceAsColor") @Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		//override getView() method
		sh=PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
		LayoutInflater inflater = context.getLayoutInflater();
		View listViewItem = inflater.inflate(R.layout.customlist_chat, null, true);
		//cust_list_view is xml file of layout created in step no.2

		TextView t1= (TextView) listViewItem.findViewById(R.id.textView1);
		TextView t2 = (TextView) listViewItem.findViewById(R.id.textView2);
//		Toast.makeText(context.getApplicationContext(), "Login_id"+sh.getString("log_id",""), Toast.LENGTH_SHORT).show();


		if(s_id[position].equalsIgnoreCase( sh.getString("log_id","")))




		{



			//t2.setText(message[position]+"\n["+date[position]+"]");
			t2.setText(message[position]);
			t2.setBackgroundColor(Color.parseColor("#FCFCFF"));
			LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)t2.getLayoutParams();
			params.bottomMargin = 70;
			t2.setLayoutParams(params);

			GradientDrawable shape = new GradientDrawable();

//			shape.setCornerRadius(35);
			shape.setCornerRadii(new float[] { 50, 50, 50, 0, 50, 50, 50, 50 });
			shape.setColor((Color.parseColor("#FCFCFF")));
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
				shape.setPadding(0,40,0,40);
			}
			t2.setBackgroundDrawable(shape);

		}
		else
		{
			//t1.setText(message[position]+"\n["+date[position]+"]");
			t1.setText(message[position]);
			t1.setBackgroundColor(Color.parseColor("#A3A6E6"));
			LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)t1.getLayoutParams();
			params.topMargin = 70;
			t1.setLayoutParams(params);

			GradientDrawable shape = new GradientDrawable();

//			shape.setCornerRadius(35);
			shape.setCornerRadii(new float[] { 0, 50, 50, 50, 50, 50, 50, 50 });
			shape.setColor((Color.parseColor("#A3A6E6")));
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
				shape.setPadding(0,40,0,40);
			}
			t1.setBackgroundDrawable(shape);

		}
//		sh=PreferenceManager.getDefaultSharedPreferences(getContext());
		return  listViewItem;
	}
}
