package com.kuriss.train.member.entity;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.ArrayList;
import java.util.List;

public class MemberQuery {
    private QueryWrapper<Member> queryWrapper;
    private String orderByClause;
    private boolean distinct;
    private List<Criteria> oredCriteria;

    public MemberQuery() {
        this.queryWrapper = new QueryWrapper<>();
        this.oredCriteria = new ArrayList<>();
    }

    // 设置排序规则
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
        queryWrapper.orderBy(true, true, orderByClause);
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    // 设置去重
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
        queryWrapper.select(distinct);
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    // 添加 OR 条件
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
        queryWrapper.or().nested(wrapper -> wrapper.apply(criteria.buildConditions()));
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        queryWrapper.or().nested(wrapper -> wrapper.apply(criteria.buildConditions()));
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.isEmpty()) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        return new Criteria();
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
        queryWrapper.clear();
    }

    public QueryWrapper<Member> getQueryWrapper() {
        // 根据 oredCriteria 构建条件
        oredCriteria.forEach(criteria -> queryWrapper.apply(criteria.buildConditions()));
        return queryWrapper;
    }

    // Criteria 类用于构建查询条件
    public static class Criteria {
        private final List<String> conditions;

        protected Criteria() {
            this.conditions = new ArrayList<>();
        }

        public boolean isValid() {
            return !conditions.isEmpty();
        }

        public List<String> getAllConditions() {
            return conditions;
        }

        public Criteria andIdEqualTo(Long value) {
            if (value != null) {
                conditions.add("id = " + value);
            }
            return this;
        }

        public Criteria andMobileEqualTo(String value) {
            if (value != null) {
                conditions.add("mobile = '" + value + "'");
            }
            return this;
        }

        public Criteria andMobileLike(String value) {
            if (value != null) {
                conditions.add("mobile LIKE '" + value + "'");
            }
            return this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            if (value1 != null && value2 != null) {
                conditions.add("id BETWEEN " + value1 + " AND " + value2);
            }
            return this;
        }


        protected String buildConditions() {
            return String.join(" AND ", conditions);
        }
    }
}
