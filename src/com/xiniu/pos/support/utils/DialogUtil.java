package com.xiniu.pos.support.utils;

import com.xiniu.pos.R;
import com.xiniu.pos.support.widgets.WaitDialog;

import android.app.Activity;
import android.app.Dialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * 对话框工具类 ，调用时只需 DialogUtil.
 * 
 * @author Administrator
 * 
 */
public class DialogUtil {
	public static Dialog getMenuDialog(Activity context, View view) {

		final Dialog dialog = new Dialog(context, R.style.MenuDialogStyle);
		dialog.setContentView(view);
		Window window = dialog.getWindow();
		WindowManager.LayoutParams lp = window.getAttributes();

		int screenW = getScreenWidth(context);
		// int screenH = getScreenHeight(context);
		lp.width = screenW;
		window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
		// window.setWindowAnimations(R.style.MenuDialogAnimation); // 添加动画
		return dialog;
	}

	public static Dialog getRequestDialog(Activity context,
			String displayMessage) {

		final Dialog dialog = new Dialog(context, R.style.Dialog);
		dialog.setContentView(R.layout.wait_dialog_view);
		dialog.setCancelable(false);
		dialog.setCanceledOnTouchOutside(false);
		Window window = dialog.getWindow();
		WindowManager.LayoutParams lp = window.getAttributes();

		int screenW = getScreenWidth(context);
		lp.width = (int) (0.6 * screenW);

		TextView titleTxtv = (TextView) dialog.findViewById(R.id.tvLoad);
		titleTxtv.setText(displayMessage);
		return dialog;
	}

	/**
	 * 黑色主题 的请求对话框 ，如无需改动 显示消息 ，传null
	 * @param context
	 * @param displayMessage
	 * @return
	 */
	public static Dialog getRequestDialogForBlack(Activity context,
			String displayMessage) {
		final Dialog dialog = new Dialog(context, R.style.Dialog);
		dialog.setContentView(R.layout.request_dialog_black);
		dialog.setCancelable(false);
		dialog.setCanceledOnTouchOutside(false);
		if (displayMessage != null) {
			TextView titleTxtv = (TextView) dialog.findViewById(R.id.tips_msg);
			titleTxtv.setText(displayMessage);
		}
		return dialog;

	}

	public static Dialog getCustomDialog(Activity context) {
		final Dialog dialog = new Dialog(context, R.style.Dialog);
		return dialog;
	}

	public static int getScreenWidth(Activity context) {
		DisplayMetrics dm = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}

	public static int getScreenHeight(Activity context) {
		DisplayMetrics dm = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}
	



	public static WaitDialog getWaitDialog(Activity activity, int message) {
		WaitDialog dialog = null;
		try {
			dialog = new WaitDialog(activity, R.style.dialog_waiting);
			dialog.setMessage(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dialog;
	}

	public static WaitDialog getWaitDialog(Activity activity, String message) {
		WaitDialog dialog = null;
		try {
			dialog = new WaitDialog(activity, R.style.dialog_waiting);
			dialog.setMessage(message);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dialog;
	}

	public static WaitDialog getCancelableWaitDialog(Activity activity,
			String message) {
		WaitDialog dialog = null;
		try {
			dialog = new WaitDialog(activity, R.style.dialog_waiting);
			dialog.setMessage(message);
			dialog.setCancelable(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dialog;
	}

}
