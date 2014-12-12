package com.xiniu.pos.ui.oauth;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.xiniu.api.domain.system.User;
import com.xiniu.pos.R;
import com.xiniu.pos.bean.AccountBean;
import com.xiniu.pos.bean.TokenBean;
import com.xiniu.pos.dao.oauth.OAuthDao;
import com.xiniu.pos.support.exception.AppException;
import com.xiniu.pos.support.utils.AppLogger;
import com.xiniu.pos.support.utils.Constants;
import com.xiniu.pos.support.utils.GlobalContext;
import com.xiniu.pos.support.utils.SharePreferenceUtil;
import com.xiniu.pos.support.utils.Utility;
import com.xiniu.pos.ui.interfaces.AbstractAppActivity;
import com.xiniu.pos.ui.main.MainActivity;
import com.xiniu.pos.support.database.DatabaseManager;


@SuppressLint({ "NewApi", "ValidFragment" })
public class OAuthActivity extends AbstractAppActivity {
	private WebView webView;
	private MenuItem refreshItem;
	private SharePreferenceUtil mSpUtil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.oauthtest);
		mSpUtil = GlobalContext.getInstance().getSpUtil();
		webView = (WebView) findViewById(R.id.webView1);

		webView.setWebViewClient(new OAuthWebView());

		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setSaveFormData(false);
		settings.setSavePassword(false);
		settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
		settings.setRenderPriority(WebSettings.RenderPriority.HIGH);

		CookieSyncManager.createInstance(this);
		CookieManager cookieManager = CookieManager.getInstance();
		cookieManager.removeAllCookie();
		// new OAuthTask().execute("");

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		webView.clearCache(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actionbar_menu_oauthactivity, menu);
		refreshItem = menu.findItem(R.id.menu_refresh);
		refresh();
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:

			// Intent intent = new Intent(this, AccountActivity.class);
			// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
			// | Intent.FLAG_ACTIVITY_NEW_TASK);
			// intent.putExtra("launcher", false);
			// startActivity(intent);

			return true;
		case R.id.menu_refresh:
			refresh();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void refresh() {
		webView.clearView();
		webView.loadUrl("about:blank");
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ImageView iv = (ImageView) inflater.inflate(
				R.layout.refresh_action_view, null);

		Animation rotation = AnimationUtils.loadAnimation(this, R.anim.refresh);
		iv.startAnimation(rotation);

		refreshItem.setActionView(iv);
		webView.loadUrl(getOpenOAuthUrl());
	}

	private void completeRefresh() {

		if (refreshItem.getActionView() != null) {
			refreshItem.getActionView().clearAnimation();
			refreshItem.setActionView(null);
		}

	}

	private class OAuthWebView extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {

			AppLogger.e("URL******************************************" + url);
			if (url.startsWith(Constants.DIRECT_URL)) {

				handleRedirectUrl(view, url);
				view.stopLoading();
				return;
			}
			super.onPageStarted(view, url, favicon);

		}

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			if (!url.equals("about:blank"))
				completeRefresh();
		}
	}

	private void handleRedirectUrl(WebView view, String url) {
		Bundle values = Utility.parseUrl(url);

		String error = values.getString("error");
		String error_code = values.getString("error_code");

		Intent intent = new Intent();
		intent.putExtras(values);

		if (error == null && error_code == null) {

			String access_token = values.getString("code");
			setResult(RESULT_OK, intent);
			new OAuthTask().execute(access_token);

		} else {
			Toast.makeText(OAuthActivity.this, "cancle login",
					Toast.LENGTH_SHORT).show();
			finish();
		}

	}

	private String getOpenOAuthUrl() {

		// Map<String, String> parameters = new HashMap<String, String>();
		// parameters.put("client_id", "1065511513");
		// parameters.put("response_type", "token");
		// parameters.put("redirect_uri","https://api.weibo.com/oauth2/default.html");
		// parameters.put("display", "mobile");
		// return "https://api.weibo.com/oauth2/authorize"+"?"+
		// Utility.encodeUrl(parameters)
		// + "&scope=friendships_groups_read,friendships_groups_write";

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("client_id", Constants.CLIENT_ID);
		parameters.put("response_type", Constants.RESPONSE_TYPE);
		parameters.put("redirect_uri", Constants.REDIRECT_URI);
		parameters.put("display", Constants.DISPLAY);
		return Constants.OAUTH_ROOT_URL + "?"
				+ Utility.encodeUrl(parameters);

	}

	class OAuthTask extends AsyncTask<String, User, DBResult> {

		AppException e;

		ProgressFragment progressFragment = ProgressFragment.newInstance();

		@Override
		protected void onPreExecute() {
			progressFragment.setAsyncTask(this);

			progressFragment.show(getFragmentManager(), "");

		}

		@Override
		protected DBResult doInBackground(String... params) {

			String code = params[0];

			try {
				String result = OAuthDao.getAccessToken(code);
				TokenBean token = JSON.parseObject(result, TokenBean.class);
				AccountBean account = new AccountBean();
				account.setAccess_token(token.getAccess_token());
				account.setUid(token.getUserid());
				mSpUtil.setUserId(token.getUserid());

				/*User user = UserDao.getUserInfo(
						Long.parseLong(token.getUserid()),
						token.getAccess_token());
				mSpUtil.setUserName(user.getName());
				account.setInfo(user);*/
				return DatabaseManager.getInstance()
						.addOrUpdateAccount(account);

			} catch (Exception e) {

				cancel(true);
				return null;
			}

		}

		@Override
		protected void onCancelled(DBResult dbResult) {
			super.onCancelled(dbResult);
			if (progressFragment != null) {
				progressFragment.dismissAllowingStateLoss();
			}
			if (e != null)
				Toast.makeText(OAuthActivity.this, e.getError(),
						Toast.LENGTH_SHORT).show();
			webView.loadUrl(getOpenOAuthUrl());
		}

		@Override
		protected void onPostExecute(DBResult dbResult) {
			if (progressFragment.isVisible()) {
				progressFragment.dismissAllowingStateLoss();
			}
			switch (dbResult) {
			case add_successfuly:
				Toast.makeText(OAuthActivity.this, "login success",
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(OAuthActivity.this, MainActivity.class);
				startActivity(intent);
				break;
			case update_successfully:
				Toast.makeText(OAuthActivity.this, "update user success",
						Toast.LENGTH_SHORT).show();
				Intent intent1 = new Intent(OAuthActivity.this, MainActivity.class);
				startActivity(intent1);
				break;
			}
			finish();

		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		// if (isFinishing())
		// webView.stopLoading();
	}

	static class ProgressFragment extends DialogFragment {

		AsyncTask asyncTask = null;

		public static ProgressFragment newInstance() {
			ProgressFragment frag = new ProgressFragment();
			frag.setRetainInstance(true);
			Bundle args = new Bundle();
			frag.setArguments(args);
			return frag;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {

			ProgressDialog dialog = new ProgressDialog(getActivity());
			dialog.setMessage("授权中………………");
			dialog.setIndeterminate(false);
			dialog.setCancelable(true);

			return dialog;
		}

		@Override
		public void onCancel(DialogInterface dialog) {

			if (asyncTask != null) {
				asyncTask.cancel(true);
			}

			super.onCancel(dialog);
		}

		void setAsyncTask(AsyncTask task) {
			asyncTask = task;
		}
	}

	public static enum DBResult {
		add_successfuly, update_successfully
	}

}
