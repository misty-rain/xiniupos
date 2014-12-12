package com.xiniu.pos.support.utils;

import com.xiniu.pos.support.widgets.WaitDialog;

/**
 * 
* @ClassName: DialogControl 
* @Description: TODO dialog interface
* @author misty-rain 
* @date 2014-12-5 下午4:04:56 
*
 */
public interface DialogControl {

	public abstract void hideWaitDialog();

	public abstract WaitDialog showWaitDialog();

	public abstract WaitDialog showWaitDialog(int resid);

	public abstract WaitDialog showWaitDialog(String text);
}
