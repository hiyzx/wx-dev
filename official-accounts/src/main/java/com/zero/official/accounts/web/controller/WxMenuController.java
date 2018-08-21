package com.zero.official.accounts.web.controller;

import com.zero.official.accounts.service.IWxMenuService;
import com.zero.official.accounts.vo.BaseReturnVo;
import com.zero.official.accounts.web.exception.BaseException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author yezhaoxing
 * @date 2018/8/21
 */
@RestController
@RequestMapping("/manager/menu")
public class WxMenuController {

    @Resource
    private IWxMenuService wxMenuService;

    @PostMapping("/create")
    public BaseReturnVo create(@RequestParam String menuJson) throws IOException, BaseException {
        wxMenuService.createMenu(menuJson);
        return BaseReturnVo.success();
    }


    @GetMapping("/getMenu")
    public String getMenu() throws BaseException {
        return "";
    }
}
