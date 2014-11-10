package com.se.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class SignUpAction {
	
	HttpClient httpClient;
	HttpPost httppost;
	HttpResponse response;
	HttpEntity entity;
	InputStream isr;
	
	public void SignUp(String email,String password,String name,String phoneNumber){
		EnterValues enter = new EnterValues();
		enter.execute(email, password, name, phoneNumber);
	}
	
	
	private class EnterValues extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String email = params[0];
			String pasword = params[1];
			// String confirmPassword = params[2];
			String name = params[2];
			String encodeName = "";
			try {
				encodeName = URLEncoder.encode(name, "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String phoneNumber = params[3];
			// TODO Auto-generated method stub
			try {
				String params1 = "email='" + email + "'&&" + "pas='" + pasword
						+ "'&&" + "name='" + encodeName + "'&&" + "phno='"
						+ phoneNumber + "'";
				String fullUrl = "http://omega.uta.edu/create_user_profile.php?"
						+ params;
				System.out.println("fullurl - " + fullUrl);
				httpClient = new DefaultHttpClient();
				/*
				 * httppost = new HttpPost(
				 * "http://192.168.0.13/verify_password_local.php?" + params);
				 */
				httppost = new HttpPost(
						"http://omega.uta.edu/~sxk7162/create_user_profile.php?"
								+ params1);
				// httppost = new
				// HttpPost("http://omega.uta.edu/~sxk7162/db_mysql_o.php?");
				System.out.println("httpPost is done");
				response = httpClient.execute(httppost);
				System.out.println(response);
				entity = response.getEntity();
				if (entity != null) {
					isr = entity.getContent();
					System.out.println("byte - " + isr.available());
				}
			} catch (UnsupportedEncodingException e) {
				Log.e("log_tag", " Error in UnsupportedEncodingException - "
						+ e.toString());
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
