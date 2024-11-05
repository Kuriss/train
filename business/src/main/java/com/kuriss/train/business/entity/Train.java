package com.kuriss.train.business.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

/**
 * <p>
 * 车次
 * </p>
 *
 * @author Kuriss
 * @since 2024-11-05
 */
@Getter
@Setter
@TableName("train")
public class Train implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId("id")
    private Long id;

    /**
     * 车次编号
     */
    @TableField("code")
    private String code;

    /**
     * 车次类型|枚举[TrainTypeEnum]
     */
    @TableField("type")
    private String type;

    /**
     * 始发站
     */
    @TableField("start")
    private String start;

    /**
     * 始发站拼音
     */
    @TableField("start_pinyin")
    private String startPinyin;

    /**
     * 出发时间
     */
    @TableField("start_time")
    private LocalTime startTime;

    /**
     * 终点站
     */
    @TableField("end")
    private String end;

    /**
     * 终点站拼音
     */
    @TableField("end_pinyin")
    private String endPinyin;

    /**
     * 到站时间
     */
    @TableField("end_time")
    private LocalTime endTime;

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
