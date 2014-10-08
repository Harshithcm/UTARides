package com.se.uta_rides;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/*LaunchActivity - Allows user to select "Booking a ride" and "Providing a Ride"*/
public class LaunchActivity extends Activity implements OnClickListener {

	Button student, carOwner;
	Intent selectPath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);
		
		/*Retrieves Student and Car Owner buttons*/
		student = (Button) findViewById(R.id.launchStudent);
		carOwner = (Button) findViewById(R.id.launchCarOwner);
		student.setOnClickListener(this);
		carOwner.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {

		switch (view.getId()) {

		case R.id.launchStudent:
			startActivity(new Intent("com.se.uta_rides.SEARCHACTIVITY"));
			break;

		case R.id.launchCarOwner:
			startActivity(new Intent("com.se.uta_rides.CAROWNERSETAVAILABLEACTIVITY"));
		}

	}

}