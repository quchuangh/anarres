package com.chuang.anarres.rbac.model.co;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@ApiModel(value="DictItemCO对象", description="字典项;")
public class DictItemCO implements Serializable {

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

    @ApiModelProperty(value = "备注")
    @NotBlank(message = "备注不能为空")
    private String description;

    @ApiModelProperty(value = "是否启用")
    @NotNull(message = "是否启用不能为空")
    private Boolean enabled;

}
