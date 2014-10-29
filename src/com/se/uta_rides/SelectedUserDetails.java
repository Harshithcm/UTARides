package com.se.uta_rides;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SelectedUserDetails extends Activity{

	String passedName=null;
	String passedEmail=null;
	String passedPhone=null;
	String passedStartTime=null;
	String passedEndTime=null;
	//ArrayList<HashMap<String, String>> passedVar;
	private TextView passedViewName=null;
	private TextView passedViewEmail=null;
	private TextView passedViewPhone=null;
	private TextView passedViewStart=null;
	private TextView passedViewEnd=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selected);
		
		passedName=getIntent().getStringExtra("firstName");
		passedEmail=getIntent().getStringExtra("email");
		passedPhone=getIntent().getStringExtra("phoneNumber");
		passedStartTime=getIntent().getStringExtra("startTime");
		passedEndTime=getIntent().getStringExtra("endTime");
		
		passedViewName=(TextView)findViewById(R.id.sName);
		passedViewEmail=(TextView)findViewById(R.id.sEmail);
		passedViewPhone=(TextView)findViewById(R.id.sPhone);
		passedViewStart=(TextView)findViewById(R.id.sStart);
		passedViewEnd=(TextView)findViewById(R.id.sEnd);
		
		passedViewName.setText("Name:"+passedName);
		//passedViewName.setTypeface(null,Typeface.BOLD_ITALIC);
		passedViewEmail.setText("Email:"+passedEmail);
		passedViewPhone.setText("Call | Msg -> "+passedPhone);
		passedViewPhone.setTypeface(null,Typeface.BOLD_ITALIC);
		passedViewStart.setText("Begin:"+passedStartTime);
		passedViewEnd.setText("End:"+passedEndTime);
		
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
	
}
