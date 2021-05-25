package com.chuang.anarres.rbac.model.co;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 用户 
 * </p>
 *
 * @author chuang
 * @since 2021-05-21
 */
@Data
@Accessors(chain = true)
@ApiModel(value="UserCO对象", description="用户")
public class UserCO implements Serializable {

    @ApiModelProperty(value = "账号")
    @NotBlank(message = "账号不能为空")
    private String username;


    @ApiModelProperty(value = "昵称")
    @NotBlank(message = "昵称不能为空")
    private String name;

    @ApiModelProperty(value = "绑定IP")
    private String ipBound;

    @ApiModelProperty(value = "绑定MAC")
    private String macBound;

}
