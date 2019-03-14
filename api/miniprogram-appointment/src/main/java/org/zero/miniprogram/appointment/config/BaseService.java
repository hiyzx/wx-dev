package org.zero.miniprogram.appointment.config;

import java.util.List;

/**
 * @author yezhaoxing
 * @date 2018/8/29
 * @description Service 层 基础接口，其他Service 接口 请继承该接口
 */
public interface BaseService<T> {
    void save(T model);// 持久化

    void save(List<T> models);// 批量持久化

    void deleteById(Integer id);// 通过主鍵刪除

    void deleteByIds(String ids);// 批量刪除 eg：ids -> “1,2,3,4”

    void update(T model);// 更新

    T findById(Integer id);// 通过ID查找

    T findBy(String fieldName, Object value) throws Exception; // 通过Model中某个成员变量名称（非数据表中column的名称）查找,value需符合unique约束

    List<T> findByIds(String ids);// 通过多个ID查找//eg：ids -> “1,2,3,4”

    List<T> findAll();// 获取所有
}