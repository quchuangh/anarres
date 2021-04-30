package com.chuang.anarres.office.sys.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "菜单")
public class TreeMenuBO {

    @ApiModelProperty("菜单唯一值")
    private String key;

    @ApiModelProperty("菜单标题(支持html)")
    private String text;

    @ApiModelProperty("国际化(支持html)")
    private String i18n;

    @ApiModelProperty("编号")
    private String code;

    @ApiModelProperty("父菜单ID")
    private Integer parentId;

    @ApiModelProperty("菜单路径")
    private String parents;

    @ApiModelProperty("排序")
    private Integer sort;


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
    private Boolean disabled;

    @ApiModelProperty("是否为快捷菜单")
    private Boolean shortcut;

    @ApiModelProperty("子菜单")
    private List<TreeMenuBO> children;
}
