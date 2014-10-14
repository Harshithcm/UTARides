package com.se.uta_rides;

import java.util.Calendar;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

/*CarOwnerSetAvailbleActivity - Allows Car Owners to set their availability times*/
public class CarOwnerSetAvailbleActivity extends Activity implements
		OnClickListener, OnItemSelectedListener {
	private Spinner dayDropDownList, favSpotDropDownList;
	private Button buttonSave, buttonStartTime, buttonEndTime;
	private EditText textStartTime, textEndTime;
	Calendar calendar;
	String selectedDate, selectedTime;
	int mYear, mMonth, mDay, tHour, tMinute;
	TimePickerDialog timePick;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_carowner_set_available);

		dayDropDownList = (Spinner) findViewById(R.id.dayDropDownList);
		System.out.println(1);
		favSpotDropDownList = (Spinner) findViewById(R.id.favSpotDropDownList);
		System.out.println(2);
		buttonSave = (Button) findViewById(R.id.buttonSave);
		System.out.println(3);
		buttonStartTime = (Button) findViewById(R.id.buttonStartTime);
		System.out.println(3.5);
		buttonEndTime = (Button) findViewById(R.id.buttonEndTime);
		System.out.println(4);
		textStartTime = (EditText) findViewById(R.id.textStartTime);
		System.out.println(4.5);
		textEndTime = (EditText) findViewById(R.id.textEndTime);
		System.out.println(4.6);

		buttonSave.setOnClickListener(this);
		System.out.println(5);
		buttonStartTime.setOnClickListener(this);
		System.out.println(6);
		buttonEndTime.setOnClickListener(this);
		System.out.println(7);

		dayDropDownList.setOnItemSelectedListener(this);
		System.out.println(8);
		favSpotDropDownList.setOnItemSelectedListener(this);
		System.out.println(9);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonSave:
			Toast.makeText(
					CarOwnerSetAvailbleActivity.this,
					"OnClickListener : "
							+ "\nSpinner 1 : "
							+ String.valueOf(dayDropDownList.getSelectedItem())
							+ "\nSpinner 2 : "
							+ String.valueOf(favSpotDropDownList
									.getSelectedItem()), Toast.LENGTH_SHORT)
					.show();

			break;

		case R.id.buttonStartTime:
			calendar = Calendar.getInstance();
			tHour = calendar.get(Calendar.HOUR_OF_DAY);
			tMinute = calendar.get(Calendar.MINUTE);

			/* Lets the user pick the time of the ride */
			timePick = new TimePickerDialog(this,
					new TimePickerDialog.OnTimeSetListener() {

						@Override
						public void onTimeSet(TimePicker view, int hourOfDay,
								int minute) {
							System.out.println("... " + hourOfDay);
							System.out.println(".... " + minute);
							textStartTime.setText(hourOfDay + ":" + minute);
						}
					}, tHour, tMinute, false);

			timePick.show();

			break;

		case R.id.buttonEndTime:
			calendar = Calendar.getInstance();
			tHour = calendar.get(Calendar.HOUR_OF_DAY);
			tMinute = calendar.get(Calendar.MINUTE);

			/* Lets the user pick the time of the ride */
			timePick = new TimePickerDialog(this,
					new TimePickerDialog.OnTimeSetListener() {

						@Override
						public void onTimeSet(TimePicker view, int hourOfDay,
								int minute) {
							System.out.println("... " + hourOfDay);
							System.out.println(".... " + minute);
							textEndTime.setText(hourOfDay + ":" + minute);
						}
					}, tHour, tMinute, false);

			timePick.show();

			break;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(
				parent.getContext(),
				"OnItemSelectedListener : "
						+ parent.getItemAtPosition(position).toString(),
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}
}