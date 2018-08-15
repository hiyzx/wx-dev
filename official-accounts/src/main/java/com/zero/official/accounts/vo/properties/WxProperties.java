package com.zero.official.accounts.vo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author yezhaoxing
 * @date 2018/08/13
 */
@Component
@ConfigurationProperties(prefix = "wx")
@Data
public class WxProperties {

    private String token;

    private String appId;

    private String appSecret;
}
