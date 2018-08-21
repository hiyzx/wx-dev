package com.zero.official.accounts.vo.wx.dto.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel("创建菜单dto")
public class WxMenuDto {

    @ApiModelProperty("一级菜单数组,1-3个")
    private List<WxMenuButton> button = new ArrayList<>();

    @Data
    public static class WxMenuButton {

        @ApiModelProperty("响应动作类型:view网页，click点击，miniprogram小程序")
        private String type;

        @ApiModelProperty("标题")
        private String name;

        @ApiModelProperty("菜单KEY值，用于消息接口推送")
        private String key;

        @ApiModelProperty("网页链接")
        private String url;

        @ApiModelProperty("小程序的appid")
        private String appid;

        @ApiModelProperty("小程序的页面路径")
        private String pagepath;

        @ApiModelProperty("调用新增永久素材接口返回的合法media_id")
        private String media_id;

        @ApiModelProperty("二级菜单数组,1-5个")
        private List<WxMenuButton> sub_button = new ArrayList<>();

    }
}
