package com.xiniu.pos.ui.commodity;

import java.util.ArrayList;
import java.util.List;

import android.accounts.Account;
import android.os.Bundle;

import com.xiniu.pos.R;
import com.xiniu.pos.bean.AccountBean;
import com.xiniu.pos.bean.CategoryBean;
import com.xiniu.pos.bean.PosItemBean;
import com.xiniu.pos.support.database.CategoryDBTask;
import com.xiniu.pos.support.database.DBManager;
import com.xiniu.pos.support.database.DatabaseManager;
import com.xiniu.pos.support.utils.GlobalContext;
import com.xiniu.pos.support.utils.SharePreferenceUtil;
import com.xiniu.pos.support.utils.TimeUtil;
import com.xiniu.pos.ui.interfaces.BaseActivity;
import com.xiniu.pos.ui.main.MainActivity;
import com.xiniu.pos.ui.oauth.OAuthActivity.DBResult;

/**
 * 商品详细
 * 
 * @ClassName: CommodityDetail
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author misty-rain
 * @date 2014-12-9 下午3:15:42
 * 
 */
public class CommodityDetail extends BaseActivity {
	public DBManager dm;

	private SharePreferenceUtil sp;

	@Override
	protected int getLayoutId() {
		return R.layout.main_left_layout;
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
	public void onBackPressed() {

		super.onBackPressed();
	}

	@Override
	protected void init(Bundle savedInstanceState) {
		super.init(savedInstanceState);

		setActionBarTitle(getActionBarTitle());
		sp = GlobalContext.getInstance().getSpUtil();
		sp.saveItemList(initData());
		List<PosItemBean> list=sp.getItemList();

	}

	private List<PosItemBean> initData() {
		List<PosItemBean> list = new ArrayList<PosItemBean>();
		PosItemBean p1 = new PosItemBean();
		p1.setId("1");
		p1.setName("one");
		p1.setUnit_price(1.00);
		list.add(p1);
		PosItemBean p2 = new PosItemBean();
		p2.setId("2");
		p2.setName("two");
		p2.setUnit_price(2.00);
		list.add(p2);
		PosItemBean p3 = new PosItemBean();
		p3.setId("3");
		p3.setName("three");
		p3.setUnit_price(3.00);
		list.add(p3);
		return list;

	}

	private void importSqlData() {
		dm = new DBManager(CommodityDetail.this);
		dm.openDatabase();
		// dm.closeDatabase();
	}



}
