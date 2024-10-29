package com.kuriss.train.member.service;

import com.kuriss.train.member.entity.Member;

import java.util.List;

public interface IMemberService {
    // Service相关方法
    /**
     * 统计符合条件的记录数
     */
    long countByExample(Member record);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(Member record);

    /**
     * 根据条件查询记录
     */
    List<Member> selectByExample(Member record);

    /**
     * 根据主键查询记录
     */
        Member selectByPrimaryKey(Long id);

    /**
     * 根据条件更新非空字段
     */
    int updateByExampleSelective(Member record);

    /**
     * 插入记录（非空字段）
     */
    int insertSelective(Member record);
}
