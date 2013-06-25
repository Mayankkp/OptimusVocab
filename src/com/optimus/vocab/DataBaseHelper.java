package com.optimus.vocab;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataBaseHelper extends SQLiteOpenHelper {
	private static String TAG = "DataBaseHelper"; // Tag just for the LogCat
													// window
	// destination path (location) of our database on device
	private static String DB_PATH = "";
	private static String DB_NAME = "baseball.db";// Database name
	private SQLiteDatabase mDataBase;
	private final Context mContext;

	public DataBaseHelper(Context context) {
		super(context, DataBaseHelper.DB_NAME, null, 1);// 1? its Database
														// Version
		DataBaseHelper.DB_PATH = "/data/data/" + context.getPackageName()
				+ "/databases/";
		this.mContext = context;
	}

	// Check that the database exists here: /data/data/your package/databases/Da
	// Name
	private boolean checkDataBase() {
		File dbFile = new File(DataBaseHelper.DB_PATH + DataBaseHelper.DB_NAME);
		// Log.v("dbFile", dbFile + "   "+ dbFile.exists());
		return dbFile.exists();
	}

	@Override
	public synchronized void close() {
		if (this.mDataBase != null)
			this.mDataBase.close();
		super.close();
	}

	// Copy the database from assets
	private void copyDataBase() throws IOException {
		InputStream mInput = this.mContext.getAssets().open(
				DataBaseHelper.DB_NAME);
		String outFileName = DataBaseHelper.DB_PATH + DataBaseHelper.DB_NAME;
		OutputStream mOutput = new FileOutputStream(outFileName);
		byte[] mBuffer = new byte[1024];
		int mLength;
		while ((mLength = mInput.read(mBuffer)) > 0)
			mOutput.write(mBuffer, 0, mLength);
		mOutput.flush();
		mOutput.close();
		mInput.close();
	}

	public void createDataBase() throws IOException {
		// If database not exists copy it from the assets

		boolean mDataBaseExist = this.checkDataBase();
		if (!mDataBaseExist) {
			this.getReadableDatabase();
			this.close();
			try {
				// Copy the database from assests
				this.copyDataBase();
				Log.e(DataBaseHelper.TAG, "createDatabase database created");
			} catch (IOException mIOException) {
				throw new Error("ErrorCopyingDataBase");
			}
		}
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	// Open the database, so we can query it
	public boolean openDataBase() throws SQLException {
		String mPath = DataBaseHelper.DB_PATH + DataBaseHelper.DB_NAME;
		// Log.v("mPath", mPath);

		this.mDataBase = SQLiteDatabase.openDatabase(mPath, null,
				SQLiteDatabase.NO_LOCALIZED_COLLATORS
						| SQLiteDatabase.CREATE_IF_NECESSARY);
		// mDataBase = SQLiteDatabase.openDatabase(mPath, null,
		// SQLiteDatabase.NO_LOCALIZED_COLLATORS);
		return this.mDataBase != null;
	}

}