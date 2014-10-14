package com.se.uta_rides;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity{

	   private EditText  username=null;
	   private EditText password=null;
	   TextView signup;
	   private Button login;
	   //signup = (TextView) findViewById(R.id.textView4);
	    //signup.setMovementMethod(LinkMovementMethod.getInstance());
	   
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	
		  setContentView(R.layout.activity_log_in);
	      username = (EditText)findViewById(R.id.editText1);
	      password = (EditText)findViewById(R.id.editText2);
	      final String DEFAULT="N/A";
	      login = (Button)findViewById(R.id.button1);
	      
	      SharedPreferences sharedpreferences= getSharedPreferences("MyData",Context.MODE_PRIVATE);
	      String name = sharedpreferences.getString("name",DEFAULT);
		  String pass =  sharedpreferences.getString("pass",DEFAULT);
	      if(!name.equals(DEFAULT) && !pass.equals(DEFAULT)){
	      
	      Intent openActivity = new Intent("com.se.uta_rides.LAUNCHACTIVITY");
		  startActivity(openActivity);
		  finish();
	      }else{
		  
		  login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
												
				if((username.getText().toString().equals("admin") && 
					      password.getText().toString().equals("admin"))){    //&& (!username.equals("") && !password.equals(""))){
					      //Toast.makeText(getApplicationContext(), "Redirecting...", 
					      //Toast.LENGTH_SHORT).show();
					      
					      SharedPreferences sharedpreferences= getSharedPreferences("MyData",Context.MODE_PRIVATE);			      
					      SharedPreferences.Editor editor = sharedpreferences.edit();
					      editor.putString("name", username.getText().toString());
					      editor.putString("pass", password.getText().toString());
					      editor.commit();
					      Intent openActivity = new Intent("com.se.uta_rides.LAUNCHACTIVITY");
						  startActivity(openActivity);
						  finish();
					   }	
					   else{
					      Toast.makeText(getApplicationContext(), "Wrong Credentials",
					      Toast.LENGTH_SHORT).show();
					      				
					   }
							 			
			
				}
			});
	      
	      signup = (TextView) findViewById(R.id.textView4);
	      SpannableString content = new SpannableString("signup");
	      content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
	      signup.setText(content);
	      //signup.setAutoLinkMask(Linkify.class);
	      signup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			   	
			Intent openActivity = new Intent("com.se.uta_rides.SIGNUPACTIVITY");
		    startActivity(openActivity);
				finish();
			}
		});
	      
	  }  
	}
}
	
