package com.kadir.xml.earthquakes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.kadir.xml.earthquakes.adapter.QuakeListSerializable;
import com.kadir.xml.earthquakes.model.Quakes;
import com.kadir.xml.earthquakes.task.DownloadDataASYNC;
import com.kadir.xml.earthquakes.utilities.Network;
import com.kadir.xml.earthquakes.utilities.Utilities;
import com.parse.ParseUser;

public class Activity_Main extends Activity {

	private static final String URL = "http://www.koeri.boun.edu.tr/sismo/zeqmap/xmlt/son24saat.xml";
	private static ArrayList<Quakes> QuakeLists = new ArrayList<Quakes>();

	private TextView txtTime;
	private TextView txtLocation;
	private TextView txtLatitude;
	private TextView txtLongitude;
	private TextView txtIndensity;
	private TextView txtDepth;

	private Button btnNearMe;
	private Button btnLastestTime;

	private LocationManager locationManager;
	private String provider;
	private Location location = null;
	private double LONG = 0.0d;
	private double LATI = 0.0d;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.mainactivity);
		
		if (ParseUser.getCurrentUser() ==null) Utilities.clearAndStartNewActivity(this, Login_Activity.class);
		
		initializeComponents();
		registerEvents();
		try {
			DownloadData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void UpdateTextFields(Quakes quake) {

		txtTime.setText(quake.getName());
		txtLocation.setText(quake.getLokasyon());
		txtLatitude.setText(String.valueOf(quake.getLat()) + "°");
		txtLongitude.setText(String.valueOf(quake.getLng()) + "°");
		txtIndensity.setText(String.valueOf(quake.getMag()));
		txtDepth.setText(String.valueOf(quake.getDepth()) + " km");
	}

	private void initializeComponents() {

		txtTime = (TextView) findViewById(R.id.TxtTimeParse);
		txtLocation = (TextView) findViewById(R.id.TxtLocationParse);
		txtLatitude = (TextView) findViewById(R.id.TxtLatitudeParse);
		txtLongitude = (TextView) findViewById(R.id.TxtLongitudeParse);
		txtIndensity = (TextView) findViewById(R.id.TxtIndensityParse);
		txtDepth = (TextView) findViewById(R.id.TxtDepthParse);

		btnNearMe = (Button) findViewById(R.id.btnNearMe);
		btnLastestTime = (Button) findViewById(R.id.btnLastestTime);

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		provider = locationManager.getBestProvider(new Criteria(), false);
		location = locationManager.getLastKnownLocation(provider);
		if (location != null) {
			LONG = location.getLongitude();
			LATI = location.getLatitude();
		} else {
			LONG = 41.00d;
			LATI = 29.00d;
		}
	}

	private void registerEvents() {
		btnNearMe.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(Activity_Main.this, "Clicked Near Me",
				// Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(Activity_Main.this,
						Activity_gps.class);
				Collections.sort(QuakeLists, new Comparator<Quakes>() {

					@Override
					public int compare(Quakes q1, Quakes q2) {

						int result = 0;

						double firstLati = LATI - q1.getLat();
						double firstLong = LONG - q1.getLng();
						double secondLati = LATI - q2.getLat();
						double secondLong = LONG - q2.getLng();
						double firstDistance = (Math.abs(firstLati) + Math
								.abs(firstLong)) / 2;
						double secondDistance = (Math.abs(secondLati) + Math
								.abs(secondLong)) / 2;

						if (firstDistance > secondDistance) {
							result = 1;
						} else if (firstDistance < secondDistance) {
							result = -1;
						} else if (firstDistance == secondDistance) {
							result = 0;
						}
						return result;
					}
				});
				intent.putExtra("list_quakes", new QuakeListSerializable(
						QuakeLists));
				intent.putExtra("order_by", 1);
				startActivity(intent);
			}
		});

		btnLastestTime.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(Activity_Main.this, "Clicked Lastest Time",
				// Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(Activity_Main.this,
						Activity_gps.class);
				Collections.sort(QuakeLists, new Comparator<Quakes>() {

					@Override
					public int compare(Quakes first, Quakes second) {
						return first.getName().compareTo(second.getName());
					}
					
				});
				intent.putExtra("list_quakes", new QuakeListSerializable(
						QuakeLists));
				intent.putExtra("order_by", 0);
				startActivity(intent);
			}
		});
	}

	private void DownloadData() throws Exception, Exception {

		if (Network.isNetworkAvailable(this)) {
			QuakeLists = new DownloadDataASYNC(this).execute(URL).get();
			if (QuakeLists != null || QuakeLists.size() != 0) {
				UpdateTextFields(QuakeLists.get(QuakeLists.size() - 1));
			}
		} else {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.connection_error_title);
			builder.setMessage(R.string.connection_error_message);
			builder.setPositiveButton(android.R.string.ok,
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							finish();
						}
					});
			builder.create().show();
		}

	}
}