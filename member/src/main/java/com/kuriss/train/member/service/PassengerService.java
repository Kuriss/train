package com.kuriss.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.kuriss.train.common.util.SnowUtil;
import com.kuriss.train.member.entity.Passenger;
import com.kuriss.train.member.entity.PassengerSaveDto;
import com.kuriss.train.member.mapper.PassengerMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {
    @Resource
    private PassengerMapper passengerMapper;

    public void save(PassengerSaveDto psd) {
        DateTime now = DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(psd, Passenger.class);
        passenger.setId(SnowUtil.getSnowflakeNextId());
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        passengerMapper.insert(passenger);
    }
}
