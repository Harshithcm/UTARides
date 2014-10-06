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
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.se.uta_rides.json.JSONfunctions;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class LoadAvailableListActivity extends Activity {
	JSONArray jsonArray;
	ListView listView;
	ListAdapter adapter;
	ProgressDialog mProgressDialog;
	ArrayList<HashMap<String, String>> arrayList;
	ListView resultView;
	String dateSearch, timeSearch, result;
	private static final String FIRST_NAME = "co_fname";
	private static final String LAST_NAME = "co_lname";
	private static final String PHONE_NUMBER = "co_contact";
	String firstName;
	String lastName;
	String phoneNumber;
	List<NameValuePair> newValuePairs;
	HttpClient httpClient;
	HttpPost httppost;
	HttpResponse response;
	HttpEntity entity;
	InputStream isr;
	//String result = " ";
	JSONArray jArray;

	// @SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_available);
		StrictMode.enableDefaults();
		resultView = (ListView) findViewById(R.id.listAvailable);

		dateSearch = getIntent().getStringExtra("dateSearch");
		timeSearch = getIntent().getStringExtra("timeSearch");

		arrayList = new ArrayList<HashMap<String, String>>();

		//new DownloadJSON().execute();
	//}

	//private class DownloadJSON extends AsyncTask<String, String, JSONArray>
		//	implements OnItemClickListener {
//		String firstName;
//		String lastName;
//		String phoneNumber;
//		List<NameValuePair> newValuePairs;
//		HttpClient httpClient;
//		HttpPost httppost;
//		HttpResponse response;
//		HttpEntity entity;
//		InputStream isr;
//		String result = " ";
//		JSONArray jArray;

	//	@Override
	/*	protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(LoadAvailableListActivity.this);
			mProgressDialog.setTitle("Loading List...");
			mProgressDialog
					.setMessage("Loading Available List of Car Onwers...");
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.show();
		}*/

	//	@Override
	//	protected JSONArray doInBackground(String... params) {
//			jsonArray = JSONfunctions.getJSONfromURL(dateSearch, timeSearch);
			//jsonArray = JSONfunctions.getJSONfromURL(dateSearch, timeSearch);
			try {
				System.out.println("time stamp - " + dateSearch + " " + timeSearch);
				newValuePairs = new ArrayList<NameValuePair>(2);
				newValuePairs.add(new BasicNameValuePair("day_id", dateSearch));
				newValuePairs.add(new BasicNameValuePair("time", timeSearch));
				System.out.println("Value"+newValuePairs.toString());

				String params = "day_id='" + dateSearch +"'&&time='" + timeSearch + "'";
				String fullUrl = "http://omega.uta.edu/~sxk7162/db_mysql_o.php?" + params;
				System.out.println("fullurl - " + fullUrl);
				httpClient = new DefaultHttpClient();
				httppost = new HttpPost(
						"http://omega.uta.edu/~sxk7162/db_mysql_o.php?" + params);
				// httppost = new HttpPost("http://192.168.0.13/db_mysql.php?day_id='2014/10/09'&&time='21:30'");
			//	httppost.setEntity(new UrlEncodedFormEntity(newValuePairs));
				response = httpClient.execute(httppost);
				entity = response.getEntity();
//				System.out.println("Entity value" + entity.getContentLength());
				System.out.println("Entity Object" + entity.toString());
				if (entity != null) {
					isr = entity.getContent();
					System.out.println("byte - " + isr.available());
				} //else {
				//	return jArray;
				//}
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
				System.out.println("result from ISR : "+result);
			} catch (Exception e) {
				Log.e("log_tag", "Error converting result " + e.toString());
			}

			try {
				jsonArray = new JSONArray(result);
			} catch (JSONException e) {
				Log.e("log_tag", "Error parsing data " + e.toString());
			}
			//return jsonArray;
	//	}

	//	@Override
	//	protected void onPostExecute(JSONArray jArray) {
		//	mProgressDialog.dismiss();

			try {
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					firstName = jsonObject.getString(FIRST_NAME);
					lastName = jsonObject.getString(LAST_NAME);
					phoneNumber = jsonObject.getString(PHONE_NUMBER);

					HashMap<String, String> map = new HashMap<String, String>();

					map.put(FIRST_NAME, firstName);
					map.put(LAST_NAME, lastName);
					map.put(PHONE_NUMBER, phoneNumber);
					arrayList.add(map);

					resultView = (ListView) findViewById(R.id.listAvailable);
					ListAdapter adapter = new SimpleAdapter(
							LoadAvailableListActivity.this, arrayList,
							R.layout.activity_median,
							new String[] { FIRST_NAME },
							new int[] { R.id.textMedian });
					resultView.setAdapter(adapter);
			//		resultView.setOnItemClickListener(this);
				}
			} catch (JSONException e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}

		}

		//@Override
	/*	public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Intent i = new Intent(LoadAvailableListActivity.this,
					CarOwnersDetailsActivity.class);
			i.putExtra("carOwnerFirstName", firstName);
			i.putExtra("carOwnerLastName", lastName);
			i.putExtra("carOwnerNumber", phoneNumber);
			startActivity(i);
		}*/
	
}