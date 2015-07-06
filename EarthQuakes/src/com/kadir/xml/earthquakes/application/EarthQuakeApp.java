package com.kadir.xml.earthquakes.application;

import com.parse.Parse;

import android.app.Application;

public class EarthQuakeApp extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		Parse.enableLocalDatastore(this);
		Parse.initialize(this, ParseConstants.PARSE_API_KEY,
				ParseConstants.PARSE_APP_KEY);
	}

}
