package com.kuriss.train.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kuriss.train.business.entity.Station;
import com.kuriss.train.business.entity.StationQuery;
import com.kuriss.train.business.mapper.StationMapper;
import com.kuriss.train.business.service.IStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationServiceImpl implements IStationService {
    // 实现Service方法
    @Autowired
    private StationMapper stationMapper;

    @Override
    public long countByExample(StationQuery query) {
        return stationMapper.selectCount(query.getQueryWrapper());
    }

    @Override
    public int deleteByExample(StationQuery query) {
        return stationMapper.delete(query.getQueryWrapper());
    }

    @Override
    public List<Station> selectByExample(StationQuery query) {
        return stationMapper.selectList(query.getQueryWrapper());
    }

    @Override
    public Station selectByPrimaryKey(Long id) {
        return stationMapper.selectById(id);
    }

    @Override
    public int updateByExampleSelective(Station record,StationQuery stationQuery) {
        UpdateWrapper<Station> updateWrapper = new UpdateWrapper<>(record);
        return stationMapper.update(record, updateWrapper);
    }

    @Override
    public int insertSelective(Station record) {
        return stationMapper.insert(record); // 非空字段插入
    }
}
