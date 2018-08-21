package com.zero.official.accounts.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yezhaoxing
 * @date 2017/7/17
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Value("${project.version}")
    private String version;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.zero.official.accounts.web.controller"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("official-accounts API").description("公众号开发API").license("仅供内部参考")
                .contact(new Contact("zero", "https://github.com/hiyzx/", "452002276@qq.com")).version(version)
                .termsOfServiceUrl(null).build();
    }
}