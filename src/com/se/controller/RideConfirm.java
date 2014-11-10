package com.se.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

public class RideConfirm extends Activity{
	
	HttpClient httpClient;
	HttpPost httppost;
	HttpResponse response;
	HttpEntity entity;
	InputStream isr;
	ParseApplication parse = new ParseApplication();
	
	public void confirmRide(String emailid_car1,String emailid_stu1,String date1,String time1,String location1){
		UserValidation validates = new UserValidation();
		validates.execute(emailid_car1,emailid_stu1,date1,time1,location1);
	}
	
	private class UserValidation extends AsyncTask<String,String,String>{

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String emailid_car = params[0];
			String emailid_stu = params[1];
			String date = params[2];
			String time = params[3];
			String location = params[4];
			try{
				//inserting the data into database
				String params1 = "s_email='" + emailid_stu + "'&&co_email='" + emailid_car
						+ "'&&rdate='" + date + "'&&rtime='" + time +"'&&rloc='" + location
						+ "'";
				String fullUrl = "http://omega.uta.edu/~sxk7162/update_ride_details.php?"
						+ params1;
				System.out.println("fullurl - " + fullUrl);
				httpClient = new DefaultHttpClient();
				httppost = new HttpPost(
						"http://omega.uta.edu/~sxk7162/update_ride_details.php?"
								+ params1);
				response = httpClient.execute(httppost);
				System.out.println(response);
				entity = response.getEntity();
				System.out.println("Entity Object" + entity.toString());
				if (entity != null) {
					isr = entity.getContent();
					parse.sendAccepted(emailid_stu);
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
			
			return null;
		}
		
	}

}
