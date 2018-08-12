package com.zero.official.accounts.controller;

import com.zero.official.accounts.utils.WeChatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
@Slf4j
public class CheckTokenController {

    private static final String TOKEN = "hiyzx";

    @GetMapping("/checkToken")
    public void test(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        String sortString = WeChatUtil.sort(TOKEN, timestamp, nonce);// 排序
        String mytoken = WeChatUtil.SHA1(sortString); // 加密
        if (!Objects.equals(mytoken, "") && mytoken.equals(signature)) {// 校验签名
            log.info("签名校验通过。");
            response.getWriter().println(echostr); // 如果检验成功输出echostr，微信服务器接收到此输出，才会确认检验完成。
        } else {
            log.error("签名校验失败。");
        }
    }
}
