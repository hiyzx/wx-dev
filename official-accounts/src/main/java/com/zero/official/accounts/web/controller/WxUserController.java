package com.zero.official.accounts.web.controller;

import com.zero.official.accounts.service.IWxUserService;
import com.zero.official.accounts.vo.ReturnVo;
import com.zero.official.accounts.vo.wx.response.user.WxUser;
import com.zero.official.accounts.vo.wx.response.user.WxUserListResult;
import com.zero.official.accounts.web.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(description = "用户相关接口")
public class WxUserController {

    @Resource
    private IWxUserService wxUserService;

    @GetMapping("/list")
    @ApiOperation("查询用户列表")
    public ReturnVo<WxUserListResult> list() throws BaseException {
        return ReturnVo.success(wxUserService.list());
    }

    @GetMapping("/info/get")
    @ApiOperation("查询单个用户信息")
    public ReturnVo<WxUser> getInfo(@RequestParam String openid) throws BaseException {
        return ReturnVo.success(wxUserService.getInfo(openid));
    }
}
