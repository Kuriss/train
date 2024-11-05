package com.kuriss.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuriss.train.common.context.LoginMemberContext;
import com.kuriss.train.common.resp.PageResp;
import com.kuriss.train.common.util.SnowUtil;
import com.kuriss.train.member.entity.Passenger;
import com.kuriss.train.member.entity.PassengerQuery;
import com.kuriss.train.member.entity.PassengerQueryDto;
import com.kuriss.train.member.entity.PassengerSaveDto;
import com.kuriss.train.member.mapper.PassengerMapper;
import com.kuriss.train.member.resp.PassengerQueryResp;
import com.kuriss.train.member.service.impl.PassengerServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PassengerService {
    @Resource
    private PassengerMapper passengerMapper;
    @Autowired
    private PassengerServiceImpl passengerServiceImpl;

    public void save(PassengerSaveDto psd) {
        Date now = DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(psd, Passenger.class);
        if(ObjectUtil.isNull(passenger.getId())) {
            passenger.setMemberId(LoginMemberContext.getId());
            passenger.setId(SnowUtil.getSnowflakeNextId());
            passenger.setCreateTime(now);
            passenger.setUpdateTime(now);
            passengerMapper.insert(passenger);
        }else {
            passenger.setUpdateTime(now);
            passengerServiceImpl.updateByPrimaryKey(passenger);
        }
    }
    public PageResp<PassengerQueryResp> queryList(PassengerQueryDto pqd) {
        PassengerQuery pq = new PassengerQuery();
        PassengerQuery.Criteria criteria = pq.createCriteria();
        if(ObjectUtil.isNotNull(pqd.getMemberId())){

            criteria.andMemberIdEqualTo(pqd.getMemberId());
        }
        PageHelper.startPage(pqd.getPage(),pqd.getSize());
        List<Passenger> passengerList = passengerServiceImpl.selectByExample(pq);
        List<PassengerQueryResp> passengerQueryResps = BeanUtil.copyToList(passengerList, PassengerQueryResp.class);

        PageInfo<Passenger> pageInfo = new PageInfo<>(passengerList);
        PageResp<PassengerQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(passengerQueryResps);
        return pageResp;
    }
    public void delete(Long id){
        passengerServiceImpl.deleteByPrimaryKey(id);
    }
}
