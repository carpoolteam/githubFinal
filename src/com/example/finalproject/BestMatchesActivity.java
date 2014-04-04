package com.example.finalproject;


import java.util.ArrayList;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;



public class BestMatchesActivity extends Activity {
	private DatabaseManager dManager;
	private SQLiteDatabase dataBase;

	private ArrayList<String> id = new ArrayList<String>();
	private ArrayList<String> match_fName = new ArrayList<String>();
	private ArrayList<String> match_lName = new ArrayList<String>();
	private ArrayList<String> match_city = new ArrayList<String>();
	private ArrayList<String> match_day = new ArrayList<String>();
	private ArrayList<String> match_arrTime = new ArrayList<String>();
	private ArrayList<String> match_depTime = new ArrayList<String>();

	

	private ListView matchList;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bestmatches_activity);

		matchList = (ListView) findViewById(R.id.List);

		dManager = new DatabaseManager(this);
		
		//add new record
		/*findViewById(R.id.btnAdd).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				Intent i = new Intent(getApplicationContext(),
						AddActivity.class);
				i.putExtra("update", false);
				startActivity(i);

			}
		});*/
		
		//click to contact 
		matchList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				Intent i = new Intent(getApplicationContext(),
						ContactActivity.class);
				i.putExtra("Fname", match_fName.get(arg2));
				i.putExtra("Lname", match_lName.get(arg2));
				//i.putExtra("ID", userId.get(arg2));
				i.putExtra("city", match_city.get(arg2));
				i.putExtra("day", match_day.get(arg2));
				i.putExtra("arrT", match_arrTime.get(arg2));
				i.putExtra("depT", match_depTime.get(arg2));
				// Need phone, email, count, gender, offering and status to be added for ContactActivity
				
				startActivity(i);

			}
		});	
	}
	
	@Override
	protected void onResume() {
		displayData();
		super.onResume();
	}

	/**
	 * displays data from SQLite
	 */
	private void displayData() {
		//dataBase = dManager.getWritableDatabase();
		//dManager = new DatabaseManager(BestMatchesActivity.this);
		Cursor usersCursor = dManager.retrieveUsersRows();
		//Cursor scheduleCursor = dManager.retrieveScheduleRows();
		//Cursor scheduleCursor = dManager.retrieveMessagesRows();
				//rawQuery("SELECT * FROM "		
				//+ DatabaseManager.TABLE_USERS , null);
		//String[] columns = new String[]{"email", "password", "fname" , "lname", "state", "county", "twon", "zip", "phone", "gender"};        
		//Cursor cursor = dManager.query("useres", columns, null , null, null, null, null, null); 
		
		/*addLayout.setVisibility(View.GONE);
        mydManager = new DatabaseManager(this);
        mydManager.openReadable();
        tableContent = mydManager.retrieveRows();
        response.setText("The rows in the products table are:");
        productRec = (ListView)findViewById(R.id.prodrec);        
        ArrayAdapter<String> arrayAdpt=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tableContent);
        productRec.setAdapter(arrayAdpt);  
        productRec.setVisibility(View.VISIBLE);
        mydManager.close();
        return true;*/
		
		
		
		
		id.clear();
		match_fName.clear();
		match_lName.clear();
		match_city.clear();
		match_day.clear();
		match_arrTime.clear();
		match_depTime.clear();
		
		if (usersCursor.moveToFirst()) {
			do {
				//id.add(mCursor.getString(mCursor.getColumnIndex(DbHelper.id)));
				id.add(usersCursor.getString(usersCursor.getColumnIndex("id")));
				match_fName.add(usersCursor.getString(usersCursor.getColumnIndex("fname")));
				match_lName.add(usersCursor.getString(usersCursor.getColumnIndex("lname")));
				match_city.add(usersCursor.getString(usersCursor.getColumnIndex("twon")));
				//match_day.add(mCursor.getString(mCursor.getColumnIndex("lname")));
				//match_arrTime.add(mCursor.getString(mCursor.getColumnIndex("lname")));
				//match_depTime.add(mCursor.getString(mCursor.getColumnIndex("lname")));

			} while (usersCursor.moveToNext());
		}
		BestMatchesAdapter disadpt = new BestMatchesAdapter(BestMatchesActivity.this,id, match_fName, match_lName, match_city);
		matchList.setAdapter(disadpt);
		usersCursor.close();
	}

	

}
