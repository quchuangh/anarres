package com.chuang.anarres.crud.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.chuang.anarres.crud.enums.RoleType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 角色表;角色
 * </p>
 *
 * @author chuang
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role")
public class Role implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色标识
     */
    @TableField("role")
    private String role;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 简介
     */
    @TableField("description")
    private String description;

    /**
     * 是否启用
     */
    @TableField("enabled")
    private Boolean enabled;

    /**
     * 角色类型
     */
    @TableField("role_type")
    private RoleType roleType;

    /**
     * 创建人
     */
    @TableField(value = "creator", fill = FieldFill.INSERT)
    private String creator;

    /**
     * 创建时间
     */
    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    /**
     * 更新人
     */
    @TableField(value = "updater", fill = FieldFill.INSERT_UPDATE)
    private String updater;

    /**
     * 更新时间
     */
    @TableField(value = "updated_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;


}
