package com.chuang.anarres.office.sys.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("权限参数")
public class AL {
    @ApiModelProperty(value = "权限id")
    @NotNull(message = "权限不能为空")
    private Integer abilityId;
    @ApiModelProperty(value = "是否可以把这个功能授权给别人")
    @NotNull(message = "是否授权不能为空")
    private Boolean licensable;
    @ApiModelProperty(value = "是否选用")
    @NotNull(message = "选择不能为空")
    private Boolean checked;
}
