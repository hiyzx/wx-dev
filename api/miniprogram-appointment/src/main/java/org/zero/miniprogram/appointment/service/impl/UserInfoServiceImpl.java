package org.zero.miniprogram.appointment.service.impl;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.zero.miniprogram.appointment.dao.UserInfoMapper;
import org.zero.miniprogram.appointment.model.dto.UserInfoDto;
import org.zero.miniprogram.appointment.model.dto.UserWxInfoDto;
import org.zero.miniprogram.appointment.model.po.UserInfo;
import org.zero.miniprogram.appointment.model.vo.UserInfoVo;
import org.zero.miniprogram.appointment.service.IUserInfoService;

import javax.annotation.Resource;


/**
 * @author
 * @date 2019/02/18
 */
@Service
@Slf4j
public class UserInfoServiceImpl extends AbstractService<UserInfo> implements IUserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public Integer saveOrUpdateWxInfo(UserWxInfoDto userWxInfoDto) {
        UserInfo userInfo = new UserInfo();
        userInfo.setOpenid(userWxInfoDto.getOpenid());
        userInfo = userInfoMapper.selectOne(userInfo);
        if(userInfo != null){
            BeanUtils.copyProperties(userWxInfoDto, userInfo);
            userInfo.setUpdateTime(DateUtil.date());
            userInfoMapper.updateByPrimaryKeySelective(userInfo);
        } else {
            userInfo = new UserInfo();
            BeanUtils.copyProperties(userWxInfoDto, userInfo);
            userInfo.setCreateTime(DateUtil.date());
            userInfoMapper.insertSelective(userInfo);
        }
        return userInfo.getId();
    }

    @Override
    public UserInfoVo selectById(Integer id) {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
        UserInfoVo rtn = new UserInfoVo();
        if (userInfo != null) {
            BeanUtils.copyProperties(userInfo, rtn);
        }
        return rtn;
    }

    @Override
    public void saveOrUpdate(UserInfoDto userInfoDto) {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userInfoDto.getId());
        if (userInfo != null) {
            BeanUtils.copyProperties(userInfoDto, userInfo);
            userInfo.setUpdateTime(DateUtil.date());
            userInfoMapper.updateByPrimaryKey(userInfo);
        } else {
            userInfo = new UserInfo();
            BeanUtils.copyProperties(userInfoDto, userInfo);
            userInfo.setCreateTime(DateUtil.date());
            userInfoMapper.insertSelective(userInfo);
        }
    }
}
