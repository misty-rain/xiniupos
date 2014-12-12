package com.xiniu.pos.ui.commodity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xiniu.pos.R;
import com.xiniu.pos.bean.CategoryBean;
import com.xiniu.pos.bean.ItemBean;
import com.xiniu.pos.bean.PosItemBean;
import com.xiniu.pos.support.database.ItemDBTask;
import com.xiniu.pos.support.lib.MyAsyncTask;
import com.xiniu.pos.support.utils.AppLogger;
import com.xiniu.pos.ui.adapter.CommodityAdapter;
import com.xiniu.pos.ui.interfaces.BaseActivity;
import com.xiniu.pos.ui.order.OrderCarList;

/**
 * 
 * @ClassName: CommodityList
 * @Description: TODO商品列表
 * @author misty-rain
 * @date 2014-12-10 下午3:51:44
 * 
 */
@SuppressLint("NewApi")
public class CommodityList extends BaseActivity {
	protected PullToRefreshListView pullToRefreshListView;
	List<PosItemBean> itemList = new ArrayList<PosItemBean>();
	CommodityAdapter adapter;

	@Override
	protected int getLayoutId() {
		return R.layout.commodity_list;
	}

	@Override
	protected boolean hasBackButton() {
		return true;
	}

	@Override
	protected int getActionBarTitle() {
		return R.string.action_bar_title_commodity_detail;
	}

	@Override
	protected int getActionBarCustomView() {
		return R.layout.actionbar_custom_backtitle;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.action_bar_menu_commoditylist, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;

		case R.id.action_to_tempordercar:
			Intent intent = new Intent(CommodityList.this, OrderCarList.class);
			startActivity(intent);

			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void init(Bundle savedInstanceState) {
		super.init(savedInstanceState);

		setActionBarTitle(getActionBarTitle());
		initView();
		String categoryId = getIntent().getStringExtra("categoryId");
		new GetAuditListDataTask().execute(categoryId);

	}

	private void initView() {
		pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.listView);
		pullToRefreshListView
				.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {

					@Override
					public void onRefresh(
							PullToRefreshBase<ListView> refreshView) {

					}
				});

		pullToRefreshListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				/*
				 * Button btnAdd; PosItemBean ib; btnAdd = (Button)
				 * view.findViewById(R.id.btn_call_contacts); if (view
				 * instanceof Button) { ib = (PosItemBean) view.getTag();
				 * 
				 * } else {
				 * 
				 * ib = (PosItemBean) btnAdd.getTag();
				 * btnAdd.setOnClickListener(new OnClickListener() {
				 * 
				 * @Override public void onClick(View arg0) {
				 * 
				 * 
				 * } });
				 * 
				 * }
				 */
			}

		});

	}

	public ListView getListView() {
		return pullToRefreshListView.getRefreshableView();
	}

	private class GetAuditListDataTask extends
			MyAsyncTask<String, Void, List<PosItemBean>> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.engc.jlcollege.support.lib.MyAsyncTask#doInBackground(Params[])
		 */
		@Override
		protected List<PosItemBean> doInBackground(String... params) {

			itemList = ItemDBTask.getInstance().getItemListByCategoryId(
					params[0]);
			return itemList;
		}

		@Override
		protected void onPostExecute(List<PosItemBean> list) {
			adapter = new CommodityAdapter(CommodityList.this, list,
					getListView());
			pullToRefreshListView.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			pullToRefreshListView.onRefreshComplete();

		}

	}

}
