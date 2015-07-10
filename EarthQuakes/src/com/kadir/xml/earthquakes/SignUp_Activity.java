package com.kadir.xml.earthquakes;

import com.kadir.xml.earthquakes.utilities.Utilities;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class SignUp_Activity extends Activity {

	private EditText userNameTxt;
	private EditText userPassTxt;
	private EditText emailTxt;
	private Button signUpBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.signupactivity);
		initializeComponents();
		registerEvents();
	}

	private void registerEvents() {

		signUpBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String username = userNameTxt.getText().toString();
				String password = userPassTxt.getText().toString();
				String email = emailTxt.getText().toString();
				if (username.trim().isEmpty() || password.trim().isEmpty()
						|| email.trim().isEmpty()) {
					Utilities.showAlertDialog(SignUp_Activity.this, getString(R.string.connection_error_title),
							getString(R.string.err_empty), null);
				} else {
					setProgressBarIndeterminateVisibility(true);
					ParseUser user = new ParseUser();
					user.setUsername(username);
					user.setPassword(password);
					user.setEmail(email);
					user.signUpInBackground(new SignUpCallback() {

						@Override
						public void done(ParseException exception) {
							setProgressBarIndeterminateVisibility(false);
							if (exception == null) {
								Utilities.clearAndStartNewActivity(
										SignUp_Activity.this,
										Activity_Main.class);
							} else {
								Utilities.showAlertDialog(SignUp_Activity.this,
										getString(R.string.connection_error_title), exception.getMessage(), null);
							}
						}
					});
				}
			}
		});
	}

	private void initializeComponents() {

		userNameTxt = (EditText) findViewById(R.id.userNameRegisterTxt);
		userPassTxt = (EditText) findViewById(R.id.userPassRegisterTxt);
		emailTxt = (EditText) findViewById(R.id.userEmailRegisterTxt);
		signUpBtn = (Button) findViewById(R.id.SignUpbtn);

	}
}
