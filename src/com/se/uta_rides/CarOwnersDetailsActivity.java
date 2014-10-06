package com.se.uta_rides;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/*CarOwnersDetailsActivity - Displays the Name and Contact of the selected Car Owner*/

public class CarOwnersDetailsActivity extends Activity {
	private String carOwnerName, carOwnerNumber, carOwnerFromTime, carOwnerToTime;
	private TextView riderName, riderNumber, riderFromTime, riderToTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_carowner_details);

		/*Retrieves Name and Number text view*/
		riderName = (TextView) findViewById(R.id.riderName);
		riderNumber = (TextView) findViewById(R.id.riderNumber);
		
		/*Retrieves Name and Number from Search Activity*/
		carOwnerName = getIntent().getStringExtra("carOwnerName");
		carOwnerNumber = getIntent().getStringExtra("carOwnerNumber");
		
		/*Set the Name and Number*/
		riderName.setText(carOwnerName);
		riderNumber.setText(carOwnerNumber);
	}
}