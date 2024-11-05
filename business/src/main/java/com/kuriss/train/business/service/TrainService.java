package com.kuriss.train.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuriss.train.business.entity.Train;
import com.kuriss.train.business.entity.TrainQuery;
import com.kuriss.train.business.req.TrainQueryReq;
import com.kuriss.train.business.req.TrainSaveReq;
import com.kuriss.train.business.resp.TrainQueryResp;
import com.kuriss.train.business.service.impl.TrainServiceImpl;
import com.kuriss.train.common.exception.BusinessException;
import com.kuriss.train.common.exception.BusinessExceptionEnum;
import com.kuriss.train.common.resp.PageResp;
import com.kuriss.train.common.util.SnowUtil;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {
    private static final Logger LOG = LoggerFactory.getLogger(TrainService.class);

    @Resource
    private TrainServiceImpl trainServiceImpl;

    public void save(TrainSaveReq req) {
        DateTime now = DateTime.now();
        Train train = BeanUtil.copyProperties(req, Train.class);
        if (ObjectUtil.isNull(train.getId())) {
            // 保存之前，先校验唯一键是否存在
            Train trainDB = selectByUnique(req.getCode());
            if (ObjectUtil.isNotEmpty(trainDB)) {
                throw new BusinessException(BusinessExceptionEnum.BUSINESS_TRAIN_CODE_UNIQUE_ERROR);
            }
            train.setId(SnowUtil.getSnowflakeNextId());
            train.setCreateTime(now);
            train.setUpdateTime(now);
            trainServiceImpl.insertSelective(train);
        } else {
            train.setUpdateTime(now);
            trainServiceImpl.updateByPrimaryKey(train);
        }
    }

    private Train selectByUnique(String code) {
        TrainQuery trainQuery = new TrainQuery();
        trainQuery.createCriteria()
                .andCodeEqualTo(code);
        List<Train> list = trainServiceImpl.selectByExample(trainQuery);
        if (CollUtil.isNotEmpty(list)) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public PageResp<TrainQueryResp> queryList(TrainQueryReq req) {
        TrainQuery trainQuery = new TrainQuery();

        trainQuery.getQueryWrapper().orderByDesc("id");

        LOG.info("查询页码：{}", req.getPage());
        LOG.info("每页条数：{}", req.getSize());
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Train> trainList = trainServiceImpl.selectByExample(trainQuery);

        PageInfo<Train> pageInfo = new PageInfo<>(trainList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        List<TrainQueryResp> list = BeanUtil.copyToList(trainList, TrainQueryResp.class);

        PageResp<TrainQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

    public void delete(Long id) {
        trainServiceImpl.deleteByPrimaryKey(id);
    }


    public List<TrainQueryResp> queryAll() {
        TrainQuery trainQuery = new TrainQuery();

        trainQuery.getQueryWrapper().orderByDesc("code");

        List<Train> trainList = trainServiceImpl.selectByExample(trainQuery);
        return BeanUtil.copyToList(trainList, TrainQueryResp.class);
    }

    public List<Train> selectAll() {
        TrainQuery trainQuery = new TrainQuery();
        trainQuery.setOrderByClause("code asc");
        return trainServiceImpl.selectByExample(trainQuery);
    }
}
