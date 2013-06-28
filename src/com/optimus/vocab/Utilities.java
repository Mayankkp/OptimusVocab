package com.optimus.vocab;

import java.util.Calendar;

import android.R.integer;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.util.Log;

/**
 * Created by Mayank on 24/06/13.
 */
public class Utilities {
	
	public static String TAG = "Utilities";
	static String SETTING_PREFS = "setting_prefs";
	static String LOCK_LIMIT = "lock_limit";
	static String TIME_OF_INSTALLATION = "time_install";
    public static String KEY_NUMBER_OF_QUESTIONS_ALLOWED = "numberOfQuestionsAllowed";
    public static String KEY_DATABASE_CREATED = "databaseCreated";
    public static int DEFAULT_NUMBER_OF_QUESTIONS_ALLOWED = 20;
    public static int TOTAL_NUMBER_OF_QUESTIONS = 3380;
    public static final String filename="previousdate.txt";



    public static  int getNumberOfQuestionAvailable(Context context){
        SharedPreferences prefs = context.getSharedPreferences(context.getApplicationContext().getPackageName(), Context.MODE_PRIVATE);
        return  prefs.getInt(KEY_NUMBER_OF_QUESTIONS_ALLOWED, DEFAULT_NUMBER_OF_QUESTIONS_ALLOWED);

    }

    public static int getTotalNumberOFQuestions(){
        return TOTAL_NUMBER_OF_QUESTIONS;
    }
    
    
    public static Typeface getBoldTypeFace(Context context){
    	return Typeface.createFromAsset(context.getAssets(),"fonts/Omnes-Bold.ttf");
    }
    public static Typeface getMediumTypeFace(Context context){
    	return Typeface.createFromAsset(context.getAssets(),"fonts/Omnes-Medium.ttf");
    }
    
    @SuppressLint("NewApi")
	public static void initializeDatabase(Context context){
        SharedPreferences prefs = context.getSharedPreferences(context.getApplicationContext().getPackageName(), Context.MODE_PRIVATE);
        int isDatabaseCreated = prefs.getInt(KEY_DATABASE_CREATED, 0);
        
        	Log.d(TAG, "Creating Database");
    		TestAdapter mDbHelper = new TestAdapter(context);
    		mDbHelper.createDatabase();
    		mDbHelper.open();
    		boolean success = mDbHelper.initializeDatabase();
    		if (success){
    		prefs.edit().putInt(KEY_DATABASE_CREATED, 1);
    		prefs.edit().apply();
    		
		}
    }
    
    
    public  static int getTestLockLimit(Context context) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SETTING_PREFS,Context.MODE_PRIVATE);
		return sharedPreferences.getInt(LOCK_LIMIT, 1);
	}

    public static void setTimeOfAppInstallation(Context context) {
		SharedPreferences preferences = context.getSharedPreferences(SETTING_PREFS,
				Context.MODE_PRIVATE);

		if (preferences.getBoolean("isFirstTime", true)) {
			Calendar calendar = Calendar.getInstance();
			//java.util.Date dates = calendar.getTime();
			long date = calendar.getTimeInMillis();
			Log.e("Tag", ""+ date);
			Editor editor = preferences.edit();
			editor.putLong(TIME_OF_INSTALLATION, date);
			editor.putBoolean("isFirstTime", false);
			editor.commit();
		}
	}
    
    public static  void setTestLockLimit(Context context) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SETTING_PREFS, Context.MODE_PRIVATE);
		int previous_lock_limit = sharedPreferences.getInt(LOCK_LIMIT, 1);
		Editor editor = sharedPreferences.edit();
		editor.putInt(LOCK_LIMIT, previous_lock_limit + 1);
		editor.commit();
	}
    
   
}
