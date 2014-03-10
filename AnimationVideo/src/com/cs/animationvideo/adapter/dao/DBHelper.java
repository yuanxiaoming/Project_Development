package com.cs.animationvideo.adapter.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	public DBHelper(Context context) {
		super(context, "animationvideo.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE IF NOT EXISTS server_data(_id INTEGER PRIMARY KEY ,url VARCHAR UNIQUE , data VARCHAR NOT NULL)";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {

	}

}
