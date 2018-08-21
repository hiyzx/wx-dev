package com.zero.official.accounts.vo.wx.response.menu;

import com.zero.official.accounts.vo.wx.dto.menu.WxMenuDto;
import lombok.Data;

import java.util.List;

@Data
public class WxMenuResult {
    private WxMenuDto menu;
    private List<WxMenuDto> conditionalmenu;
}
