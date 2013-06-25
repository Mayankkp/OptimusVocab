package com.optimus.vocab;

import java.util.ArrayList;

import android.R.integer;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends Activity implements OnItemClickListener {

	SimpleCursorAdapter mAdapter;
	ListView listViewTests;
	final static int TOTAL_QUESIONS = 3383;
	final static int QUESTION_PER_TEST = 20;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		configureUi();
		Log.d("DataAdapter", "called");
		

		int numberOFRows = Utilities.getTotalNumberOFQuestions()
				/ Utilities.getNumberOfQuestionAvailable(this);

	}

	private void configureUi() {
		listViewTests = (ListView) findViewById(R.id.list_tests);
		int no_of_tests = TOTAL_QUESIONS / QUESTION_PER_TEST;
		listViewTests.setAdapter(new list(no_of_tests, this));
		listViewTests.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		// start test Acivity by passing number of test
		Intent intent = new Intent(this, TestActivity.class);
		intent.putExtra("test_no", arg2 + 1);
		startActivity(intent);

	}

}
