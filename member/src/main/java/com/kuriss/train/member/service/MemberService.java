package com.kuriss.train.member.service;

import cn.hutool.core.collection.CollUtil;
import com.kuriss.train.common.exception.BusinessException;
import com.kuriss.train.common.exception.BusinessExceptionEnum;
import com.kuriss.train.member.entity.Member;
import com.kuriss.train.member.entity.MemberQuery;
import com.kuriss.train.member.entity.MemberRegisterDto;
import com.kuriss.train.member.service.impl.MemberServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Resource
    private MemberServiceImpl memberServiceImpl;
    public int count(){
        return Math.toIntExact(memberServiceImpl.countByExample(null));
    }
    public long register(MemberRegisterDto mrd){
        String mobile = mrd.getMobile();
        MemberQuery mq = new MemberQuery();
        mq.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = memberServiceImpl.selectByExample(mq);

        if(CollUtil.isNotEmpty(members)){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }
        Member member = new Member();

        member.setMobile(mobile);
        member.setId(System.currentTimeMillis());
        memberServiceImpl.insertSelective(member);
        return member.getId();
    }
}
