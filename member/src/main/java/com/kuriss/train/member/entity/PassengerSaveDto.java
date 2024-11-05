package com.kuriss.train.member.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
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
public class PassengerSaveDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long memberId;
    @NotBlank(message = "名字不能为空")
    private String name;
    @NotBlank(message = "身份证不能为空")
    private String idCard;
    @NotBlank(message = "旅客类型不能为空")
    private String type;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;


}
