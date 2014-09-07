package com.pytech.first.persistent.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pytech.first.models.Item;
import com.pytech.first.persistent.MyDBHelper;
import com.pytech.first.util.constants.Colors;

public class ItemDAO {
	public static final String TABLE_NAME = "item";

	public static final String KEY_ID = "id";
	public static final String DATETIME_COLUMN = "datetime";
	public static final String COLOR_COLUMN = "color";
	public static final String TITLE_COLUMN = "title";
	public static final String CONTENT_COLUMN = "content";
	public static final String FILENAME_COLUMN = "filename";
	public static final String LATITUDE_COLUMN = "latitude";
	public static final String LONGITUDE_COLUMN = "longitude";
	public static final String LASTMODIFY_COLUMN = "lastmodify";

	public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATETIME_COLUMN
			+ " INTEGER NOT NULL, " + COLOR_COLUMN + " INTEGER NOT NULL, " + TITLE_COLUMN + " TEXT NOT NULL, " + CONTENT_COLUMN + " TEXT NOT NULL, "
			+ FILENAME_COLUMN + " TEXT, " + LATITUDE_COLUMN + " REAL, " + LONGITUDE_COLUMN + " REAL, " + LASTMODIFY_COLUMN + " INTEGER)";

	private SQLiteDatabase db;
	
	private static ItemDAO instance;
	
	public static synchronized ItemDAO getInstance(Context context) {
		if(instance == null) {
			instance = new ItemDAO(context);
		}
		return instance;
	}

	public ItemDAO(Context context) {
		this.db = MyDBHelper.getDatabase(context);
	}

	public void close() {
		this.db.close();
	}

	public Item insert(Item item) {
		ContentValues cv = new ContentValues();

		cv.put(DATETIME_COLUMN, item.getDatetime());
		cv.put(COLOR_COLUMN, item.getColor().parseColor());
		cv.put(TITLE_COLUMN, item.getTitle());
		cv.put(CONTENT_COLUMN, item.getContent());
		cv.put(FILENAME_COLUMN, item.getFileName());
		cv.put(LATITUDE_COLUMN, item.getLatitude());
		cv.put(LONGITUDE_COLUMN, item.getLongitude());
		cv.put(LASTMODIFY_COLUMN, item.getLastModify());

		long id = db.insert(TABLE_NAME, null, cv);
		item.setId(id);
		return item;
	}

	public boolean update(Item item) {
		ContentValues cv = new ContentValues();

		cv.put(DATETIME_COLUMN, item.getDatetime());
		cv.put(COLOR_COLUMN, item.getColor().parseColor());
		cv.put(TITLE_COLUMN, item.getTitle());
		cv.put(CONTENT_COLUMN, item.getContent());
		cv.put(FILENAME_COLUMN, item.getFileName());
		cv.put(LATITUDE_COLUMN, item.getLatitude());
		cv.put(LONGITUDE_COLUMN, item.getLongitude());
		cv.put(LASTMODIFY_COLUMN, item.getLastModify());

		String where = KEY_ID + "=" + item.getId();
		return db.update(TABLE_NAME, cv, where, null) > 0;
	}

	public boolean delete(long id) {
		String where = KEY_ID + "=" + id;
		return db.delete(TABLE_NAME, where, null) > 0;
	}

	public List<Item> getAll() {
		List<Item> result = new ArrayList<>();
		Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null, null);
		while(cursor.moveToNext()) {
			result.add(getRecord(cursor));
		}
		cursor.close();
		return result;
	}

	public Item get(long id) {
		Item item = null;
		String where = KEY_ID + "=" + id;
		Cursor result = db.query(TABLE_NAME, null, where, null, null, null, null, null);
		if(result.moveToFirst()) {
			item = getRecord(result);
		}
		result.close();
		return item;
	}

	public Item getRecord(Cursor cursor) {
		Item result = new Item();
		result.setId(cursor.getLong(0));
		result.setDatetime(cursor.getLong(1));
		result.setColor(Colors.fromColorId(cursor.getInt(2)));
		result.setTitle(cursor.getString(3));
		result.setContent(cursor.getString(4));
		result.setFileName(cursor.getString(5));
		result.setLatitude(cursor.getDouble(6));
		result.setLongitude(cursor.getDouble(7));
		result.setLastModify(cursor.getLong(8));
		return result;
	}

	public int getCount() {
		int result = 0;
		Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);
		if(cursor.moveToNext()) {
			result = cursor.getInt(0);
		}
		return result;
	}
}
