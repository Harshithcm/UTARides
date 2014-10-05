//package com.se.uta_rides.adapters;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import android.R;
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//public class ListViewAdapter extends BaseAdapter implements OnClickListener{
//	Context context;
//	LayoutInflater inflater;
//	ArrayList<HashMap<String, String>> data;
//	HashMap<String, String> resultp = new HashMap<String, String>();
//	
//	public ListViewAdapter(Context context, ArrayList<HashMap<String, String>> arrayList){
//		this.context = context;
//		data = arrayList;
//	}
//	
//	@Override
//	public int getCount(){
//		return data.size();
//	}
//	
//	@Override
//	public Object getItem(int position){
//		return null;
//	}
//	
//	@Override
//	public long getItemId(int position){
//		return 0;
//	}
//
//	public View getView(int position, View convertView, ViewGroup parent) {
//		TextView riderName;
//		TextView riderNumber;
//		
//		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		
//		View itemView = inflater.inflate(R.layout.activity_carowner_details, parent, false);
//		resultp = data.get(position);
//
//		
//		riderName = (TextView) itemView.findViewById(R.id.riderName);
//		riderNumber = (TextView) itemView.findViewById(R.id.riderNumber);
//		
//		riderName.setText(resultp.get("riderName"));
//		riderNumber.setText(resultp.get("riderNumber"));
//		
//		itemView.setOnClickListener(this);
//	}
//
//	@Override
//	public void onClick(View v) {
//		resultp = data.get(position);
//		
//	}
//}