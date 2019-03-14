package org.zero.miniprogram.appointment.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UserInfoDto {

    private Integer id;

    @ApiModelProperty("姓名")
    private String fullName;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("生日")
    private Date birthday;

    @ApiModelProperty("工作地")
    private String workCity;

    @ApiModelProperty("家乡")
    private String hometown;

    @ApiModelProperty("月薪")
    private Double salary;

    @ApiModelProperty("学历")
    private String educationalBackground;

    @ApiModelProperty("毕业院校")
    private String graduatedSchool;

    @ApiModelProperty("自我介绍")
    private String introduction;
}