package com.xiniu.pos.support.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xiniu.pos.bean.CategoryBean;
import com.xiniu.pos.bean.PosItemBean;
import com.xiniu.pos.support.database.table.CategoryItemTable;
import com.xiniu.pos.support.database.table.CategoryTable;
import com.xiniu.pos.support.database.table.ItemTable;
import com.xiniu.pos.support.utils.GlobalContext;

/**
 * 
 * @ClassName: item
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author misty-rain
 * @date 2014-12-10 上午9:29:33
 * 
 */
public class ItemDBTask {

	private static ItemDBTask singleton = null;

	private SQLiteDatabase wsd = null;

	private SQLiteDatabase rsd = null;

	private ItemDBTask() {

	}

	public static ItemDBTask getInstance() {

		if (singleton == null) {
			//DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
			//SQLiteDatabase wsd = databaseHelper.getWritableDatabase();
			//SQLiteDatabase rsd = databaseHelper.getReadableDatabase();

			singleton = new ItemDBTask();
			singleton.wsd = new DBManager(GlobalContext.getInstance())
			.openDatabase();
			singleton.rsd = new DBManager(GlobalContext.getInstance())
					.openDatabase();
		}

		return singleton;
	}

	/**
	 * 创建or 修改 子商品表
	 * 
	 * @param category
	 * @return
	 */

	public int addOrUpdateItem(PosItemBean item) {

		ContentValues cv = new ContentValues();
		cv.put(ItemTable.ID, item.getId());
		cv.put(ItemTable.NAME, item.getName());
		cv.put(ItemTable.PICTURE_URL, item.getPicture_url());
		cv.put(ItemTable.UNIT_PRICE, item.getUnit_price());
		cv.put(ItemTable.IS_ACTIVE, item.getIs_active());
		cv.put(ItemTable.SPECIFICATION, item.getSpecification());
		cv.put(ItemTable.ROW_VERSION, item.getRow_version());
		cv.put(ItemTable.IS_DELETED, item.getIs_deleted());
		cv.put(ItemTable.CREATED_BY, item.getCreated_by());
		cv.put(ItemTable.CREATION_TIME, item.getCreation_time());
		cv.put(ItemTable.LAST_UPDATED_BY, item.getLast_updated_by());
		cv.put(ItemTable.LAST_UPDATE_TIME, item.getLast_update_time());

		Cursor c = rsd.query(ItemTable.TABLE_NAME, null, ItemTable.ID + "=?",
				new String[] { item.getId() }, null, null, null);

		if (c != null && c.getCount() > 0) {
			String[] args = { item.getId() };
			rsd.update(ItemTable.TABLE_NAME, cv, ItemTable.ID + "=?", args);
			return 1;
		} else {

			wsd.insert(ItemTable.TABLE_NAME, ItemTable.ID, cv);
			return 0;
		}

	}

	/**
	 * 获得所有商品集合
	 * 
	 * @return
	 */
	public List<PosItemBean> getItemList(final String id, final String name) {

		String sql = "select * from " + ItemTable.TABLE_NAME + " where "
				+ ItemTable.ID + " = " + id + " or " + ItemTable.NAME
				+ " like " + name + "%";
		Cursor c = rsd.rawQuery(sql, null);
		List<PosItemBean> list = new ArrayList<PosItemBean>();
		while (c.moveToNext()) {
			PosItemBean item = new PosItemBean();
			int colid = c.getColumnIndex(ItemTable.ID);
			item.setId(c.getString(colid));

			colid = c.getColumnIndex(ItemTable.NAME);
			item.setName(c.getString(colid));

			colid = c.getColumnIndex(ItemTable.PICTURE_URL);
			item.setPicture_url(c.getString(colid));

			colid = c.getColumnIndex(ItemTable.UNIT_PRICE);
			item.setUnit_price(Double.parseDouble(c.getString(colid)));

			colid = c.getColumnIndex(ItemTable.IS_ACTIVE);
			item.setIs_active(c.getString(colid));

			colid = c.getColumnIndex(ItemTable.SPECIFICATION);
			item.setSpecification(c.getString(colid));

			colid = c.getColumnIndex(ItemTable.ROW_VERSION);
			item.setRow_version(c.getString(colid));

			colid = c.getColumnIndex(ItemTable.IS_DELETED);
			item.setIs_deleted(c.getString(colid));

			colid = c.getColumnIndex(ItemTable.CREATED_BY);
			item.setCreated_by(c.getString(colid));

			colid = c.getColumnIndex(ItemTable.CREATION_TIME);
			item.setCreation_time(c.getString(colid));

			colid = c.getColumnIndex(ItemTable.LAST_UPDATED_BY);
			item.setLast_updated_by(c.getString(colid));

			colid = c.getColumnIndex(ItemTable.LAST_UPDATE_TIME);
			item.setLast_update_time(c.getString(colid));

			list.add(item);
		}
		return list;

	}

	/**
	 * 根据分类Id 得到具体的商品
	 * 
	 * @param categoryId
	 * @return
	 */
	public List<PosItemBean> getItemListByCategoryId(final String categoryId) {

		String sql = "select i." + ItemTable.NAME + " as iname,c." + CategoryTable.NAME
				+ ",i." + ItemTable.ID + ",i." + ItemTable.UNIT_PRICE + ",i."
				+ ItemTable.PICTURE_URL + " from "
				+ CategoryItemTable.TABLE_NAME + " pic inner join "
				+ ItemTable.TABLE_NAME + " i on  pic."
				+ CategoryItemTable.ITEM_ID + "=i." + ItemTable.ID
				+ " inner join " + CategoryTable.TABLE_NAME + " c on  c."
				+ CategoryTable.ID + "=pic." + CategoryItemTable.CATEGORY_ID
				+ " where pic."+CategoryItemTable.CATEGORY_ID+"=" + categoryId;
		Cursor c = rsd.rawQuery(sql, null);
		List<PosItemBean> list = new ArrayList<PosItemBean>();
		while (c.moveToNext()) {
			PosItemBean item = new PosItemBean();
			int colid = c.getColumnIndex(ItemTable.ID);
			item.setId(c.getString(colid));

			colid = c.getColumnIndex("iname");
			item.setName(c.getString(colid));

			colid = c.getColumnIndex(ItemTable.PICTURE_URL);
			item.setPicture_url(c.getString(colid));

			colid = c.getColumnIndex(ItemTable.UNIT_PRICE);
			item.setUnit_price(Double.parseDouble(c.getString(colid)));

			/*colid = c.getColumnIndex(ItemTable.IS_ACTIVE);
			item.setIs_active(c.getString(colid));

			colid = c.getColumnIndex(ItemTable.SPECIFICATION);
			item.setSpecification(c.getString(colid));

			colid = c.getColumnIndex(ItemTable.ROW_VERSION);
			item.setRow_version(c.getString(colid));

			colid = c.getColumnIndex(ItemTable.IS_DELETED);
			item.setIs_deleted(c.getString(colid));

			colid = c.getColumnIndex(ItemTable.CREATED_BY);
			item.setCreated_by(c.getString(colid));

			colid = c.getColumnIndex(ItemTable.CREATION_TIME);
			item.setCreation_time(c.getString(colid));

			colid = c.getColumnIndex(ItemTable.LAST_UPDATED_BY);
			item.setLast_updated_by(c.getString(colid));

			colid = c.getColumnIndex(ItemTable.LAST_UPDATE_TIME);
			item.setLast_update_time(c.getString(colid));*/

			list.add(item);
		}
		return list;

	}

}
