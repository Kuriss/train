package com.kuriss.train.member.service;

import com.kuriss.train.member.entity.Passenger;

import java.util.List;

public interface IPassengerService {
    // Service相关方法
    /**
     * 统计符合条件的记录数
     */
    long countByExample(Passenger record);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(Passenger record);

    /**
     * 根据条件查询记录
     */
    List<Passenger> selectByExample(Passenger record);

    /**
     * 根据主键查询记录
     */
        Passenger selectByPrimaryKey(Long id);

    /**
     * 根据条件更新非空字段
     */
    int updateByExampleSelective(Passenger record);

    /**
     * 插入记录（非空字段）
     */
    int insertSelective(Passenger record);
}
