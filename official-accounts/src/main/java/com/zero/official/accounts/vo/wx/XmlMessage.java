package com.zero.official.accounts.vo.wx;

import cn.hutool.core.map.MapUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.Map;

/**
 * @author yezhaoxing
 * @date 2018/08/13
 */
@Data
@XStreamAlias("XmlMessage")
public class XmlMessage {

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

    public static XmlMessage value(Map<String,Object> xmlMap){
        XmlMessage xmlMessage = new XmlMessage();
        xmlMessage.setToUserName(MapUtil.getStr(xmlMap, "ToUserName"));
        xmlMessage.setFromUserName(MapUtil.getStr(xmlMap, "FromUserName"));
        xmlMessage.setCreateTime(MapUtil.getLong(xmlMap, "CreateTime"));
        xmlMessage.setMsgType(MapUtil.getStr(xmlMap, "MsgType"));
        xmlMessage.setContent(MapUtil.getStr(xmlMap, "Content"));
        xmlMessage.setMsgId(MapUtil.getStr(xmlMap, "MsgId"));
        return xmlMessage;
    }
}
