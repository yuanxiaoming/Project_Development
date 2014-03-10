package com.cs.animationvideo.adapter.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
	private DBHelper dbHelper ;
	private DBManager(Context context){
		dbHelper = new DBHelper(context);
	}
	
	private static SQLiteDatabase db = null ;
	
	public SQLiteDatabase getInstance(){
		if(db == null){
			syncInitDB();
		}
		return db ;
	}
	
	private synchronized void syncInitDB(){
		if(db == null){
			db = dbHelper.getWritableDatabase();
		}
	}
	
}
