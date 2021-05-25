package com.chuang.anarres.rbac.model.uo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
@ApiModel(value="DictItemUO对象", description="字典项")
public class DictItemUO implements Serializable {

    @ApiModelProperty(value = "ID")
    @NotNull(message = "ID不能为空")
    private Integer id;

    @ApiModelProperty(value = "名称")
    @NotBlank(message = "名称不能为空")
    private String label;

    @ApiModelProperty(value = "值")
    @NotNull(message = "值不能为空")
    private Byte val;

    @ApiModelProperty(value = "排序")
    @NotNull(message = "排序不能为空")
    private Integer sortRank;

    @ApiModelProperty(value = "类型编码")
    @NotNull(message = "类型编码不能为空")
    private String dictTypeCode;

    @ApiModelProperty(value = "父id")
    @NotNull(message = "父id不能为空")
    private Integer parentId;

    @ApiModelProperty(value = "路径")
    @NotBlank(message = "路径不能为空")
    private String parents;

    @ApiModelProperty(value = "备注")
    @NotBlank(message = "备注不能为空")
    private String description;

    @ApiModelProperty(value = "是否启用")
    @NotNull(message = "是否启用不能为空")
    private Boolean enabled;

}
