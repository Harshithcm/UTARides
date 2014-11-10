package com.se.view;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.se.controller.LoadAvailableList;
import com.se.uta_rides.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

/*SearchActivity - Allows users to Search for a list of Car Owner, within a specified time and date*/
public class SearchActivity extends Activity implements OnClickListener,
		OnItemSelectedListener {
	Button buttonSearch, buttonDate, buttonTime, buttonMap;
	private Spinner favDestDropDownList;
	EditText textDate, textTime;
	Intent i;
	Calendar calendar;
	String selectedDate, selectedTime,selectedLocation;
	int mYear, mMonth, mDay, tHour, tMinute;
	DatePickerDialog datePick;
	TimePickerDialog timePick;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		/* Retrieves the Date, Time and Search button */
		buttonDate = (Button) findViewById(R.id.buttonDate);
		buttonTime = (Button) findViewById(R.id.buttonTime);
		buttonSearch = (Button) findViewById(R.id.buttonSearch);
		//buttonMap = (Button) findViewById(R.id.buttonMap);
		favDestDropDownList = (Spinner) findViewById(R.id.favDestDropDownList);

		ArrayAdapter<CharSequence> favDestDropDownListAdapter = ArrayAdapter
				.createFromResource(this, R.array.favDestDropDownList,
						android.R.layout.simple_spinner_item);
		favDestDropDownListAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		favDestDropDownList.setAdapter(favDestDropDownListAdapter);
		favDestDropDownList.setOnItemSelectedListener(this);
		System.out.println(9);

		/* Retrieves the Date and Time text views */
		textDate = (EditText) findViewById(R.id.textDate);
		textTime = (EditText) findViewById(R.id.textTime);

		buttonDate.setOnClickListener(this);
		buttonTime.setOnClickListener(this);
		buttonSearch.setOnClickListener(this);
		//buttonMap.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		/*case R.id.buttonMap:
			i = new Intent("com.se.uta_rides.maps.MAPSACTIVITY");
			startActivity(i);
			break;*/
		
		case R.id.buttonDate:
			calendar = Calendar.getInstance();
			mYear = calendar.get(Calendar.YEAR);
			mMonth = calendar.get(Calendar.MONTH);
			mDay = calendar.get(Calendar.DAY_OF_MONTH);

			/* Lets the user pick the date of the ride */
			datePick = new DatePickerDialog(this,
					new DatePickerDialog.OnDateSetListener() {

						@Override
						public void onDateSet(DatePicker view, int year,
								int monthOfYear, int dayOfMonth) {
							textDate.setText(dayOfMonth + "-"
									+ (monthOfYear + 1) + "-" + year);
							selectedDate = year + "-" + (monthOfYear + 1) + "-"
									+ dayOfMonth;

						}
					}, mYear, mMonth, mDay);

			datePick.show();

			break;

		case R.id.buttonTime:
			calendar = Calendar.getInstance();
			tHour = calendar.get(Calendar.HOUR_OF_DAY);
			tMinute = calendar.get(Calendar.MINUTE);

			/* Lets the user pick the time of the ride */
			timePick = new TimePickerDialog(this,
					new TimePickerDialog.OnTimeSetListener() {

						@Override
						public void onTimeSet(TimePicker view, int hourOfDay,
								int minute) {
							textTime.setText(hourOfDay + ":" + minute);

							selectedTime = hourOfDay + ":" + minute;
						}
					}, tHour, tMinute, false);

			timePick.show();

			break;

		case R.id.buttonSearch:
			String dateSearch = selectedDate;
			String timeSearch = selectedTime;
			String locationSearch = selectedLocation;
			DateFormat formatter;
			Date selDate = null;
			formatter = new SimpleDateFormat("yyyy-MM-dd");
			try {
				selDate = formatter.parse(selectedDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(sdf.format(date));
			
			if (selDate.before(date)){
				Toast.makeText(getApplicationContext(), "Please Select a future date",
						Toast.LENGTH_SHORT).show();
			}else{
			
			
			System.out
					.println("before select " + dateSearch + " " + timeSearch);
			i = new Intent(SearchActivity.this, LoadAvailableList.class);
			i.putExtra("dateSearch", dateSearch);
			i.putExtra("timeSearch", timeSearch);
			i.putExtra("locationSearch", locationSearch);
			startActivity(i);
			}
			break;
		}
	}

	public String functionDate() {
		System.out.println("selected date " + selectedDate);
		return selectedDate;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long arg3) {
		Toast.makeText(
				parent.getContext(),
				"Location selected : "
						+ parent.getItemAtPosition(position).toString(),
				Toast.LENGTH_SHORT).show();
		selectedLocation = parent.getItemAtPosition(position).toString();

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}
}