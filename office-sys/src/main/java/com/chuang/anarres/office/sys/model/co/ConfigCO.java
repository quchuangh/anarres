package com.chuang.anarres.office.sys.model.co;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

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
@ApiModel(value="ConfigCO对象", description="系统配置表 ")
public class ConfigCO implements Serializable {

    @ApiModelProperty(value = "编码")
    @NotBlank(message = "编码不能为空")
    private String code;

    @ApiModelProperty(value = "值")
    @NotBlank(message = "值不能为空")
    private String value;

    @ApiModelProperty(value = "值校验")
    @NotBlank(message = "值校验不能为空")
    private String valueRegex;

}
