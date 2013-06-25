package com.optimus.vocab;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

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
					ArrayList<String> list_inner = new ArrayList<String>();
					Question question = new Question(cursor.getString(0),
							cursor.getString(2), cursor.getString(3),
							cursor.getString(4), cursor.getString(5),
							cursor.getString(6), cursor.getString(7));
					Log.d(TAG, question.getQuestion());
					questions.add(question);
				} while (cursor.moveToNext());
		} catch (Exception e) {
			Log.e("Error", "" + e.toString());
		}
		return questions;
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
}
