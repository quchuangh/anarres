package com.chuang.anarres.model;

import com.baomidou.mybatisplus.annotation.*;
import com.chuang.anarres.enums.DomainType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author ath
 * @since 2020-09-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_seo_domain")
public class SeoDomain implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 模板
     */
    private String templateWebsite;

    private String proposalReference;
    /**
     * 域名
     */
    private String domain;

    /**
     * title
     */
    private String title;

    /**
     * 关键词列表
     */
    private String keywords;

    /**
     * 菜单列表
     */
    private String menus;

    /**
     * 主关键词
     */
    private String mainKey;

    /**
     * 类型
     */
    private DomainType type;

    /**
     * 服务器IP
     */
    private String serverIp;

    /** 目录 */
    private String dir;

    /**
     * 服务器标签
     */
    private String serverTag;

    /**
     * 网页后缀
     */
    private String suffix;

    /**
     * js 组
     */
    private String jsGroup;

    /**
     * 网站说明
     */
    private String description;

    private Integer dailyArticle;

    private LocalTime articleTime;

    private Boolean newSite;

    private LocalDateTime lastArticleTime;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

    @TableField(fill = FieldFill.INSERT)
    private String creator;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updater;

}
