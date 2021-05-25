package com.chuang.anarres.rbac.model.uo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 职位表;
 * </p>
 *
 * @author chuang
 * @since 2021-05-24
 */
@Data
@Accessors(chain = true)
@ApiModel(value="PositionUO对象", description="职位表;")
public class PositionUO implements Serializable {

    @ApiModelProperty(value = "id")
    @NotNull(message = "id不能为空")
    private Integer id;

    @ApiModelProperty(value = "职位编号")
    @NotBlank(message = "职位编号不能为空")
    private String positionCode;

    @ApiModelProperty(value = "名称")
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "简介")
    @NotBlank(message = "简介不能为空")
    private String description;

    @ApiModelProperty(value = "组织编号")
    @NotBlank(message = "组织编号不能为空")
    private String organizationCode;

    @ApiModelProperty(value = "是否启用")
    @NotNull(message = "是否启用不能为空")
    private Boolean enabled;

    @ApiModelProperty(value = "自带角色")
    private String roleCode;

}
