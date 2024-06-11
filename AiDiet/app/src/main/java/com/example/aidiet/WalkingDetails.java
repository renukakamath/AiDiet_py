package com.example.aidiet;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.FloatMath;

public class WalkingDetails extends Activity {

    TextView t1;
    Button b1;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private com.example.dietapp.ShakeDetector mShakeDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walking_details);

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
                //Toast.makeText(getApplicationContext(), "value"+q, Toast.LENGTH_LONG).show();


            }
        });

        ///////////////////////////////////////////////

//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                t1.setText(0);
//                count=0;
//            }
//        });


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
}