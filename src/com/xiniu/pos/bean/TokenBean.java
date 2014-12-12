package com.xiniu.pos.bean;

public class TokenBean extends Entity {

	private String access_token;
	private String expires_in;
	private String re_expires_in;
	private String refresh_token;
	private String token_type;
	private String userid;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	public String getRe_expires_in() {
		return re_expires_in;
	}

	public void setRe_expires_in(String re_expires_in) {
		this.re_expires_in = re_expires_in;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}
