package com.chuang.anarres.office.sys.model.ro;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.chuang.anarres.enums.UserStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

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
@ApiModel(value="UserRO对象", description="用户 ")
public class UserRO implements Serializable {

    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "账号")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "盐")
    private String salt;
    @ApiModelProperty(value = "状态")
    private UserStatus state;
    @ApiModelProperty(value = "登录次数")
    private Integer loginTimes;
    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime lastLoginTime;
    @ApiModelProperty(value = "最后登录IP")
    private String lastLoginIp;
    @ApiModelProperty(value = "登录成功次数")
    private Integer loginSuccessTimes;
    @ApiModelProperty(value = "绑定IP")
    private String ipBound;
    @ApiModelProperty(value = "绑定MAC")
    private String macBound;
    @ApiModelProperty(value = "是否删除")
    private Boolean deleted;
    @ApiModelProperty(value = "创建人")
    private String creator;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;
    @ApiModelProperty(value = "更新人")
    private String updater;
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;
}
