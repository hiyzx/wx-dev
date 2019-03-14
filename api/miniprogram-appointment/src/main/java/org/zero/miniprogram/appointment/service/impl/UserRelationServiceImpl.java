package org.zero.miniprogram.appointment.service.impl;

import org.springframework.stereotype.Service;
import org.zero.miniprogram.appointment.dao.UserRelationMapper;
import org.zero.miniprogram.appointment.model.po.UserRelation;
import org.zero.miniprogram.appointment.service.IUserRelationService;

import javax.annotation.Resource;


/**
 * @author 
 * @date 2019/02/18
 */
@Service
public class UserRelationServiceImpl extends AbstractService<UserRelation> implements IUserRelationService {
    @Resource
    private UserRelationMapper userRelationMapper;

}
