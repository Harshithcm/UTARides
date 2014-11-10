package com.se.view;

import java.text.DateFormat;

import com.se.controller.CarOwnerSetDetails;
import com.se.data.RideDetailsData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.TimePickerDialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import com.se.uta_rides.R;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

/*CarOwnerSetAvailbleActivity - Allows Car Owners to set their availability times*/
public class CarOwnerSetAvailbleActivity extends Activity implements
		OnClickListener, OnItemSelectedListener {
	private Spinner dayDropDownList, favSpotDropDownList;
	private Button buttonSave, buttonUpdate, buttonStartTime, buttonEndTime;
	private EditText textStartTime, textEndTime;
	Calendar calendar;
	RideDetailsData rideData = new RideDetailsData();
	int mYear, mMonth, mDay, tHour, tMinute;
	TimePickerDialog timePick;
	HttpEntity entity;
	InputStream isr;
	JSONArray jArray;
	String result, status1;
	JSONArray jsonArray;
	CarOwnerSetDetails carOwnerSet = new CarOwnerSetDetails();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_carowner_set_available);

		dayDropDownList = (Spinner) findViewById(R.id.dayDropDownList);
		favSpotDropDownList = (Spinner) findViewById(R.id.favSpotDropDownList);
		buttonStartTime = (Button) findViewById(R.id.buttonStartTime);
		buttonEndTime = (Button) findViewById(R.id.buttonEndTime);
		textStartTime = (EditText) findViewById(R.id.textStartTime);
		textEndTime = (EditText) findViewById(R.id.textEndTime);
		buttonSave = (Button) findViewById(R.id.buttonSave);
		
		buttonSave.setOnClickListener(this);
		buttonStartTime.setOnClickListener(this);
		buttonEndTime.setOnClickListener(this);

		dayDropDownList.setOnItemSelectedListener(this);
		buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
		buttonUpdate.setOnClickListener(this);
		ArrayAdapter<CharSequence> dayDropDownListAdapter = ArrayAdapter
				.createFromResource(this, R.array.dayDropDownList,
						android.R.layout.simple_spinner_item);
		dayDropDownListAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		dayDropDownList.setAdapter(dayDropDownListAdapter);
		dayDropDownList.setOnItemSelectedListener(this);

		ArrayAdapter<CharSequence> favSpotDropDownListAdapter = ArrayAdapter
				.createFromResource(this, R.array.favSpotDropDownList,
						android.R.layout.simple_spinner_item);
		favSpotDropDownListAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		favSpotDropDownList.setAdapter(favSpotDropDownListAdapter);
		favSpotDropDownList.setOnItemSelectedListener(this);
		
		
		rideData.setLocation(String.valueOf(favSpotDropDownList.getSelectedItem()));
		rideData.setDay(String.valueOf(dayDropDownList.getSelectedItem()));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonSave:

			Toast.makeText(
					CarOwnerSetAvailbleActivity.this,
					"You selected : "
							+ "\n"
							+ "Day : "
							+ String.valueOf(dayDropDownList.getSelectedItem())
							+ "\n"
							+ "Location : "
							+ String.valueOf(favSpotDropDownList
									.getSelectedItem()), Toast.LENGTH_SHORT)
					.show();

			carOwnerSet.saveData();

			break;

		case R.id.buttonUpdate:
			System.out.println("update");
			AsyncTask<String, String, String> checkTimings=carOwnerSet.updateData();
			try {
				if (checkTimings.get() != "") {
					System.out.println("timings not set set it first");
					Toast.makeText(CarOwnerSetAvailbleActivity.this,
							"Please save the timings before update", 2000)
							.show();
				} else {
					Toast.makeText(
							CarOwnerSetAvailbleActivity.this,
							"You selected : "
									+ "\n"
									+ "Day : "
									+ String.valueOf(dayDropDownList
											.getSelectedItem())
									+ "\n"
									+ "Location : "
									+ String.valueOf(favSpotDropDownList
											.getSelectedItem()),
							Toast.LENGTH_SHORT).show();
				}
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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

							String selectedStartTime = hourOfDay + ":" + minute;
							rideData.setSelectedStartTime(selectedStartTime);
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

							String selectedEndTime = hourOfDay + ":" + minute;
							rideData.setSelectedEndTime(selectedEndTime);
						}
					}, tHour, tMinute, false);

			timePick.show();

			break;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}

	
}
