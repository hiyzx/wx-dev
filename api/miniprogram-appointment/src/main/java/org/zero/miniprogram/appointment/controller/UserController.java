package org.zero.miniprogram.appointment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zero.miniprogram.appointment.model.dto.UserInfoDto;
import org.zero.miniprogram.appointment.model.dto.UserWxInfoDto;
import org.zero.miniprogram.appointment.model.vo.BaseReturnVo;
import org.zero.miniprogram.appointment.model.vo.ReturnVo;
import org.zero.miniprogram.appointment.model.vo.UserInfoVo;
import org.zero.miniprogram.appointment.service.IUserInfoService;
import org.zero.miniprogram.appointment.util.WxUtil;

import javax.annotation.Resource;

/**
 * @author yezhaoxing
 * @since 2019/02/18
 */
@RestController
public class UserController {

    @Resource
    private IUserInfoService userInfoService;

    @GetMapping("/getOpenid")
    public ReturnVo<String> getOpenid(@RequestParam String code) {
        return ReturnVo.success(WxUtil.getOpenid(code));
    }

    @GetMapping("/saveWxInfo")
    public ReturnVo<Integer> saveWxInfo(@RequestBody UserWxInfoDto userWxInfoDto) {
        return ReturnVo.success(userInfoService.saveOrUpdateWxInfo(userWxInfoDto));
    }

    @GetMapping("/getUserInfo")
    public ReturnVo<UserInfoVo> getUserInfo(@RequestParam Integer wxInfoId) {
        return ReturnVo.success(userInfoService.selectById(wxInfoId));
    }

    @GetMapping("/saveUserInfo")
    public BaseReturnVo saveUserInfo(@RequestBody UserInfoDto userInfoDto) {
        userInfoService.saveOrUpdate(userInfoDto);
        return BaseReturnVo.success();
    }
}
