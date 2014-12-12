package com.xiniu.pos.support.database;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xiniu.pos.bean.PosItemBean;
import com.xiniu.pos.bean.TempComCarBean;
import com.xiniu.pos.support.database.table.CategoryItemTable;
import com.xiniu.pos.support.database.table.CategoryTable;
import com.xiniu.pos.support.database.table.ItemTable;
import com.xiniu.pos.support.database.table.TempComCarTable;
import com.xiniu.pos.support.utils.GlobalContext;
import com.xiniu.pos.ui.adapter.TempCarAdapter;

/**
 * 
 * @ClassName: TempComCarDBTask
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author misty-rain
 * @date 2014-12-11 下午1:45:59
 * 
 */
public class TempComCarDBTask {
	private static TempComCarDBTask singleton = null;

	private SQLiteDatabase wsd = null;

	private SQLiteDatabase rsd = null;

	private TempComCarDBTask() {

	}

	public static TempComCarDBTask getInstance() {

		if (singleton == null) {
			// DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
			// SQLiteDatabase wsd = databaseHelper.getWritableDatabase();
			// SQLiteDatabase rsd = databaseHelper.getReadableDatabase();

			singleton = new TempComCarDBTask();
			singleton.wsd = new DBManager(GlobalContext.getInstance())
					.openDatabase();
			;
			singleton.rsd = new DBManager(GlobalContext.getInstance())
					.openDatabase();
			;
		}

		return singleton;
	}

	/**
	 * 创建or 修改 子商品表
	 * 
	 * @param category
	 * @return
	 */

	public int addOrUpdateItemToTempCar(TempComCarBean item) {

		ContentValues cv = new ContentValues();
		cv.put(TempComCarTable.ID, item.getId());
		cv.put(TempComCarTable.COMMODNAME, item.getCommodName());
		cv.put(TempComCarTable.SINGLECOMMODTOTALCOUNT,
				item.getSingleCommodTotalCount());
		cv.put(TempComCarTable.SINGLECOMMODPRICE, item.getSingleCommodPrice());
		cv.put(TempComCarTable.COMMODTOTALCOUNT, item.getCommodTotalCount());
		cv.put(TempComCarTable.COMMODTOTALPRICE, item.getCommodTotalPrice());
		cv.put(TempComCarTable.SINGLECOMPICURL, item.getSingleComPicUrl());
		cv.put(TempComCarTable.OPERATION_TIME, item.getOperationTime());

		Cursor c = rsd.query(TempComCarTable.TABLE_NAME, null,
				TempComCarTable.ID + "=?", new String[] { item.getId() }, null,
				null, null);

		if (c != null && c.getCount() > 0) {
			String[] args = { item.getId() };
			rsd.update(TempComCarTable.TABLE_NAME, cv, TempComCarTable.ID
					+ "=?", args);
			return 1;
		} else {

			wsd.insert(TempComCarTable.TABLE_NAME, TempComCarTable.ID, cv);
			return 0;
		}

	}

	/**
	 * 移除购物车的商品
	 * 
	 * @param comID
	 * @return
	 */
	public int removeTempCarCom(final String comID) {
		String[] params={comID};
		return  wsd.delete(TempComCarTable.TABLE_NAME, TempComCarTable.ID,
				params);

	}

	/**
	 * 获得所有购物车中的商品
	 * 
	 * @return
	 */
	public List<TempComCarBean> getTempComCarList() {

		String sql = "select * from " + TempComCarTable.TABLE_NAME;
		Cursor c = rsd.rawQuery(sql, null);
		List<TempComCarBean> list = new ArrayList<TempComCarBean>();
		while (c.moveToNext()) {
			TempComCarBean item = new TempComCarBean();
			int colid = c.getColumnIndex(TempComCarTable.ID);
			item.setId(c.getString(colid));

			colid = c.getColumnIndex(TempComCarTable.COMMODNAME);
			item.setCommodName(c.getString(colid));

			colid = c.getColumnIndex(TempComCarTable.SINGLECOMMODTOTALCOUNT);
			item.setSingleCommodTotalCount(c.getInt(colid));

			colid = c.getColumnIndex(TempComCarTable.SINGLECOMMODPRICE);
			item.setSingleCommodPrice(Double.parseDouble(c.getString(colid)));

			colid = c.getColumnIndex(TempComCarTable.COMMODTOTALCOUNT);
			item.setCommodTotalCount(c.getInt(colid));

			colid = c.getColumnIndex(TempComCarTable.COMMODTOTALPRICE);
			item.setCommodTotalPrice(Double.parseDouble(c.getString(colid)));

			colid = c.getColumnIndex(TempComCarTable.SINGLECOMPICURL);
			item.setSingleComPicUrl(c.getString(colid));

			colid = c.getColumnIndex(TempComCarTable.OPERATION_TIME);
			item.setOperationTime(c.getString(colid));

			list.add(item);
		}
		return list;

	}

	/*
	 * 获得订单的价格总额
	 */
	public BigDecimal getAllOrderPrice() {

		String sql = "select sum(" + TempComCarTable.COMMODTOTALPRICE
				+ ") as totalprice " + " from " + TempComCarTable.TABLE_NAME;
		Cursor c = rsd.rawQuery(sql, null);
		BigDecimal bd = null;
		while (c.moveToNext()) {
			int colid = c.getColumnIndex("totalprice");

			bd = new BigDecimal(c.getString(colid));
		}
		return bd;

	}

}
