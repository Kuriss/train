package com.kuriss.train.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kuriss.train.business.entity.Train;
import com.kuriss.train.business.entity.TrainQuery;
import com.kuriss.train.business.mapper.TrainMapper;
import com.kuriss.train.business.service.ITrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ${tableComment} 服务实现类
 */
@Service
public class TrainServiceImpl implements ITrainService {
    // 实现Service方法
    @Autowired
    private TrainMapper trainMapper;

    @Override
    public long countByExample(TrainQuery query) {
        return trainMapper.selectCount(query.getQueryWrapper());
    }

    @Override
    public int deleteByExample(TrainQuery query) {
        return trainMapper.delete(query.getQueryWrapper());
    }

    @Override
    public List<Train> selectByExample(TrainQuery query) {
        return trainMapper.selectList(query.getQueryWrapper());
    }

    @Override
    public Train selectByPrimaryKey(Long id) {
        return trainMapper.selectById(id);
    }

    @Override
    public int updateByExampleSelective(Train record) {
        UpdateWrapper<Train> updateWrapper = new UpdateWrapper<>(record);
        return trainMapper.update(record, updateWrapper);
    }

    @Override
    public int insertSelective(Train record) {
        return trainMapper.insert(record); // 非空字段插入
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return trainMapper.deleteById(id);
    }

    @Override
    public int updateByPrimaryKey(Train train) {
        UpdateWrapper<Train> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", train.getId());
        if(train.getCode()!=null){
            updateWrapper.set("code", train.getCode());
        }
        if(train.getEnd()!=null){
            updateWrapper.set("end", train.getEnd());
        }
        if(train.getStart()!=null){
            updateWrapper.set("start", train.getStart());
        }
        if(train.getType()!=null){
            updateWrapper.set("type", train.getType());
        }
        if (train.getStartPinyin()!=null){
            updateWrapper.set("start_pinyin", train.getStartPinyin());
        }
        if(train.getEndPinyin()!=null){
            updateWrapper.set("end_pinyin", train.getEndPinyin());
        }
        if(train.getStartTime()!=null){
            updateWrapper.set("start_time", train.getStartTime());
        }
        if(train.getEndTime()!=null){
            updateWrapper.set("end_time", train.getEndTime());
        }
        return trainMapper.update(null, updateWrapper);
    }
}
