package com.chuang.anarres.office.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.chuang.anarres.office.sys.entity.api.TreeModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单表;
 * </p>
 *
 * @author chuang
 * @since 2020-12-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_menu")
public class Menu implements Serializable, TreeModel {

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
     * 父菜单
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 菜单路径
     */
    @TableField("parents")
    private String parents;

    /**
     * 排序
     */
    @TableField("sort_rank")
    private Integer sortRank;

    /**
     * 菜单标题(支持html)
     */
    @TableField("text")
    private String text;

    /**
     * 国际化(支持html)
     */
    @TableField("i18n")
    private String i18n;

    /**
     * 链接地址
     */
    @TableField("link")
    private String link;

    /**
     * 外部链接
     */
    @TableField("external_link")
    private String externalLink;

    /**
     * target
     */
    @TableField("target")
    private String target;

    /**
     * 是否隐藏面包屑
     */
    @TableField("hide_in_breadcrumb")
    private Boolean hideInBreadcrumb;

    /**
     * 是否允许复用
     */
    @TableField("reuse")
    private Boolean reuse;

    /**
     * 图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 权限
     */
    @TableField("permission")
    private String permission;

    /**
     * 是否启用
     */
    @TableField("enabled")
    private Boolean enabled;

    /**
     * 是否支持快捷方式
     */
    @TableField("can_shortcut")
    private Boolean canShortcut;

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
