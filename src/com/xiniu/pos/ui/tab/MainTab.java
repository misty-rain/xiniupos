package com.xiniu.pos.ui.tab;

import com.xiniu.pos.R;
import com.xiniu.pos.ui.category.CategoryFragment;
import com.xiniu.pos.ui.main.MainFragment;
import com.xiniu.pos.ui.order.OrderFragment;
import com.xiniu.pos.ui.settting.SettingFragment;
import com.xiniu.pos.ui.transfer.TransferFragment;


/**
 * 
* @ClassName: MainTab 
* @Description: TODO tab 组装
* @author misty-rain 
* @date 2014-12-5 下午3:25:59 
*
 */


public enum MainTab {

	MAIN(0, R.string.tab_title_index, R.drawable.tab_icon_index, MainFragment.class),

	COMCAR(1, R.string.tab_title_order, R.drawable.tab_icon_sort,
			OrderFragment.class),

	TRANSFER(2, R.string.tab_title_transfer, R.drawable.tab_icon_order, TransferFragment.class),

	SETTING(3, R.string.tab_title_setting, R.drawable.tab_icon_me, SettingFragment.class);
	
	private int idx;
	private int resName;
	private int resIcon;
	private Class<?> clz;

	private MainTab(int idx, int resName, int resIcon, Class<?> clz) {
		this.idx = idx;
		this.resName = resName;
		this.resIcon = resIcon;
		this.clz = clz;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getResName() {
		return resName;
	}

	public void setResName(int resName) {
		this.resName = resName;
	}

	public int getResIcon() {
		return resIcon;
	}

	public void setResIcon(int resIcon) {
		this.resIcon = resIcon;
	}

	public Class<?> getClz() {
		return clz;
	}

	public void setClz(Class<?> clz) {
		this.clz = clz;
	}
}
