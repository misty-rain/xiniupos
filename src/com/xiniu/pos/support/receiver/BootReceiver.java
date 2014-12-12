package com.xiniu.pos.support.receiver;

import com.xiniu.pos.support.utils.AppLogger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 监听 应用卸载 or 安装 广播
 * @author Administrator
 *
 */
public class BootReceiver extends BroadcastReceiver{



	@Override
	public void onReceive(Context context, Intent intent) {
		
		if(intent.getAction().equals("android.intent.action.PACKAGE_ADDED")){
			String packageName=intent.getDataString();
			AppLogger.i("安装了"+packageName+"包名的应用");
		}
		
		if(intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")){
			String packageName=intent.getDataString();
			AppLogger.i("安装了"+packageName+"包名的应用");
		}
	}

}
