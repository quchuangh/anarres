package com.chuang.anarres.office.sys.model.co;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
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
@Accessors(chain = true)
@ApiModel(value="OrganizationCO对象", description="组织表;")
public class OrganizationCO implements Serializable {

    @ApiModelProperty(value = "编号")
    @NotBlank(message = "编号不能为空")
    private String code;

    @ApiModelProperty(value = "名称")
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "简介")
    @NotBlank(message = "简介不能为空")
    private String description;

    @ApiModelProperty(value = "自带角色")
    private String roleCode;

    @ApiModelProperty(value = "是否启用")
    @NotNull(message = "是否启用不能为空")
    private Boolean enabled;

    @ApiModelProperty(value = "上级权限(系统的权限设计没有父子关系，这样做只是为了方便查看)")
    @NotNull(message = "上级权限(系统的权限设计没有父子关系，这样做只是为了方便查看)不能为空")
    private Integer parentId;

    @ApiModelProperty(value = "排序")
    @NotNull(message = "排序不能为空")
    private Integer sortRank;

}
