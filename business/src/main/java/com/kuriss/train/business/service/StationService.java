package com.kuriss.train.business.service;

import cn.hutool.core.bean.BeanUtil;
import com.kuriss.train.business.entity.Station;
import com.kuriss.train.business.entity.StationQuery;
import com.kuriss.train.business.resp.StationQueryResp;
import com.kuriss.train.business.service.impl.StationServiceImpl;
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
}
