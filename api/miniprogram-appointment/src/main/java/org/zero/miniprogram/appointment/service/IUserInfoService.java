package org.zero.miniprogram.appointment.service;

import org.zero.miniprogram.appointment.config.BaseService;
import org.zero.miniprogram.appointment.model.dto.UserInfoDto;
import org.zero.miniprogram.appointment.model.dto.UserWxInfoDto;
import org.zero.miniprogram.appointment.model.po.UserInfo;
import org.zero.miniprogram.appointment.model.vo.UserInfoVo;

/**
 * @author 
 * @date 2019/02/18
 */
public interface IUserInfoService extends BaseService<UserInfo> {

    Integer saveOrUpdateWxInfo(UserWxInfoDto userWxInfoDto);

    UserInfoVo selectById(Integer id);

    void saveOrUpdate(UserInfoDto userInfoDto);
}
