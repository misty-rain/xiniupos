package com.xiniu.pos.ui.setttlement;

import android.os.Bundle;

import com.xiniu.pos.R;
import com.xiniu.pos.ui.interfaces.BaseActivity;

/**
 * 结算
 * @author Administrator
 *
 */
public class SettleMent extends BaseActivity {
	
	@Override
	protected int getLayoutId() {
		return R.layout.settlement_main;
	}

	@Override
	protected boolean hasBackButton() {
		return true;
	}

	@Override
	protected int getActionBarTitle() {
		return R.string.action_bar_title_settlement_order;
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
