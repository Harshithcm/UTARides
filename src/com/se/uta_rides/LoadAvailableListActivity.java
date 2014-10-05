package com.se.uta_rides;

import java.util.ArrayList;
import java.util.HashMap;

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

	// @SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_available);
		StrictMode.enableDefaults();
		resultView = (ListView) findViewById(R.id.listAvailable);

		dateSearch = getIntent().getStringExtra("dateSearch");
		timeSearch = getIntent().getStringExtra("timeSearch");

		arrayList = new ArrayList<HashMap<String, String>>();

		new DownloadJSON().execute();
	}

	private class DownloadJSON extends AsyncTask<String, String, JSONArray>
			implements OnItemClickListener {
		String firstName;
		String lastName;
		String phoneNumber;

		@Override
		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(LoadAvailableListActivity.this);
			mProgressDialog.setTitle("Loading List...");
			mProgressDialog
					.setMessage("Loading Available List of Car Onwers...");
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.show();
		}

		@Override
		protected JSONArray doInBackground(String... params) {
			jsonArray = JSONfunctions.getJSONfromURL(
					"http://192.168.0.13/db_mysql.php", dateSearch, timeSearch);
			return jsonArray;
		}

		@Override
		protected void onPostExecute(JSONArray jArray) {
			mProgressDialog.dismiss();

			try {
				for (int i = 0; i < jArray.length(); i++) {
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
					resultView.setOnItemClickListener(this);
				}
			} catch (JSONException e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}

		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Intent i = new Intent(LoadAvailableListActivity.this,
					CarOwnersDetailsActivity.class);
			i.putExtra("carOwnerFirstName", firstName);
			i.putExtra("carOwnerLastName", lastName);
			i.putExtra("carOwnerNumber", phoneNumber);
			startActivity(i);
		}
	}
}