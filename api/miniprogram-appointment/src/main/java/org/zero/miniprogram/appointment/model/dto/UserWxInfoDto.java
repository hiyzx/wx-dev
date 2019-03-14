package org.zero.miniprogram.appointment.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserWxInfoDto {

    @ApiModelProperty("微信唯一识别号")
    private String openid;

    @ApiModelProperty("微信昵称")
    private String nickName;

    @ApiModelProperty("微信头像")
    private String avatarUrl;

    @ApiModelProperty("性别:1男 2女")
    private Byte gender;
}