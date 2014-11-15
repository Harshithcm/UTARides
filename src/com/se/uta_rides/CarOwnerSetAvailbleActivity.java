package com.se.uta_rides;

import java.text.DateFormat;
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
	private EditText textStartTime, textEndTime, textNumberOfSeats;
	Calendar calendar;
	String selectedDate, selectedTime, selectedNumberOfSeats;
	String selectedStartTime, selectedEndTime;
	int mYear, mMonth, mDay, tHour, tMinute;
	TimePickerDialog timePick;
	HttpEntity entity;
	InputStream isr;
	JSONArray jArray;
	String result, status1;
	JSONArray jsonArray;

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
		
		textNumberOfSeats = (EditText) findViewById(R.id.textNumberOfSeats);
		selectedNumberOfSeats = textNumberOfSeats.getText().toString();
		System.out.println("number of seats="+selectedNumberOfSeats);
		buttonSave = (Button) findViewById(R.id.buttonSave);
		
		buttonSave.setOnClickListener(this);
		buttonStartTime.setOnClickListener(this);
		buttonEndTime.setOnClickListener(this);

		dayDropDownList.setOnItemSelectedListener(this);

		System.out.println(8);
		buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
		buttonUpdate.setOnClickListener(this);


		System.out.println(9);
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
	}

	@Override
	public void onClick(View v) {
		selectedNumberOfSeats = textNumberOfSeats.getText().toString();
		System.out.println("number of seats="+selectedNumberOfSeats);
		switch (v.getId()) {
		case R.id.buttonSave:
			if(selectedNumberOfSeats.isEmpty())
			{
				Toast.makeText(getApplicationContext(), "Enter values in number of seats field",
						Toast.LENGTH_SHORT).show();
			}
			else{
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
					selectedStartTime, selectedEndTime, selectedNumberOfSeats);
			}
			break;

		case R.id.buttonUpdate:
			System.out.println("update");
			if(selectedNumberOfSeats.isEmpty())
			{
				Toast.makeText(getApplicationContext(), "Enter values in number of seats field",
						Toast.LENGTH_SHORT).show();
			}
			else{
			
			UpdateData update = new UpdateData();

			SharedPreferences userDetails1 = getSharedPreferences("MyData",
					Context.MODE_PRIVATE);
			String userName1 = userDetails1.getString("name", "null");

			AsyncTask<String, String, String> checkTimings = update.execute(
					userName1,
					String.valueOf(dayDropDownList.getSelectedItem()),
					String.valueOf(favSpotDropDownList.getSelectedItem()),
					selectedStartTime, selectedEndTime, selectedNumberOfSeats);
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
		// if (position > 0) {
		// Toast.makeText(parent.getContext(),
		// "Saving your Preferences " + position + " ", Toast.LENGTH_SHORT)
		// .show();
		// }
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
				String encodedLoc = URLEncoder.encode(loc, "UTF-8").replace(
						"+", "%20");
				String st = params[3];
				String et = params[4];
				String seats = params[5];
				String totimingsPHP = "email='" + email + "'&&" + "day_id='"
						+ day_id + "'&&" + "loc='" + encodedLoc + "'&&"
						+ "st='" + st + "'&&" + "et='" + et + "'&&seats=" + seats;
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
			} catch (UnsupportedEncodingException e) {
				Log.e("CarOwnerSetAvailableActivity URL Encode - ",
						e.toString());
			} catch (IllegalArgumentException e) {
				Log.e("CarOwnerSetAvailableActivity Illegal Args - ",
						e.toString());
			} catch (HttpResponseException e) {
				Log.e("CarOwnerSetAvailableActivity Response - ", e.toString());
			} catch (ClientProtocolException e) {
				Log.e("CarOwnerSetAvailableActivity Protocol - ", e.toString());
			} catch (HttpHostConnectException e) {
				Log.e("CarOwnerSetAvailableActivity Connection - ",
						e.toString());
			} catch (IOException e) {
				Log.e("CarOwnerSetAvailableActivity IO - ", e.toString());
			}

			return null;
		}
	}

	private class UpdateData extends AsyncTask<String, String, String> {
		HttpClient httpClient;
		HttpPost httpPost;

		@Override
		protected String doInBackground(String... params) {
			try {
				String email = params[0];
				String day_id = params[1];
				String loc = params[2];
				String encodedLoc = URLEncoder.encode(loc, "UTF-8").replace(
						"+", "%20");
				String st = params[3];
				String et = params[4];
				String seats = params[5];
				String totimingsPHP = "email='" + email + "'&&" + "day_id='"
						+ day_id + "'&&" + "loc='" + encodedLoc + "'&&"
						+ "st='" + st + "'&&" + "et='" + et + "'&&seats=" + seats;
				httpClient = new DefaultHttpClient();
				httpPost = new HttpPost(
						"http://omega.uta.edu/~sxk7162/update_timings_check.php?"
								+ totimingsPHP);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			System.out.println("wooooooooo hooo");

			try {
				HttpResponse httpResponse = httpClient.execute(httpPost);
				entity = httpResponse.getEntity();
				if (entity != null) {
					isr = entity.getContent();
					System.out.println("byte - " + isr.available());
				}
			} catch (UnsupportedEncodingException e) {
				Log.e("CarOwnerSetAvailableActivity URL Encode - ",
						e.toString());
			} catch (IllegalArgumentException e) {
				Log.e("CarOwnerSetAvailableActivity Illegal Args - ",
						e.toString());
			} catch (HttpResponseException e) {
				Log.e("CarOwnerSetAvailableActivity Response - ", e.toString());
			} catch (ClientProtocolException e) {
				Log.e("CarOwnerSetAvailableActivity Protocol - ", e.toString());
			} catch (HttpHostConnectException e) {
				Log.e("CarOwnerSetAvailableActivity Connection - ",
						e.toString());
			} catch (IOException e) {
				Log.e("CarOwnerSetAvailableActivity IO - ", e.toString());
			}
			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(isr, "iso-8859-1"), 8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
				isr.close();

				result = sb.toString();
				System.out.println("result from ISR : " + result);
			} catch (Exception e) {
				Log.e("log_tag", "Error converting result " + e.toString());
			}

			System.out.println("CarOwnerUpdateAvailability - " + result);

			return result;
		}
	}
}