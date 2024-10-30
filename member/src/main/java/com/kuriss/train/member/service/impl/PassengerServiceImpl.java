package com.kuriss.train.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kuriss.train.member.entity.Passenger;
import com.kuriss.train.member.mapper.PassengerMapper;
import com.kuriss.train.member.service.IPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ${tableComment} 服务实现类
 */
@Service
public class PassengerServiceImpl implements IPassengerService {
    // 实现Service方法
    @Autowired
    private PassengerMapper passengerMapper;

    @Override
    public long countByExample(Passenger record) {
        QueryWrapper<Passenger> queryWrapper = new QueryWrapper<>(record);
        return passengerMapper.selectCount(queryWrapper);
    }

    @Override
    public int deleteByExample(Passenger record) {
        QueryWrapper<Passenger> queryWrapper = new QueryWrapper<>(record);
        return passengerMapper.delete(queryWrapper);
    }

    @Override
    public List<Passenger> selectByExample(Passenger record) {
        QueryWrapper<Passenger> queryWrapper = new QueryWrapper<>(record);
        return passengerMapper.selectList(queryWrapper);
    }

    @Override
    public Passenger selectByPrimaryKey(Long id) {
        return passengerMapper.selectById(id);
    }

    @Override
    public int updateByExampleSelective(Passenger record) {
        UpdateWrapper<Passenger> updateWrapper = new UpdateWrapper<>(record);
        return passengerMapper.update(record, updateWrapper);
    }

    @Override
    public int insertSelective(Passenger record) {
        return passengerMapper.insert(record); // 非空字段插入
    }
}
