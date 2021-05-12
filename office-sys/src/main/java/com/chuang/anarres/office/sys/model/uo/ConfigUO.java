package com.chuang.anarres.office.sys.model.uo;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 系统配置表 
 * </p>
 *
 * @author chuang
 * @since 2021-05-11
 */
@Data
@Accessors(chain = true)
@ApiModel(value="ConfigUO对象", description="系统配置表 ")
public class ConfigUO implements Serializable {

    @ApiModelProperty(value = "编码")
    @NotBlank(message = "编码不能为空")
    private String code;

    @ApiModelProperty(value = "值")
    @NotBlank(message = "值不能为空")
    private String value;

    @ApiModelProperty(value = "值类型")
    @NotBlank(message = "值类型不能为空")
    private String valueType;

}
