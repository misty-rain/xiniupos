package com.xiniu.pos.support.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xiniu.pos.bean.CategoryBean;
import com.xiniu.pos.support.database.table.CategoryTable;
import com.xiniu.pos.support.utils.GlobalContext;

/**
 * 
 * @ClassName: CategoryDBTask
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author misty-rain
 * @date 2014-12-9 下午7:19:41
 * 
 */
public class CategoryDBTask {
	private static CategoryDBTask singleton = null;

	private SQLiteDatabase wsd = null;

	private SQLiteDatabase rsd = null;

	private CategoryDBTask() {

	}

	public static CategoryDBTask getInstance() {

		if (singleton == null) {
			//DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
			//SQLiteDatabase wsd = databaseHelper.getWritableDatabase();
			//SQLiteDatabase rsd = databaseHelper.getReadableDatabase();

			singleton = new CategoryDBTask();
			singleton.wsd = new DBManager(GlobalContext.getInstance()).openDatabase();
			singleton.rsd = new DBManager(GlobalContext.getInstance()).openDatabase();
		}

		return singleton;
	}
	/**
	 * 创建or 修改 类型表
	 * 
	 * @param category
	 * @return
	 */

	public int addOrUpdateCategory(CategoryBean category) {

		ContentValues cv = new ContentValues();
		cv.put(CategoryTable.ID, category.getId());
		cv.put(CategoryTable.NAME, category.getName());
		cv.put(CategoryTable.DESCRIPTION, category.getDescription());
		cv.put(CategoryTable.PARENT_ID, category.getParent_id());
		cv.put(CategoryTable.ORDER_INDEX, category.getOrder_index());
		cv.put(CategoryTable.ROW_VERSION, category.getRow_version());
		cv.put(CategoryTable.IS_DELETED, category.getIs_deleted());
		cv.put(CategoryTable.CREATED_BY, category.getCreated_by());
		cv.put(CategoryTable.CREATION_TIME, category.getCreation_time());
		cv.put(CategoryTable.LAST_UPDATED_BY, category.getLast_updated_by());
		cv.put(CategoryTable.LAST_UPDATE_TIME, category.getLast_update_time());

		Cursor c = rsd.query(CategoryTable.TABLE_NAME, null,
				CategoryTable.ID + "=?", new String[] { category.getId() },
				null, null, null);

		if (c != null && c.getCount() > 0) {
			String[] args = { category.getId() };
			rsd.update(CategoryTable.TABLE_NAME, cv,
					CategoryTable.ID + "=?", args);
			return 1;
		} else {

			wsd.insert(CategoryTable.TABLE_NAME, CategoryTable.ID, cv);
			return 0;
		}

	}

	/**
	 * 获得所有分类集合
	 * 
	 * @return
	 */
	public List<CategoryBean> getCategoryList() {

		String sql = "select * from " + CategoryTable.TABLE_NAME;
		//Cursor c = rsd.rawQuery(sql, null);
		Cursor c=rsd.rawQuery(sql, null);
		List<CategoryBean> list = new ArrayList<CategoryBean>();
		while (c.moveToNext()) {
			CategoryBean category = new CategoryBean();
			int colid = c.getColumnIndex(CategoryTable.ID);
			category.setId(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.NAME);
			category.setName(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.DESCRIPTION);
			category.setDescription(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.PARENT_ID);
			category.setParent_id(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.ORDER_INDEX);
			category.setOrder_index(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.ROW_VERSION);
			category.setRow_version(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.IS_DELETED);
			category.setIs_deleted(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.CREATED_BY);
			category.setCreated_by(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.CREATION_TIME);
			category.setCreation_time(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.LAST_UPDATED_BY);
			category.setLast_updated_by(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.LAST_UPDATE_TIME);
			category.setLast_update_time(c.getString(colid));

			list.add(category);
		}
		return list;

	}
	
	/**
	 * 获得父分类集合
	 * @return
	 */
	public List<CategoryBean> getParentCategoryList() {

		String sql = "select * from " + CategoryTable.TABLE_NAME + " where " + CategoryTable.PARENT_ID + " is null";
		Cursor c = rsd.rawQuery(sql, null);
		List<CategoryBean> list = new ArrayList<CategoryBean>();
		while (c.moveToNext()) {
			CategoryBean category = new CategoryBean();
			int colid = c.getColumnIndex(CategoryTable.ID);
			category.setId(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.NAME);
			category.setName(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.DESCRIPTION);
			category.setDescription(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.PARENT_ID);
			category.setParent_id(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.ORDER_INDEX);
			category.setOrder_index(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.ROW_VERSION);
			category.setRow_version(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.IS_DELETED);
			category.setIs_deleted(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.CREATED_BY);
			category.setCreated_by(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.CREATION_TIME);
			category.setCreation_time(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.LAST_UPDATED_BY);
			category.setLast_updated_by(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.LAST_UPDATE_TIME);
			category.setLast_update_time(c.getString(colid));

			list.add(category);
		}
		return list;

	}
	/**
	 * 获得子分类list
	 * @return
	 */
	public List<CategoryBean> getChildCategoryList() {

		String sql = "select * from " + CategoryTable.TABLE_NAME + " where " + CategoryTable.PARENT_ID + " in ( select " + CategoryTable.ID + " from " + CategoryTable.TABLE_NAME + ")";
		Cursor c = rsd.rawQuery(sql, null);
		List<CategoryBean> list = new ArrayList<CategoryBean>();
		while (c.moveToNext()) {
			CategoryBean category = new CategoryBean();
			int colid = c.getColumnIndex(CategoryTable.ID);
			category.setId(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.NAME);
			category.setName(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.DESCRIPTION);
			category.setDescription(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.PARENT_ID);
			category.setParent_id(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.ORDER_INDEX);
			category.setOrder_index(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.ROW_VERSION);
			category.setRow_version(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.IS_DELETED);
			category.setIs_deleted(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.CREATED_BY);
			category.setCreated_by(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.CREATION_TIME);
			category.setCreation_time(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.LAST_UPDATED_BY);
			category.setLast_updated_by(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.LAST_UPDATE_TIME);
			category.setLast_update_time(c.getString(colid));

			list.add(category);
		}
		return list;

	}
	
	/**
	 * 获得子分类list
	 * @return
	 */
	public List<CategoryBean> getCategoryListByParentId(final String parentId) {

		String sql = "select * from " + CategoryTable.TABLE_NAME + " where " + CategoryTable.PARENT_ID + " = "+parentId;
		Cursor c = rsd.rawQuery(sql, null);
		List<CategoryBean> list = new ArrayList<CategoryBean>();
		while (c.moveToNext()) {
			CategoryBean category = new CategoryBean();
			int colid = c.getColumnIndex(CategoryTable.ID);
			category.setId(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.NAME);
			category.setName(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.DESCRIPTION);
			category.setDescription(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.PARENT_ID);
			category.setParent_id(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.ORDER_INDEX);
			category.setOrder_index(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.ROW_VERSION);
			category.setRow_version(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.IS_DELETED);
			category.setIs_deleted(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.CREATED_BY);
			category.setCreated_by(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.CREATION_TIME);
			category.setCreation_time(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.LAST_UPDATED_BY);
			category.setLast_updated_by(c.getString(colid));

			colid = c.getColumnIndex(CategoryTable.LAST_UPDATE_TIME);
			category.setLast_update_time(c.getString(colid));

			list.add(category);
		}
		return list;

	}


}
