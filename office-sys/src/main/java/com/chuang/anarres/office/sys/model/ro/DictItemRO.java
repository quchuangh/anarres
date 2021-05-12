package com.chuang.anarres.office.sys.model.ro;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字典项;
 * </p>
 *
 * @author chuang
 * @since 2021-05-11
 */
@Data
@Accessors(chain = true)
@ApiModel(value="DictItemRO对象", description="字典项")
public class DictItemRO implements Serializable {

    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "名称")
    private String label;
    @ApiModelProperty(value = "值")
    private Byte val;
    @ApiModelProperty(value = "排序")
    private Integer sortRank;
    @ApiModelProperty(value = "类型编码")
    private String dictTypeCode;
    @ApiModelProperty(value = "父id")
    private Integer parentId;
    @ApiModelProperty(value = "路径")
    private String parents;
    @ApiModelProperty(value = "备注")
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
