package com.chuang.anarres.office.sys.model.uo;

import com.chuang.anarres.office.sys.enums.AbilityType;
import com.chuang.anarres.office.sys.model.ro.TreeRO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class AbilityUO extends TreeRO {

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("权限字符")
    private String ability;

    @ApiModelProperty("权限路径(系统的权限设计没有父子关系，这样做只是为了方便查看)")
    private String parents;

    @ApiModelProperty("权限类型")
    private AbilityType abilityType;

    @ApiModelProperty("排序")
    private Integer sortRank;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("是否启用")
    private Boolean enabled;

    @ApiModelProperty("创建人")
    private String creator;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新人")
    private String updater;

    @ApiModelProperty("更新时间")
    private LocalDateTime updatedTime;
}
