package com.zero.official.accounts.web.controller;

import com.zero.official.accounts.service.IWxMenuService;
import com.zero.official.accounts.vo.BaseReturnVo;
import com.zero.official.accounts.vo.wx.dto.menu.WxMenuDto;
import com.zero.official.accounts.vo.wx.response.menu.WxMenuResult;
import com.zero.official.accounts.web.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author yezhaoxing
 * @date 2018/8/21
 */
@RestController
@RequestMapping("/manager/menu")
@Api(description = "菜单相关接口")
public class WxMenuController {

    @Resource
    private IWxMenuService wxMenuService;

    @PostMapping("/create")
    @ApiOperation("创建")
    public BaseReturnVo create(@RequestBody WxMenuDto wxMenuDto) throws IOException, BaseException {
        wxMenuService.create(wxMenuDto);
        return BaseReturnVo.success();
    }

    @GetMapping("/list")
    @ApiOperation("查询列表")
    public WxMenuResult list() throws BaseException {
        return wxMenuService.list();
    }
}
