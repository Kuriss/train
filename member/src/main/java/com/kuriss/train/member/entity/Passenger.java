package com.kuriss.train.member.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
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
@TableName("passenger")
public class Passenger implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId("id")
    private Long id;

    /**
     * 会员id
     */
    @TableField("member_id")
    private Long memberId;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 身份证
     */
    @TableField("id_card")
    private String idCard;

    /**
     * 旅客类型|枚举[PassengerTypeEnum]
     */
    @TableField("type")
    private String type;

    /**
     * 新增时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;


}
