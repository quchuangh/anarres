package com.chuang.anarres.office.sys.model.vo;

import com.chuang.anarres.office.sys.model.bo.AclBO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class MenuVO extends TreeVO {

    @ApiModelProperty("菜单唯一值")
    private String key;

    @ApiModelProperty("菜单标题(支持html)")
    private String text;

    @ApiModelProperty("国际化(支持html)")
    private String i18n;

    @ApiModelProperty("编号")
    private String code;

    @ApiModelProperty("菜单路径")
    private String parents;

    @ApiModelProperty("排序")
    private Integer sortRank;


    @ApiModelProperty("链接地址")
    private String link;

    @ApiModelProperty("外部链接")
    private String externalLink;

    @ApiModelProperty("target")
    private String target;

    @ApiModelProperty("是否隐藏面包屑")
    private Boolean hideInBreadcrumb;

    @ApiModelProperty("是否允许复用")
    private Boolean reuse;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("ACL")
    private AclBO acl;

    @ApiModelProperty("是否启用")
    private Boolean enabled;

    @ApiModelProperty("是否可以为快捷菜单")
    private Boolean canShortcut;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updatedTime;
}
