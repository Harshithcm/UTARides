package com.se.pushNotification;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.parse.ParsePushBroadcastReceiver;
import com.se.uta_rides.RideConfirmActivity;
import com.se.uta_rides.SplashActivity;

public class Receiver extends ParsePushBroadcastReceiver {
	
	
	/*public void onPushReceive(Context context, Intent intent) {
			final String TAG = "MyNotificationsReceiver";
		
		    try {
		      JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));

		      String notificationText = json.getString("alert");
		      System.out.println("notificationText = "+notificationText);
		    } catch (JSONException e) {
		      Log.d(TAG, "JSONException: " + e.getMessage());
		    }
	}*/
	
	
	@Override
	public void onPushOpen(Context context, Intent intent) {
		final String TAG = "MyNotificationsReceiver";
		String notificationText = "";
		String notify = "";
		String time = "";
		String date = "";
	    try {
	      JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
	      //JSONObject json1 = new JSONObject(intent.getExtras().getString("com.parse.Target"));
	      notificationText = json.getString("alert");
	      notify = json.getString("User_id");
	      time = json.getString("time");
	      date = json.getString("date");
	      System.out.println("notificationText = "+notificationText);
	      System.out.println("email id = "+notify);
	    } catch (JSONException e) {
	      Log.d(TAG, "JSONException: " + e.getMessage());
	    }
	    
	    
	    
	    if(notificationText.equalsIgnoreCase("request")){
		
	    //Log.e("Push", "Clicked");
	    Intent i = new Intent(context, RideConfirmActivity.class);
	    i.putExtras(intent.getExtras());
	    i.putExtra("email_id", notify);
	    i.putExtra("time", time);
	    i.putExtra("time", date);
	    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    context.startActivity(i);
	    }else{
	    	 Intent i = new Intent(context, SplashActivity.class);
	 	    i.putExtras(intent.getExtras());
	 	    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	 	    context.startActivity(i);
	    }
	}
}
