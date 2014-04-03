package com.example.finalproject;



import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManager {
     public static final String DB_NAME = "carpool";
     public static final String TABLE_MSG = "messages", TABLE_USERS = "users", TABLE_SCHEDULE = "schedule";
     public static final int DB_VERSION = 1;
     private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + " (email TEXT, password TEXT, fname TEXT, lname TEXT, state TEXT, county TEXT, twon TEXT, zip TEXT, phone TEXT, gender TEXT);";
     private static final String CREATE_TABLE_SCHEDULE = "CREATE TABLE " + TABLE_SCHEDULE + " (email TEXT, day TEXT, arrive_time TEXT, depart_time TEXT, offering TEXT, staus TEXT );";
     private static final String CREATE_TABLE_MSG = "CREATE TABLE " + TABLE_MSG + " (from_email TEXT, to_email TEXT, date TEXT, time TEXT, message TEXT);";
    		 
     private SQLHelper helper;
     private SQLiteDatabase db;
     private Context context;

     public DatabaseManager(Context c){
         this.context = c;
         helper=new SQLHelper(c);
         this.db = helper.getWritableDatabase();
    }

    public DatabaseManager openReadable() throws android.database.SQLException {
        helper=new SQLHelper(context);
        db = helper.getReadableDatabase();
        return this; 
    }
    
    public void close(){
        helper.close();
    }
    //Add new row to TABLE_USERS
    public boolean addUser(String email, String pass, String f, String l, String st, String county, String twon, String zip, String phone, String gen){
        ContentValues newEntry = new ContentValues();
        newEntry.put("email", email); 
        newEntry.put("password", pass); 
        newEntry.put("fname" , f);
        newEntry.put( "lname",l );
        newEntry.put("state" , st );
        newEntry.put( "county", county);
        newEntry.put( "twon",twon );
        newEntry.put( "zip", zip );
        newEntry.put( "phone", phone );
        newEntry.put( "gender", gen);
        try{db.insertOrThrow(TABLE_USERS, null, newEntry);}
        catch(Exception e) {
            Log.e("Error in inserting rows ", e.toString());
            e.printStackTrace();
            return false;           
        }
        db.close();
        return true;
    }
    
    //Add row to TABLE_SCHEDULE
    public boolean addNewDay(String email, String day, String arr_time, String dept_time, String offering, String status){
        ContentValues newEntry = new ContentValues();
        newEntry.put("email", email); 
        newEntry.put("day", day); 
        newEntry.put("arrive_time" , arr_time);
        newEntry.put( "depart_time",dept_time );
        newEntry.put("offerig" , offering );
        newEntry.put( "status", status);       
        try{db.insertOrThrow(TABLE_SCHEDULE, null, newEntry);}
        catch(Exception e) {
            Log.e("Error in inserting rows ", e.toString());
            e.printStackTrace();
            return false;           
        }
        db.close();
        return true;
    }

    
    //Add new row to TABLE_MSG
    public boolean addNewMessage(String tmail, String fmail, String date, String time, String msg){
        ContentValues newEntry = new ContentValues();
        newEntry.put("to_email", tmail);
        newEntry.put("from_email", fmail);
        newEntry.put("date", date); 
        newEntry.put("time" , time);
        newEntry.put( "message", msg );               
        try{db.insertOrThrow(TABLE_MSG, null, newEntry);}
        catch(Exception e) {
            Log.e("Error in inserting rows ", e.toString());
            e.printStackTrace();
            return false;           
        }
        db.close();
        return true;
    }
    
    // Getting single user's profile
 public ArrayList<String> getProfile(String email) {
	 ArrayList<String> profileRows = new ArrayList<String>();
	 String[] columns = new String[]{"email", "password", "fname" , "lname", "state", "county", "twon", "zip", "phone", "gender"};        
     Cursor cursor = db.query(TABLE_USERS, columns, "email =?" ,new String[] { email }, null, null, null, null);             															 	
     cursor.moveToFirst();
     
     while (cursor.isAfterLast() == false) {
    	 profileRows.add(cursor.getString(0) + ", " + cursor.getString(1) + ", " + cursor.getString(2) +
    			 ", " + cursor.getString(3) + ", " + cursor.getString(4) + ", "+ cursor.getString(5) +", "
    			 + cursor.getString(6) + ", " + cursor.getString(7) + ", " + cursor.getString(8) + ", " + cursor.getString(9) + ", ");
         cursor.moveToNext();
     }  
     if (cursor != null && !cursor.isClosed()) {
         cursor.close();
     }
     return profileRows;
 }
 
 //get specific day record(s)
 public ArrayList<String> getDaySchedule(String email, String day) {
	 ArrayList<String> dayRows = new ArrayList<String>();
	 String[] columns = new String[]{"email", "day", "arrive_time", "depart_time", "offering", "staus"};        
     Cursor cursor = db.query(TABLE_SCHEDULE, columns, "email =? AND day=?" ,new String[] { email, day }, null, null, null, null);             															 	
     cursor.moveToFirst();
     
     while (cursor.isAfterLast() == false) {
    	 dayRows.add(cursor.getString(0) + ", " + cursor.getString(1) + ", " + cursor.getString(2) +
    			 ", " + cursor.getString(3) + ", " + cursor.getString(4) + ", "+ cursor.getString(5) +", ");
         cursor.moveToNext();
     }  
     if (cursor != null && !cursor.isClosed()) {
         cursor.close();
     }
     return dayRows;
 }

 //get specific fromEmail message(s)
 public ArrayList<String> getFromEmailMessages(String femail) {
	 ArrayList<String> messageRows = new ArrayList<String>();
	 String[] columns = new String[]{"from_email", "to_email", "date", "time", "message"};        
     Cursor cursor = db.query(TABLE_MSG, columns, "email =?", new String[] { femail }, null, null, null, null);             															 	
     cursor.moveToFirst();
     
     while (cursor.isAfterLast() == false) {
    	 messageRows.add(cursor.getString(0) + ", " + cursor.getString(1) + ", " + cursor.getString(2) +
    			 		", " + cursor.getString(3) + ", " + cursor.getString(4) + ", ");
         cursor.moveToNext();
     }  
     if (cursor != null && !cursor.isClosed()) {
         cursor.close();
     }
     return messageRows;
 }
 
//get specific ToEmail message(s)
public ArrayList<String> getToEmailMessages(String to_email) {
	 ArrayList<String> messageRows = new ArrayList<String>();
	 String[] columns = new String[]{"from_email", "to_email", "date", "time", "message"};        
    Cursor cursor = db.query(TABLE_MSG, columns, "email =?", new String[] { to_email }, null, null, null, null);             															 	
    cursor.moveToFirst();
    
    while (cursor.isAfterLast() == false) {
   	 messageRows.add(cursor.getString(0) + ", " + cursor.getString(1) + ", " + cursor.getString(2) +
   			 		", " + cursor.getString(3) + ", " + cursor.getString(4) + ", ");
        cursor.moveToNext();
    }  
    if (cursor != null && !cursor.isClosed()) {
        cursor.close();
    }
    return messageRows;
}

/*
 * Getting All profile, messages and days methods go here
 */
     public class SQLHelper extends SQLiteOpenHelper {
        public SQLHelper(Context c){
            super(c, DB_NAME, null, DB_VERSION);
        }
        
     // Updating single contact
     public int updateProfile(String email, String update, String colomn) {
     //SQLiteDatabase db = this.getWritableDatabase();

     ContentValues values = new ContentValues();
     values.put("email", email);
     values.put(colomn, update);

     // updating row
     return db.update(TABLE_USERS, values, "email= ?",new String[] { email });
     }
     
     public int updateSchedule(String email, String update, String colomn) {
    	 
     ContentValues values = new ContentValues();
     values.put("email", email);
     values.put(colomn, update);

     // updating row
     return db.update(TABLE_SCHEDULE, values, "email= ?",
      new String[] { email });
     }
     
     // Deleting single user
     public void deleteProfile(String email) {  
    	 db.delete(TABLE_USERS, "email = ?",
    	 new String[] { email });  
     }
     
     public void deleteDaySchedule(String email, String day) {  
    	 db.delete(TABLE_USERS, "email = ? AND day=?",
    	 new String[] { email, day });  
     }   
     
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_USERS);
            db.execSQL(CREATE_TABLE_SCHEDULE);
            db.execSQL(CREATE_TABLE_MSG);                       
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("users table","Upgrading database i.e. dropping table and recreating it");
            Log.w("schedule table","Upgrading database i.e. dropping table and recreating it");
            Log.w("messages table","Upgrading database i.e. dropping table and recreating it");            
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHEDULE);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_MSG);
            onCreate(db);  
        }
    }
}