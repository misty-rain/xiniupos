package com.xiniu.pos.support.settinghelper;

import android.content.Context;

import com.xiniu.pos.support.utils.GlobalContext;
import com.xiniu.pos.support.utils.Utility;

/**
 * User: qii
 * Date: 12-11-28
 */
/**  
 * Copyright © 2014ENGC. All rights reserved.
 * @Title: SettingUtility.java
 * @Package: com.engc.jlcollege.support.settinghelper
 * @Description: TODO
 * @author: Administrator  
 * @date: 2014-7-7 下午5:17:18
 */
/**
 * Copyright © 2014ENGC. All rights reserved.
 * 
 * @Title: SettingUtility.java
 * @Package: com.engc.jlcollege.support.settinghelper
 * @Description: TODO
 * @author: Administrator
 * @date: 2014-7-7 下午5:17:20
 */
public class SettingUtility {

	private static final String FIRSTSTART = "firststart";

	private SettingUtility() {

	}

	/**
	 * 设置默认账户ID
	 * 
	 * @Title: setDefaultAccountId
	 * @Description: TODO
	 * @return: void
	 */
	public static void setDefaultAccountId(String id) {
		SettingHelper.setEditor(getContext(), "id", id);
	}

	/**
	 * 获得默认账户ID
	 * 
	 * @Title: getDefaultAccountId
	 * @Description: TODO
	 * @return: String
	 */
	public static String getDefaultAccountId() {
		return SettingHelper.getSharedPreferences(getContext(), "id", "");
	}

	/**
	 * 获得上下文环境
	 * 
	 * @Title: getContext
	 * @Description: TODO
	 * @return: Context
	 */
	private static Context getContext() {
		return GlobalContext.getInstance();
	}

	/**
	 * 判断是否为第一次启动
	 * 
	 * @Title: firstStart
	 * @Description: TODO
	 * @return: boolean
	 */
	public static boolean firstStart() {
		boolean value = SettingHelper.getSharedPreferences(getContext(),
				FIRSTSTART, true);
		if (value)
			SettingHelper.setEditor(getContext(), FIRSTSTART, false);
		return value;
	}

	/**
	 * 是否开启过滤
	 * 
	 * @Title: isEnableFilter
	 * @Description: TODO
	 * @return: boolean
	 */
	public static boolean isEnableFilter() {
		return SettingHelper.getSharedPreferences(getContext(),
				SettingConstant.FILTER, false);
	}

	/**
	 * 获得字体大小
	 * 
	 * @Title: getFontSize
	 * @Description: TODO
	 * @return: int
	 */
	public static int getFontSize() {
		String value = SettingHelper.getSharedPreferences(getContext(),
				SettingConstant.FONT_SIZE, "15");
		return Integer.valueOf(value);
	}

	/**
	 * 获得应用主题
	 * 
	 * @Title: getAppTheme
	 * @Description: TODO
	 * @return: int
	 */
	public static int getAppTheme() {
		String value = SettingHelper.getSharedPreferences(getContext(),
				SettingConstant.THEME, "1");

		switch (Integer.valueOf(value)) {
		case 1:
			return 0;

		case 2:
			// return R.style.AppTheme_Pure_Black;
			return 1;

		default:
			// return R.style.AppTheme_Four;
			return 2;

		}
	}

	/**
	 * 获得大图模式
	 * 
	 * @Title: getHighPicMode
	 * @Description: TODO
	 * @return: int
	 */
	public static int getHighPicMode() {
		String value = SettingHelper.getSharedPreferences(getContext(),
				SettingConstant.LIST_HIGH_PIC_MODE, "1");
		return Integer.valueOf(value);
	}

	/**
	 * 获得回复头像模式
	 * 
	 * @Title: getCommentRepostAvatar
	 * @Description: TODO
	 * @return: int
	 */
	public static int getCommentRepostAvatar() {
		String value = SettingHelper.getSharedPreferences(getContext(),
				SettingConstant.COMMENT_REPOST_AVATAR, "1");
		return Integer.valueOf(value);
	}

	/**
	 * 获得listview 头像 图像 模式
	 * 
	 * @Title: getListAvatarMode
	 * @Description: TODO
	 * @return: int
	 */
	public static int getListAvatarMode() {
		String value = SettingHelper.getSharedPreferences(getContext(),
				SettingConstant.LIST_AVATAR_MODE, "1");
		return Integer.valueOf(value);
	}

	/**
	 * 获得listview 图像 模式
	 * 
	 * @Title: getListPicMode
	 * @Description: TODO
	 * @return: int
	 */

	public static int getListPicMode() {
		String value = SettingHelper.getSharedPreferences(getContext(),
				SettingConstant.LIST_PIC_MODE, "1");
		return Integer.valueOf(value);
	}

	/**
	 * 设置是否显示回复列表中头像
	 * 
	 * @Title: setEnableCommentRepostAvatar
	 * @Description: TODO
	 * @return: void
	 */
	public static void setEnableCommentRepostAvatar(boolean value) {
		SettingHelper.setEditor(getContext(),
				SettingConstant.SHOW_COMMENT_REPOST_AVATAR, value);
	}

	/**
	 * 设置是否显示回复列表中头像
	 * 
	 * @Title: getEnableCommentRepostListAvatar
	 * @Description: TODO
	 * @return: boolean
	 */
	public static boolean getEnableCommentRepostListAvatar() {
		return SettingHelper.getSharedPreferences(getContext(),
				SettingConstant.SHOW_COMMENT_REPOST_AVATAR, true);
	}

	/**
	 * 获得应用通知栏样式
	 * 
	 * @Title: getNotificationStyle
	 * @Description: TODO
	 * @return: int
	 */
	public static int getNotificationStyle() {
		String value = SettingHelper.getSharedPreferences(getContext(),
				SettingConstant.JBNOTIFICATION_STYLE, "1");

		switch (Integer.valueOf(value)) {
		case 1:
			return 1;

		case 2:
			return 2;

		default:
			return 1;

		}
	}

	/**
	 * 判断是否加载图片
	 * 
	 * @Title: isEnablePic
	 * @Description: TODO
	 * @return: boolean
	 */
	public static boolean isEnablePic() {
		return !SettingHelper.getSharedPreferences(getContext(),
				SettingConstant.DISABLE_DOWNLOAD_AVATAR_PIC, false);
	}

	/**
	 * 判断是否加载大图片
	 * 
	 * @Title: getEnableBigPic
	 * @Description: TODO
	 * @return: boolean
	 */
	public static boolean getEnableBigPic() {
		return SettingHelper.getSharedPreferences(getContext(),
				SettingConstant.SHOW_BIG_PIC, false);
	}

	/**
	 * 获得是否获取消息
	 * 
	 * @Title: getEnableFetchMSG
	 * @Description: TODO
	 * @return: boolean
	 */
	public static boolean getEnableFetchMSG() {
		return SettingHelper.getSharedPreferences(getContext(),
				SettingConstant.ENABLE_FETCH_MSG, false);
	}

	/**
	 * 获得是否需要自动刷新
	 * 
	 * @Title: getEnableAutoRefresh
	 * @Description: TODO
	 * @return: boolean
	 */
	public static boolean getEnableAutoRefresh() {
		return SettingHelper.getSharedPreferences(getContext(),
				SettingConstant.AUTO_REFRESH, false);
	}

	/**
	 * 获得是否启用大头像
	 * 
	 * @Title: getEnableAutoRefresh
	 * @Description: TODO
	 * @return: boolean
	 */
	public static boolean getEnableBigAvatar() {
		return SettingHelper.getSharedPreferences(getContext(),
				SettingConstant.SHOW_BIG_AVATAR, false);
	}

	/**
	 * 获得是否启动声音
	 * 
	 * @Title: getEnableAutoRefresh
	 * @Description: TODO
	 * @return: boolean
	 */
	public static boolean getEnableSound() {
		return SettingHelper.getSharedPreferences(getContext(),
				SettingConstant.SOUND, true)
				&& Utility.isSystemRinger(getContext());
	}

	/**
	 * 获得信息时不启用led灯
	 * 
	 * @Title: getEnableAutoRefresh
	 * @Description: TODO
	 * @return: boolean
	 */
	public static boolean disableFetchAtNight() {
		return SettingHelper.getSharedPreferences(getContext(),
				SettingConstant.DISABLE_FETCH_AT_NIGHT, true)
				&& Utility.isSystemRinger(getContext());
	}

	/**
	 * 获得频率
	 * 
	 * @Title: getFrequency
	 * @Description: TODO
	 * @return: String
	 */
	public static String getFrequency() {
		return SettingHelper.getSharedPreferences(getContext(),
				SettingConstant.FREQUENCY, "1");
	}

	/**
	 * 
	 * @Title: setEnableBigPic
	 * @Description: 设置是否加载大图片
	 * @return: void
	 */
	public static void setEnableBigPic(boolean value) {
		SettingHelper.setEditor(getContext(), SettingConstant.SHOW_BIG_PIC,
				value);
	}

	/**
	 * 设置是否加载大头像
	 * 
	 * @Title: setEnableBigAvatar
	 * @Description: TODO
	 * @return: void
	 */
	public static void setEnableBigAvatar(boolean value) {
		SettingHelper.setEditor(getContext(), SettingConstant.SHOW_BIG_AVATAR,
				value);
	}

	/**
	 * 设置是否开启模拟器
	 * 
	 * @Title: setEnableFilter
	 * @Description: TODO
	 * @return: void
	 */
	public static void setEnableFilter(boolean value) {
		SettingHelper.setEditor(getContext(), SettingConstant.FILTER, value);
	}

	/**
	 * 设置是否自动获取消息
	 * 
	 * @Title: setEnableFetchMSG
	 * @Description: TODO
	 * @return: void
	 */
	public static void setEnableFetchMSG(boolean value) {
		SettingHelper.setEditor(getContext(), SettingConstant.ENABLE_FETCH_MSG,
				value);
	}

	/**
	 * 设置是否允许震动
	 * 
	 * @Title: allowVibrate
	 * @Description: TODO
	 * @return: boolean
	 */
	public static boolean allowVibrate() {
		return SettingHelper.getSharedPreferences(getContext(),
				SettingConstant.ENABLE_VIBRATE, false);

	}

	/**
	 * 设置是否启动led灯
	 * 
	 * @Title: allowLed
	 * @Description: TODO
	 * @return: boolean
	 */
	public static boolean allowLed() {
		return SettingHelper.getSharedPreferences(getContext(),
				SettingConstant.ENABLE_LED, false);

	}

	/**
	 * 获得彩铃
	 * 
	 * @Title: getRingtone
	 * @Description: TODO
	 * @return: String
	 */
	public static String getRingtone() {
		return SettingHelper.getSharedPreferences(getContext(),
				SettingConstant.ENABLE_RINGTONE, "");

	}

	/**
	 * 支持是否允许快速滑动
	 * 
	 * @Title: allowFastScroll
	 * @Description: TODO
	 * @return: boolean
	 */
	public static boolean allowFastScroll() {
		return SettingHelper.getSharedPreferences(getContext(),
				SettingConstant.LIST_FAST_SCROLL, false);

	}

}
