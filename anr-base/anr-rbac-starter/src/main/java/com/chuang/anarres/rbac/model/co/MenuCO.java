package com.chuang.anarres.rbac.model.co;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("菜单参数")
public class MenuCO {

    @ApiModelProperty(value = "菜单标题(支持html)", required = true)
    @NotBlank
    private String text;

    @ApiModelProperty(value = "国际化(支持html)", required = true)
    @NotBlank
    private String i18n;

    @ApiModelProperty(value = "编号", required = true)
    @NotBlank
    private String code;

    @ApiModelProperty(value = "父菜单ID",required = true)
    @NotNull
    private Integer parentId;


    @ApiModelProperty(value = "排序", required = true)
    @NotNull
    private Integer sortRank;

    @ApiModelProperty(value = "链接地址", required = true)
    @NotBlank
    private String link;

    @ApiModelProperty("外部链接")
    @NotNull
    private String externalLink;

    @ApiModelProperty(value = "target", required = true)
    @NotBlank
    private String target;

    @ApiModelProperty(value = "是否隐藏面包屑", required = true)
    @NotNull
    private Boolean hideInBreadcrumb;

    @ApiModelProperty(value = "是否允许复用", required = true)
    @NotNull
    private Boolean reuse;

    @ApiModelProperty(value = "图标", required = true)
    private String icon;

    @ApiModelProperty(value = "ACL")
    private String acl;

    @ApiModelProperty(value = "是否启用", required = true)
    @NotNull
    private Boolean enabled;

    @ApiModelProperty(value = "是否可以为快捷菜单", required = true)
    @NotNull
    private Boolean canShortcut;
}
