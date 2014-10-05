package com.se.uta_rides;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(2000);
				} catch(InterruptedException e){
					e.printStackTrace();
				} finally{
					Intent i = new Intent("com.se.uta_rides.LAUNCHACTIVITY");
					startActivity(i);
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
}
