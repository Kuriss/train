package com.kuriss.train.member.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@TableName("passenger")
public class PassengerSaveDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long memberId;

    private String name;

    private String idCard;

    private String type;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
