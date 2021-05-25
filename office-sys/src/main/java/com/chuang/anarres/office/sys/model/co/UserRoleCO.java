package com.chuang.anarres.office.sys.model.co;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Accessors(chain = true)
@ApiModel(value="用户角色", description="用户角色")
public class UserRoleCO {

    @ApiModelProperty(value = "账号")
    @NotBlank(message = "账号不能为空")
    private String username;

    @ApiModelProperty(value = "账号")
    @NotEmpty(message = "角色列表不能为空")
    private List<String> roles;
}
