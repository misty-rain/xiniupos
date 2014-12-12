package com.xiniu.pos.dao.person;

import com.xiniu.api.ApiException;
import com.xiniu.api.domain.membership.Member;
import com.xiniu.api.request.membership.MemberGetRequest;
import com.xiniu.api.response.membership.MemberGetResponse;
import com.xiniu.pos.support.http.HttpUtility;

/**
 * 用户dao
 * 
 * @ClassName: PersonDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author misty-rain
 * @date 2014-12-11 下午3:34:42
 * 
 */
public class PersonDao {

	/**
	 * 通过id查询 会员信息
	 * 
	 * @param id
	 * @param accessToken
	 * @return
	 */
	public static Member getMemberShip(final String id, final String accessToken) {
		Member mb = null;
		MemberGetRequest request = new MemberGetRequest();
		request.setId(Long.parseLong(id));
		try {
			MemberGetResponse response = HttpUtility.getInstance().client
					.execute(request, accessToken);
			mb = response.getMember();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mb;

	}

}
