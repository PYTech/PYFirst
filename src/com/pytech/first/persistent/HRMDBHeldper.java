package com.pytech.first.persistent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class HRMDBHeldper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "hrm";
	public static final int VERSION = 1;

	private static SQLiteDatabase database;

	public HRMDBHeldper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	public static SQLiteDatabase getDatabase(Context context) {
		if(database == null || !database.isOpen()) {
			database = new HRMDBHeldper(context, DATABASE_NAME, null, VERSION).getWritableDatabase();
		}
		return database;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
