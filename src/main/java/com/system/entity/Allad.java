package com.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Lai
 * @since 2022-05-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_allad")
public class Allad implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 所属人id
     */
    @TableField("owner_ID")
    private Integer ownerId;

    /**
     * 宣传图
     */
    private String img;

    /**
     * 广告主题
     */
    @TableField("ADtheme")
    private String ADtheme;

    /**
     * 摘要
     */
    private String content;

    /**
     * 状态
     */
    private String state;

    /**
     * 时间
     */
    private Timestamp date;

}
