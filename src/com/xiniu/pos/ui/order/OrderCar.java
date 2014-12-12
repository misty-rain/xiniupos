package com.xiniu.pos.ui.order;

import android.os.Bundle;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xiniu.pos.R;
import com.xiniu.pos.ui.interfaces.BaseActivity;

/**
 * 
* @ClassName: OrderCar 
* @Description: TODO 收银台 购物车list 
* @author misty-rain 
* @date 2014-12-11 下午5:47:09 
*
 */
public class OrderCar extends BaseActivity {
	
	@Override
	protected int getLayoutId() {
		return R.layout.member;
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
	
	}

}
