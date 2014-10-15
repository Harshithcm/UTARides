package com.se.uta_rides;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/*LoadAvailableListActivity - Loads the list of available Car Owners*/
public class LoadAvailableListActivity extends Activity {
	JSONArray jsonArray;
	ListView listView;
	ListAdapter adapter;
	ProgressDialog mProgressDialog;
	ArrayList<HashMap<String, String>> arrayList;
	ListView resultView;
	String dateSearch, timeSearch, result, locationSearch;
	String firstName, lastName, phoneNumber;
	private static final String NAME = "u_name";
	private static final String LAST_NAME = "co_lname";
	private static final String PHONE_NUMBER = "co_contact";	
	List<NameValuePair> newValuePairs;
	HttpClient httpClient;
	HttpPost httppost;
	HttpResponse response;
	HttpEntity entity;
	InputStream isr;
	JSONArray jArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_available_list);
		StrictMode.enableDefaults();
		resultView = (ListView) findViewById(R.id.listAvailable);

		/* Retrieves the Date and Time values sent from Search Activity */
		dateSearch = getIntent().getStringExtra("dateSearch");
		timeSearch = getIntent().getStringExtra("timeSearch");
		locationSearch = getIntent().getStringExtra("locationSearch");
		arrayList = new ArrayList<HashMap<String, String>>();

		try {
			System.out.println("time stamp - " + dateSearch + " " + timeSearch);
			newValuePairs = new ArrayList<NameValuePair>(2);
			newValuePairs.add(new BasicNameValuePair("day_id", dateSearch));
			newValuePairs.add(new BasicNameValuePair("time", timeSearch));
			System.out.println("Value" + newValuePairs.toString());

			/*Create a query parameters to be sent to web services*/
			String params = "day_id='" + dateSearch + "'&&time='" + timeSearch
					+ "'&&loc='"+locationSearch+"'";
			String fullUrl = "http://omega.uta.edu/~sxk7162/get_carowner_details.php?"
					+ params;
			System.out.println("fullurl - " + fullUrl);
			httpClient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://omega.uta.edu/~sxk7162/get_carowner_details.php?" + params);
			response = httpClient.execute(httppost);
			System.out.println(response);
			entity = response.getEntity();
			System.out.println("Entity Object" + entity.toString());
			if (entity != null) {
				isr = entity.getContent();
				System.out.println("byte - " + isr.available());
			}
		} catch (UnsupportedEncodingException e) {
			Log.e("log_tag",
					" Error in UnsupportedEncodingException - " + e.toString());
		} catch (ClientProtocolException e) {
			Log.e("log_tag",
					" Error in ClientProtocolException - " + e.toString());
		} catch (IOException e) {
			Log.e("log_tag", " Error in IOException - " + e.toString());
		} catch (Exception e) {
			Log.e("log_tag", " Error in Connection" + e.toString());
		}

		// Convert Response to String
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					isr, "iso-8859-1"), 8);
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

		try {
			jsonArray = new JSONArray(result);
		} catch (JSONException e) {
			Log.e("log_tag", "Error parsing data " + e.toString());
		}
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				firstName = jsonObject.getString(NAME);
				//lastName = jsonObject.getString(LAST_NAME);
				//phoneNumber = jsonObject.getString(PHONE_NUMBER);

				HashMap<String, String> map = new HashMap<String, String>();

				map.put(NAME, firstName);
				//map.put(LAST_NAME, lastName);
				//map.put(PHONE_NUMBER, phoneNumber);
				arrayList.add(map);
			}
				/*Retrieve a List View to set the list of available Rides*/
				resultView = (ListView) findViewById(R.id.listAvailable);
				ListAdapter adapter = new SimpleAdapter(
						LoadAvailableListActivity.this, arrayList,
						R.layout.activity_median, new String[] { NAME },
						new int[] { R.id.textMedian });
				resultView.setAdapter(adapter);
			
		} catch (JSONException e) {
			Log.e("Error", e.getMessage());
			e.printStackTrace();
		}
	}
}