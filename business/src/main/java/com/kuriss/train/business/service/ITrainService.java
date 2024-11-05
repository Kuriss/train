package com.kuriss.train.business.service;

import com.kuriss.train.business.entity.Train;
import com.kuriss.train.business.entity.TrainQuery;

import java.util.List;

public interface ITrainService {
    // Service相关方法
    /**
     * 统计符合条件的记录数
     */
    long countByExample(TrainQuery record);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(TrainQuery record);

    /**
     * 根据条件查询记录
     */
    List<Train> selectByExample(TrainQuery record);

    /**
     * 根据主键查询记录
     */
        Train selectByPrimaryKey(Long id);

    /**
     * 根据条件更新非空字段
     */
    int updateByExampleSelective(Train record);

    /**
     * 插入记录（非空字段）
     */
    int insertSelective(Train record);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKey(Train train);
}
