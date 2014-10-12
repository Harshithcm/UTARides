//package com.se.uta_rides;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ListAdapter;
//import android.widget.ListView;
//import android.widget.SimpleAdapter;
//
//public class LoginActivity extends Activity implements OnClickListener {
//	
//	Button buttonLogin;
//	EditText userName,password;
//	InputStream isr;
//	JSONArray jsonArray;
//	String result;
//	String tag;
//	String error_msg;
//	String status;
//	
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//		buttonLogin = (Button) findViewById(R.id.logButton);
//		userName = (EditText) findViewById(R.id.uName);
//		password = (EditText) findViewById(R.id.password);
//		
//		buttonLogin.setOnClickListener(this);
//		
//	}
//
//	@Override
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//		ArrayList<NameValuePair> postParam = new ArrayList<NameValuePair>();
//		postParam.add(new BasicNameValuePair("username",userName.getText().toString()));
//		postParam.add(new BasicNameValuePair("password",password.getText().toString()));
//		
//		HttpClient httpClient = new DefaultHttpClient();
//		HttpPost httpPost = new HttpPost("http://localhost:8080/useraccount/register/doregister");
//		try {
//			httpPost.setEntity(new UrlEncodedFormEntity(postParam));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		HttpResponse response = null;
//		try {
//			response = httpClient.execute(httpPost);
//		} catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		HttpEntity entity = response.getEntity();
//		
//		try {
//			BufferedReader reader = new BufferedReader(new InputStreamReader(
//					isr, "iso-8859-1"), 8);
//			StringBuilder sb = new StringBuilder();
//			String line = null;
//			while ((line = reader.readLine()) != null) {
//				sb.append(line);
//			}
//			isr.close();
//
//			result = sb.toString();
//			System.out.println("result from ISR : "+result);
//		} catch (Exception e) {
//			Log.e("log_tag", "Error converting result " + e.toString());
//		}
//
//		try {
//			jsonArray = new JSONArray(result);
//		} catch (JSONException e) {
//			Log.e("log_tag", "Error parsing data " + e.toString());
//		}
//		
//		try {
//			for (int i = 0; i < jsonArray.length(); i++) {
//				JSONObject jsonObject = jsonArray.getJSONObject(i);
//				tag = jsonObject.getString("tag");
//				status = jsonObject.getString("status");
//				error_msg = jsonObject.getString("error_msg");
//
//				if (status=="false"){
//					
//				}
//			}
//		} catch (JSONException e) {
//			Log.e("Error", e.getMessage());
//			e.printStackTrace();
//		}
//		
//		
//	}
//	
//	
//
//}
