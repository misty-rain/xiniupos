package com.xiniu.pos.ui.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xiniu.pos.R;
import com.xiniu.pos.bean.PosItemBean;
import com.xiniu.pos.bean.TempComCarBean;
import com.xiniu.pos.support.database.ItemDBTask;
import com.xiniu.pos.support.database.TempComCarDBTask;
import com.xiniu.pos.support.lib.MyAsyncTask;
import com.xiniu.pos.support.widgets.BottomFloatListView;
import com.xiniu.pos.ui.adapter.CommodityAdapter;
import com.xiniu.pos.ui.adapter.TempCarAdapter;
import com.xiniu.pos.ui.commodity.CommodityList;
import com.xiniu.pos.ui.interfaces.BaseActivity;
import com.xiniu.pos.ui.setttlement.SettleMent;

public class OrderCarList extends BaseActivity {
	protected BottomFloatListView carList;
	private ViewGroup vp;
	List<TempComCarBean> itemList = new ArrayList<TempComCarBean>();
	TempCarAdapter adapter;
	BigDecimal bdTotalprice;
	private TextView txtTotalPrice;
	private Button btnSettleMent;

	@Override
	protected int getLayoutId() {
		return R.layout.order_car_list;
	}

	@Override
	protected boolean hasBackButton() {
		return true;
	}

	@Override
	protected int getActionBarTitle() {
		return R.string.action_bar_title_orderlist;
	}

	@Override
	protected int getActionBarCustomView() {
		return R.layout.actionbar_custom_backtitle;
	}

	@Override
	public void onBackPressed() {

		super.onBackPressed();

	}

	@Override
	protected void init(Bundle savedInstanceState) {
		super.init(savedInstanceState);
		setActionBarTitle(getActionBarTitle());
		initView();
		new GetTempCarDataTask().execute("");

	}

	private void initView() {
		carList = (BottomFloatListView) findViewById(R.id.listordercar);
		vp = (ViewGroup) findViewById(R.id.bottombar);
		txtTotalPrice = (TextView) vp.findViewById(R.id.txtprice);
		btnSettleMent = (Button) vp.findViewById(R.id.btn_go_settlement);
		btnSettleMent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(OrderCarList.this, SettleMent.class);
				startActivity(intent);

			}
		});

	}

	private class GetTempCarDataTask extends
			MyAsyncTask<String, Void, List<TempComCarBean>> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.engc.jlcollege.support.lib.MyAsyncTask#doInBackground(Params[])
		 */
		@Override
		protected List<TempComCarBean> doInBackground(String... params) {
			itemList = TempComCarDBTask.getInstance().getTempComCarList();
			bdTotalprice = TempComCarDBTask.getInstance().getAllOrderPrice();
			return itemList;
		}

		@Override
		protected void onPostExecute(List<TempComCarBean> list) {
			adapter = new TempCarAdapter(OrderCarList.this, list, null);
			carList.setAdapter(adapter);
			carList.setBottomBar(vp);
			txtTotalPrice.setText("总计 : ￥" + bdTotalprice.toString());
			// adapter.notifyDataSetChanged();

		}

	}

}
