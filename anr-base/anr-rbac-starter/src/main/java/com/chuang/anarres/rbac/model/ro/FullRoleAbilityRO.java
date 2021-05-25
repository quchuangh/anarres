package com.chuang.anarres.rbac.model.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class FullRoleAbilityRO extends LicensableAbilityRO {
    @ApiModelProperty("当前登录的用户是否可对其进行操作")
    private Boolean disableOpt;
    @ApiModelProperty("是否已选择")
    private Boolean checked;
}
