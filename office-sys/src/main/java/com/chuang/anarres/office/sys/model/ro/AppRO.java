package com.chuang.anarres.office.sys.model.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "系统信息")
public class AppRO {

    @ApiModelProperty("App名称")
    private String name;
    @ApiModelProperty("App描述")
    private String description;
}
