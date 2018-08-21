package com.zero.official.accounts.service;

import com.zero.official.accounts.vo.wx.dto.menu.WxMenuDto;
import com.zero.official.accounts.vo.wx.response.menu.WxMenuResult;
import com.zero.official.accounts.web.exception.BaseException;

import java.io.IOException;

/**
 * @author yezhaoxing
 * @date 2018/8/21
 */
public interface IWxMenuService {

    void create(WxMenuDto wxMenuDto) throws BaseException, IOException;

    WxMenuResult list() throws BaseException;
}
