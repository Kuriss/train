package com.kuriss.train.member.entity;

import com.kuriss.train.common.req.PageReq;
import lombok.Data;

@Data
public class PassengerQueryDto extends PageReq {
    private Long memberId;
}
