package com.se.uta_rides;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class WelcomeActivity extends Activity implements OnClickListener {
	Intent i;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonLogIn:
			i = new Intent("com.se.uta_rides.LOGINACTIVITY");
			startActivity(i);
			break;

		case R.id.buttonSignUp:
			i = new Intent("com.se.uta_rides.SIGNUPACTIVITY");
			startActivity(i);
			break;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		Button buttonLogIn = (Button) findViewById(R.id.buttonLogIn);
		Button buttonSignUp = (Button) findViewById(R.id.buttonSignUp);

		buttonLogIn.setOnClickListener(this);
		buttonSignUp.setOnClickListener(this);
	}
}
