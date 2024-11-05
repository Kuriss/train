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

    @Override
    public int updateByPrimaryKey(Station station) {
        UpdateWrapper<Station> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", station.getId());
        // 设置要更新的字段
        if (station.getName() != null) {
            updateWrapper.set("name", station.getName());
        }
        if (station.getNamePinyin() != null) {
            updateWrapper.set("name_pinyin", station.getNamePinyin());
        }
        if (station.getNamePy() != null) {
            updateWrapper.set("name_py", station.getNamePy());
        }
        if (station.getCreateTime() != null) {
            updateWrapper.set("create_time", station.getCreateTime());
        }
        if (station.getUpdateTime() != null) {
            updateWrapper.set("update_time", station.getUpdateTime());
        }

        return stationMapper.update(null, updateWrapper);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {

        return stationMapper.deleteById(id);
    }
}
