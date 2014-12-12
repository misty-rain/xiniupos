package com.xiniu.pos.dao.oauth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.xiniu.api.domain.system.User;
import com.xiniu.api.internal.util.WebUtils;
import com.xiniu.pos.bean.URLConstants;
import com.xiniu.pos.support.http.HttpMethod;
import com.xiniu.pos.support.http.HttpUtility;
import com.xiniu.pos.support.utils.AppLogger;
import com.xiniu.pos.support.utils.Constants;

public class OAuthDao {

	private String access_token;

	public OAuthDao(String access_token) {

		this.access_token = access_token;
	}

	/**
	 * 获取用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public User getOAuthUserInfo(final long userId,final String accessToken) throws Exception {
		
		

		User user = null;
		try {
			//user = UserDao.getUserInfo(userId,accessToken);
		} catch (Exception e) {
			AppLogger.e(e.getMessage());
		}

		/*
		 * Map<String, String> map = new HashMap<String, String>();
		 * map.put("uid", uid); map.put("access_token", access_token);
		 * 
		 * String url =URLConstants.GET_USER_INFO;
		 * 
		 * String result =
		 * HttpUtility.getInstance().executeNormalTask(HttpMethod.Get, url,
		 * map);
		 * 
		 * 
		 * UserInfo user=JSON.parseObject(result,UserInfo.class);
		 */

		return user;
	}

	private String getOAuthUserUIDJsonData() throws Exception {

		String url = URLConstants.GET_USER_ID;
		Map<String, String> map = new HashMap<String, String>();
		map.put("access_token", access_token);

		return HttpUtility.getInstance().executeNormalTask(HttpMethod.Get, url,
				map);

	}

	public static String getAccessToken(final String code) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("grant_type", Constants.GRANT_TYPE);
		param.put("code", code);
		param.put("client_id", Constants.GET_TOKEN_CLIENT_ID);
		param.put("client_secret", Constants.GET_TOKEN_SECRET);
		param.put("redirect_uri", Constants.GET_TOKEN_REDIRECT_URI);
		param.put("view", Constants.GET_TOKEN_VIEW);
		param.put("state", Constants.GET_TOKEN_STATE);
		String result = "";
		try {
			result = WebUtils.doPost(Constants.GET_ACCESS_TOKEN_BASE_URL,
					param, 3000, 3000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

}
