package com.se.uta_rides;

<<<<<<< HEAD
//import com.parse.ParseObject;
=======
>>>>>>> 3c11cac54c60e2d7d3945a520d558f6d9291fece
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/*CarOwnersDetailsActivity - Displays the Name and Contact of the selected Car Owner*/

public class CarOwnersDetailsActivity extends Activity {
	private String carOwnerName, carOwnerNumber, carOwnerFromTime, carOwnerToTime;
	private TextView riderName, riderNumber, riderFromTime, riderToTime;

<<<<<<< HEAD
	//ParseObject obj;

=======
>>>>>>> 3c11cac54c60e2d7d3945a520d558f6d9291fece
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