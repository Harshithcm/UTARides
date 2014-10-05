package com.se.uta_rides;

import com.parse.ParseObject;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class CarOwnersDetailsActivity extends Activity {
	private String carOwnerName, carOwnerNumber, carOwnerFromTime, carOwnerToTime;
	private TextView riderName, riderNumber, riderFromTime, riderToTime;

	ParseObject obj;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_carowner_details);

		riderName = (TextView) findViewById(R.id.riderName);
		riderNumber = (TextView) findViewById(R.id.riderNumber);
//		riderFromTime = (TextView) findViewById(R.id.riderFromTime);
//		riderToTime = (TextView) findViewById(R.id.riderToTime);

		carOwnerName = getIntent().getStringExtra("carOwnerName");
		carOwnerNumber = getIntent().getStringExtra("carOwnerNumber");
		
		riderName.setText(carOwnerName);
		riderNumber.setText(carOwnerNumber);
	}
}