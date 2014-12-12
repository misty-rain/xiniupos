package com.xiniu.pos.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.xiniu.pos.R;
import com.xiniu.pos.ui.interfaces.AbstractAppFragment;

/**
 * 左侧滑动 栏
 */
public class LeftMenuFragment extends AbstractAppFragment implements
		OnClickListener {
	private View homeBtnLayout; // 左侧首页菜单
	private View friendsBtnLayout; // 左侧好友菜单
	// private View appCenterBtnLayout; // 左侧应用中心菜单
	private View setupBtnLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_left_fragment, container,
				false);
		

		return view;
	}

	@Override
	public void onClick(View v) {
		Fragment newContent = null;
		switch (v.getId()) {

		}

		if (newContent != null)
			switchFragment(newContent);

	}

	// 切换到不同的功能内容

	private void switchFragment(Fragment fragment) {
		if (getActivity() == null)
			return;
		/*
		 * MainTimeLineActivity ra = (MainTimeLineActivity) getActivity();
		 * ra.switchContent(fragment);
		 */

	}

}
