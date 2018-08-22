package com.zero.official.accounts.service;

import com.zero.official.accounts.vo.wx.response.user.WxUser;
import com.zero.official.accounts.vo.wx.response.user.WxUserListResult;
import com.zero.official.accounts.web.exception.BaseException;

/**
 * @author yezhaoxing
 * @since 2018/08/21
 */
public interface IWxUserService {

    WxUserListResult list() throws BaseException;

    WxUser getInfo(String openid) throws BaseException;
}
