package com.kuriss.train.member.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberRegisterDto {
    @NotBlank(message = "手机号不能为空")
    String mobile;
    Long id;
}
