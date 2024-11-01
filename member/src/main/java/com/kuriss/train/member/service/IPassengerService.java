package com.kuriss.train.member.service;

import com.kuriss.train.member.entity.Passenger;
import com.kuriss.train.member.entity.PassengerQuery;

import java.util.List;

public interface IPassengerService {
    // Service相关方法
    /**
     * 统计符合条件的记录数
     */
    long countByExample(PassengerQuery record);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(PassengerQuery record);

    /**
     * 根据条件查询记录
     */
    List<Passenger> selectByExample(PassengerQuery record);

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
