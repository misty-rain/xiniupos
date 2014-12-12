package com.xiniu.pos.ui.membership;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.xiniu.pos.R;
import com.xiniu.pos.dao.person.PersonDao;
import com.xiniu.pos.support.utils.DialogUtil;
import com.xiniu.pos.support.utils.StringUtility;
import com.xiniu.pos.support.utils.Utility;
import com.xiniu.pos.support.widgets.TipsToast;
import com.xiniu.pos.ui.interfaces.BaseActivity;

/**
 * 
 * @ClassName: Member
 * @Description: 会员信息
 * @author misty-rain
 * @date 2014-12-11 下午4:20:49
 * 
 */
public class Member extends BaseActivity {
	private EditText edtMCardNum;
	private Button btnSumbit;
	private Dialog requestDialog;
	private TipsToast tipsToast;

	@Override
	protected int getLayoutId() {
		return R.layout.member;
	}

	@Override
	protected boolean hasBackButton() {
		return true;
	}

	@Override
	protected int getActionBarTitle() {
		return R.string.action_bar_title_member;
	}

	@Override
	protected int getActionBarCustomView() {
		return R.layout.actionbar_custom_backtitle;
	}

	@Override
	public void onBackPressed() {

		super.onBackPressed();

	}

	@Override
	protected void init(Bundle savedInstanceState) {
		super.init(savedInstanceState);
		setActionBarTitle(getActionBarTitle());
		initView();

	}

	private void initView() {
		edtMCardNum = (EditText) findViewById(R.id.edtmemberCard);
		btnSumbit = (Button) findViewById(R.id.btnSubmit);
		btnSumbit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				initRequestView();

			}
		});
	}

	private void initRequestView() {
		if (StringUtility.isEmpty(edtMCardNum.getText().toString())) {
			Utility.ToastMessage(Member.this,
					getString(R.string.warn_membercard_no_not_null));
			return;
		}

		requestDialog = DialogUtil.getRequestDialogForBlack(Member.this,
				"查询中……");
		requestDialog.show();

		getMemberInfo("000001");
	}

	/**
	 * 提示信息
	 * 
	 * @param iconResId
	 * @param msgResId
	 */
	private void showTips(int iconResId, String msg) {
		if (tipsToast != null) {
			if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
				tipsToast.cancel();
			}
		} else {
			tipsToast = TipsToast.makeText(getApplication().getBaseContext(),
					msg, TipsToast.LENGTH_SHORT);
		}
		tipsToast.show();
		tipsToast.setIcon(iconResId);
		tipsToast.setText(msg);
	}

	public void getMemberInfo(final String cardNo) {
		final Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.what == 1) {
					requestDialog.dismiss();
					showTips(R.drawable.tips_success, msg.obj.toString());

				} else if (msg.what == 0) {
				} else if (msg.what == -1) {

				}
			}
		};
		new Thread() {
			public void run() {
				Message msg = new Message();
				com.xiniu.api.domain.membership.Member member = PersonDao
						.getMemberShip(cardNo, "dd");
				requestDialog.cancel();
				if (member != null) {
					msg.what = 1;
					// msg.obj = result.getMessage();

				} else {
					msg.what = 0;
					msg.obj = getString(R.string.http_exception_error);
				}

				handler.sendMessage(msg);
			}
		}.start();
	}

}
