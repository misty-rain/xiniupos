package com.xiniu.pos.support.utils;

/**
 * 字符集 转码类  
 * @author wutao
 *
 */
public class Character{
	
	public static String chinaToUnicode(String str) {
		StringBuffer ret = new StringBuffer();
		String tmp = null;
		try {
			byte[] bb = str.getBytes("UTF-16");
			for (int i = 3; i < bb.length; i+=2) {
				if (bb[i - 1] != 0)
					tmp = toHEXString(bb[i - 1]) + toHEXString(bb[i]);
				else
					tmp = "00" + toHEXString(bb[i]);
				ret.append("\\u").append(tmp);
			}
			return ret.toString();
		} catch (Exception e) {
			return str;
		}
	}

	public static String unicodeToChina(String str) {
		String[] s = str.split("\\\\u");
		StringBuffer ret = new StringBuffer();
		for(int i = 1 ; i < s.length; i++ ) {
			ret.append((char)Integer.parseInt(s[i],16));
		}
		return ret.toString();
	}

	private static String toHEXString(byte b) {
		return ("" + "0123456789ABCDEF".charAt(0xf & b >> 4) + "0123456789ABCDEF".charAt(b & 0xF));
	}
	
	public static String unescape(String str){
		if(str == null || str.length() == 0)
			return "";
		if(str.indexOf("\\u") == -1)
			str = chinaToUnicode(str);
		int index = str.indexOf("\\u");
		if(index == -1)
			return str;
		String temp = str.substring(0,index);
		String[] s = str.substring(index,str.length()).split("\\\\u");
		StringBuffer ret = new StringBuffer(temp);
		for(int i = 1 ; i < s.length; i++ ) {
			try{
				ret.append((char)Integer.parseInt(s[i].substring(0,4),16));
				if(s[i].length() > 4)
					ret.append(s[i].substring(4,s[i].length()));
			}catch(Exception e){
				ret.append("\\u"+s[i]);
			}
			
		}
		return ret.toString();
	}
}