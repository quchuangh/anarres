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
 * 字典类型;
 * </p>
 *
 * @author chuang
 * @since 2021-05-11
 */
@Data
@Accessors(chain = true)
@ApiModel(value="DictTypeCO对象", description="字典类型;")
public class DictTypeCO implements Serializable {

    @ApiModelProperty(value = "编码")
    @NotBlank(message = "编码不能为空")
    private String code;

    @ApiModelProperty(value = "是否启用")
    @NotNull(message = "是否启用不能为空")
    private Boolean enabled;


    @ApiModelProperty(value = "昵称")
    @NotBlank(message = "昵称不能为空")
    private String name;

    @ApiModelProperty(value = "说明")
    @NotBlank(message = "说明不能为空")
    private String description;

}
