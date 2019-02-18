package com.zero.official.accounts.service.impl;

import com.zero.official.accounts.service.IWxMenuService;
import com.zero.official.accounts.service.IWxService;
import com.zero.official.accounts.utils.HttpClient;
import com.zero.official.accounts.utils.JsonHelper;
import com.zero.official.accounts.utils.WxUtil;
import com.zero.official.accounts.vo.wx.dto.menu.WxMenuDto;
import com.zero.official.accounts.vo.wx.response.menu.WxMenuResult;
import com.zero.official.accounts.web.exception.BaseException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author yezhaoxing
 * @date 2018/8/21
 */
@Service
public class WxMenuServiceImpl implements IWxMenuService {

    private static final String CREATE_URI = "/cgi-bin/menu/create?access_token=%s";

    private static final String LIST_URI = "/cgi-bin/menu/get?access_token=%s";

    private static final String SUCCESS_ERR_CODE = "0";

    @Resource
    private HttpClient wxHttpClient;

    @Resource
    private IWxService wxService;

    @Override
    public void create(WxMenuDto wxMenuDto) throws BaseException, IOException {
        String response = wxHttpClient.post(String.format(CREATE_URI, wxService.getAccessToken()),
                JsonHelper.toJSon(wxMenuDto));
        WxUtil.handlerException(response);
    }

    @Override
    public WxMenuResult list() throws BaseException {
        String response = wxHttpClient.get(String.format(LIST_URI, wxService.getAccessToken()));
        WxUtil.handlerException(response);
        return JsonHelper.readValue(response, WxMenuResult.class);
    }
}
