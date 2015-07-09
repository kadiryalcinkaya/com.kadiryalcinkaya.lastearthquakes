package com.kadir.xml.earthquakes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kadir.xml.earthquakes.utilities.Utilities;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Login_Activity extends Activity {

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
		setProgressBarIndeterminate(true);
	}

	private void registerEvents() {

		loginBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String username = userNameTxt.getText().toString();
				String password = passWordTxt.getText().toString();
				if (username.isEmpty() || password.isEmpty()) {
					Utilities.showAlertDialog(Login_Activity.this, "Oopps!",
							"Username/Password Cant be empty!", null);
				} else {
					setProgressBarIndeterminateVisibility(true);
					ParseUser.logInInBackground(username, password,
							new LogInCallback() {

								@Override
								public void done(ParseUser user,
										ParseException exception) {
									setProgressBarIndeterminateVisibility(false);
									if (exception == null) {
										Utilities.clearAndStartNewActivity(
												Login_Activity.this,
												Activity_Main.class);
									} else {
										Utilities.showAlertDialog(
												Login_Activity.this, "Ooopss!",
												exception.getMessage(), null);
									}
								}
							});
				}
			}
		});

		signUpTxt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Login_Activity.this,
						SignUp_Activity.class);
				startActivity(intent);
			}
		});
	}

}
