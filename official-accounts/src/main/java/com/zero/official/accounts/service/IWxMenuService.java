package com.zero.official.accounts.service;

import com.zero.official.accounts.web.exception.BaseException;

import java.io.IOException;

/**
 * @author yezhaoxing
 * @date 2018/8/21
 */
public interface IWxMenuService {

    void createMenu(String menuJson) throws BaseException, IOException;
}
