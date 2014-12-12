package com.xiniu.pos.support.utils;

/**
 * 应用 字典类
 * 
 * @author Administrator
 * 
 */
public class AppConstants {
	public static final String BIND_CARD_FOR_BIND = "1"; // 绑定银行卡
	public static final String BIND_CARD_FOR_CANCLE_BIND = "2"; // 解除绑定银行卡
	public static final int PAY_FOR_ICBC = 0; // 工商银行 B2C 支付
	public static final int PAY_FOR_SHCOOLCARD = 1; // 校园卡 支付
	public static final int PAY_FOR_ALIPAY = 2; // 支付宝支付
	public static final int MSG_SET_ALIAS = 1001; //jpush 设置 alias
	public static final int MSG_SET_TAGS = 1002; //jpush 设置 tag
    public static final String APPID="4";//苏州卫校应用程序Id
    public static final String PLATFORMIDFORANDROID="0" ;//android 平台ID
    
    public static final String OPERATION_TYPE_FOR_INSTALL="0"; // 安装应用类型
    public static final String OPERATION_TYPE_FOR_UNINSTALL="1"; //卸载应用类型
	
	/**
	 * 校园卡支付 代码 and message
	 * 
	 * @param payCode
	 * @return
	 */
	public static String getSchoolCardPayMessageByCode(String payCode) {
		if (payCode.equals("13001"))
			return "账户不存在";
		else if (payCode.equals("13002"))
			return "消费密码错误";
		else if (payCode.equals("13006"))
			return "卡片已超出有效期";
		else if (payCode.equals("13003"))
			return "卡状态异常";
		else if (payCode.equals("13005"))
			return "余额不足";
		else if (payCode.equals("10001"))
			return "缴费异常";
		else
			return "缴费成功";

	}

}
