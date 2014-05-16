package com.example.gpsapp;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class GPSActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gps);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
			.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.g, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		static LocationManager lm;

		static TextView txtSats;
		static TextView txtaltitude;
		static TextView txtbearing;
		static TextView txtaccuracy;
		static TextView txtelapsedtime;
		static TextView txtutctime;
		static TextView txtspeed;
		static TextView txtdistancetomelbourne;
		static TextView txtdistancebetweenmelbournesanfran;
		static Activity currentActivity;

		static double lat;
		static double lon;
		static double altitude;
		static double bearing;
		static double accuracy;
		static long elapsedTime;
		static long UTCTime;
		static float speed;


		final static LocationListener mLocationListener = new LocationListener() {

			@Override
			public void onLocationChanged(Location location) {
				populateLocationData();

			}

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub

			}

		};
		
		
		

		public PlaceholderFragment() {


		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_g, container,
					false);

			lm = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
			
			
			lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, mLocationListener);
			
			txtSats = (TextView) rootView.findViewById(R.id.txtStats);
			txtaltitude = (TextView) rootView.findViewById(R.id.txtaltitude);
			txtbearing = (TextView) rootView.findViewById(R.id.txtbearing);
			txtaccuracy = (TextView) rootView.findViewById(R.id.txtaccuracy);
			txtelapsedtime = (TextView) rootView.findViewById(R.id.txtelapsedtime);
			txtutctime = (TextView) rootView.findViewById(R.id.txtutctime);
			txtspeed = (TextView) rootView.findViewById(R.id.txtspeed);
			txtdistancetomelbourne = (TextView) rootView.findViewById(R.id.txtdistancetomelbourne);
			txtdistancebetweenmelbournesanfran = (TextView) rootView.findViewById(R.id.txtdistancebetweenmelbournesanfran);

			return rootView;
		}

		private static void populateLocationData() {

			if (lm.getLastKnownLocation(LocationManager.GPS_PROVIDER) != null) {

				lat = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude();
				lon = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude();
				altitude = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER).getAltitude();
				bearing = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER).getBearing();
				accuracy = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER).getAccuracy();
				elapsedTime = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER).getElapsedRealtimeNanos();
				UTCTime = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER).getTime();
				speed = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER).getSpeed();

				Location locMelbourne = new Location(LocationManager.GPS_PROVIDER);
				locMelbourne.setLatitude(-37.8602828);
				locMelbourne.setLongitude(145.079616);
				float distanceToMelbourne = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER).distanceTo(locMelbourne);

				Location locSanFran = new Location(LocationManager.GPS_PROVIDER);
				locSanFran.setLatitude(37.7577);
				locSanFran.setLongitude(-122.4376);
				float distanceBetweenMelbourneSanFran = locMelbourne.distanceTo(locSanFran);


				txtSats.setText(Double.toString(lat) + " " + Double.toString(lon));		
				txtaltitude.setText((Double.toString(altitude)));
				txtbearing.setText((Double.toString(bearing)));
				txtaccuracy.setText((Double.toString(accuracy)));
				txtelapsedtime.setText((Double.toString(elapsedTime)));
				txtutctime.setText((Double.toString(UTCTime)));
				txtspeed.setText((Float.toString(speed)));
				txtdistancetomelbourne.setText((Float.toString(distanceToMelbourne)));
				txtdistancebetweenmelbournesanfran.setText((Float.toString(distanceBetweenMelbourneSanFran)));

			}


		}

	}
}
