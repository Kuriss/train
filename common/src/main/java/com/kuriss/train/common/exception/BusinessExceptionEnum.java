package com.kuriss.train.common.exception;

import lombok.Getter;

@Getter
public enum BusinessExceptionEnum {

    MEMBER_MOBILE_EXIST("手机号已注册"),
    MEMBER_MOBILE_CODE_ERROR("短信验证码错误"),
    MEMBER_MOBILE_NOT_EXIST("请先获取短信验证码");

    private String desc;

    BusinessExceptionEnum(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "BusinessExceptionEnum{" +
                "desc='" + desc + '\'' +
                '}';
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
