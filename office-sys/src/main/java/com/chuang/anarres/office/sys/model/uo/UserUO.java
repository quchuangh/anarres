package com.chuang.anarres.office.sys.model.uo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.chuang.anarres.enums.UserStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
@ApiModel(value="UserUO对象", description="用户 ")
public class UserUO implements Serializable {

    @ApiModelProperty(value = "账号")
    @NotBlank(message = "账号不能为空")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "盐")
    @NotBlank(message = "盐不能为空")
    private String salt;

    @ApiModelProperty(value = "状态")
    @NotNull(message = "状态不能为空")
    private UserStatus state;

    @ApiModelProperty(value = "登录次数")
    @NotNull(message = "登录次数不能为空")
    private Integer loginTimes;

    @ApiModelProperty(value = "最后登录时间")
    @NotNull(message = "最后登录时间不能为空")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(value = "最后登录IP")
    @NotBlank(message = "最后登录IP不能为空")
    private String lastLoginIp;

    @ApiModelProperty(value = "登录成功次数")
    @NotNull(message = "登录成功次数不能为空")
    private Integer loginSuccessTimes;

    @ApiModelProperty(value = "绑定IP")
    @NotBlank(message = "绑定IP不能为空")
    private String ipBound;

    @ApiModelProperty(value = "绑定MAC")
    @NotBlank(message = "绑定MAC不能为空")
    private String macBound;

    @ApiModelProperty(value = "是否删除")
    @NotNull(message = "是否删除不能为空")
    private Boolean deleted;

}
