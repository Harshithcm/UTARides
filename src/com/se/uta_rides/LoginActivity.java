package com.se.uta_rides;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.UnderlineSpan;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener{

	JSONArray jsonArray;
	private EditText  username=null;
	private EditText password=null;
	TextView signup;
	private Button login;
	HttpClient httpClient;
	HttpPost httppost;
	HttpResponse response;
	HttpEntity entity;
	String status;
	InputStream isr;
	JSONArray jArray;
	String result;
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//signup = (TextView) findViewById(R.id.textView4);
		//signup.setMovementMethod(LinkMovementMethod.getInstance());
		setContentView(R.layout.activity_log_in);
		username = (EditText)findViewById(R.id.editText1);
		password = (EditText)findViewById(R.id.editText2);
		final String DEFAULT="N/A";
		//login = (Button)findViewById(R.id.button1);
		System.out.println("onCreate finished");
	}
	
	
	/*SharedPreferences sharedpreferences= getSharedPreferences("MyData",Context.MODE_PRIVATE);
	      String name = sharedpreferences.getString("name",DEFAULT);
		  String pass =  sharedpreferences.getString("pass",DEFAULT);
	      if(!name.equals(DEFAULT) && !pass.equals(DEFAULT)){

	      Intent openActivity = new Intent("com.se.uta_rides.LAUNCHACTIVITY");
		  startActivity(openActivity);
		  finish();
	      }else
	      {*/
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		System.out.println("Entered OnClick");
		switch (v.getId()){
		case R.id.button1:
			String params = "username='" + username + "'password='" + password
			+ "'";
			String fullUrl = "http://omega.uta.edu/~sxk7162/verify_password?"
					+ params;
			System.out.println("fullurl - " + fullUrl);
			httpClient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://omega.uta.edu/~sxk7162/verify_password?" + params);
			//httppost = new HttpPost("http://omega.uta.edu/~sxk7162/db_mysql_o.php?");
			try {
				response = httpClient.execute(httppost);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(response);
			entity = response.getEntity();

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
					status = jsonObject.getString("Status");
					System.out.println("Printing the status");

				}

			} catch (JSONException e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}

			if((status=="True"))
			{    //&& (!username.equals("") && !password.equals(""))){
				//Toast.makeText(getApplicationContext(), "Redirecting...", 
				//Toast.LENGTH_SHORT).show();

				SharedPreferences sharedpreferences= getSharedPreferences("MyData",Context.MODE_PRIVATE);			      
				SharedPreferences.Editor editor = sharedpreferences.edit();
				editor.putString("name", username.getText().toString());
				editor.putString("pass", password.getText().toString());
				editor.commit();
				Intent openActivity = new Intent("com.se.uta_rides.LAUNCHACTIVITY");
				startActivity(openActivity);
				finish();
			}	
			else{
				Toast.makeText(getApplicationContext(), "Wrong Credentials",
						Toast.LENGTH_SHORT).show();

			}

		}

	


	signup = (TextView) findViewById(R.id.textView4);
	SpannableString content = new SpannableString("signup");
	content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
	signup.setText(content);
	//signup.setAutoLinkMask(Linkify.class);
	signup.setOnClickListener(new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			Intent openActivity = new Intent("com.se.uta_rides.SIGNUPACTIVITY");
			startActivity(openActivity);
			finish();
		}
	});

}  

}
