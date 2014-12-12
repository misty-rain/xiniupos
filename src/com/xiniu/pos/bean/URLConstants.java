package com.xiniu.pos.bean;

import com.xiniu.pos.support.utils.PropertiesUtils;

public class URLConstants {
	public static String ROOT_URL = PropertiesUtils.getURLProperties()
			.getProperty("ROOT_URL");

	public static String GET_USER_ID = PropertiesUtils.getURLProperties()
			.getProperty("GET_USER_ID");
	public static String GET_USER_INFO = PropertiesUtils.getURLProperties()
			.getProperty("GET_USER_INFO");

}
