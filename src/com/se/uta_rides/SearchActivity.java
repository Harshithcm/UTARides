package com.se.uta_rides;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class SearchActivity extends Activity implements
		OnClickListener {
	Button buttonSearch, buttonDate, buttonTime;
	EditText textDate, textTime;
	Intent i;
	Calendar calendar;
	int mYear, mMonth, mDay, tHour, tMinute;
	DatePickerDialog datePick;
	TimePickerDialog timePick;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		buttonDate = (Button) findViewById(R.id.buttonDate);
		buttonTime = (Button) findViewById(R.id.buttonTime);
		buttonSearch = (Button) findViewById(R.id.buttonSearch);

		textDate = (EditText) findViewById(R.id.textDate);
		textTime = (EditText) findViewById(R.id.textTime);

		buttonDate.setOnClickListener(this);
		buttonTime.setOnClickListener(this);
		buttonSearch.setOnClickListener(this);
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
		case R.id.buttonDate:
			calendar = Calendar.getInstance();
			mYear = calendar.get(Calendar.YEAR);
			mMonth = calendar.get(Calendar.MONTH);
			mDay = calendar.get(Calendar.DAY_OF_MONTH);

			datePick = new DatePickerDialog(this,
					new DatePickerDialog.OnDateSetListener() {

						@Override
						public void onDateSet(DatePicker view, int year,
								int monthOfYear, int dayOfMonth) {
							textDate.setText(dayOfMonth + "-"
									+ (monthOfYear + 1) + "-" + year);
						}
					}, mYear, mMonth, mDay);

			datePick.show();

			break;

		case R.id.buttonTime:
			calendar = Calendar.getInstance();
			tHour = calendar.get(Calendar.HOUR_OF_DAY);
			tMinute = calendar.get(Calendar.MINUTE);

			timePick = new TimePickerDialog(this,
					new TimePickerDialog.OnTimeSetListener() {

						@Override
						public void onTimeSet(TimePicker view, int hourOfDay,
								int minute) {
							textTime.setText(hourOfDay + ":" + minute);
						}
					}, tHour, tMinute, false);

			timePick.show();

			break;

		case R.id.buttonSearch:
			i = new Intent(SearchActivity.this, LoadAvailableListActivity.class);
			i.putExtra("dateSearch", functionDate());
			i.putExtra("timeSearch", textTime.getText().toString());
			startActivity(i);
			break;
		}
	}

	public String functionDate() {
		Date dateData = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		try {
			dateData = formatter.parse(formatter.format(textDate.getText()
					.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		calendar = Calendar.getInstance();
		calendar.setTime(dateData);
		return dateData.toString();
	}
}