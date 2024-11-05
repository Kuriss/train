package com.kuriss.train.business.service;

import com.kuriss.train.business.entity.Station;
import com.kuriss.train.business.entity.StationQuery;

import java.util.List;

public interface IStationService {
    // Service相关方法
    /**
     * 统计符合条件的记录数
     */
    long countByExample(StationQuery record);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(StationQuery record);

    /**
     * 根据条件查询记录
     */
    List<Station> selectByExample(StationQuery record);

    /**
     * 根据主键查询记录
     */
        Station selectByPrimaryKey(Long id);

    /**
     * 根据条件更新非空字段
     */
    int updateByExampleSelective(Station record,StationQuery stationQuery);

    /**
     * 插入记录（非空字段）
     */
    int insertSelective(Station record);

    public int updateByPrimaryKey(Station station);
    int deleteByPrimaryKey(Long id);
}
