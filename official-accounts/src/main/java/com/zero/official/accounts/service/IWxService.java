package com.zero.official.accounts.service;

import com.zero.official.accounts.web.exception.BaseException;

/**
 * @author yezhaoxing
 * @since 2018/08/13
 */
public interface IWxService {

    void checkSignature(String signature, String timestamp, String nonce) throws BaseException;

    String getAccessToken() throws BaseException;

    String getMenu() throws BaseException;
}
