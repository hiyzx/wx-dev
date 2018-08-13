package com.zero.official.accounts.web.controller;

import com.zero.official.accounts.service.IWxService;
import com.zero.official.accounts.utils.XmlUtil;
import com.zero.official.accounts.vo.wx.XmlMessage;
import com.zero.official.accounts.web.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

@RestController("/wx")
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
        // 将request请求，传到Message工具类的转换方法中，返回接收到的Map对象
        Map<String, Object> map = XmlUtil.xmlToMap(request);
        // 从集合中，获取XML各个节点的内容
        String ToUserName = (String) map.get("ToUserName");
        String FromUserName = (String) map.get("FromUserName");
        String CreateTime = (String) map.get("CreateTime");
        String MsgType = (String) map.get("MsgType");
        String Content = (String) map.get("Content");
        String MsgId = (String) map.get("MsgId");

        if (MsgType.equals("text")) {// 判断消息类型是否是文本消息(text)

            XmlMessage message = new XmlMessage();

            message.setFromUserName(ToUserName);// 原来【接收消息用户】变为回复时【发送消息用户】

            message.setToUserName(FromUserName);

            message.setMsgType("text");

            message.setCreateTime(new Date().getTime());// 创建当前时间为消息时间

            message.setContent("您好，" + FromUserName + "\n我是：" + ToUserName

                    + "\n您发送的消息类型为：" + MsgType + "\n您发送的时间为" + CreateTime

                    + "\n我回复的时间为：" + message.getCreateTime() + "\n您发送的内容是" + Content);

            str = XmlUtil.objectToXml(message); // 调用Message工具类，将对象转为XML字符串

            out.print(str);
            out.close();
        }
    }
}