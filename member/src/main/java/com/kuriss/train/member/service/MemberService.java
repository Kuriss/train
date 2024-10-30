package com.kuriss.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.kuriss.train.common.exception.BusinessException;
import com.kuriss.train.common.exception.BusinessExceptionEnum;
import com.kuriss.train.common.util.JwtUtil;
import com.kuriss.train.common.util.SnowUtil;
import com.kuriss.train.member.entity.*;
import com.kuriss.train.member.resp.MemberLoginResp;
import com.kuriss.train.member.service.impl.MemberServiceImpl;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MemberService {
    private static Logger LOG = LoggerFactory.getLogger(MemberService.class);
    @Resource
    private MemberServiceImpl memberServiceImpl;
    public int count(){
        return Math.toIntExact(memberServiceImpl.countByExample(null));
    }
    public long register(MemberRegisterDto mrd){
        String mobile = mrd.getMobile();
        Member memberDB = selectMembers(mobile);

        if(ObjectUtil.isNotNull(memberDB)){
            Member member = new Member();
            member.setMobile(mobile);
            member.setId(SnowUtil.getSnowflakeNextId());
            memberServiceImpl.insertSelective(member);
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }
        Member member = new Member();

        member.setMobile(mobile);
        member.setId(SnowUtil.getSnowflakeNextId());
        memberServiceImpl.insertSelective(member);
        return member.getId();
    }

    public void sendCode(MemberSendCodeDto msd) {
        String mobile = msd.getMobile();
        Member memberDB = selectMembers(mobile);

        if(ObjectUtil.isNull(memberDB)){
            LOG.info("手机号不存在，插入记录");
            Member member = new Member();
            member.setMobile(mobile);
            member.setId(SnowUtil.getSnowflakeNextId());
            memberServiceImpl.insertSelective(member);
        }
        else {
            LOG.info("手机号存在，不插入记录");
        }
//        String code = RandomUtil.randomString(4);
        String code = "8888";
        // 保存短信记录表：手机号，短信验证码，有效期，是否已使用，业务类型，发送时间，使用时间
        LOG.info("验证码：{}", code);
        // 对接短信通道，发送短信
    }

    public MemberLoginResp login(MemberLoginDto mld) {
        String mobile = mld.getMobile();
        String code = mld.getCode();
        Member memberDB = selectMembers(mobile);

        if(ObjectUtil.isNull(memberDB)){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_NOT_EXIST);
        }

        if(!"8888".equals(code)){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_ERROR);
        }
        MemberLoginResp memberLoginResp = BeanUtil.copyProperties(memberDB, MemberLoginResp.class);
        Map<String, Object> map = BeanUtil.beanToMap(memberLoginResp);
        String token = JwtUtil.createToken(memberLoginResp.getId(), memberLoginResp.getMobile());
        memberLoginResp.setToken(token);
        return memberLoginResp;
    }

    private Member selectMembers(String mobile) {
        MemberQuery mq = new MemberQuery();
        mq.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = memberServiceImpl.selectByExample(mq);
        if(CollUtil.isEmpty(members)){
            return null;
        } else {
            return members.get(0);
        }
    }
}
