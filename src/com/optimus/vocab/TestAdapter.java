package com.optimus.vocab;

import java.io.IOException;
import java.util.ArrayList;

import android.R.bool;
import android.R.integer;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TestAdapter {

	protected static final String TAG = "DataAdapter";

	private final Context mContext;
	private SQLiteDatabase mDb;
	private DataBaseHelper mDbHelper;

	public TestAdapter(Context context) {
		this.mContext = context;
		this.mDbHelper = new DataBaseHelper(this.mContext);
	}

	public void close() {
		this.mDbHelper.close();
	}

	public TestAdapter createDatabase() throws SQLException {
		try {
			this.mDbHelper.createDataBase();
		} catch (IOException mIOException) {
			Log.e(TestAdapter.TAG, mIOException.toString()
					+ "  UnableToCreateDatabase");
			throw new Error("UnableToCreateDatabase");
		}
		return this;
	}

	public ArrayList<Question> getQuestions(int startID, int endID) {

		ArrayList<Question> questions = new ArrayList<Question>();
		String selectQuery = "SELECT  * FROM questions where id >= " + startID
				+ " and id <= " + endID;
		Log.d("DataAdapter", selectQuery);
		try {
			Cursor cursor = this.mDb.rawQuery(selectQuery, null);
			if (cursor.moveToFirst())
				do {
					Question question = new Question(cursor.getString(0),
							cursor.getString(2), cursor.getString(3),
							cursor.getString(4), cursor.getString(5),
							cursor.getString(6), cursor.getString(1));
					Log.d(TestAdapter.TAG, question.getQuestion());
					questions.add(question);
				} while (cursor.moveToNext());
		} catch (Exception e) {
			Log.e("Error", "" + e.toString());
		}
		return questions;
	}

	
	public ArrayList<TestStatus> getAllTests() {

		ArrayList<TestStatus> tests = new ArrayList<TestStatus>();
		String selectQuery = "SELECT  * FROM testStatus";
		try {
			Cursor cursor = this.mDb.rawQuery(selectQuery, null);
			if (cursor.moveToFirst())
				do {
					TestStatus test = new TestStatus(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2), "Test " + cursor.getInt(2));
					tests.add(test);
				} while (cursor.moveToNext());
		} catch (Exception e) {
			Log.e("Error", "" + e.toString());
			e.printStackTrace();
		}
		return tests;
	}

	public TestAdapter open() throws SQLException {
		try {
			this.mDbHelper.openDataBase();
			this.mDbHelper.close();
			this.mDb = this.mDbHelper.getReadableDatabase();
		} catch (SQLException mSQLException) {
			Log.e(TestAdapter.TAG, "open >>" + mSQLException.toString());
			throw mSQLException;
		}
		return this;
	}
	
	
	
	public boolean initializeDatabase() {
		try {
		for(int i = 1 ; i < 170 ;i++){
			String selectQuery = "insert into testStatus values ("+i+",0, 0);";
			Log.d("Utilities",selectQuery);
			this.mDb.execSQL(selectQuery);
		}
		}catch (Exception e) {
			Log.e("Utilities", e.toString());
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
