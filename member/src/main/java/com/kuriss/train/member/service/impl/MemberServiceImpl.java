package com.kuriss.train.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kuriss.train.member.entity.Member;
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
    // 实现Service方法
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public long countByExample(Member record) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>(record);
        return memberMapper.selectCount(queryWrapper);
    }

    @Override
    public int deleteByExample(Member record) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>(record);
        return memberMapper.delete(queryWrapper);
    }

    @Override
    public List<Member> selectByExample(Member record) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>(record);
        return memberMapper.selectList(queryWrapper);
    }

    @Override
    public Member selectByPrimaryKey(Long id) {
        return memberMapper.selectById(id);
    }

    @Override
    public int updateByExampleSelective(Member record) {
        UpdateWrapper<Member> updateWrapper = new UpdateWrapper<>(record);
        return memberMapper.update(record, updateWrapper);
    }

    @Override
    public int insertSelective(Member record) {
        return memberMapper.insert(record); // 非空字段插入
    }
}
