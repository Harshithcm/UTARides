package com.se.uta_rides;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignupActivity extends Activity implements OnClickListener{

	   private EditText editSignUpEmail;
	   private EditText editSignUpPassword;
	   private EditText editSignUpConfirmPassword;
	   private EditText editSignUpName;
	   private EditText editPhoneNumber;
	   private Button signUpButton;
	   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_signup);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}  
}