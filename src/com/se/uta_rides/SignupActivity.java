package com.se.uta_rides;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignupActivity extends Activity implements OnClickListener{

	private EditText editSignUpEmail;
	private EditText editSignUpPassword;
	private EditText editSignUpConfirmPassword;
	private EditText editSignUpName;
	private EditText editPhoneNumber;
	private Button signUpButton;
	HttpClient httpClient;
	HttpPost httppost;
	HttpResponse response;
	HttpEntity entity;
	InputStream isr;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		editSignUpEmail = (EditText)findViewById(R.id.editSignUpEmail);
		editSignUpPassword = (EditText)findViewById(R.id.editSignUpPassword);
		editSignUpConfirmPassword = (EditText)findViewById(R.id.editSignUpConfirmPassword);
		editSignUpName = (EditText)findViewById(R.id.editSignUpName);
		editPhoneNumber = (EditText)findViewById(R.id.editPhoneNumber);
		signUpButton = (Button)findViewById(R.id.signUpButton);
		
		signUpButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		System.out.println("Entered onClick");
		String email = editSignUpEmail.getText().toString();
		System.out.println("Printing email"+email);
		String password = editSignUpPassword.getText().toString();
		String confirmPassword = editSignUpConfirmPassword.getText().toString();
		String name = editSignUpName.getText().toString();
		String phoneNumber = editPhoneNumber.getText().toString();
		List<String> list = new ArrayList<String>(10);
		EnterValues enter = new EnterValues();
		enter.execute(email,password,name,phoneNumber);
		
		
	}  
	
	private class EnterValues extends AsyncTask<String,Void,String>{

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String email = params[0];
			String pasword = params[1];
			//String confirmPassword = params[2];
			String name = params[2];
			String phoneNumber = params[3];
			// TODO Auto-generated method stub
			try{
				String params1 = "email='" + email +"'&&"+ "pas='" + pasword+"'&&"+"name='"+name+"'&&"+"phno='"+phoneNumber+"'";
				String fullUrl = "http://omega.uta.edu/create_user_profile.php?"
						+ params;
				System.out.println("fullurl - " + fullUrl);
				httpClient = new DefaultHttpClient();
				/*httppost = new HttpPost(
						"http://192.168.0.13/verify_password_local.php?" + params);*/
				httppost = new HttpPost(
						"http://omega.uta.edu/~sxk7162/create_user_profile.php?" + params1);
				//httppost = new HttpPost("http://omega.uta.edu/~sxk7162/db_mysql_o.php?");
				System.out.println("httpPost is done");
				response = httpClient.execute(httppost);
				System.out.println(response);
				entity = response.getEntity();
				if (entity != null) {
					isr = entity.getContent();
					System.out.println("byte - " + isr.available());
				}
			}catch (UnsupportedEncodingException e) {
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