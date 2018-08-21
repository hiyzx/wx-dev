package com.zero.official.accounts.service;

import com.zero.official.accounts.web.exception.BaseException;

/**
 * @author yezhaoxing
 * @since 2018/08/21
 */
public interface IWxUserService {

    String list() throws BaseException;

    String getInfo(String openid) throws BaseException;
}
