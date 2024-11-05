package com.kuriss.train.business.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 车站
 * </p>
 *
 * @author Kuriss
 * @since 2024-11-05
 */
@Getter
@Setter
@TableName("station")
public class Station implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId("id")
    private Long id;

    /**
     * 站名
     */
    @TableField("name")
    private String name;

    /**
     * 站名拼音
     */
    @TableField("name_pinyin")
    private String namePinyin;

    /**
     * 站名拼音首字母
     */
    @TableField("name_py")
    private String namePy;

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
