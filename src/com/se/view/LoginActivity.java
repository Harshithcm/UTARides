package com.se.view;

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

import com.se.uta_rides.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.SpannableString;
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
import com.se.controller.LoginAction;

public class LoginActivity extends Activity implements OnClickListener{

	JSONArray jsonArray;
	EditText  username=null;
	EditText password=null;
	TextView signup;
	private Button login;
	HttpClient httpClient;
	HttpPost httppost;
	HttpResponse response;
	HttpEntity entity;
	String status,result,uname,pasw,resp;
	InputStream isr;
	JSONArray jArray;
	TextView txt_Error;
	InputStream res = null;
	LoginAction log = new LoginAction();


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
		SharedPreferences sharedpreferences= getSharedPreferences("MyData",Context.MODE_PRIVATE);
		String name = sharedpreferences.getString("name",DEFAULT);
		String pass =  sharedpreferences.getString("pass",DEFAULT);
		if(!name.equals(DEFAULT) && !pass.equals(DEFAULT)){

			Intent openActivity = new Intent("com.se.uta_rides.LAUNCHACTIVITY");
			startActivity(openActivity);
			finish();
		}

		
		login = (Button)findViewById(R.id.button1);
		login.setOnClickListener(this);

		signup = (TextView) findViewById(R.id.textView4);
		SpannableString content = new SpannableString("signup");
		content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
		signup.setText(content);
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


	@Override
	public void onClick(View v) {


		// TODO Auto-generated method stub
		System.out.println("Entered OnClick");
		log.loginActivity(uname, pasw);

	}  

	

}
