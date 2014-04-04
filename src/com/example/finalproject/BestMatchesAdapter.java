package com.example.finalproject;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class BestMatchesAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<String> id;
	private ArrayList<String> match_fName;
	private ArrayList<String> match_lName;
	private ArrayList<String> match_city;
	private ArrayList<String> match_day;
	private ArrayList<String> match_arrTime ;
	private ArrayList<String> match_depTime;
	

	public BestMatchesAdapter(Context c, ArrayList<String> id, ArrayList<String> match_fName, 
			ArrayList<String> match_lName, ArrayList<String> match_city, ArrayList<String> match_day, 
			ArrayList<String> match_arrTime, ArrayList<String> match_depTime) {
		this.mContext = c;

		this.id = id;
		this.match_fName = match_fName;
		this.match_lName = match_lName;
		this.match_city = match_city;
		this.match_day = match_day;
		this.match_arrTime = match_arrTime;
		this.match_depTime = match_depTime;
		
	}
	
	//temp constructor
	public BestMatchesAdapter(Context c, ArrayList<String> id, ArrayList<String> match_fName, 
			ArrayList<String> match_lName, ArrayList<String> match_city) {
		this.mContext = c;

		this.id = id;
		this.match_fName = match_fName;
		this.match_lName = match_lName;
		this.match_city = match_city;				
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return id.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int pos, View child, ViewGroup parent) {
		Holder mHolder;
		LayoutInflater layoutInflater;
		if (child == null) {
			layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			child = layoutInflater.inflate(R.layout.listcell, null);
			mHolder = new Holder();
			mHolder.txt_id = (TextView) child.findViewById(R.id.txt_id);
			mHolder.txt_fName = (TextView) child.findViewById(R.id.txt_fName);
			mHolder.txt_lName = (TextView) child.findViewById(R.id.txt_lName);
			mHolder.txt_city = (TextView) child.findViewById(R.id.txt_city);
			mHolder.txt_day = (TextView) child.findViewById(R.id.txt_day);
			mHolder.txt_arrTime = (TextView) child.findViewById(R.id.txt_arrTime);
			mHolder.txt_depTime = (TextView) child.findViewById(R.id.txt_depTime);
			
			child.setTag(mHolder);
		} else {
			mHolder = (Holder) child.getTag();
		}
		mHolder.txt_id.setText(id.get(pos));
		mHolder.txt_fName.setText(match_fName.get(pos));
		mHolder.txt_lName.setText(match_lName.get(pos));
		mHolder.txt_lName.setText(match_city.get(pos));
		mHolder.txt_lName.setText(match_day.get(pos));
		mHolder.txt_lName.setText(match_arrTime.get(pos));
		mHolder.txt_lName.setText(match_depTime.get(pos));
		

		return child;
	}

	public class Holder {
		TextView txt_id;
		TextView txt_fName;
		TextView txt_lName;
		TextView txt_city;
		TextView txt_day;
		TextView txt_arrTime;
		TextView txt_depTime;
		
	}

}
