package com.se.uta_rides;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignupActivity extends Activity{

	   private EditText username=null;
	   private EditText password=null;
	   private EditText email=null;
	   private EditText phone = null;
	   private Button Register;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
	      username = (EditText)findViewById(R.id.editText1);
	      password = (EditText)findViewById(R.id.editText2);
	      email = (EditText)findViewById(R.id.editText3);
	      phone = (EditText)findViewById(R.id.editText4);
	      Register = (Button)findViewById(R.id.button1);
		  Register.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				    Intent intent = new Intent(SignupActivity.this, Login.class);
				    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);   
				    startActivity(intent);
				  
				//Intent openActivity = new Intent(this,"");
			    //startActivity(openActivity);
				
			}
		});
		
	}
	   
	   
	   
	   
	   
}
