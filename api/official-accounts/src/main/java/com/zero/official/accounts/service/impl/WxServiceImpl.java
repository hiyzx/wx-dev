package com.zero.official.accounts.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zero.official.accounts.enums.CodeEnum;
import com.zero.official.accounts.service.IWxService;
import com.zero.official.accounts.utils.HttpClient;
import com.zero.official.accounts.utils.JsonHelper;
import com.zero.official.accounts.utils.RedisHelper;
import com.zero.official.accounts.utils.SHA1Util;
import com.zero.official.accounts.vo.properties.WxProperties;
import com.zero.official.accounts.web.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * @author yezhaoxing
 * @date 2018/08/13
 */
@Service
@Slf4j
public class WxServiceImpl implements IWxService {

    private static final String GET_TOKEN_URI = "/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    @Resource
    private WxProperties wxProperties;
    @Resource
    private HttpClient wxHttpClient;
    @Resource
    private RedisHelper<String, String> redisHelper;

    @Override
    public void checkSignature(String signature, String timestamp, String nonce) throws BaseException {
        // 排序
        String[] strArray = { wxProperties.getToken(), timestamp, nonce };
        Arrays.sort(strArray);
        StringBuilder sb = new StringBuilder();
        for (String str : strArray) {
            sb.append(str);
        }
        if (!signature.equals(SHA1Util.SHA1(sb.toString()))) {// 校验签名
            throw new BaseException(CodeEnum.FAIL, "校验失败");
        }
    }

    @Override
    public String getAccessToken() throws BaseException {
        String accessToken = redisHelper.get("access_token");
        if (accessToken == null) {
            String response = wxHttpClient
                    .get(String.format(GET_TOKEN_URI, wxProperties.getAppId(), wxProperties.getAppSecret()));
            log.info(response);
            Map<String, Object> accessTokenMap = JsonHelper.readValue(response,
                    new TypeReference<Map<String, Object>>() {
                    });
            if (accessTokenMap.containsKey("access_token")) {
                accessToken = (String) accessTokenMap.get("access_token");
                if (accessTokenMap.containsKey("expires_in")) {
                    int expiresIn = (Integer) (accessTokenMap.get("expires_in"));
                    redisHelper.set("access_token", accessToken, expiresIn);
                }
                return accessToken;
            } else {
                log.error("getAccessToken error：errCode={},errMsg={}", accessTokenMap.get("errcode"),
                        accessTokenMap.get("errmsg"));
                throw new BaseException(CodeEnum.WX_REQUEST_FAIL, "getAccessToken error");
            }
        } else {
            return accessToken;
        }
    }
}
