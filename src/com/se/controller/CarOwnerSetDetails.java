package com.se.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import com.se.data.RideDetailsData;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

public class CarOwnerSetDetails extends Activity{
	
	
	HttpEntity entity;
	InputStream isr;
	JSONArray jArray;
	String result, status1;
	JSONArray jsonArray;
	RideDetailsData rideData = new RideDetailsData();
	public void saveData(){
		
		SharedPreferences userDetails = getSharedPreferences("MyData",
				Context.MODE_PRIVATE);
		String userName = userDetails.getString("name", "null");
		new SendData().execute(userName,
				rideData.getDay(),rideData.getLocation(),
				rideData.getSelectedStartTime(), rideData.getSelectedEndTime());
	}
	
	public AsyncTask<String, String, String> updateData(){
		
		UpdateData update = new UpdateData();

		SharedPreferences userDetails1 = getSharedPreferences("MyData",
				Context.MODE_PRIVATE);
		String userName1 = userDetails1.getString("name", "null");

		AsyncTask<String, String, String> checkTimings = update.execute(
				userName1,
				rideData.getDay(),rideData.getLocation(),
				rideData.getSelectedStartTime(), rideData.getSelectedEndTime());
		return checkTimings;
		
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
				String totimingsPHP = "email='" + email + "'&&" + "day_id='"
						+ day_id + "'&&" + "loc='" + encodedLoc + "'&&"
						+ "st='" + st + "'&&" + "et='" + et + "'";
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
				String totimingsPHP = "email='" + email + "'&&" + "day_id='"
						+ day_id + "'&&" + "loc='" + encodedLoc + "'&&"
						+ "st='" + st + "'&&" + "et='" + et + "'";
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
