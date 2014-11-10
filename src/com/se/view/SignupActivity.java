package com.se.view;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import com.parse.ParseInstallation;
import com.se.controller.SignUpAction;
import com.se.data.User;
import com.se.uta_rides.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends Activity implements OnClickListener {

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
	String name="";
	SignUpAction signUp = new SignUpAction();
	User user = new User();

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		editSignUpEmail = (EditText) findViewById(R.id.editSignUpEmail);
		editSignUpPassword = (EditText) findViewById(R.id.editSignUpPassword);
		editSignUpConfirmPassword = (EditText) findViewById(R.id.editSignUpConfirmPassword);
		editSignUpName = (EditText) findViewById(R.id.editSignUpName);
		editPhoneNumber = (EditText) findViewById(R.id.editPhoneNumber);
		signUpButton = (Button) findViewById(R.id.signUpButton);

		signUpButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		System.out.println("Entered onClick");
		user.setEmail(editSignUpEmail.getText().toString());
		user.setPassword(editSignUpPassword.getText().toString());
		String confirmPassword = editSignUpConfirmPassword.getText().toString();
		user.setUserName(editSignUpName.getText().toString());
		user.setPhoneNumber(editPhoneNumber.getText().toString());
		// int intPhone = Integer.parseInt(phoneNumber);
		
		//storing the email id of student into parse database.
		
		ParseInstallation installation = ParseInstallation.getCurrentInstallation();
		System.out.println(installation);
		installation.put("User_id", user.getEmail());
		installation.saveInBackground();
		System.out.println("saved into DB");
		
		//validation sequence for all the inputs
		if(user.getEmail().isEmpty()){
			Toast.makeText(getApplicationContext(), "Enter values in email field",
					Toast.LENGTH_SHORT).show();
		}else if (user.getPassword().isEmpty()) {
			Toast.makeText(getApplicationContext(), "Enter values in password field",
					Toast.LENGTH_SHORT).show();
		}else if (confirmPassword.isEmpty()) {
			Toast.makeText(getApplicationContext(), "Enter values in confirm password field",
					Toast.LENGTH_SHORT).show();
		}else if (name.isEmpty()) {
			Toast.makeText(getApplicationContext(), "Enter values in name field",
					Toast.LENGTH_SHORT).show();
		}else if (user.getPhoneNumber().isEmpty()) {
			Toast.makeText(getApplicationContext(), "Enter values in phone number field",
					Toast.LENGTH_SHORT).show();
		}else if (!confirmPassword.equals(user.getPassword())) {
			Toast.makeText(getApplicationContext(), "Passwords don't match",
					Toast.LENGTH_SHORT).show();
		} else if (user.getPassword().length() < 8) {
			Toast.makeText(getApplicationContext(),
					"Password must contain atleast 8 characters",
					Toast.LENGTH_SHORT).show();
		} else if (!user.getEmail().contains("@mavs.uta.edu")) {
			Toast.makeText(getApplicationContext(), "Enter a valid Mavs ID",
					Toast.LENGTH_SHORT).show();
		} else if (!(user.getPhoneNumber().length() == 10)) {
			Toast.makeText(getApplicationContext(),
					"Enter a valid phone number", Toast.LENGTH_SHORT).show();
		} else {
			signUp.SignUp(user.getEmail(), confirmPassword, name, user.getPhoneNumber());
			Toast.makeText(
					SignupActivity.this,
					"Please Login", Toast.LENGTH_SHORT)
					.show();
			
			Intent i = new Intent("com.se.uta_rides.LOGINACTIVITY");
			startActivity(i);
			finish();
		}
	}

	
}