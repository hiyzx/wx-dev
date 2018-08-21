package com.zero.official.accounts.service.impl;

import com.zero.official.accounts.enums.CodeEnum;
import com.zero.official.accounts.service.IWxMenuService;
import com.zero.official.accounts.service.IWxService;
import com.zero.official.accounts.utils.HttpClient;
import com.zero.official.accounts.utils.JsonHelper;
import com.zero.official.accounts.vo.wx.response.BaseResponseVo;
import com.zero.official.accounts.web.exception.BaseException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yezhaoxing
 * @date 2018/8/21
 */
@Service
public class WxMenuServiceImpl implements IWxMenuService {

    private static final String CREATE_URI = "/cgi-bin/menu/create";

    private static final String SUCCESS_ERR_CODE = "0";

    @Resource
    private HttpClient wxHttpClient;

    @Resource
    private IWxService wxService;

    @Override
    public void createMenu(String menuJson) throws BaseException, IOException {
        Map<String, String> params = new HashMap<>(2);
        params.put("access_token", wxService.getAccessToken());
        String response = wxHttpClient.post(CREATE_URI, params);
        BaseResponseVo baseResponseVo = JsonHelper.readValue(response, BaseResponseVo.class);
        if(!SUCCESS_ERR_CODE.equals(baseResponseVo.getErrcode())){
            throw new BaseException(CodeEnum.WX_REQUEST_FAIL, "request wechat fail");
        }
    }
}
