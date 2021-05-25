package com.chuang.anarres.office.sys.model.ro;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 职位表;
 * </p>
 *
 * @author chuang
 * @since 2021-05-24
 */
@Data
@Accessors(chain = true)
@ApiModel(value="PositionRO对象", description="职位表;")
public class PositionRO implements Serializable {

    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "职位编号")
    private String positionCode;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "简介")
    private String description;
    @ApiModelProperty(value = "组织编号")
    private String organizationCode;
    @ApiModelProperty(value = "是否启用")
    private Boolean enabled;
    @ApiModelProperty(value = "自带角色")
    private String roleCode;
    @ApiModelProperty(value = "创建人")
    private String creator;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;
    @ApiModelProperty(value = "更新人")
    private String updater;
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;
}
