package com.kuriss.train.member.service;

import com.kuriss.train.member.entity.Member;
import com.kuriss.train.member.entity.MemberQuery;

import java.util.List;

public interface IMemberService {
    /**
     * 统计符合条件的记录数
     */
    long countByExample(MemberQuery query);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(MemberQuery query);

    /**
     * 根据条件查询记录
     */
    List<Member> selectByExample(MemberQuery query);

    /**
     * 根据主键查询记录
     */
    Member selectByPrimaryKey(Long id);

    /**
     * 根据条件更新非空字段
     */
    int updateByExampleSelective(Member record, MemberQuery query);

    /**
     * 插入记录（非空字段）
     */
    int insertSelective(Member record);
}
