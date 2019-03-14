package org.zero.miniprogram.appointment.model.po;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 微信唯一识别号
     */
    private String openid;

    /**
     * 微信昵称
     */
    private String nickName;

    /**
     * 微信头像
     */
    private String avatarUrl;

    /**
     * 性别:1男 2女
     */
    private Byte gender;

    /**
     * 姓名
     */
    private String fullName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 工作地
     */
    private String workCity;

    /**
     * 家乡
     */
    private String hometown;

    /**
     * 月薪
     */
    private Double salary;

    /**
     * 学历
     */
    private String educationalBackground;

    /**
     * 毕业院校
     */
    private String graduatedSchool;

    /**
     * 是否填写资料
     */
    private Byte isFillInformation;

    /**
     * 是否发布 0未发布 1已发布
     */
    private Byte isRelease;

    /**
     * 是否vip 0不是 1是
     */
    private Byte isVip;

    private Date createTime;

    private Date updateTime;

    /**
     * 自我介绍
     */
    private String introduction;
}