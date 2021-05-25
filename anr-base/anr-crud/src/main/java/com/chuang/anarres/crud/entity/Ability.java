package com.chuang.anarres.crud.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.chuang.anarres.crud.entity.api.TreeModel;
import com.chuang.anarres.crud.enums.AbilityType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 权限表 权限
 * </p>
 *
 * @author chuang
 * @since 2021-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_ability")
public class Ability implements TreeModel, Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 权限字符
     */
    @TableField("ability")
    private String ability;

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
     * 权限类型
     */
    @TableField("ability_type")
    private AbilityType abilityType;

    /**
     * 排序
     */
    @TableField("sort_rank")
    private Integer sortRank;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

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
