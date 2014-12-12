package com.xiniu.pos.support.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import android.annotation.SuppressLint;

/**
 * 时间工具类
 * 
 * @author way
 * 
 */
@SuppressLint("SimpleDateFormat")
public class TimeUtil {
	/** 年月日时分秒(无下划线) yyyyMMddHHmmss */
	public static final String dtLong = "HHmmss";

	private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};
	
	/**
	 * 获取的时间格式为 yyyy-MM-dd HH:mm
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm");
		Calendar cal = Calendar.getInstance();
		return format.format(cal.getTime());

	}
	public static String getTime(long time) {
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm");
		return format.format(new Date(time));
	}

	public static String getHourAndMin(long time) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		return format.format(new Date(time));
	}

	public static String getChatTime(long timesamp) {
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		Date today = new Date(System.currentTimeMillis());
		Date otherDay = new Date(timesamp);
		int temp = Integer.parseInt(sdf.format(today))
				- Integer.parseInt(sdf.format(otherDay));

		switch (temp) {
		case 0:
			result = "今天 " + getHourAndMin(timesamp);
			break;
		case 1:
			result = "昨天 " + getHourAndMin(timesamp);
			break;
		case 2:
			result = "前天 " + getHourAndMin(timesamp);
			break;

		default:
			// result = temp + "天前 ";
			result = getTime(timesamp);
			break;
		}

		return result;
	}
	
	/**
	 * 将字符串转位日期类型
	 * 
	 * @param sdate
	 * @return
	 */
	public static Date toDate(String sdate) {
		try {

			return dateFormater2.get().parse(sdate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
	 * 
	 * @return 以yyyyMMddHHmmss为格式的当前系统时间
	 */
	public static String getOrderNumTime() {
		
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(dtLong);
		return df.format(date);
	}
	
	/**
	 * 产生随机的三位数
	 * 
	 * @return
	 */
	public static String getThreeRandoms() {
		Random rad = new Random();
		return rad.nextInt(1000) + "";
	}
	
	/**
	 * 产生订单编号
	 * @return
	 */
	public static String getOrderNo(){
		return getOrderNumTime()+getThreeRandoms();
	}
}
