package com.xiniu.pos.support.utils;

import android.content.Context;

/**
 * 
 * Copyright © 2014ENGC. All rights reserved.
 * @Title: AppConfig.java
 * @Package: com.engc.jlcollege.support.utils
 * @Description: 应用程序配置
 * @author: 吴涛  
 * @date: 2014-7-7 下午5:15:09
 */
public class AppConfig {

    private AppConfig() {

    }

    public static final int DEFAULT_MSG_COUNT_25 = 25;
    public static final int DEFAULT_MSG_COUNT_50 = 50;

    public static final int DEFAULT_DB_CACHE_COUNT = 50;

    //friend timeline
    public static final long AUTO_REFRESH_INITIALDELAY = 9l;
    public static final long AUTO_REFRESH_PERIOD = 7l;


    //if download pic failed,retry
    public static final int RETRY_TIMES = 6;

    //pic cache saved days
    public static final int SAVED_DAYS = 5;

    //swipe to close
    public static final int SWIPE_MIN_DISTANCE = 30;
    
	private Context mContext;
	private static AppConfig appConfig;
	
	public static AppConfig getAppConfig(Context context)
	{
		if(appConfig == null){
			appConfig = new AppConfig();
			appConfig.mContext = context;
		}
		return appConfig;
	}
}
