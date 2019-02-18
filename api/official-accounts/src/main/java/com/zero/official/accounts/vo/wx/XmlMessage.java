package com.zero.official.accounts.vo.wx;

import cn.hutool.core.map.MapUtil;
import lombok.Data;

import java.util.Map;

/**
 * @author yezhaoxing
 * @date 2018/08/13
 */
@Data
public class XmlMessage {

    private String ToUserName;
    private String FromUserName;
    private Long CreateTime;
    private String MsgType;
    private String Content;
    private String MsgId;
    private String Event;
    /*private String Latitude;
    private String Longitude;
    private String Precision;*/
    private String EventKey;
    private String Label;
    private String Location_X;
    private String Location_Y;
    private String Scale;

    public static XmlMessage value(Map<String, Object> xmlMap) {
        XmlMessage xmlMessage = new XmlMessage();
        xmlMessage.setToUserName(MapUtil.getStr(xmlMap, "ToUserName"));
        xmlMessage.setFromUserName(MapUtil.getStr(xmlMap, "FromUserName"));
        xmlMessage.setCreateTime(MapUtil.getLong(xmlMap, "CreateTime"));
        xmlMessage.setMsgType(MapUtil.getStr(xmlMap, "MsgType"));
        xmlMessage.setContent(MapUtil.getStr(xmlMap, "Content"));
        xmlMessage.setMsgId(MapUtil.getStr(xmlMap, "MsgId"));
        xmlMessage.setEvent(MapUtil.getStr(xmlMap, "Event"));
        xmlMessage.setLabel(MapUtil.getStr(xmlMap, "Label"));
        xmlMessage.setLocation_X(MapUtil.getStr(xmlMap, "Location_X"));
        xmlMessage.setLocation_Y(MapUtil.getStr(xmlMap, "Location_Y"));
        xmlMessage.setScale(MapUtil.getStr(xmlMap, "Scale"));
        xmlMessage.setEventKey(MapUtil.getStr(xmlMap, "EventKey"));
        return xmlMessage;
    }
}
