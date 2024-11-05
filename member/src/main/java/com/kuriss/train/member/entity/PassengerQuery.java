package com.kuriss.train.member.entity;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PassengerQuery {
    private QueryWrapper<Passenger> queryWrapper;
    private String orderByClause;
    private boolean distinct;
    private List<Criteria> oredCriteria;

    public PassengerQuery() {
        this.queryWrapper = new QueryWrapper<>();
        this.oredCriteria = new ArrayList<>();
    }

    // 设置排序规则
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
        if (orderByClause != null && !orderByClause.trim().isEmpty()) {
            String[] parts = orderByClause.trim().split("\\s+");
            if (parts.length >= 1) {
                String column = parts[0];
                boolean isAsc = true; // 默认升序
                if (parts.length >= 2) {
                    isAsc = !"desc".equalsIgnoreCase(parts[1]);
                }
                queryWrapper.orderBy(true, isAsc, column);
            }
        }
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

    public QueryWrapper<Passenger> getQueryWrapper() {
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

        public Criteria andMemberIdEqualTo(Long value) {
            if (value != null) {
                conditions.add("member_id = " + value);
            }
            return this;
        }

        public Criteria andNameEqualTo(String value) {
            if (value != null) {
                conditions.add("name = '" + value + "'");
            }
            return this;
        }

        public Criteria andIdCardEqualTo(String value) {
            if (value != null) {
                conditions.add("id_card = '" + value + "'");
            }
            return this;
        }

        public Criteria andTypeEqualTo(String value) {
            if (value != null) {
                conditions.add("type = '" + value + "'");
            }
            return this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            if (value1 != null && value2 != null) {
                conditions.add("create_time BETWEEN '" + value1 + "' AND '" + value2 + "'");
            }
            return this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            if (value1 != null && value2 != null) {
                conditions.add("update_time BETWEEN '" + value1 + "' AND '" + value2 + "'");
            }
            return this;
        }

        protected String buildConditions() {
            return String.join(" AND ", conditions);
        }
    }
}
