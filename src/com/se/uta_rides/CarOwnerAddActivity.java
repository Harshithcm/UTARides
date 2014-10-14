package com.se.uta_rides;

import java.util.Calendar;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class CarOwnerAddActivity extends Activity implements OnClickListener {

	private Spinner spinner1, spinner2;
	private Button btnSubmit, buttonStartTime, buttonEndTime;
	private EditText textStartTime, textEndTime;
	Calendar calendar;
	String selectedDate, selectedTime;
	int mYear, mMonth, mDay, tHour, tMinute;
	TimePickerDialog timePick;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_carowner_set_available);

		spinner1 = (Spinner) findViewById(R.id.dayDropDownList);
		System.out.println(1);
		spinner2 = (Spinner) findViewById(R.id.favSpotDropDownList);
		System.out.println(2);
		btnSubmit = (Button) findViewById(R.id.buttonSave);
		System.out.println(3);
		buttonStartTime = (Button) findViewById(R.id.buttonStartTime);
		System.out.println(3.5);
		buttonEndTime = (Button) findViewById(R.id.buttonEndTime);
		System.out.println(4);
		textStartTime = (EditText) findViewById(R.id.textStartTime);
		System.out.println(4.5);
		textEndTime = (EditText) findViewById(R.id.textEndTime);
		System.out.println(4.6);

		btnSubmit.setOnClickListener(this);
		System.out.println(5);
		buttonStartTime.setOnClickListener(this);
		System.out.println(6);
		buttonEndTime.setOnClickListener(this);
		System.out.println(7);

		addListenerOnSpinnerItemSelection();
	}

	public void addListenerOnSpinnerItemSelection() {
		spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
		spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonSave:
			Toast.makeText(
					CarOwnerAddActivity.this,
					"OnClickListener : " + "\nSpinner 1 : "
							+ String.valueOf(spinner1.getSelectedItem())
							+ "\nSpinner 2 : "
							+ String.valueOf(spinner2.getSelectedItem()),
					Toast.LENGTH_SHORT).show();

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
}