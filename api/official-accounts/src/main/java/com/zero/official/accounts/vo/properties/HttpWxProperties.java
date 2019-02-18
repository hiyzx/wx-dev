package com.zero.official.accounts.vo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author yezhaoxing
 * @date 2017/10/17
 */
@Component
@ConfigurationProperties(prefix = "http.wx")
@Data
public class HttpWxProperties {

    private String scheme;

    private String hostname;

    private Integer port;

}
