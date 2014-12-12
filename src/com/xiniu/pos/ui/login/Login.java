package com.xiniu.pos.ui.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.xiniu.pos.R;
import com.xiniu.pos.support.utils.Utility;
import com.xiniu.pos.ui.interfaces.AbstractAppActivity;

public class Login extends AbstractAppActivity implements OnClickListener{
	private AutoCompleteTextView actAccountName, actPassWord;
	private ImageView imgLogin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		initView();
	}

	
	
	private void initView() {
	
		actAccountName = (AutoCompleteTextView) findViewById(R.id.login_account);
		actPassWord = (AutoCompleteTextView) findViewById(R.id.login_password);
		imgLogin = (ImageView) findViewById(R.id.btnlogin);
		imgLogin.setOnClickListener(this);

	}

	/**
	 * 登陆 handler
	 * 
	 * @param accountName
	 * @param accountPwd
	 */
	private void login(final String accountName, final String accountPwd) {
		final Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.what == 1) {

					
				} else if (msg.what == 0) {
					
				} else if (msg.what == -1) {
					

				}
			}
		};
		new Thread() {
			public void run() {
				Message msg = new Message();

			//	try {
				/*	User user = LoginDao.Login(accountName, accountPwd);
					if (user != null) {
						if (user.getUsercode() != null) {
							LoginDao.saveLoginInfo(LoginActivity.this,user);
							msg.what = 1;// 成功
							msg.obj = user;

						} else {
							msg.what = 0;
							msg.obj = user.getMessage();
						}
					} else {
						msg.what = 0;
						msg.obj = getString(R.string.http_exception_error);
					}*/
			//	} catch (AppException e) {
				//	e.printStackTrace();
				//	msg.what = -1;
				//	msg.obj = e;
				//}

				handler.sendMessage(msg);
			}
		}.start();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnlogin:
			if (!Utility.isConnected(this)) {
				Utility.ToastMessage(this, R.string.network_not_connected);
				return;
			}
			/*userCode = actAccountName.getText().toString();
			if (TextUtils.isEmpty(userCode)) {
				Utility.ToastMessage(getApplicationContext(),
						R.string.login_user_name_hint);
				return;
			}
			passWord = actPassWord.getText().toString();
			if (TextUtils.isEmpty(passWord)) {
				Utility.ToastMessage(getApplicationContext(),
						R.string.login_password_hint);
				return;
			}

			loginDialog = DialogUtil.getRequestDialog(LoginActivity.this,
					"正在登陆中");
			loginDialog.show();
			Utility.initAnim(v.getContext(), (ImageView) loginDialog
					.findViewById(R.id.auth_loading_icon), R.anim.rotate);
			login(userCode, passWord);
			*/

			break;
		
		default:
			break;
		}
	}



	
	
}
