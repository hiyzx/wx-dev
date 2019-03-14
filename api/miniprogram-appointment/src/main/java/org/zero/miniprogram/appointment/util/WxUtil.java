package org.zero.miniprogram.appointment.util;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

/**
 * @author yezhaoxing
 * @since 2019/03/14
 */
public class WxUtil {

    private static final String APPID = "wx029c85105e7f24d6";
    private static final String SECRET = "dca26b41601506af829de50a67dfd0c2";

    private static final String URL = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    public static String getOpenid(String code) {
        String requestUrl = String.format(URL, APPID, SECRET, code);
        String response = HttpUtil.get(requestUrl);
        return JSONUtil.parseObj(response).getStr("openid");
    }
}
