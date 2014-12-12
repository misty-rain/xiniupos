package com.xiniu.pos.support.utils;

public interface Constants {

	public static String APP_KEY = PropertiesUtils.getSysProperties().getProperty(
			"APP_KEY");
	public static String SECRET=PropertiesUtils.getSysProperties().getProperty(
			"SECRET");

	public static String DIRECT_URL = PropertiesUtils.getSysProperties().getProperty(
			"DIRECT_URL");
	public static String SINA_DIRECT_URL = PropertiesUtils.getSysProperties().getProperty(
			"SINA_DIRECT_URL");
	public static String DISPLAY = PropertiesUtils.getSysProperties().getProperty(
			"DISPLAY");
	public static String URL_OAUTH2_ACCESS_AUTHORIZE=PropertiesUtils.getSysProperties().getProperty(
			"URL_OAUTH2_ACCESS_AUTHORIZE");
	public static String CLIENT_ID=PropertiesUtils.getSysProperties().getProperty(
			"CLIENT_ID");
	public static String RESPONSE_TYPE=PropertiesUtils.getSysProperties().getProperty(
			"RESPONSE_TYPE");
	public static String REDIRECT_URI=PropertiesUtils.getSysProperties().getProperty(
			"REDIRECT_URI");
	public static String OAUTH_ROOT_URL=PropertiesUtils.getSysProperties().getProperty(
			"OAUTH_ROOT_URL");
	public static String DEFAULT_XNCLIENT_BASE_URL=PropertiesUtils.getSysProperties().getProperty(
			"DEFAULT_XNCLIENT_BASE_URL");
	public static String GET_ACCESS_TOKEN_BASE_URL=PropertiesUtils.getSysProperties().getProperty(
			"GET_ACCESS_TOKEN_BASE_URL");
	public static String GRANT_TYPE=PropertiesUtils.getSysProperties().getProperty(
			"GRANT_TYPE"); 
	public static String GET_TOKEN_CLIENT_ID=PropertiesUtils.getSysProperties().getProperty(
			"GET_TOKEN_CLIENT_ID");
	public static String GET_TOKEN_SECRET=PropertiesUtils.getSysProperties().getProperty(
			"GET_TOKEN_SECRET"); 
	
	public static String GET_TOKEN_REDIRECT_URI=PropertiesUtils.getSysProperties().getProperty(
			"GET_TOKEN_REDIRECT_URI"); 
	public static String GET_TOKEN_VIEW=PropertiesUtils.getSysProperties().getProperty(
			"GET_TOKEN_VIEW");
	public static String GET_TOKEN_STATE=PropertiesUtils.getSysProperties().getProperty(
			"GET_TOKEN_STATE"); 
	
	

}
