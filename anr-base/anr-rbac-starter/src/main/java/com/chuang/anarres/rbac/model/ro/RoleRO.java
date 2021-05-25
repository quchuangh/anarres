package com.chuang.anarres.rbac.model.ro;

import com.chuang.anarres.crud.enums.RoleType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 角色表;角色
 * </p>
 *
 * @author chuang
 * @since 2021-05-07
 */
@Data
@Accessors(chain = true)
@ApiModel(value="RoleRO对象", description="角色表;角色")
public class RoleRO implements Serializable {

    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "角色标识")
    private String role;
    @ApiModelProperty(value = "角色类型")
    private RoleType roleType;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "简介")
    private String description;
    @ApiModelProperty(value = "是否启用")
    private Boolean enabled;
    @ApiModelProperty(value = "创建人")
    private String creator;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;
    @ApiModelProperty(value = "更新人")
    private String updater;
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;
}
