package com.se.uta_rides;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends Activity implements OnClickListener{

	private EditText editSignUpEmail;
	private EditText editSignUpPassword;
	private EditText editSignUpConfirmPassword;
	private EditText editSignUpName;
	private EditText editPhoneNumber;
	private Button signUpButton;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);

		editSignUpEmail = (EditText) findViewById(R.id.editSignUpEmail);
		editSignUpPassword = (EditText) findViewById(R.id.editSignUpPassword);  
		editSignUpConfirmPassword = (EditText) findViewById(R.id.editSignUpConfirmPassword);
		editSignUpName = (EditText) findViewById(R.id.editSignUpName);
		editPhoneNumber = (EditText) findViewById(R.id.editPhoneNumber);
		signUpButton = (Button) findViewById(R.id.signUpButton);
		
		signUpButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (editSignUpEmail.getText().toString().contains("@mavs.uta.edu")){
			Toast.makeText(SignupActivity.this, "Use a mavs.uta.edu Email Id", Toast.LENGTH_LONG).show();
		} 
	}
}