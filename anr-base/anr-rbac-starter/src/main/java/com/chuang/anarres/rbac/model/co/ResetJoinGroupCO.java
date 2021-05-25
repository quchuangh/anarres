package com.chuang.anarres.rbac.model.co;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@ApiModel("重置用户所在组织")
public class ResetJoinGroupCO {

    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank
    private String username;

    @ApiModelProperty(value = "组织编号", required = true)
    private List<String> organizationCodes;
}
