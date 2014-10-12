package com.se.uta_rides;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/*SplashActivity - Displays the Splash Screen*/
public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		/*
		 * Creates a thread to display the splash screen for 2 seconds and
		 * redirects to Ride preferences screen
		 */
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					Intent i = new Intent("com.se.uta_rides.LOGINACTIVITY");
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
