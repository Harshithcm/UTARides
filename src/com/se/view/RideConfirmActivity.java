package com.se.view;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import com.se.controller.ParseApplication;
import com.se.controller.RideConfirm;
import com.se.uta_rides.R;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RideConfirmActivity extends Activity implements OnClickListener{

	String emailid_car1="";
	String emailid_stu1="";
	String date1="";
	String time1="";
	String location1="";
	Button confirm,reject;
	HttpClient httpClient;
	HttpPost httppost;
	HttpResponse response;
	HttpEntity entity;
	InputStream isr;
	String status="";
	String studentName="";
	private TextView name=null;
	private TextView date=null;
	private TextView time=null;
	private TextView location=null;
	
	ParseApplication parse = new ParseApplication();
	RideConfirm ride = new RideConfirm();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rideconfirm);
		//reading the values from the sending intent which is Receiver class
		emailid_stu1 = getIntent().getStringExtra("email_id");
		studentName = getIntent().getStringExtra("f_name");
		date1 = getIntent().getStringExtra("date");
		time1 = getIntent().getStringExtra("time");
		location1 = getIntent().getStringExtra("loc");
		
		name=(TextView)findViewById(R.id.name);
		date=(TextView)findViewById(R.id.date);
		time=(TextView)findViewById(R.id.time);
		location=(TextView)findViewById(R.id.location);
		
		//setting values into the TextView
		name.setText("Name:"+studentName);
		date.setText("Date:"+date1);
		time.setText("Time:"+time1);
		location.setText("Location:"+location1);
		
		//collecting name from Shared preferences
		final String DEFAULT="N/A";
		SharedPreferences sharedpreferences= getSharedPreferences("MyData",Context.MODE_PRIVATE);
		emailid_car1 = sharedpreferences.getString("name",DEFAULT);
	
		
		System.out.println("email id of Car owner"+emailid_car1);
		System.out.println("email id of student"+emailid_stu1);
		System.out.println("date recorded"+date1);
		System.out.println("time recorded"+time1);
		confirm = (Button)findViewById(R.id.confirmButton);
		reject = (Button)findViewById(R.id.rejectButton);
		
		//setting up the OnClick Listeners for buttons
		confirm.setOnClickListener(this);
		reject.setOnClickListener(this);

	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.confirmButton:
			ride.confirmRide(emailid_car1,emailid_stu1,date1,time1,location1);
			break;
			
		case R.id.rejectButton:
			parse.sendNotificationtoStudent(emailid_stu1);
			break;
			
		}
	}
	

}
