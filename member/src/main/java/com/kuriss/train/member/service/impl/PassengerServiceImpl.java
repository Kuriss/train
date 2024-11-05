package com.kuriss.train.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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

    @Override
    public int updateByPrimaryKey(Passenger passenger) {
        UpdateWrapper<Passenger> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", passenger.getId());

        // 设置要更新的字段
        if (passenger.getMemberId() != null) {
            updateWrapper.set("member_id", passenger.getMemberId());
        }
        if (passenger.getName() != null) {
            updateWrapper.set("name", passenger.getName());
        }
        if (passenger.getIdCard() != null) {
            updateWrapper.set("id_card", passenger.getIdCard());
        }
        if (passenger.getType() != null) {
            updateWrapper.set("type", passenger.getType());
        }
        if (passenger.getCreateTime() != null) {
            updateWrapper.set("create_time", passenger.getCreateTime());
        }
        if (passenger.getUpdateTime() != null) {
            updateWrapper.set("update_time", passenger.getUpdateTime());
        }

        return passengerMapper.update(null, updateWrapper);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return passengerMapper.deleteById(id);
    }
}
