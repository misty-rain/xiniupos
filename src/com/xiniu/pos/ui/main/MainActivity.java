package com.xiniu.pos.ui.main;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.xiniu.pos.R;
import com.xiniu.pos.support.database.DBManager;
import com.xiniu.pos.ui.interfaces.BaseActivity;
import com.xiniu.pos.ui.tab.MainTab;

public class MainActivity extends BaseActivity implements OnTabChangeListener {
	private FragmentTabHost mTabHost;
	public DBManager dm;


	@Override
	protected int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	protected void onPause() {
		super.onPause();

	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void init(Bundle savedInstanceState) {
		super.init(savedInstanceState);
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		if (android.os.Build.VERSION.SDK_INT > 10) {
			mTabHost.getTabWidget().setShowDividers(0);
		}
		initTabs();
		mTabHost.setCurrentTab(0);

	}
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}



	private void initTabs() {
		MainTab[] tabs = MainTab.values();
		final int size = tabs.length;
		for (int i = 0; i < size; i++) {
			MainTab mainTab = tabs[i];
			TabSpec tab = mTabHost.newTabSpec(getString(mainTab.getResName()));

			View indicator = inflateView(R.layout.tab_indicator);
			ImageView icon = (ImageView) indicator.findViewById(R.id.tab_icon);
			icon.setImageResource(mainTab.getResIcon());
			TextView title = (TextView) indicator.findViewById(R.id.tab_titile);
			title.setText(getString(mainTab.getResName()));
			tab.setIndicator(indicator);
			tab.setContent(new TabContentFactory() {

				@Override
				public View createTabContent(String tag) {
					return new View(MainActivity.this);
				}
			});

			mTabHost.addTab(tab, mainTab.getClz(), null);
			if (mainTab.equals(MainTab.SETTING)) {
				View con = indicator.findViewById(R.id.container);
				// con.setBackgroundColor(Color.parseColor("#00ff00"));
				/*
				 * mBvTweet = new BadgeView(this, con);
				 * mBvTweet.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
				 * mBvTweet.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
				 * mBvTweet
				 * .setBackgroundResource(R.drawable.tab_notification_bg);
				 */
			}
		}
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	
	@Override
	public void onTabChanged(String tabId) {
		final int size = mTabHost.getTabWidget().getTabCount();
		for (int i = 0; i < size; i++) {
			View v = mTabHost.getTabWidget().getChildAt(i);
			if (i == mTabHost.getCurrentTab()) {
				v.findViewById(R.id.tab_icon).setSelected(true);
				v.findViewById(R.id.tab_titile).setSelected(true);
			} else {
				v.findViewById(R.id.tab_icon).setSelected(false);
				v.findViewById(R.id.tab_titile).setSelected(false);
			}
		}
		supportInvalidateOptionsMenu();
	}
	
	
	
}
