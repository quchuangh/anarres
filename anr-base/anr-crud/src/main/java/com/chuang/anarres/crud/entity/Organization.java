package com.chuang.anarres.crud.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.chuang.anarres.crud.entity.api.TreeModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 组织表;
 * </p>
 *
 * @author chuang
 * @since 2021-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_organization")
public class Organization implements TreeModel, Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 编号
     */
    @TableField("code")
    private String code;

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
     * 自带角色
     */
    @TableField("role_code")
    private String roleCode;

    /**
     * 是否启用
     */
    @TableField("enabled")
    private Boolean enabled;

    /**
     * 上级权限(系统的权限设计没有父子关系，这样做只是为了方便查看)
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 权限路径(系统的权限设计没有父子关系，这样做只是为了方便查看)
     */
    @TableField("parents")
    private String parents;

    /**
     * 排序
     */
    @TableField("sort_rank")
    private Integer sortRank;

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
