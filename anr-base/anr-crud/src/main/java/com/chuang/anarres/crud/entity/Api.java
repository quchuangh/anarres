package com.chuang.anarres.crud.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 接口 
 * </p>
 *
 * @author chuang
 * @since 2021-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_api")
public class Api implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 唯一编号
     */
    @TableField("code")
    private String code;

    /**
     * 地址
     */
    @TableField("url")
    private String url;

    /**
     * 标签
     */
    @TableField("label")
    private String label;

    /**
     * 说明
     */
    @TableField("description")
    private String description;

    /**
     * HttpMethod
     */
    @TableField("method")
    private String method;

    /**
     * 是否启用
     */
    @TableField("enabled")
    private Boolean enabled;

    /**
     * 创建人
     */
    @TableField(value = "creator", fill = FieldFill.INSERT)
    private String creator;

    /**
     * 创建时间
     */
    @TableField("created_time")
    private LocalDateTime createdTime;

    /**
     * 更新人
     */
    @TableField(value = "updater", fill = FieldFill.INSERT_UPDATE)
    private String updater;

    /**
     * 更新时间
     */
    @TableField("updated_time")
    private LocalDateTime updatedTime;


}
