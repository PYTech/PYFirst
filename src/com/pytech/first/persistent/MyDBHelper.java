package com.pytech.first.persistent;

import com.pytech.first.persistent.dao.ItemDAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "my_note";
	public static final int VERSION = 1;

	private static SQLiteDatabase database;

	public MyDBHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	public static SQLiteDatabase getDatabase(Context context) {
		if(database == null || !database.isOpen()) {
			init(context);
		}
		return database;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Do nothing for now.
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + ItemDAO.TABLE_NAME);
	    this.onCreate(db);
	}

	protected static void init(Context context) {
		database = new MyDBHelper(context, DATABASE_NAME, null, VERSION).getWritableDatabase();
		database.execSQL(ItemDAO.CREATE_TABLE);
	}
}
