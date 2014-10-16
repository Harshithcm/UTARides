package com.se.uta_rides;

import java.util.Calendar;

import android.app.Activity;
import android.app.TimePickerDialog;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
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
	private Button buttonSave, buttonStartTime, buttonEndTime;
	private EditText textStartTime, textEndTime;
	Calendar calendar;
	String selectedDate, selectedTime;
	String selectedStartTime, selectedEndTime;
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
		System.out.println(3);
		buttonStartTime = (Button) findViewById(R.id.buttonStartTime);
		System.out.println(3.5);
		buttonEndTime = (Button) findViewById(R.id.buttonEndTime);
		System.out.println(4);
		textStartTime = (EditText) findViewById(R.id.textStartTime);
		System.out.println(4.5);
		textEndTime = (EditText) findViewById(R.id.textEndTime);
		System.out.println(4.6);
		buttonSave = (Button) findViewById(R.id.buttonSave);

		buttonSave.setOnClickListener(this);
		System.out.println(5);
		buttonStartTime.setOnClickListener(this);
		System.out.println(6);
		buttonEndTime.setOnClickListener(this);
		System.out.println(7);

		dayDropDownList.setOnItemSelectedListener(this);
		System.out.println(8);

		ArrayAdapter<CharSequence> dayDropDownListAdapter = ArrayAdapter
				.createFromResource(this, R.array.dayDropDownList,
						android.R.layout.simple_spinner_item);
		dayDropDownListAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		dayDropDownList.setAdapter(dayDropDownListAdapter);
		dayDropDownList.setOnItemSelectedListener(this);
		System.out.println(8);

		ArrayAdapter<CharSequence> favSpotDropDownListAdapter = ArrayAdapter
				.createFromResource(this, R.array.favSpotDropDownList,
						android.R.layout.simple_spinner_item);
		favSpotDropDownListAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		favSpotDropDownList.setAdapter(favSpotDropDownListAdapter);
		favSpotDropDownList.setOnItemSelectedListener(this);
		System.out.println(9);
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

			SharedPreferences userDetails = getSharedPreferences("MyData",
					Context.MODE_PRIVATE);
			String userName = userDetails.getString("name", "null");

			new SendData().execute(userName,
					String.valueOf(dayDropDownList.getSelectedItem()),
					String.valueOf(favSpotDropDownList.getSelectedItem()),
					selectedStartTime, selectedEndTime);

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
							
							selectedStartTime = hourOfDay + ":" + minute;
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

							selectedEndTime = hourOfDay + ":" + minute;
						}
					}, tHour, tMinute, false);

			timePick.show();

			break;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
//		if (position > 0) {
//			Toast.makeText(parent.getContext(),
//					"Saving your Preferences " + position + " ", Toast.LENGTH_SHORT)
//					.show();
//		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}

	private class SendData extends AsyncTask<String, String, String> {
		HttpClient httpClient;
		HttpPost httpPost;

		@Override
		protected String doInBackground(String... params) {
			try {
				String email = params[0];
				String day_id = params[1];
				String loc = params[2];
				String encodedLoc = URLEncoder.encode(loc, "UTF-8");
				String st = params[3];
				String et = params[4];
				String totimingsPHP = "email='" + email + "'&&" + "day_id='" + day_id
						+ "'&&" + "loc='" + encodedLoc + "'&&" + "st='" + st
						+ "'&&" + "et='" + et + "'";
				httpClient = new DefaultHttpClient();
				httpPost = new HttpPost(
						"http://omega.uta.edu/~sxk7162/enter_timings.php?"
								+ totimingsPHP);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			System.out.println("wooooooooo hooo");

			try {
				HttpResponse httpResponse = httpClient.execute(httpPost);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (HttpResponseException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (HttpHostConnectException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return null;
		}
	}
}