package com.kuriss.train.member.service;

import com.kuriss.train.member.entity.Member;
import com.kuriss.train.member.entity.MemberRegisterDto;
import com.kuriss.train.member.service.impl.MemberServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Resource
    private MemberServiceImpl memberServiceImpl;
    public int count(){
        return Math.toIntExact(memberServiceImpl.countByExample(null));
    }
    public long register(MemberRegisterDto mrd){
        Member member = new Member();
        String mobile = mrd.getMobile();
        member.setMobile(mobile);
        member.setId(System.currentTimeMillis());
        memberServiceImpl.insertSelective(member);
        return member.getId();
    }
}
