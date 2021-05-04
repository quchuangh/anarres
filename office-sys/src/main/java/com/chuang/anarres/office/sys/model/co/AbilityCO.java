package com.chuang.anarres.office.sys.model.co;

import com.chuang.anarres.office.sys.enums.AbilityType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AbilityCO {

    @ApiModelProperty("名称")
    @NotNull
    private String name;

    @ApiModelProperty("权限字符")
    @NotNull
    private String ability;

    @ApiModelProperty("权限类型")
    @NotNull
    private AbilityType abilityType;

    @ApiModelProperty("排序")
    @NotNull
    private Integer sortRank;

    @ApiModelProperty(value = "父菜单ID")
    @NotNull
    private Integer parentId;

    @ApiModelProperty("是否可向下授权")
    @NotNull
    private Boolean licensable;

    @ApiModelProperty("是否启用")
    @NotNull
    private Boolean enabled;

    @ApiModelProperty("描述")
    @NotNull
    private String description;

}
