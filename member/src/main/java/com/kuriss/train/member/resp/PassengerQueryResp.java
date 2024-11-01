package com.kuriss.train.member.resp;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <p>
 * 乘车人
 * </p>
 *
 * @author Kuriss
 * @since 2024-10-30
 */
@Getter
@Setter
public class PassengerQueryResp{

    private Long id;

    private Long memberId;

    private String name;

    private String idCard;

    private String type;

    private Date createTime;

    private Date updateTime;


}
