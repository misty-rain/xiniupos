package com.xiniu.pos.support.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import android.content.Context;
import android.content.SharedPreferences;

import com.alibaba.fastjson.JSON;
import com.xiniu.api.domain.system.User;
import com.xiniu.pos.bean.PosItemBean;

public class SharePreferenceUtil {
	public static final String MESSAGE_NOTIFY_KEY = "message_notify";
	public static final String MESSAGE_SOUND_KEY = "message_sound";
	public static final String SHOW_HEAD_KEY = "show_head";
	public static final String PULLREFRESH_SOUND_KEY = "pullrefresh_sound";
	private SharedPreferences sp;
	private SharedPreferences.Editor editor;

	public SharePreferenceUtil(Context context, String file) {
		sp = context.getSharedPreferences(file, Context.MODE_PRIVATE);
		editor = sp.edit();
	}

	/**
	 * 清除
	 */
	public void clearData() {
		sp.edit().clear().commit();
	}

	/**
	 * 保存用户信息
	 * 
	 * @param user
	 */
	public void saveUserInfo(User user) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oStream = new ObjectOutputStream(baos);
			oStream.writeObject(user);
			String userString = new String(Base64.encodeBase64(baos
					.toByteArray()));
			editor.putString("user", userString);
			editor.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 将商品列表以json 方式存入sp
	 * 
	 * @param user
	 */
	public void saveItemList(List<PosItemBean> itemList) {
		String value = JSON.toJSONString(itemList,true);
		editor.putString("itemList", value);
		editor.commit();
	}

	public List<PosItemBean> getItemList() {
		List<PosItemBean> list = JSON.parseArray(sp.getString("itemList", ""),
				PosItemBean.class);
		return list;

	}

	/**
	 * @param userId
	 */
	public void setUserId(String userId) {
		editor.putString("userId", userId);
		editor.commit();

	}

	public String getUserId() {
		return sp.getString("userId", "");

	}

	/**
	 * @param userId
	 */
	public void setUserName(String userName) {
		editor.putString("userName", userName);
		editor.commit();

	}

	public String getUserName() {
		return sp.getString("userName", "");

	}

	// 设置初次绘制的解锁图案
	public void setFirstGesturePwd(String firstGesturePwd) {
		editor.putString("firstGesturePwd", firstGesturePwd);
		editor.commit();

	}

	public String getFirstGesturePwd() {
		return sp.getString("firstGesturePwd", "");

	}

	// 存储设置绘制的解锁图案,用来判断是否使用解锁方案
	public void setGesturePwd(String GesturePwd) {
		editor.putString("GesturePwd", GesturePwd);
		editor.commit();

	}

	public String getGesturePwd() {
		return sp.getString("GesturePwd", "");

	}

	// 用来判断用户是否启用密码锁
	public void setIsSettingGesturePwd(String iSG) {
		editor.putString("isSettingGesturePwd", iSG);
		editor.commit();

	}

	public String getIsSettingGesturePwd() {
		return sp.getString("isSettingGesturePwd", "");

	}

	// appid
	public void setAppId(String appid) {
		// TODO Auto-generated method stub
		editor.putString("appid", appid);
		editor.commit();
	}

	public String getAppId() {
		return sp.getString("appid", "");
	}

	// 头像图标
	public String getHeadIcon() {
		return sp.getString("headIcon", "");
	}

	public void setHeadIcon(String icon) {
		editor.putString("headIcon", icon);
		editor.commit();
	}

	// 设置Tag
	public void setTag(String tag) {
		editor.putString("tag", tag);
		editor.commit();
	}

	public String getTag() {
		return sp.getString("tag", "");
	}

	// 是否通知
	public boolean getMsgNotify() {
		return sp.getBoolean(MESSAGE_NOTIFY_KEY, true);
	}

	public void setMsgNotify(boolean isChecked) {
		editor.putBoolean(MESSAGE_NOTIFY_KEY, isChecked);
		editor.commit();
	}

	// 新消息是否有声音
	public boolean getMsgSound() {
		return sp.getBoolean(MESSAGE_SOUND_KEY, true);
	}

	public void setMsgSound(boolean isChecked) {
		editor.putBoolean(MESSAGE_SOUND_KEY, isChecked);
		editor.commit();
	}

	// 刷新是否有声音
	public boolean getPullRefreshSound() {
		return sp.getBoolean(PULLREFRESH_SOUND_KEY, true);
	}

	public void setPullRefreshSound(boolean isChecked) {
		editor.putBoolean(PULLREFRESH_SOUND_KEY, isChecked);
		editor.commit();
	}

	// 是否显示自己头像
	public boolean getShowHead() {
		return sp.getBoolean(SHOW_HEAD_KEY, true);
	}

	public void setShowHead(boolean isChecked) {
		editor.putBoolean(SHOW_HEAD_KEY, isChecked);
		editor.commit();
	}

	// 表情翻页效果
	public int getFaceEffect() {
		return sp.getInt("face_effects", 3);
	}

	public void setFaceEffect(int effect) {
		if (effect < 0 || effect > 11)
			effect = 3;
		editor.putInt("face_effects", effect);
		editor.commit();
	}
}
