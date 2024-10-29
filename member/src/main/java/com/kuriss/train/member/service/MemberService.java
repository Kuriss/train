package com.kuriss.train.member.service;

import com.kuriss.train.member.entity.Member;
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
    public long register(String mobile){
        Member member = new Member();
        member.setMobile(mobile);
        member.setId(System.currentTimeMillis());
        memberServiceImpl.insertSelective(member);
        return member.getId();
    }
}
