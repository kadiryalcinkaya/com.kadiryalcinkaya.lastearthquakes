package com.kadir.xml.earthquakes;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class SignUp_Activity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.signupactivity);
		initializeComponents();
		registerEvents();
	}

	private void registerEvents() {
		
		
	}

	private void initializeComponents() {
		
		
	}
}
