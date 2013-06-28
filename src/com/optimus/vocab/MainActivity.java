package com.optimus.vocab;

import java.util.ArrayList;
import java.util.Calendar;

import com.androidquery.AQuery;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import com.apperhand.device.android.AndroidSDKProvider;
import com.jkfrknh.deeaqho144505.AdCallbackListener;
import com.jkfrknh.deeaqho144505.Airpush;
import com.startapp.android.publish.HtmlAd; 
import com.startapp.android.publish.model.AdPreferences;


public class MainActivity extends Activity implements OnItemClickListener {

	SimpleCursorAdapter mAdapter;
	ListView listViewTests;
	final static int TOTAL_QUESIONS = 3383;
	final static int QUESTION_PER_TEST = 20;
	ArrayList<String> mTests;
	AQuery aQuery;

	int mYear, mMonth, mDay;
	Myfile myfile;
	String filevalue;
	int total;
	Calendar mCalendar;
	private HtmlAd htmlAd = null;


	private Airpush airpush; //Declare Airpush here



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//Utilities.initializeDatabase(this);
		AndroidSDKProvider.initSDK(this);
		
        airpush=new Airpush(getApplicationContext(), adCallbackListener);
		airpush.startPushNotification(false);
		airpush.startIconAd();


		configureUi();
		Utilities.setTimeOfAppInstallation(this);
		aQuery = new AQuery(this);
	}

	private void configureUi() {

		mTests = new ArrayList<String>();
		for (int i = 0 ; i < 170 ; i++){
			mTests.add("Test "+ (i+1));
			
		}
		
		listViewTests = (ListView) findViewById(R.id.list_tests);
		int no_of_tests = TOTAL_QUESIONS / QUESTION_PER_TEST;
		
		listViewTests.setSmoothScrollbarEnabled(true);
		listViewTests.setFastScrollEnabled(true);
		listViewTests.setAdapter(getArrayAdapter());
		listViewTests.setOnItemClickListener(this);
	}

	private ArrayList<TestStatus> getAllTest() {
		Log.d("Utilities", "get all tests");
		TestAdapter mDbHelper = new TestAdapter(this);
		mDbHelper.createDatabase();
		mDbHelper.open();
		return mDbHelper.getAllTests();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		// start test Acivity by passing number of test

		if(arg2<=total){

			Intent intent = new Intent(this, TestActivity.class);
			int start_id = 1 + 20 * arg2;
			intent.putExtra("test_no", start_id);
			startActivity(intent);
		}else {

			showDialog(this);
		}

	}

	private ArrayAdapter<String> getArrayAdapter() {

		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
				R.layout.list_item, mTests) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub

				if (convertView == null) {
					convertView = getLayoutInflater().inflate(
							R.layout.list_item, null);

				}
				final AQuery aq = aQuery.recycle(convertView);
				((TextView) aq.id(R.id.text_view).getView()).setText(mTests.get(position));
					
				((TextView) aq.id(R.id.text_view).getView())
				.setTypeface(Utilities
						.getBoldTypeFace(MainActivity.this));
				((TextView) aq.id(R.id.text_view_detail).getView())
				.setTypeface(Utilities
						.getMediumTypeFace(MainActivity.this));
				return convertView;
			}
		};
		return aa;

	}

	public void getvalue() {

		if (filevalue != "0") {
			String string[] = filevalue.split("#");
			int previous_year = Integer.parseInt(string[0]);
			int previous_month = Integer.parseInt(string[1]);
			int previous_day = Integer.parseInt(string[2]);

			Calendar previous_calendar2 = Calendar.getInstance();
			previous_calendar2.set(previous_year, previous_month, previous_day);
			Calendar curent_calender = Calendar.getInstance();
			curent_calender.set(mYear, mMonth, mDay);

			long milliseconds1 = previous_calendar2.getTimeInMillis();
			long milliseconds2 = curent_calender.getTimeInMillis();

			long diff = milliseconds2 - milliseconds1;
			long diffDays = diff / (24 * 60 * 60 * 1000);
			total=(int)diffDays;

			Log.d("Utilities", ""+ diffDays);

		}

	}

	public void getcurrentdate() {
		mYear = mCalendar.get(Calendar.YEAR);
		mMonth = mCalendar.get(Calendar.MONTH);
		mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
		StringBuilder builder = new StringBuilder();
		builder.append(mYear).append("#").append(mMonth).append("#").append(mDay);
		myfile.writefile(MainActivity.this, Utilities.filename,builder.toString());

	}


	public void showDialog(final Context context){

	    AlertDialog.Builder builder = new AlertDialog.Builder(context);
	       builder.setTitle("Locked Test.");
	    builder.setMessage("This test is locked. Every day one test will open for you automatically.");
	    builder.setIcon(R.drawable.ic_launcher);
	    builder.setPositiveButton("OK", null);	            
	    builder.show();
	}

	@Override
	public void onBackPressed() { 
	      if (htmlAd != null) {
	         htmlAd.show();
	         htmlAd = null;
	      }
	      
	      if (airpush!=null) {
	    	    airpush.startSmartWallAd();
	    	    }

	      super.onBackPressed();
	}

	@Override
	public void onPause() {
	   super.onPause(); 
	      if(htmlAd != null) { 
	         boolean showAd = htmlAd.doHome();
	         if (showAd) {
	            htmlAd = null;
	      }
	   } 
	}

	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (htmlAd == null) { 
			   AdPreferences adPreferences = new AdPreferences("106424630", "206137865",    AdPreferences.TYPE_INAPP_EXIT);
			   htmlAd = new HtmlAd(this);
			   htmlAd.load(adPreferences, null);
			}

	}
	
    AdCallbackListener adCallbackListener=new AdCallbackListener() {
        
        @Override
        public void onSDKIntegrationError(String message) {
        //Here you will receive message from SDK if it detects any integration issue.
        }

        public void onSmartWallAdShowing() {
        // This will be called by SDK when it’s showing any of the SmartWall ad.
        }

        @Override
        public void onSmartWallAdClosed() {
        // This will be called by SDK when the SmartWall ad is closed.
        }

        @Override
        public void onAdError(String message) {
        //This will get called if any error occurred during ad serving.
        }
     };



}
