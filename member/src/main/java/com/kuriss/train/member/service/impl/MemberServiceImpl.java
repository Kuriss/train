package com.kuriss.train.member.service.impl;

import com.kuriss.train.member.entity.Member;
import com.kuriss.train.member.entity.MemberQuery;
import com.kuriss.train.member.mapper.MemberMapper;
import com.kuriss.train.member.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ${tableComment} 服务实现类
 */
@Service
public class MemberServiceImpl implements IMemberService {
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public long countByExample(MemberQuery query) {
        return memberMapper.selectCount(query.getQueryWrapper());
    }

    @Override
    public int deleteByExample(MemberQuery query) {
        return memberMapper.delete(query.getQueryWrapper());
    }

    @Override
    public List<Member> selectByExample(MemberQuery query) {
        return memberMapper.selectList(query.getQueryWrapper());
    }

    @Override
    public Member selectByPrimaryKey(Long id) {
        return memberMapper.selectById(id);
    }

    @Override
    public int updateByExampleSelective(Member record, MemberQuery query) {

        return 0;
    }

    @Override
    public int insertSelective(Member record) {
        return memberMapper.insert(record); // 非空字段插入
    }
}
