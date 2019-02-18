package com.zero.official.accounts.bean;

import com.zero.official.accounts.utils.HttpClient;
import com.zero.official.accounts.vo.properties.HttpWxProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author yezhaoxing
 * @date 2018/08/07
 */
@Configuration
public class HttpClientBean {

    @Resource
    private HttpWxProperties httpWxProperties;

    @Bean("wxHttpClient")
    public HttpClient wxHttpClient() {
        return new HttpClient(httpWxProperties.getScheme(), httpWxProperties.getHostname(), httpWxProperties.getPort());
    }

}
