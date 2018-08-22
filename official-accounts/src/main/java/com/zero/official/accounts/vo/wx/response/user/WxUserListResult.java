package com.zero.official.accounts.vo.wx.response.user;

import lombok.Data;

@Data
public class WxUserListResult {
	private int total;
	private int count;
	private WxOpenId data;
	private String next_openid;

	@Data
	public static class WxOpenId {
		private String[] openid;
	}
}
