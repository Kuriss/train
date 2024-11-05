package com.kuriss.train.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuriss.train.business.entity.TrainCarriage;
import com.kuriss.train.business.entity.TrainSeat;
import com.kuriss.train.business.entity.TrainSeatExample;
import com.kuriss.train.business.enums.SeatColEnum;
import com.kuriss.train.business.mapper.TrainSeatMapper;
import com.kuriss.train.business.req.TrainSeatQueryReq;
import com.kuriss.train.business.req.TrainSeatSaveReq;
import com.kuriss.train.business.resp.TrainSeatQueryResp;
import com.kuriss.train.common.resp.PageResp;
import com.kuriss.train.common.util.SnowUtil;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrainSeatService {

    private static final Logger LOG = LoggerFactory.getLogger(TrainSeatService.class);

    @Resource
    private TrainSeatMapper trainSeatMapper;

    @Resource
    private TrainCarriageService trainCarriageService;

    public void save(TrainSeatSaveReq req) {
        DateTime now = DateTime.now();
        TrainSeat trainSeat = BeanUtil.copyProperties(req, TrainSeat.class);
        if (ObjectUtil.isNull(trainSeat.getId())) {
            trainSeat.setId(SnowUtil.getSnowflakeNextId());
            trainSeat.setCreateTime(now);
            trainSeat.setUpdateTime(now);
            trainSeatMapper.insert(trainSeat);
        } else {
            trainSeat.setUpdateTime(now);
            trainSeatMapper.updateByPrimaryKey(trainSeat);
        }
    }

    public PageResp<TrainSeatQueryResp> queryList(TrainSeatQueryReq req) {
        TrainSeatExample trainSeatExample = new TrainSeatExample();
        trainSeatExample.setOrderByClause("train_code asc, carriage_index asc, carriage_seat_index asc");
        TrainSeatExample.Criteria criteria = trainSeatExample.createCriteria();
        if (ObjectUtil.isNotEmpty(req.getTrainCode())) {
            criteria.andTrainCodeEqualTo(req.getTrainCode());
        }

        LOG.info("查询页码：{}", req.getPage());
        LOG.info("每页条数：{}", req.getSize());
        PageHelper.startPage(req.getPage(), req.getSize());
        List<TrainSeat> trainSeatList = trainSeatMapper.selectByExample(trainSeatExample);

        PageInfo<TrainSeat> pageInfo = new PageInfo<>(trainSeatList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        List<TrainSeatQueryResp> list = BeanUtil.copyToList(trainSeatList, TrainSeatQueryResp.class);

        PageResp<TrainSeatQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

    public void delete(Long id) {
        trainSeatMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public void genTrainSeat(String trainCode) {
        DateTime now = DateTime.now();
        TrainSeatExample trainSeatExample = new TrainSeatExample();
        TrainSeatExample.Criteria criteria = trainSeatExample.createCriteria();
        criteria.andTrainCodeEqualTo(trainCode);
        trainSeatMapper.deleteByExample(trainSeatExample);

        List<TrainCarriage> carriageList = trainCarriageService.selectByTrainCode(trainCode);

        for(TrainCarriage carriage : carriageList) {
            Integer rowCount = carriage.getRowCount();
            String seatType = carriage.getSeatType();
            int seatIndex = 1;
            List<SeatColEnum> colEnumList = SeatColEnum.getColsByType(seatType);
            for(int row =1;row<=rowCount;row++) {
                for(SeatColEnum colEnum : colEnumList) {
                    TrainSeat trainSeat = new TrainSeat();
                    trainSeat.setTrainCode(trainCode);
                    trainSeat.setSeatType(seatType);
                    trainSeat.setId(SnowUtil.getSnowflakeNextId());
                    trainSeat.setUpdateTime(now);
                    trainSeat.setCreateTime(now);
                    trainSeat.setRow(StrUtil.fillBefore(String.valueOf(row),'0',2));
                    trainSeat.setCarriageIndex(carriage.getIndex());
                    trainSeat.setCol(colEnum.getCode());
                    trainSeat.setCarriageSeatIndex(seatIndex++);
                    trainSeatMapper.insert(trainSeat);
                }
            }
        }
    }

    public List<TrainSeat> selectByTrainCode(String trainCode) {
        TrainSeatExample trainSeatExample = new TrainSeatExample();
        trainSeatExample.setOrderByClause("`id` asc");
        TrainSeatExample.Criteria criteria = trainSeatExample.createCriteria();
        criteria.andTrainCodeEqualTo(trainCode);
        return trainSeatMapper.selectByExample(trainSeatExample);
    }
}
