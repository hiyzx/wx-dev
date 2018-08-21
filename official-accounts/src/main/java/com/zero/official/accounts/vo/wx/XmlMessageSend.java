package com.zero.official.accounts.vo.wx;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * @author yezhaoxing
 * @date 2018/08/13
 */
@Data
@XStreamAlias("XmlMessage")
public class XmlMessageSend {

    @XStreamAlias("ToUserName")
    private String ToUserName;
    @XStreamAlias("FromUserName")
    private String FromUserName;
    @XStreamAlias("CreateTime")
    private Long CreateTime;
    @XStreamAlias("MsgType")
    private String MsgType;
    @XStreamAlias("Content")
    private String Content;
    @XStreamAlias("MsgId")
    private String MsgId;

    public static XmlMessageSend value(String toUserName, String fromUserName, String content) {
        XmlMessageSend xmlMessage = new XmlMessageSend();
        xmlMessage.setToUserName(toUserName);
        xmlMessage.setFromUserName(fromUserName);
        xmlMessage.setCreateTime(System.currentTimeMillis());
        xmlMessage.setMsgType("text");
        xmlMessage.setContent(content);
        xmlMessage.setMsgId("");
        return xmlMessage;
    }
}
