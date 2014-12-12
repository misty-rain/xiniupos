package com.xiniu.pos.support.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.alibaba.fastjson.JSON;
import com.xiniu.api.domain.system.User;
import com.xiniu.pos.bean.AccountBean;
import com.xiniu.pos.bean.CategoryBean;
import com.xiniu.pos.support.database.table.AccountTable;
import com.xiniu.pos.support.database.table.CategoryTable;
import com.xiniu.pos.ui.oauth.OAuthActivity;

public class DatabaseManager {

	private static DatabaseManager singleton = null;

	private SQLiteDatabase wsd = null;

	private SQLiteDatabase rsd = null;

	private DatabaseManager() {

	}

	public static DatabaseManager getInstance() {

		if (singleton == null) {
			DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
			SQLiteDatabase wsd = databaseHelper.getWritableDatabase();
			SQLiteDatabase rsd = databaseHelper.getReadableDatabase();

			singleton = new DatabaseManager();
			singleton.wsd = wsd;
			singleton.rsd = rsd;
		}

		return singleton;
	}

	public OAuthActivity.DBResult addOrUpdateAccount(AccountBean account) {

		ContentValues cv = new ContentValues();
		cv.put(AccountTable.UID, account.getUid());
		cv.put(AccountTable.OAUTH_TOKEN, account.getAccess_token());
		cv.put(AccountTable.USERNAME, account.getUsername());
		cv.put(AccountTable.USERNICK, account.getUsernick());
		cv.put(AccountTable.AVATAR_URL, account.getAvatar_url());

		String json = JSON.toJSONString(account.getInfo());

		// String json = new Gson().toJson(account.getInfo());
		cv.put(AccountTable.INFOJSON, json);

		Cursor c = rsd.query(AccountTable.TABLE_NAME, null, AccountTable.UID
				+ "=?", new String[] { account.getUid() }, null, null, null);

		if (c != null && c.getCount() > 0) {
			String[] args = { account.getUid() };
			wsd.update(AccountTable.TABLE_NAME, cv, AccountTable.UID + "=?",
					args);
			return OAuthActivity.DBResult.update_successfully;
		} else {

			wsd.insert(AccountTable.TABLE_NAME, AccountTable.UID, cv);
			return OAuthActivity.DBResult.add_successfuly;
		}

	}

	public void updateAccountMyInfo(AccountBean account, User myUserBean) {
		String uid = account.getUid();
		String json = JSON.toJSONString(myUserBean);

		ContentValues cv = new ContentValues();
		cv.put(AccountTable.UID, uid);
		cv.put(AccountTable.INFOJSON, json);

		int c = rsd.update(AccountTable.TABLE_NAME, cv,
				AccountTable.UID + "=?", new String[] { uid });
	}

	public AccountBean getAccount(String id) {

		String sql = "select * from " + AccountTable.TABLE_NAME + " where "
				+ AccountTable.UID + " = " + id;
		Cursor c = rsd.rawQuery(sql, null);
		if (c.moveToNext()) {
			AccountBean account = new AccountBean();
			int colid = c.getColumnIndex(AccountTable.OAUTH_TOKEN);
			account.setAccess_token(c.getString(colid));

			colid = c.getColumnIndex(AccountTable.USERNICK);
			account.setUsernick(c.getString(colid));

			colid = c.getColumnIndex(AccountTable.UID);
			account.setUid(c.getString(colid));

			colid = c.getColumnIndex(AccountTable.AVATAR_URL);
			account.setAvatar_url(c.getString(colid));

			String json = c.getString(c.getColumnIndex(AccountTable.INFOJSON));

			User value = JSON.parseObject(json, User.class);
			account.setInfo(value);

			return account;
		}
		return null;

	}

}
