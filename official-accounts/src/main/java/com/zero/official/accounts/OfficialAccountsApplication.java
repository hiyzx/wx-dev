package com.zero.official.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.zero.official.accounts")
public class OfficialAccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfficialAccountsApplication.class, args);
    }
}
