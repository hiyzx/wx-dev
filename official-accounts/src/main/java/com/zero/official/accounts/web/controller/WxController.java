package com.zero.official.accounts.web.controller;

import com.zero.official.accounts.service.IWxService;
import com.zero.official.accounts.utils.XmlUtil;
import com.zero.official.accounts.vo.wx.XmlMessage;
import com.zero.official.accounts.vo.wx.XmlMessageSend;
import com.zero.official.accounts.web.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yezhaoxing
 * @date 2018/8/15
 */
@RestController
@RequestMapping("/wx")
@Slf4j
public class WxController {

    @Resource
    private IWxService wxService;

    @GetMapping
    public String checkSignature(@RequestParam String signature, @RequestParam String timestamp,
            @RequestParam String nonce, @RequestParam String echostr) throws BaseException {
        wxService.checkSignature(signature, timestamp, nonce);
        return echostr;
    }

    @PostMapping
    public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        XmlMessage xmlMessage = XmlMessage.value(XmlUtil.xmlToMap(request));

        String msgType = xmlMessage.getMsgType();
        log.info(xmlMessage.toString());
        if ("event".equals(msgType)) {
            String event = xmlMessage.getEvent();
            if ("subscribe".equals(event)) {
                sendMsg(out, XmlMessageSend.value(xmlMessage.getFromUserName(), xmlMessage.getToUserName(),
                        "来日方长，我们终于遇见了。"));
            } else if ("unsubscribe".equals(event)) {
                log.info("{} 取消关注了", xmlMessage.getFromUserName());
            } else if ("CLICK".equals(event)) {
                sendMsg(out, XmlMessageSend.value(xmlMessage.getFromUserName(), xmlMessage.getToUserName(),
                        getClickContent(xmlMessage.getEventKey())));
            }
        } else if ("location".equals(msgType)) {
            sendMsg(out,
                    XmlMessageSend.value(xmlMessage.getFromUserName(), xmlMessage.getToUserName(),
                            String.format("您的地理位置为%s,经度为%s,维度为%s", xmlMessage.getLabel(), xmlMessage.getLocation_X(),
                                    xmlMessage.getLocation_Y())));
        } else if ("text".equals(msgType)) {
            sendMsg(out, XmlMessageSend.value(xmlMessage.getFromUserName(), xmlMessage.getToUserName(),
                    getContent(xmlMessage.getContent())));
        }
    }

    private void sendMsg(PrintWriter out, XmlMessageSend xmlMessageSend) {
        String str = XmlUtil.objectToXml(xmlMessageSend); // 调用Message工具类，将对象转为XML字符串
        out.print(str);
        out.close();
    }

    private String getContent(String content) {
        String response = defaultMsg().get(content);
        return response != null ? response : "风太大,我听不清！";
    }

    private String getClickContent(String content) {
        return clickMsg().get(content);
    }

    private Map<String, String> defaultMsg() {
        Map<String, String> msgMap = new HashMap<>();
        msgMap.put("外卖", "www.hiyzx.cn");
        msgMap.put("美食", "hello world");
        return msgMap;
    }

    private Map<String, String> clickMsg() {
        Map<String, String> msgMap = new HashMap<>();
        msgMap.put("listening", "http://www.kugou.com/");
        msgMap.put("watching", "https://www.youku.com/");
        msgMap.put("badminto", "http://sports.sina.com.cn/nba/");
        return msgMap;
    }
}