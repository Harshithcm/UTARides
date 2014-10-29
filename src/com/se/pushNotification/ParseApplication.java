package com.se.pushNotification;

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
	
	public void sendNotification(String notify){
		
		ParseQuery pQuery = ParseInstallation.getQuery();
		pQuery.whereEqualTo("User_id", notify);
		System.out.println("query executed");
		JSONObject data = new JSONObject();
		try {
			data.put("User_id", notify);
			data.put("alert", "request");
			data.put("time", "timings");
			data.put("date", "monday");
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
	

}
