package com.xiniu.pos.support.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.xiniu.pos.R;
import com.xiniu.pos.support.database.table.TempComCarTable;

public class DBManager {
	private final int BUFFER_SIZE = 400000;
	public static final String DB_NAME = "pos.db"; // 保存的数据库文件名
	public static final String PACKAGE_NAME = "com.xiniu.pos";
	public static final String DB_PATH = "/data"
			+ Environment.getDataDirectory().getAbsolutePath() + "/"
			+ PACKAGE_NAME; // 在手机里存放数据库的位置

	private SQLiteDatabase database;
	private Context context;

	public DBManager(Context context) {
		this.context = context;
	}

	public SQLiteDatabase openDatabase() {
		this.database = this.openDatabase(DB_PATH + "/" + DB_NAME);
		database.execSQL(CREATE_TEMPCOMCAR_TABLE_SQL);
		return database;
	}

	static final String CREATE_TEMPCOMCAR_TABLE_SQL = "create table IF NOT EXISTS "
			+ TempComCarTable.TABLE_NAME + "(" + TempComCarTable.ID
			+ " text,"
			+ TempComCarTable.COMMODNAME + " text,"
			+ TempComCarTable.SINGLECOMMODTOTALCOUNT + " text,"
			+ TempComCarTable.SINGLECOMMODPRICE + " text,"
			+ TempComCarTable.COMMODTOTALCOUNT + " text,"
			+ TempComCarTable.COMMODTOTALPRICE + " text,"
			+ TempComCarTable.SINGLECOMPICURL + " text,"
			+ TempComCarTable.OPERATION_TIME + " datetime" + ");";

	private SQLiteDatabase openDatabase(String dbfile) {
		SQLiteDatabase db = null;
		try {
			if (!(new File(dbfile).exists())) {
				InputStream is = this.context.getResources().openRawResource(
						R.raw.pos); // 欲导入的数据库
				FileOutputStream fos = new FileOutputStream(dbfile);
				byte[] buffer = new byte[BUFFER_SIZE];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
				fos.close();
				is.close();
			}
			db = SQLiteDatabase.openOrCreateDatabase(dbfile, null);
		} catch (FileNotFoundException e) {
			Log.e("Database", "File not found");
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("Database", "IO exception");
			e.printStackTrace();
		}

		return db;
	}

	public void closeDatabase() {
		this.database.close();
	}

}
