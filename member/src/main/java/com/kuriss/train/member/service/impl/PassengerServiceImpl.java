package com.kuriss.train.member.service.impl;

import com.kuriss.train.member.entity.Passenger;
import com.kuriss.train.member.entity.PassengerQuery;
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
    @Autowired
    private PassengerMapper passengerMapper;

    @Override
    public long countByExample(PassengerQuery query) {
        return passengerMapper.selectCount(query.getQueryWrapper());
    }

    @Override
    public int deleteByExample(PassengerQuery query) {
        return passengerMapper.delete(query.getQueryWrapper());
    }

    @Override
    public List<Passenger> selectByExample(PassengerQuery query) {
        return passengerMapper.selectList(query.getQueryWrapper());
    }

    @Override
    public Passenger selectByPrimaryKey(Long id) {
        return passengerMapper.selectById(id);
    }

    @Override
    public int updateByExampleSelective(Passenger record) {
        return 0;
    }

    @Override
    public int insertSelective(Passenger record) {
        return passengerMapper.insert(record); // 非空字段插入
    }
}
