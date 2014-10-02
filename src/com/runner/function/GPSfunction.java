package com.runner.function;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class GPSfunction implements LocationListener {

	private Context mContext;
	private LocationManager lms;
	public GPSfunction(LocationManager mLocation, Context gpsContext){
		lms = mLocation;
		mContext = gpsContext;
	}
	
	public Location locationserviceInitial()
	{
		String best_provider = LocationManager.GPS_PROVIDER;
		Criteria criteria = new Criteria();
		best_provider = lms.getBestProvider(criteria, true);
		return lms.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	}
	
	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		Toast.makeText(mContext, "Provider disable", Toast.LENGTH_SHORT);
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}
