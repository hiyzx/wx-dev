package com.zero.official.accounts.web.controller;

import com.zero.official.accounts.service.IWxService;
import com.zero.official.accounts.web.exception.BaseException;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/manager")
public class WxManagerController {

    @Resource
    private IWxService wxService;

    @GetMapping("/getAccessToken")
    @ApiOperation("获取token")
    public String getAccessToken() throws BaseException{
        return wxService.getAccessToken();
    };
}
