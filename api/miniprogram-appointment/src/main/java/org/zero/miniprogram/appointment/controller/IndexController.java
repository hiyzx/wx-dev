package org.zero.miniprogram.appointment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zero.miniprogram.appointment.model.po.UserInfo;
import org.zero.miniprogram.appointment.model.vo.BaseReturnVo;
import org.zero.miniprogram.appointment.model.vo.ReturnVo;
import org.zero.miniprogram.appointment.service.IUserInfoService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yezhaoxing
 * @since 2019/02/18
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @Resource
    private IUserInfoService userInfoService;

    @GetMapping("/list")
    public ReturnVo<List<UserInfo>> list(){
        return ReturnVo.success(userInfoService.findAll());
    }

    @GetMapping("/like")
    public BaseReturnVo like(){
        return BaseReturnVo.success();
    }
}
