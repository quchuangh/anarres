package com.chuang.anarres.model;

import com.baomidou.mybatisplus.annotation.*;
import com.chuang.anarres.enums.DomainType;
import com.chuang.anarres.enums.ProposalStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@TableName("t_seo_domain_proposal")
public class SeoDomainProposal implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    private String reference;

    /**
     * 域名
     */
    private String domain;

    /**
     * 站点模板
     */
    private String templateWebsite;

    /**
     * title
     */
    private String title;

    /**
     * 关键词列表
     */
    private String keywords;

    /**
     * list页面的title
     */
    private String listPageTitle;

    /**
     * 其他菜单名，用逗号隔开
     */
    private String otherMenus;

    /**
     * 主关键词
     */
    private String mainKey;

    /**
     * 类型
     */
    private DomainType type;

    /**
     * 网站描述
     */
    private String description;

    /**
     * 服务器IP
     */
    private String serverIp;

    /**
     * 服务器标签
     */
    private String serverTag;

    /**
     * 状态
     */
    private ProposalStatus state;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @TableField(fill = FieldFill.INSERT)
    private String creator;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updater;

    private String auditor;

    private LocalDateTime auditedTime;

    private String remark;

}
