package com.chuang.anarres.office.sys.model.ro;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 组织表;
 * </p>
 *
 * @author chuang
 * @since 2021-05-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="OrganizationRO对象", description="组织表;")
public class OrganizationRO extends TreeRO implements Serializable {

    @ApiModelProperty(value = "编号")
    private String code;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "简介")
    private String description;
    @ApiModelProperty(value = "自带角色")
    private String roleCode;
    @ApiModelProperty(value = "是否启用")
    private Boolean enabled;

    @ApiModelProperty(value = "权限路径(系统的权限设计没有父子关系，这样做只是为了方便查看)")
    private String parents;
    @ApiModelProperty(value = "排序")
    private Integer sortRank;
    @ApiModelProperty(value = "创建人")
    private String creator;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;
    @ApiModelProperty(value = "更新人")
    private String updater;
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;
}
