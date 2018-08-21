package com.zero.official.accounts.web.controller;

import com.zero.official.accounts.service.IWxUserService;
import com.zero.official.accounts.web.exception.BaseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yezhaoxing
 * @since 2018/08/21
 */
@RestController
@RequestMapping("/manager/user")
public class WxUserController {

    @Resource
    private IWxUserService wxUserService;

    @GetMapping("/list")
    public String list() throws BaseException {
        return wxUserService.list();
    }

    @GetMapping("/info/get")
    public String getInfo(@RequestParam String openid) throws BaseException {
        return wxUserService.getInfo(openid);
    }
}
