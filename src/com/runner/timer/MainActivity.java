package com.runner.timer;

import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button startButton;
	private Button stopButton;
	private Button mileButton;
	public TextView minutes;
	public TextView seconds;
	private int secNumber = 0;
	private int minNumber = 0;
	private boolean pauseTheClock = false; //use to determine the button's state
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Context thisActivity = this;
		
		startButton = (Button) findViewById(R.id.startButton);
		stopButton = (Button) findViewById(R.id.stopButton);
		
		minutes = (TextView) findViewById(R.id.min);
		seconds = (TextView) findViewById(R.id.sec);
		
		startButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Button startButton = (Button) v;
				if(pauseTheClock == false){
					timerHandler.postDelayed(timerRunnable, 0);
					pauseTheClock = true;
					startButton.setText(getResources().getString(R.string.pause_button));
				}
				else{
					timerHandler.removeCallbacks(timerRunnable);
					pauseTheClock = false;
					startButton.setText(getResources().getString(R.string.start_button));
				}
			}
			
		});
		
		stopButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				timerHandler.removeCallbacks(timerRunnable);
				minNumber = 0 ;
				secNumber = 0 ;
				minutes.setText("00");
				seconds.setText("00");
				
			}
			
		});
		
		//*******************Location**********************
		
		final LocationManager mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		/*
		mileButton = (Button) findViewById(R.id.mile_button);
		
		mileButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)||
						mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
					
				}
				else{
					Toast.makeText(thisActivity, getResources().getString(R.string.enable_GPS), Toast.LENGTH_SHORT).show();;
					startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
				}
			}
			
		});
		
		*/
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		timerHandler.removeCallbacks(timerRunnable);
		pauseTheClock = false;
		startButton = (Button) findViewById(R.id.startButton);
		startButton.setText(getResources().getString(R.string.start_button));
	}	
	
	Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
        	secNumber ++;
			if(secNumber >= 60){
				minNumber++;
				secNumber = 0;
			}
			minutes.setText("");
			seconds.setText("");
			if(minNumber<10)
				minutes.setText("0");
			minutes.append(Integer.toString(minNumber));
			if(secNumber<10)
				seconds.setText("0");
			seconds.append(Integer.toString(secNumber));
            timerHandler.postDelayed(this, 1000);
        }
    };
}
