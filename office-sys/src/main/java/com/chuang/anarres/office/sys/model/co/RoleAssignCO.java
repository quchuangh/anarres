package com.chuang.anarres.office.sys.model.co;

import com.chuang.anarres.office.sys.model.bo.AL;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel("授权参数")
public class RoleAssignCO {
    @ApiModelProperty(value = "角色id")
    @NotNull(message = "角色不能为空")
    private Integer roleId;

    @ApiModelProperty(value = "权限")
    private List<AL> abilities;
}
