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
 * 职位表;
 * </p>
 *
 * @author chuang
 * @since 2021-05-24
 */
@Data
@Accessors(chain = true)
@ApiModel(value="PositionCO对象", description="职位表;")
public class PositionCO implements Serializable {

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
