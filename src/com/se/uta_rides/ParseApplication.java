package com.se.uta_rides;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Application;
import android.widget.Button;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.PushService;
import com.se.uta_rides.CarOwnersDetailsActivity;

public class ParseApplication extends Application{
	
	
	Button send;
	@SuppressWarnings("deprecation")
	public void onCreate(){
		super.onCreate();
		Parse.initialize(this, Keys.applicationID , Keys.clientKey );
		PushService.setDefaultPushCallback(this, CarOwnersDetailsActivity.class);
		ParseInstallation.getCurrentInstallation().saveInBackground();
	}
	
	public void sendNotificationtoCarowner(String email,String date,String time,String loc,String emailid_stu){
		
		ParseQuery pQuery = ParseInstallation.getQuery();
		pQuery.whereEqualTo("User_id", email);
		System.out.println("query executed");
		JSONObject data = new JSONObject();
		try {
			data.put("emailid_stu", emailid_stu);
			data.put("alert", "request");
			data.put("time", time);
			data.put("date", date);
			data.put("location", loc);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ParsePush parsepush = new ParsePush();
		parsepush.setQuery(pQuery);
		parsepush.setData(data);
		parsepush.sendInBackground();
		System.out.println("Message sent");
		System.out.println(data.toString());
	}
	
	public void sendNotificationtoStudent(String emailid){
		ParseQuery pQuery = ParseInstallation.getQuery();
		pQuery.whereEqualTo("User_id", emailid);
		System.out.println("query executed");
		ParsePush parsepush = new ParsePush();
		parsepush.setQuery(pQuery);
		parsepush.setMessage("Rejected");
		parsepush.sendInBackground();
	}
	
	public void sendAccepted(String emailid){
		ParseQuery pQuery = ParseInstallation.getQuery();
		pQuery.whereEqualTo("User_id", emailid);
		System.out.println("query executed");
		ParsePush parsepush = new ParsePush();
		parsepush.setQuery(pQuery);
		parsepush.setMessage("accepted");
		parsepush.sendInBackground();
	}

}
