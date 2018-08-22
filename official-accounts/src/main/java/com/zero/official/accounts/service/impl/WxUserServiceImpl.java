package com.zero.official.accounts.service.impl;

import com.zero.official.accounts.service.IWxService;
import com.zero.official.accounts.service.IWxUserService;
import com.zero.official.accounts.utils.HttpClient;
import com.zero.official.accounts.utils.JsonHelper;
import com.zero.official.accounts.utils.WxUtil;
import com.zero.official.accounts.vo.wx.response.user.WxUser;
import com.zero.official.accounts.vo.wx.response.user.WxUserListResult;
import com.zero.official.accounts.web.exception.BaseException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yezhaoxing
 * @since 2018/08/21
 */
@Service
public class WxUserServiceImpl implements IWxUserService {

    private static final String LIST_URI = "/cgi-bin/user/get?access_token=%s";

    private static final String GET_INFO_URI = "/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";

    @Resource
    private HttpClient wxHttpClient;

    @Resource
    private IWxService wxService;

    @Override
    public WxUserListResult list() throws BaseException {
        String response = wxHttpClient.get(String.format(LIST_URI, wxService.getAccessToken()));
        WxUtil.handlerException(response);
        return JsonHelper.readValue(response, WxUserListResult.class);
    }

    @Override
    public WxUser getInfo(String openid) throws BaseException {
        String response = wxHttpClient.get(String.format(GET_INFO_URI, wxService.getAccessToken(), openid));
        WxUtil.handlerException(response);
        return JsonHelper.readValue(response, WxUser.class);
    }
}
