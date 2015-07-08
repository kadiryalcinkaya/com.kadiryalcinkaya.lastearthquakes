package com.kadir.xml.earthquakes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login_Activity extends Activity{
	
	private EditText userNameTxt;
	private EditText passWordTxt;
	private Button loginBtn;
	private TextView signUpTxt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.loginactivity);
		initializeComponents();
		registerEvents();
	}

	private void initializeComponents() {
		
		userNameTxt = (EditText) findViewById(R.id.userNameTxt);
		passWordTxt = (EditText) findViewById(R.id.PasswordTxt);
		loginBtn = (Button) findViewById(R.id.Login);
		signUpTxt = (TextView) findViewById(R.id.signUpTxt);
	}

	private void registerEvents() {
		
		loginBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Toast.makeText(Login_Activity.this, "Button", Toast.LENGTH_SHORT).show();
			}
		});
		
		signUpTxt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Login_Activity.this,SignUp_Activity.class);
				startActivity(intent);
			}
		});
	}

}
