package com.zero.official.accounts.web.controller;

import com.zero.official.accounts.service.IWxService;
import com.zero.official.accounts.utils.XmlUtil;
import com.zero.official.accounts.vo.wx.XmlMessage;
import com.zero.official.accounts.web.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
        String str = null;

        XmlMessage xmlMessage = XmlMessage.value(XmlUtil.xmlToMap(request));

        /*if ("text".equals(MsgType)) {// 判断消息类型是否是文本消息(text)

            XmlMessage message = new XmlMessage();

            message.setFromUserName(ToUserName);// 原来【接收消息用户】变为回复时【发送消息用户】

            message.setToUserName(FromUserName);

            message.setMsgType("text");

            message.setCreateTime(System.currentTimeMillis());// 创建当前时间为消息时间

            message.setContent("您好，" + FromUserName + "\n我是：" + ToUserName

                    + "\n您发送的消息类型为：" + MsgType + "\n您发送的时间为" + CreateTime

                    + "\n我回复的时间为：" + message.getCreateTime() + "\n您发送的内容是" + Content);

            str = XmlUtil.objectToXml(message); // 调用Message工具类，将对象转为XML字符串

            out.print(str);
            out.close();
        }*/
    }
}