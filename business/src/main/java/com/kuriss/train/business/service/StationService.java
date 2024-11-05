package com.kuriss.train.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuriss.train.business.entity.Station;
import com.kuriss.train.business.entity.StationQuery;
import com.kuriss.train.business.req.StationQueryReq;
import com.kuriss.train.business.req.StationSaveReq;
import com.kuriss.train.business.resp.StationQueryResp;
import com.kuriss.train.business.service.impl.StationServiceImpl;
import com.kuriss.train.common.exception.BusinessException;
import com.kuriss.train.common.exception.BusinessExceptionEnum;
import com.kuriss.train.common.resp.PageResp;
import com.kuriss.train.common.util.SnowUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {
    private static final Logger LOG = LoggerFactory.getLogger(StationService.class);
    @Autowired
    private StationServiceImpl stationServiceImpl;


    public List<StationQueryResp> queryAll() {
        StationQuery stationQuery = new StationQuery();
        stationQuery.setOrderByClause("name_pinyin asc");
        List<Station> stationList = stationServiceImpl.selectByExample(stationQuery);
        return BeanUtil.copyToList(stationList, StationQueryResp.class);
    }
    public void save(StationSaveReq req) {
        DateTime now = DateTime.now();
        Station station = BeanUtil.copyProperties(req, Station.class);
        if (ObjectUtil.isNull(station.getId())) {

            // 保存之前，先校验唯一键是否存在
            Station stationDB = selectByUnique(req.getName());
            if (ObjectUtil.isNotEmpty(stationDB)) {
                throw new BusinessException(BusinessExceptionEnum.BUSINESS_STATION_NAME_UNIQUE_ERROR);
            }

            station.setId(SnowUtil.getSnowflakeNextId());
            station.setCreateTime(now);
            station.setUpdateTime(now);
            stationServiceImpl.insertSelective(station);
        } else {
            station.setUpdateTime(now);
            stationServiceImpl.updateByPrimaryKey(station);
        }
    }

    private Station selectByUnique(String name) {
        StationQuery stationQuery = new StationQuery();
        stationQuery.createCriteria().andNameEqualTo(name);
        List<Station> list = stationServiceImpl.selectByExample(stationQuery);
        if (CollUtil.isNotEmpty(list)) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public PageResp<StationQueryResp> queryList(StationQueryReq req) {
        StationQuery stationQuery = new StationQuery();
        stationQuery.getQueryWrapper().orderByDesc("id");

        LOG.info("查询页码：{}", req.getPage());
        LOG.info("每页条数：{}", req.getSize());
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Station> stationList = stationServiceImpl.selectByExample(stationQuery);

        PageInfo<Station> pageInfo = new PageInfo<>(stationList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        List<StationQueryResp> list = BeanUtil.copyToList(stationList, StationQueryResp.class);

        PageResp<StationQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

    public void delete(Long id) {
        stationServiceImpl.deleteByPrimaryKey(id);
    }

}
