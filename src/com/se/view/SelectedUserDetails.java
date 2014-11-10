package com.se.view;

import java.util.ArrayList;
import java.util.HashMap;

import com.se.controller.ParseApplication;
import com.se.data.CarOwner;
import com.se.data.RideDetailsData;
import com.se.uta_rides.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SelectedUserDetails extends Activity implements OnClickListener{

	String passedName=null;
	String passedEmail=null;
	String passedPhone=null;
	String passedStartTime=null;
	String passedEndTime=null;
	String dateAcquired="";
	String timeAcquired="";
	String locSearch="";
	RideDetailsData rideData = new RideDetailsData();
	CarOwner carOwner = new CarOwner();
	//ArrayList<HashMap<String, String>> passedVar;
	private TextView passedViewName=null;
	private TextView passedViewEmail=null;
	private TextView passedViewPhone=null;
	private TextView passedViewStart=null;
	private TextView passedViewEnd=null;
	private Button book;
	String emailid_stu="";
	ParseApplication parse = new ParseApplication();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selected);
		
		final String DEFAULT="N/A";
		SharedPreferences sharedpreferences= getSharedPreferences("MyData",Context.MODE_PRIVATE);
		emailid_stu = sharedpreferences.getString("name",DEFAULT);
		
		
		
		//passedName=getIntent().getStringExtra("firstName");
		//passedEmail=getIntent().getStringExtra("email");
		//passedPhone=getIntent().getStringExtra("phoneNumber");
		//passedStartTime=getIntent().getStringExtra("startTime");
		//passedEndTime=getIntent().getStringExtra("endTime");
		//dateAcquired = getIntent().getStringExtra("datesearch");
		timeAcquired = getIntent().getStringExtra("timesearch");
		//locSearch = getIntent().getStringExtra("locsearch");
		
		passedViewName=(TextView)findViewById(R.id.sName);
		passedViewEmail=(TextView)findViewById(R.id.sEmail);
		passedViewPhone=(TextView)findViewById(R.id.sPhone);
		passedViewStart=(TextView)findViewById(R.id.sStart);
		passedViewEnd=(TextView)findViewById(R.id.sEnd);
		
		passedViewName.setText("Name ->"+carOwner.getFirstName());
		//passedViewName.setTypeface(null,Typeface.BOLD_ITALIC);
		passedViewEmail.setText("Email ->"+carOwner.getEmail());
		passedViewPhone.setText("Call | Msg ->"+carOwner.getPhoneNumber());
		passedViewStart.setText("Available From ->"+carOwner.getStartTime());
		passedViewEnd.setText("Available Till ->"+carOwner.getEndTime());
		
		book = (Button)findViewById(R.id.book);
		book.setOnClickListener(this);
		
		passedViewPhone.setOnClickListener(new View.OnClickListener() {

	        @Override
	        public void onClick(View arg0) {
	            // TODO Auto-generated method stub
	            //String phone_no= passedViewPhone.getText().toString().replaceAll("-", "");
	            //Intent callIntent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+passedPhone));
	            Intent callIntent = new Intent(Intent.ACTION_CALL);
	            callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            callIntent.setData(Uri.parse("tel:"+passedPhone));
	            //callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
	            startActivity(callIntent);
	        }
	    });
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.book:
			parse.sendNotificationtoCarowner(carOwner.getEmail(),rideData.getDay(),timeAcquired,rideData.getLocation(),emailid_stu);
		}
		
	}
	
	
	
}
