package com.chuang.anarres.model;

import lombok.Data;

import java.lang.annotation.*;

@Data
public class SeoDomainProposalVO {

    @Index("域名")
    private String domain;

    @Index("title")
    private String title;

    @Index("关键词")
    private String keywords;

    @Index("list页名")
    private String listPageTitle;

    @Index("其他菜单")
    private String otherMenus;

    @Index("主关键词")
    private String mainKey;

    @Index("类型")
    private String type;

    @Index("网站说明")
    private String description;

    @Index("服务器IP")
    private String serverIp;

    @Index("服务器Tag")
    private String serverTag;

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Index {
        String value();
    }
}
