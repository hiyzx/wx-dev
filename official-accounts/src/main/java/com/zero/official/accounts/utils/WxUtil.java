package com.zero.official.accounts.utils;

import com.zero.official.accounts.enums.CodeEnum;
import com.zero.official.accounts.vo.wx.response.BaseResponseVo;
import com.zero.official.accounts.web.exception.BaseException;

import java.util.Objects;

/**
 * @author yezhaoxing
 * @since 2018/08/21
 */
public class WxUtil {

    private static final String SUCCESS_CODE = "0";

    public static void handlerException(String response) throws BaseException {
        if (response.contains("errcode")) {
            BaseResponseVo baseResponseVo = JsonHelper.readValue(response, BaseResponseVo.class);
            if (!Objects.equals(SUCCESS_CODE, baseResponseVo.getErrcode())) {
                throw new BaseException(CodeEnum.WX_REQUEST_FAIL, baseResponseVo.getErrmsg());
            }
        }
    }
}
