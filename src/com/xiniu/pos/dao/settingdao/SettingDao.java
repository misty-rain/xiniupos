package com.xiniu.pos.dao.settingdao;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.xiniu.pos.bean.FeedBack;
import com.xiniu.pos.bean.Update;
import com.xiniu.pos.support.http.HttpMethod;
import com.xiniu.pos.support.http.HttpUtility;
import com.xiniu.pos.support.utils.TimeUtil;


/**
 * 
 * @author Administrator 设置 dao
 */
public class SettingDao {

	/**
	 * 意见反馈
	 * 
	 * @param author
	 * @param authorCode
	 * @param content
	 * @return
	 */
	public static FeedBack addFeedBack(String author, String authorCode,
			String content) {
		/*EopClient client = HttpUtility.getInstance().getEopClient();
		ClientRequest request = client.buildClientRequest();
		request.addParam("authorName", author);
		request.addParam("authorCode", authorCode);
		request.addParam("content", content);
		request.addParam("operationTime", TimeUtil.getCurrentTime());
		request.addParam("appID", AppConstants.APPID);

		FeedBack feedBack = null;
		try {

			CompositeResponse<?> res = request.post(URLS.ADD_FEED_BACK, "1.0");
			if (res.isSuccessful()) {
				feedBack = JSON.parseObject(res.getResponseContent(),
						FeedBack.class);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return feedBack;
		*/
		
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("authorName", author);
		paramsMap.put("authorCode", authorCode);
		paramsMap.put("content", content);
		paramsMap.put("operationTime", TimeUtil.getCurrentTime());
		FeedBack feedBack = null;
		try {
			String jsonString = HttpUtility.getInstance().executeNormalTask(
					HttpMethod.Post, "", paramsMap);
			feedBack = JSON.parseObject(jsonString, FeedBack.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return feedBack;

	}

	/**
	 * 获得最新版本号
	 * 
	 * @return
	 */

	public static Update getAppVersion() {
		/*EopClient client = HttpUtility.getInstance().getEopClient();
		ClientRequest request = client.buildClientRequest();
		request.addParam("appId", AppConstants.APPID);

		Update update = null;
		try {
			CompositeResponse<?> res = request.post(URLS.GET_VERSION, "1.0");
			if (res.isSuccessful()) {
				update = JSON.parseObject(res.getResponseContent(),
						Update.class);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return update;*/
		
		Map<String, String> paramsMap = new HashMap<String, String>();
		Update update = null;
		try {
			String jsonString = HttpUtility.getInstance().executeNormalTask(
					HttpMethod.Post, "", paramsMap);
			update = JSON.parseObject(jsonString, Update.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return update;
	}

	
}
