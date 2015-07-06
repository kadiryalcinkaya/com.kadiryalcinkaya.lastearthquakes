package com.kadir.xml.earthquakes;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class Login_Activity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.loginactivity);
	
		initializeComponents();
		registerEvents();
	}

	private void initializeComponents() {
		
		
	}

	private void registerEvents() {
		
		
	}

}
