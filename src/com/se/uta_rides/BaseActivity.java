package com.se.uta_rides;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View.OnClickListener;

public class BaseActivity extends Activity{
	public SharedPreferences.Editor loginPrefsEditor;
    public  SharedPreferences loginPreferences;
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.search, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    if(item.getItemId() == R.id.action_settings) {
	            openSettings();
	            return true;
	    }
	    else if(item.getItemId() == R.id.create_wishlist){
	    		Intent openAct = new Intent("com.se.uta_rides.CREATEWISHLIST");
	    		return true;
	    }
	    else if(item.getItemId()==R.id.view_requests){
	    		Intent openA = new Intent("com.se.uta_rides.VIEWREQUESTS");
	    		return true;
	    }
	    else
	    	return super.onOptionsItemSelected(item);
	}
void openSettings(){
	SharedPreferences preferences =getSharedPreferences("MyData",Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = preferences.edit();
    editor.clear();
    editor.commit();
    Intent openActivity = new Intent("com.se.uta_rides.LOGINACTIVITY");
	startActivity(openActivity);
}

}
