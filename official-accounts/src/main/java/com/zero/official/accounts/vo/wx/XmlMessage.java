package com.zero.official.accounts.vo.wx;

import lombok.Data;

/**
 * @author yezhaoxing
 * @since 2018/08/13
 */
@Data
public class XmlMessage {

    private String ToUserName;

    private String FromUserName;

    private Long CreateTime;

    private String MsgType;

    private String Content;

    private String MsgId;
}
